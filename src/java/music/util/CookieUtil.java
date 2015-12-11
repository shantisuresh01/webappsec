package music.util;

import javax.servlet.http.*;
import music.business.User;
import music.data.UserDB;

public class CookieUtil {

    public static String getCookieValue(
            Cookie[] cookies, String cookieName) {
        String cookieValue = "";
        Cookie cookie;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (cookieName.equals(cookie.getName())) {
                    cookieValue = cookie.getValue();
                }
            }
        }
        return cookieValue;
    }

    public static Cookie getCookie(Cookie[] cookies, String cookieName) {
        Cookie myCookie = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(cookieName)) {
                    myCookie = cookies[i];

                }
            }
        }
        return myCookie;
    }

    public static boolean isAuthenticated(HttpServletRequest request) {
        boolean isAuth = false;
        if (request != null) {
            HttpSession session = request.getSession();
            String sessionAuth = (String) session.getAttribute("authenticated");
            if (sessionAuth != null) {
                if (sessionAuth.equals("authenticated")) {
                    isAuth = true;
                }
                else if (sessionAuth.equals("")) {
                String emailAddress = "";
                User user = (User) request.getAttribute("user");
                emailAddress = request.getUserPrincipal().getName();
                user = UserDB.selectUser(emailAddress);
                if (user != null) {
                    isAuth = true;
                    session.setAttribute("authenticated", "true");
                }
            }
            } 

        }

        return isAuth;
    }

    public static Cookie createCookie(String cookieName, String value) {
        Cookie myCookie = null;
        if (cookieName != null && value != null) {
            myCookie = new Cookie(cookieName, value);
            myCookie.setMaxAge(60 * 24 * 365 * 2 * 60);
            myCookie.setPath("/");
        }
        return myCookie;
    }
}
