package music.validation;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import music.data.*;

public class PasswordRecoveryServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String emailAddress = request.getParameter("emailAddress");
        String answer = request.getParameter("answer");

        HttpSession session = request.getSession();
        String password = "";
        String result = "";

        String url = "/validation/passwordRecovery.jsp";
//        String message = "";


        if (emailAddress==null|| answer==null||emailAddress.equals("") || answer.equals("")) {

            result = "<h1>Result </h1>"
                    + "Please, fill the required fields.";

        } else {
            password = UserDB.recoverPassword(emailAddress, answer);
            if (password!=null && !password.equalsIgnoreCase("")) {
                result = "<h1>Result </h1> "
                        + "Email: " + emailAddress + "<br>"
                        + "Color: " + answer + "<br>"
                        + "Password: " + password + "<br>";
            } else {

                result = "<h1>Result </h1> "
                        + "The email or answer is incorrect.";
            }
        }
//        session.setAttribute("message", message);
        session.setAttribute("result", result);


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
