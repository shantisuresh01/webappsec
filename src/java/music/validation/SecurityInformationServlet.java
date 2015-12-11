package music.validation;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SecurityInformationServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        PrintWriter writer = response.getWriter();
        String adminMenue = "";
        if (request.isUserInRole("admin")) {
            adminMenue =
                    "<div id=\"menuPartners\"class=\"explorer\""
                    + "onmouseover=\"this. className='explorer_over';return true\""
                    + "onmouseout=\"this.className= 'explorer';return true\""
                    + "onmousedown=\"this.className= 'explorer_down';return true\">"
                    + "<a href= \"https://134.154.14.153:8000/yuliana/admin\" class=\"menu\">"
                    + "Product Maintenance"
                    + "</div>";

            writer.println(adminMenue);
            writer.close();
        }
    }

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }
}
