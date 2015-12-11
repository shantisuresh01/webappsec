package music.util;
 
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
 
public class DB_Info {
    public static final String MYSQL_USERNAME = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
    public static final String MYSQL_PASSWORD = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
    public static final String MYSQL_DATABASE_HOST = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
    public static final String MYSQL_DATABASE_PORT = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
    public static final String MYSQL_DATABASE_NAME = "webappsec";
    //public static final String MYSQL_DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static Connection con;
    /**
     * returns connection to DB
     * @param con Connection
     */
    public static String initConnection(){
        String url = "";
        //if(con==null)con = ServletListener.getConnection();
        if(con==null){
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
                url = "jdbc:mysql://" + MYSQL_DATABASE_HOST + ":" + MYSQL_DATABASE_PORT + "/" + 
                          MYSQL_DATABASE_NAME + "?user=" + MYSQL_USERNAME + "&password=" + MYSQL_PASSWORD;
                con = DriverManager.getConnection(url);
                // con = DriverManager.getConnection(url, MYSQL_USERNAME, MYSQL_PASSWORD);
                // con = DriverManager.getConnection("jdbc:mysql://10.0.0.6/webappsec?" +
                //                   "user=security&password=Music4me");
            } catch (SQLException e) {
                e.printStackTrace();
                return e.getMessage() + url;
            }
        }
        return "Connection Initialized!";

        // return "Connection Initialized! " + MYSQL_DATABASE_HOST + ":" + MYSQL_DATABASE_PORT + "/" + MYSQL_DATABASE_NAME + " " + MYSQL_PASSWORD;
    }
 
    public static Connection getConnection() {
        if (con == null) {
            initConnection();
        }
        return con;
    }
}
