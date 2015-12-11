package music.catalog;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DeleteCookiesServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            cookie.setMaxAge(0); 
            cookie.setPath("/"); 
            response.addCookie(cookie);
        }

        HttpSession session = request.getSession();
        session.invalidate();
        session = request.getSession(false);


        String url = "/validation/logout.jsp";
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
