package music.user.validation;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ValidateUserServlet extends HttpServlet {
    public static final String MYSQL_DATABASE_HOST = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String referer = request.getHeader("Referer");

        URL url_ref = null;
        URI uri_ref = null;

        String url = "/";

        // Create a URL object
        if (referer != null) {
            url = referer;
            try {

                url_ref = new URL(referer);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            try {
                System.out.println("\nIn User Validation\n");
                uri_ref = new URI(url_ref.toString());
                URI host = new URI("http://" + MYSQL_DATABASE_HOST + ":8080/yuliana");
                // URI host = new URI("http://localhost:8080/yuliana");

                url = "/" + host.relativize(uri_ref).toString();
                System.out.println("\n Referer = " + referer + "URI_REF: " + uri_ref.toString() + "; Host = " + host.toString() + " relativized URL = " + url);

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

        }
        System.out.println("\n Referer = " + referer + "URI_REF: " + uri_ref.toString() + " relativized URL = " + url);

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
