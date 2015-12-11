package partners;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DisplayPartnersServlet extends HttpServlet {    // declare an instance variable for the page


    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        ServletContext sc = this.getServletContext();
        String path = sc.getRealPath("/letters");
        String url = "/partners/partners.jsp";
        HttpSession session = request.getSession();

        File d = new File(path);
        String[] list = d.list();
        session.setAttribute("list", list);
        session.setAttribute("path", path);

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
