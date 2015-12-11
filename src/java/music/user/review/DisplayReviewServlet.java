package music.user.review;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DisplayReviewServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String reviewResult = "";
        
        session.setAttribute("reviewResult", reviewResult);
        String url = "/user/review/review_product.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }
}
