package music.email;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import music.business.*;
import music.data.*;

public class AddToEmailListServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String emailAddress = request.getParameter("emailAddress");

        PrintWriter writer = response.getWriter();
        String details = "";

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmailAddress(emailAddress);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);


        if (UserDB.emailExists(emailAddress)) {
            details = "<b>Details</b><br>"
                    + "This email address already exists <br><br>";
        } else {
            UserDB.insert(user);

            details = "<h1>Thanks for joining our email list</h1>"
                    + "<p>Here is the information that you entered</p>"
                    + "<b>Details</b><br>"
                    + "FirstName: " + user.getFirstName() + "<br>"
                    + "LastName: " + user.getLastName() + "<br>"
                    + "EmailAddress: " + user.getEmailAddress() + "<br><br>";
        }
        writer.println(details);
        writer.close();

//        RequestDispatcher dispatcher =
//            getServletContext().getRequestDispatcher(url);
//        dispatcher.forward(request, response);
    }
}
