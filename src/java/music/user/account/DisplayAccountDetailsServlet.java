package music.user.account;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DisplayAccountDetailsServlet extends HttpServlet {    // declare an instance variable for the page

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String url = "/user/account/update_userDetails.jsp";
        String update_message = "";

        HttpSession session = request.getSession();
        update_message = (String) session.getAttribute("update_message");

        request.setAttribute("update_message", update_message);

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);

        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        doPost(request, response);
    }
}
