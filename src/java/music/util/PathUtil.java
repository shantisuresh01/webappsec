package music.util;

import javax.servlet.http.*;

public class PathUtil {

    public static String getToURL(HttpServletRequest request) {
        String toURL = "/cart/displayCart";
        HttpSession session = request.getSession();
        if (request.getParameter("to") != null) {
            if (!(request.getParameter("to") == "")) {
                toURL = request.getParameter("to");
                session.removeAttribute("to");
            }
        }
        if (session.getAttribute("to") != null) {
            if (!(session.getAttribute("to") == "")) {
                toURL = (String) session.getAttribute("to");
            }
        }
        return toURL;
    }
}
