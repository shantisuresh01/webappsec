package music.data;

import java.sql.*;
import java.util.*;

import music.business.*;

public class UserCartDB {

    public static int insert(UserCart userCart) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        int userID = UserDB.selectUserID(userCart.getUser());
        String query = "INSERT INTO v_UserCart (UserID) "
                + "VALUES (?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            ps.executeUpdate();

            //Get the InvoiceID from the last INSERT statement.
            String identityQuery = "SELECT @@IDENTITY AS IDENTITY";
            Statement identityStatement = connection.createStatement();
            ResultSet identityResultSet = identityStatement.executeQuery(identityQuery);
            identityResultSet.next();
            int userCartID = identityResultSet.getInt("IDENTITY");
            identityResultSet.close();
            identityStatement.close();

            //Write line items to the LineItem table.
            ArrayList<LineItem> lineItems = userCart.getUserItems();
            for (int i = 0; i < lineItems.size(); i++) {
                LineItem item = lineItems.get(i);
                CartLineItemDB.insert(userCartID, item);
            }

            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static UserCart selectUserCart(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        int userID = UserDB.selectUserID(user);

        String query = "SELECT * "
                + "FROM v_UserCart "
                + "WHERE v_UserCart.UserID = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            UserCart userCart = new UserCart();
            while (rs.next()) {
                int userCartID = rs.getInt("UserCartID");
                ArrayList<LineItem> cartlineItems = CartLineItemDB.selectCartLineItems(userCartID);

                userCart.setUser(user);
                userCart.setUserCartNumber(userCartID);
                userCart.setUserItems(cartlineItems);
            }
            return userCart;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

    public static int updateUserCart(UserCart userCart) {

        int userCartID = userCart.getUserCartNumber();
        try {
            CartLineItemDB.deleteCartLineItems(userCartID);

            //Write line items to the LineItem table.
            ArrayList<LineItem> lineItems = userCart.getUserItems();
            for (int i = 0; i < lineItems.size(); i++) {
                LineItem item = lineItems.get(i);
                CartLineItemDB.insert(userCartID, item);
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;

        }
    }

    public static int deleteUserCart(UserCart userCart) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        int userCartID = userCart.getUserCartNumber();
        String query = "DELETE FROM v_UserCart "
                + "WHERE UserCartID = ?";
        try {

            CartLineItemDB.deleteCartLineItems(userCartID);

            ps = connection.prepareStatement(query);
            ps.setInt(1, userCartID);
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
}
