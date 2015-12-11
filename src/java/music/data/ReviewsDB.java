package music.data;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import music.business.*;

public class ReviewsDB {

    public static int insert(Review review) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        //PreparedStatement ps = null;
        Statement st = null;

        String message = review.getMessage();
        String title = review.getTitle();
        int productID = review.getProductID();
        int userID = review.getUserID();
        User user = UserDB.selectUserbyID(userID);
        String emailAddress = user.getEmailAddress();
        if (user != null) {

            String query = "INSERT INTO v_Reviews (ProductID, UserID, ReviewDate, Title, Message)  "
                    + "VALUES ("
                    + productID + ",  "
                    + userID + ", SYSDATE(), '"
                    + title + "', '"
                    + message
                    + "' )";

            try {
                st = connection.createStatement();
                st.executeUpdate(query);

//                st.addBatch(query);
//
//                st.executeBatch();


//            ps = connection.prepareStatement(query);
//            ps.setInt(1,review.getProductID());
//            ps.setInt(2, review.getUserID());
//            ps.setString(3,review.getTitle());
                //ps.execute();
                return 1;

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("ReviewDB Error: "+e);
                return 0;
            } finally {
                DBUtil.closeStatement(st);
                pool.freeConnection(connection);
            }
        } else {
            return 0;
        }


//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String query = "INSERT INTO Reviews (Title, Message, ReviewDate, UserID, ProductID) "
//                + "VALUES (?, ?, NOW(), ?, ? )";
//
//
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, review.getTitle());
//            ps.setString(2, review.getMessage());
//            ps.setInt(3, review.getUserID());
//            ps.setInt(4, review.getProductID());
//            return ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return 0;
//        } finally {
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
//        }
    }

    public static ArrayList<Review> selectReviewsByProductID(int productID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * "
                + "FROM v_Reviews "
                + "WHERE v_Reviews.ProductID = ? "
                + "ORDER BY v_Reviews.ReviewDate";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            ArrayList<Review> reviews = new ArrayList<Review>();
            while (rs.next()) {

                //Create the Review object
                Review review = new Review();
                review.setTitle(rs.getString("Title"));
                review.setMessage(rs.getString("Message"));
                review.setReviewDate(rs.getDate("ReviewDate"));
                review.setProductID(rs.getInt("ProductID"));
                review.setUserID(rs.getInt("UserID"));
                review.setReviewNumber(rs.getInt("ReviewID"));

                reviews.add(review);
            }
            return reviews;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

        //Vulnerable Code:

//        Statement st = null;
//        String query = "SELECT * "
//                + "FROM music.Reviews "
//                + "WHERE Reviews.ProductID = '"
//                + productID +"'";
//        try {
//            st = connection.createStatement();
//            rs = st.executeQuery(query);
//            ArrayList<Review> reviews = new ArrayList<Review>();
//            ResultSet rs1 = rs;
//            while (rs.next()) {
//
//                //Create the Review object
//                Review review = new Review();
//                review.setTitle(rs.getString("Title"));
//                review.setMessage(rs.getString("Message"));
//                review.setReviewDate(rs.getDate("ReviewDate"));
//                review.setProductID(rs.getInt("ProductID"));
//                review.setUserID(rs.getInt("UserID"));
//                review.setReviewNumber(rs.getInt("ReviewID"));
//
//                reviews.add(review);
//            }
//            return reviews;
//        } catch (SQLException ex) {
//            Logger.getLogger(ReviewsDB.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        } finally {
//            DBUtil.closeResultSet(rs);
//            DBUtil.closeStatement(st);
//            pool.freeConnection(connection);
//        }

    }
}
