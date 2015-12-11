package music.validation;

import music.registration.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DisplayPasswordRecoveryServlet extends HttpServlet { 

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();

        String url = "/validation/passwordRecovery.jsp";
        //String message = "";
        String result = "";

        //session.setAttribute("message", message);
        session.setAttribute("result", result);

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
