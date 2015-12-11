/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package music.business;
import java.util.*;
import java.text.*;
import java.io.Serializable;
/**
 *
 * @author yulia
 */
public class Review {

    private int reviewNumber;
    private String title;
    private String message;
    private Date reviewDate;
    private int userID;
    private int productID;

//    public Review(String title, String message, Date reviewDate, int userID, int productID, int reviewNumber) {
//        this.title = title;
//        this.message = message;
//        this.reviewDate = reviewDate;
//        this.userID = userID;
//        this.productID = productID;
//        this.reviewNumber = reviewNumber;
//    }

    public Review() {
        this.title = "";
        this.message = "";
        this.userID = -1;
        this.productID = -1;
        this.reviewNumber = -1;
    }

    public int getReviewNumber() {
        return reviewNumber;
    }

    public void setReviewNumber(int reviewNumber) {
        this.reviewNumber = reviewNumber;
    }
    

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
