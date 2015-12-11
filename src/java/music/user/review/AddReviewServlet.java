package music.user.review;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import music.business.*;
import music.data.*;
import music.util.UserUtil;

public class AddReviewServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String review_message = request.getParameter("message");
        String reviewResult = "";
        String url = "/user/review/review_product.jsp";

        HttpSession session = request.getSession();
        User user = UserUtil.AuthUser(request);
        Product product = (Product) session.getAttribute("product");

        Review newreview = new Review();
        Review review = new Review();
        java.util.Date today = new java.util.Date();
        if (user != null && user.getEmailAddress() != ""
                && user.getEmailAddress() != null && product != null) {
            if (UserDB.selectUserID(user) != -1 && ProductDB.selectProductID(product) != 0) {
                int userID = UserDB.selectUserID(user);
                int productID = ProductDB.selectProductID(product);
                newreview.setTitle(title);
                newreview.setMessage(review_message);
                newreview.setReviewDate(today);
                newreview.setUserID(userID);
                newreview.setProductID(productID);

                if (title != null && title != "" && ReviewsDB.insert(newreview) != 0) {
                    review = newreview;
                    reviewResult = "<h1>Result </h1>"
                            + "Your review was added";
                } else {
                    reviewResult = "<h1>Result </h1>"
                            + "Review was not added";
                }
                session.setAttribute("reviewResult", reviewResult);

                url = "/user/review/review_product.jsp";
            }
        } else {
            url = "/error/error_404.jsp";
        }


        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
