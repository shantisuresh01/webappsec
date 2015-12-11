package music.user.account;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import music.business.User;
import music.util.UserUtil;

public class DisplayAccountServlet extends HttpServlet {    // declare an instance variable for the page

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String url = "/user/account/update_user.jsp";
        String update_message = "";

        HttpSession session = request.getSession();

        
        User user = UserUtil.AuthUser(request);

        request.setAttribute("update_message", update_message);
        session.setAttribute("user", user);

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
