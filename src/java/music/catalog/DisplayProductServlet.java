package music.catalog;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

import music.business.*;
import music.data.*;

public class DisplayProductServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get request parameters
        String productCode = request.getParameter("productCode");
        String reviewNumberString = request.getParameter("reviewNumber");
        Review reviewByNumber = null;

        String url = "/cart";

        HttpSession session = request.getSession();
        // update the Model
        if (productCode != null) {
            Product product = null;
            if (ProductDB.selectProduct(productCode) != null) {
                product = ProductDB.selectProduct(productCode);
                session.setAttribute("product", product);

                int productID = ProductDB.selectProductID(product);
                session.setAttribute("productID", productID);
                ArrayList<Review> reviews = ReviewsDB.selectReviewsByProductID(productID);

                session.setAttribute("reviews", reviews);

                if (reviewNumberString != null) {
                    if (reviews != null) {
                        int reviewNumber = -1;
                        try {
                            reviewNumber = Integer.parseInt(reviewNumberString);
                        } catch (NumberFormatException e) {
                        }
                        for (int i = 0; i < reviews.size(); i++) {
                            reviewByNumber = reviews.get(i);
                            if (reviewByNumber.getReviewNumber() == reviewNumber) {
                                session.setAttribute("reviewByNumber", reviewByNumber);
                                break;
                            } else {
                                reviewByNumber = null;
                            }
                        }
                        url = "/catalog/displayDetails.jsp";
                    }
                }
                session.setAttribute("reviewByNumber", reviewByNumber);
                url = "/catalog/displayDetails.jsp";
            }
        }

        if (reviewNumberString != null && productCode == null) {
            ArrayList<Review> reviews =
                    (ArrayList<Review>) session.getAttribute("reviews");
            if (reviews != null) {
                int reviewNumber = Integer.parseInt(reviewNumberString);
                for (int i = 0; i < reviews.size(); i++) {
                    reviewByNumber = reviews.get(i);
                    if (reviewByNumber.getReviewNumber() == reviewNumber) {
                        session.setAttribute("reviewByNumber", reviewByNumber);
                        break;
                    } else {
                        reviewByNumber = null;
                    }
                }
                url = "/catalog/displayDetails.jsp";
            }
        }


        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
