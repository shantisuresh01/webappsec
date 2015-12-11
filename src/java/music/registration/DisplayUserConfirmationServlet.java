package music.registration;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import music.business.User;
import music.util.PathUtil;



public class DisplayUserConfirmationServlet extends HttpServlet {    // declare an instance variable for the page

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String url = "/registration/user_confirm.jsp";
        String userConfirmMessage = "";

        userConfirmMessage = (String)request.getAttribute("update_message");
        
        request.setAttribute("userConfirmMessage", userConfirmMessage);

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
