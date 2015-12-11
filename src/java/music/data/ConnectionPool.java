package music.data;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.System;



public class ConnectionPool {

    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;

    
    public static final String MYSQL_USERNAME = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
    public static final String MYSQL_PASSWORD = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
    public static final String MYSQL_DATABASE_HOST = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
    public static final String MYSQL_DATABASE_PORT = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
    public static final String MYSQL_DATABASE_NAME = "webappsec";
    public static final String MYSQL_DATABASE_DRIVER = "com.mysql.jdbc.Driver";

    public synchronized static ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/musicDB");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        String url = "";
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
                url = "jdbc:mysql://" + MYSQL_DATABASE_HOST + ":" + MYSQL_DATABASE_PORT + "/" + 
                          MYSQL_DATABASE_NAME + "?user=" + MYSQL_USERNAME + "&password=" + MYSQL_PASSWORD;
                return(DriverManager.getConnection(url));
            //return (DriverManager.getConnection("jdbc:mysql://10.0.0.6/webappsec?" +
            //                       "user=adminE8P5YDz&password=pd-4H__EyW2v"));
            // return (DriverManager.getConnection("jdbc:mysql://10.0.0.6/webappsec?" +
            //                       "user=security&password=Music4me"));
            // url = "jdbc:mysql://" + MYSQL_DATABASE_HOST + ":" + MYSQL_DATABASE_PORT + "/" + MYSQL_DATABASE_NAME;
            // return (DriverManager.getConnection(url, MYSQL_USERNAME, MYSQL_PASSWORD));      
            // return (DriverManager.getConnection("jdbc:mysql:///webappsec?" +
            //                       "user=adminE8P5YDz&password=pd-4H__EyW2v"));
            // return dataSource.getConnection();
        } catch (Exception sqle) {
            sqle.printStackTrace();
            return null;
        }
    }

    public void freeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
