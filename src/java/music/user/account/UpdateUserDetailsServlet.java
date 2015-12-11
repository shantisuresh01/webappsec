package music.user.account;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

import music.business.*;
import music.data.*;
import music.registration.ProcessUserServlet;
import music.util.CardValidationUtil;
import music.util.UserUtil;

public class UpdateUserDetailsServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        String update_message = "";
        String updateDetails_message = "";
        String url = "";

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String companyName = request.getParameter("companyName");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String country = request.getParameter("country");

        String creditCardType = request.getParameter("creditCardType");
        String creditCardExpMonth = request.getParameter("creditCardExpirationMonth");
        String creditCardExpYear = request.getParameter("creditCardExpirationYear");

        String creditCardNumber = request.getParameter("creditCardNumber");
        double creditCardNumberDouble = 0;
        if (CardValidationUtil.isNumber(CardValidationUtil.removeDashes(creditCardNumber))){
            creditCardNumberDouble = Double.valueOf(CardValidationUtil.removeDashes(creditCardNumber)).doubleValue();

        }

        int ccID = CardValidationUtil.getCardID(CardValidationUtil.removeDashes(creditCardNumber));
        String ccType = CardValidationUtil.getCardName(ccID);
        boolean ccValidNum = CardValidationUtil.validCCNumber(CardValidationUtil.removeDashes(creditCardNumber));
        boolean ccValid = false;

        try {
            ccValid = CardValidationUtil.validCC(CardValidationUtil.removeDashes(creditCardNumber));
        } catch (Exception ex) {
            Logger.getLogger(ProcessUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        User user = UserUtil.AuthUser(request);


        if (address1 == null || address1 == "") {
            updateDetails_message = "Please, provide address";
            url = "/user/account/displayAccountDetails";
        }
           else if (creditCardNumber == null || creditCardNumber == "") {
            updateDetails_message = "Enter valid credit card number";
            url = "/user/account/displayAccountDetails";
        }
        else if (!(ccValid || ccValidNum)){
            updateDetails_message = "Enter valid credit card number";
            url = "/user/account/displayAccountDetails";
        }
        else if (!creditCardType.equalsIgnoreCase(ccType)){
            updateDetails_message = "Enter valid credit card number";
            url = "/user/account/displayAccountDetails";
        }
        else {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setCompanyName(companyName);
            user.setAddress2(address2);
            user.setCity(city);
            user.setState(state);
            user.setZip(zip);
            user.setCountry(country);

            user.setCreditCardType(creditCardType);
            user.setCreditCardNumber(creditCardNumberDouble);
            user.setCreditCardExpirationDate(creditCardExpMonth + "/" + creditCardExpYear);


            if (UserDB.update(user) !=0 ) {
                update_message = "You account has been updated";
                url = "/user/account/update_user.jsp";
            } else {
                updateDetails_message = "You account was not updated";
                url = "/user/account/displayAccountDetails";
            }
        }

        session.setAttribute("user", user);
        session.setAttribute("update_message", update_message);
        session.setAttribute("updateDetails_message", updateDetails_message);

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
