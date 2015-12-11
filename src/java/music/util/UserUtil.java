/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package music.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import music.business.User;
import music.data.UserDB;

/**
 *
 * @author yulia
 */
public class UserUtil {

    public static User AuthUser(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        if (request != null) {
            HttpSession session = request.getSession();
            String emailAddress = "";
            if (request.getUserPrincipal() != null) {
                emailAddress = request.getUserPrincipal().getName();
                user = UserDB.selectUser(emailAddress);
                if (user != null) {
                    session.setAttribute("user", user);
                }
            }
        }
        return user;
    }

    public static boolean isAuth(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        boolean isAuth = false;
        if (request.getUserPrincipal() != null) {
            String emailAddress = request.getUserPrincipal().getName();
            if (emailAddress != null) {
                isAuth = true;
                user = AuthUser(request);
            }
        }
        return isAuth;
    }
}
