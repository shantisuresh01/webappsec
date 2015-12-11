package music.registration;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DisplayUserRegistrationServlet extends HttpServlet { 

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String url = "/registration/user_registration.jsp";
        String userRegistrationMessage = "";

        HttpSession session = request.getSession();
        
//        userRegistrationMessage = (String) session.getAttribute("userRegistrationMessage");

        request.setAttribute("userRegistrationMessage", userRegistrationMessage);

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
