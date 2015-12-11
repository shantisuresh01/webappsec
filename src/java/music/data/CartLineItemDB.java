package music.data;

import java.sql.*;
import java.util.ArrayList;

import music.business.*;

public class CartLineItemDB {
    //This method adds one lineItem to the LineItems table.

    public static int insert(int userCartID, LineItem lineItem) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        int productID = ProductDB.selectProductID(lineItem.getProduct());
        String query = "INSERT INTO v_CartLineItem(UserCartID, ProductID, Quantity) "
                + "VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userCartID);
            ps.setInt(2, productID);
            ps.setInt(3, lineItem.getQuantity());
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

    //This method returns null if a record isn't found.
    public static ArrayList<LineItem> selectCartLineItems(int userCartID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM v_CartLineItem "
                + "WHERE UserCartID = ?"
                + " ORDER BY ProductID";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userCartID);
            rs = ps.executeQuery();
            ArrayList<LineItem> lineItems = new ArrayList<LineItem>();
            while (rs.next()) {
                LineItem lineItem = new LineItem();
                int productID = rs.getInt("ProductID");
                Product product = ProductDB.selectProduct(productID);
                lineItem.setProduct(product);
                lineItem.setQuantity(rs.getInt("Quantity"));
                lineItems.add(lineItem);
            }
            return lineItems;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int deleteCartLineItems(int userCartID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "DELETE FROM v_CartLineItem "
                + "WHERE UserCartID = ?";
        try {
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
        public static int deleteCartLineItem(int userCartID, LineItem cartlineItem) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        int productID = ProductDB.selectProductID(cartlineItem.getProduct());
        String query = "DELETE FROM v_CartLineItem "
                + "WHERE UserCartID = ? AND ProductID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userCartID);
            ps.setInt(2, productID);
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
