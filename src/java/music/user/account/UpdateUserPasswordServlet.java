package music.user.account;

import music.catalog.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import music.business.*;
import music.data.*;
import music.util.UserUtil;

public class UpdateUserPasswordServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();

        String update_message = "";
        String updatePassword_message = "";
        String url = "";

        String password = request.getParameter("password");
        String answer = request.getParameter("answer");

        User user = UserUtil.AuthUser(request);

        if (password != null && answer != null) {
            if (password != "" && answer != "") {
                user.setPassword(password);
                user.setAnswer(answer);
                if (UserDB.updatePassword(user) == 1) {
                    update_message = "You password has been updated";
                    url = "/user/account/update_user.jsp";
                } else {
                    updatePassword_message = "You password was not updated";
                    url = "/user/account/displayAccountPassword";
                }

            } else {
                updatePassword_message = "You password was not updated";
                url = "/user/account/displayAccountPassword";
            }

        } else {
            updatePassword_message = "You password was not updated";
            url = "/user/account/displayAccountPassword";
        }

        session.setAttribute("user", user);
        session.setAttribute("update_message", update_message);
        session.setAttribute("updatePassword_message", updatePassword_message);

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
