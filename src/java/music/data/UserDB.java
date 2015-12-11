package music.data;

import java.sql.*;

import music.business.*;

public class UserDB {

    public static int insert(User user) {
        try {
            int insertUser = insertUser(user);
            if (insertUser == 1) {
                int insertPassword = insertPassword(user);
                if (insertPassword == 1) {
                    int insertRole = insertRole(user);
                    if (insertRole == 1) {
                        return 1;
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }

            } else {
                return 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int insertUser(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps1 = null;

        ResultSet rs = null;

        //This method adds a new record to the Users table in the database
        String query1 =
                "INSERT INTO v_User (FirstName, LastName, EmailAddress, CompanyName, "
                + "Address1, Address2, City, State, Zip, Country, "
                + "CreditCardType, CreditCardNumber, CreditCardExpirationDate) "
                + "VALUES (?, ?, ?, ?, "
                + "?, ?, ?, ?, ?, ?, "
                + "?, ?, ?)";

        try {
            ps1 = connection.prepareStatement(query1);
            ps1.setString(1, user.getFirstName());
            ps1.setString(2, user.getLastName());
            ps1.setString(3, user.getEmailAddress());
            ps1.setString(4, user.getCompanyName());
            ps1.setString(5, user.getAddress1());
            ps1.setString(6, user.getAddress2());
            ps1.setString(7, user.getCity());
            ps1.setString(8, user.getState());
            ps1.setString(9, user.getZip());
            ps1.setString(10, user.getCountry());
            ps1.setString(11, user.getCreditCardType());
            ps1.setDouble(12, user.getCreditCardNumber());
            ps1.setString(13, user.getCreditCardExpirationDate());
            return ps1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps1);
            pool.freeConnection(connection);
        }
    }

    public static int insertPassword(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps2 = null;

        ResultSet rs = null;

        //This method adds a new record to the Users table in the database

        String query2 =
                "INSERT INTO v_UserPass (EmailAddress, Password, Answer) "
                + "VALUES (?, ?, ?)";

        try {
            ps2 = connection.prepareStatement(query2);
            ps2.setString(1, user.getEmailAddress());
            ps2.setString(2, user.getPassword());
            ps2.setString(3, user.getAnswer());
            return ps2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps2);
            pool.freeConnection(connection);
        }
    }

    public static int insertRole(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps3 = null;
        ResultSet rs = null;

        //This method adds a new record to the Users table in the database
        String query3 =
                "INSERT INTO v_UserRole (EmailAddress, Rolename) "
                + "VALUES (?, 'user')";
        try {

            ps3 = connection.prepareStatement(query3);
            ps3.setString(1, user.getEmailAddress());
            return ps3.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps3);
            pool.freeConnection(connection);
        }
    }

    public static int update(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        double creditCardNumber = user.getCreditCardNumber();
        String emailAddress = user.getEmailAddress();
        String country = user.getCountry();
        //This method updates the record with a matching email address.
        //It returns a value of 0 if the email address can't be found.
        String query = "UPDATE v_User SET "
                + "FirstName = ?, "
                + "LastName = ?, "
                + "CompanyName = ?, "
                + "Address1 = ?, "
                + "Address2 = ?, "
                + "City = ?, "
                + "State = ?, "
                + "Zip = ?, "
                + "CreditCardType = ?, "
                + "CreditCardExpirationDate = ?, "
                + "CreditCardNumber = "
                + "'"
                + creditCardNumber
                + "', "
                + "Country = "
                + "'"
                + country
                + "' "
                + "WHERE EmailAddress = '"
                + emailAddress
                + "'";


        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getCompanyName());
            ps.setString(4, user.getAddress1());
            ps.setString(5, user.getAddress2());
            ps.setString(6, user.getCity());
            ps.setString(7, user.getState());
            ps.setString(8, user.getZip());
            ps.setString(9, user.getCreditCardType());
            ps.setString(10, user.getCreditCardExpirationDate());

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    //Stored SQL Injection Vulnerability: UPDATE statement
    //Query string attribute ‘answer’ generated by the form submit action is vulnerable.
    //Payload: red' WHERE EmailAddress = 'r2' #

    public static int updatePassword(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;


        String answer = user.getAnswer();
        String emailAddress = user.getEmailAddress();
        String query = "UPDATE v_UserPass SET "
                + "Password = ?"
                + ", Answer = "
                + "'"
                + answer
                + "' "
                + "WHERE EmailAddress = '"
                + emailAddress
                + "'";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getPassword());

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int updateByID(User user, int userID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;


        String query = "UPDATE v_User SET "
                + "FirstName = ?, "
                + "LastName = ?, "
                + "CompanyName = ?, "
                + "Address1 = ?, "
                + "Address2 = ?, "
                + "City = ?, "
                + "State = ?, "
                + "Zip = ?, "
                + "Country = ?, "
                + "CreditCardType = ?, "
                + "CreditCardNumber = ?, "
                + "CreditCardExpirationDate = ?, "
                + "EmailAddress = ? "
                + "WHERE UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getCompanyName());
            ps.setString(4, user.getAddress1());
            ps.setString(5, user.getAddress2());
            ps.setString(6, user.getCity());
            ps.setString(7, user.getState());
            ps.setString(8, user.getZip());
            ps.setString(9, user.getCountry());
            ps.setString(10, user.getCreditCardType());
            ps.setDouble(11, user.getCreditCardNumber());
            ps.setString(12, user.getCreditCardExpirationDate());
            ps.setString(13, user.getEmailAddress());
            ps.setInt(14, userID);

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static boolean emailExists(String emailAddress) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT EmailAddress FROM v_User "
                + "WHERE EmailAddress = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, emailAddress);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int selectUserID(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        //This method returns 0 if invoiceID isn't found.
        String query = "SELECT UserID FROM v_User "
                + "WHERE EmailAddress = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmailAddress());
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("UserID");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    //This method returns null if a record isn't found.
    public static User selectUser(String emailAddress) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM v_User "
                + "WHERE EmailAddress = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, emailAddress);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmailAddress(rs.getString("EmailAddress"));
                user.setCompanyName(rs.getString("CompanyName"));
                user.setAddress1(rs.getString("Address1"));
                user.setAddress2(rs.getString("Address2"));
                user.setCity(rs.getString("City"));
                user.setState(rs.getString("State"));
                user.setZip(rs.getString("Zip"));
                user.setCountry(rs.getString("Country"));
                user.setCreditCardType(rs.getString("CreditCardType"));
                user.setCreditCardExpirationDate(rs.getString("CreditCardExpirationDate"));
                user.setCreditCardNumber(rs.getDouble("CreditCardNumber"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static String recoverPassword(String emailAddress, String answer) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        //Vulnerable Code:
        String query = "SELECT Password FROM v_UserPass "
                + "WHERE (v_UserPass.EmailAddress = '" + emailAddress + "' "
                + "AND v_UserPass.Answer = '" + answer + "') ";
        try {
            System.out.println("recoverPassword: query: " + query);
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery(query);
            if (rs.next()) {
                System.out.println("\nResult = " + rs.getString("Password"));
                return rs.getString("Password");
            } else {
                return "";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static User selectUserbyID(int userID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM v_User "
                + "WHERE UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmailAddress(rs.getString("EmailAddress"));
                user.setCompanyName(rs.getString("CompanyName"));
                user.setAddress1(rs.getString("Address1"));
                user.setAddress2(rs.getString("Address2"));
                user.setCity(rs.getString("City"));
                user.setState(rs.getString("State"));
                user.setZip(rs.getString("Zip"));
                user.setCountry(rs.getString("Country"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
