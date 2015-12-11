package music.registration;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

import music.business.*;
import music.data.UserDB;
import music.util.CardValidationUtil;


public class ProcessUserServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        String userRegistrationMessage = "";
        String userConfirmMessage = "";
        String url = "/registration/displayUserRegistration";

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String companyName = request.getParameter("companyName");
        String emailAddress = request.getParameter("emailAddress");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String country = request.getParameter("country");
        String password = request.getParameter("password");
        String answer = request.getParameter("answer");

        String creditCardType = request.getParameter("creditCardType");
        String creditCardExpMonth = request.getParameter("creditCardExpirationMonth");
        String creditCardExpYear = request.getParameter("creditCardExpirationYear");

        String creditCardNumber = request.getParameter("creditCardNumber");
        double creditCardNumberDouble = 0;
        if (CardValidationUtil.isNumber(CardValidationUtil.removeDashes(creditCardNumber))){
            creditCardNumberDouble = Double.valueOf(CardValidationUtil.removeDashes(creditCardNumber)).doubleValue();
            
        }

        User newUser = new User();

        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmailAddress(emailAddress);
        newUser.setCompanyName(companyName);
        newUser.setAddress1(address1);
        newUser.setAddress2(address2);
        newUser.setCity(city);
        newUser.setState(state);
        newUser.setZip(zip);
        newUser.setCountry(country);
        newUser.setPassword(password);
        newUser.setAnswer(answer);
        newUser.setCreditCardType(creditCardType);
        newUser.setCreditCardExpirationDate(creditCardExpMonth + "/" + creditCardExpYear);
        newUser.setCreditCardNumber(creditCardNumberDouble);
        int ccID = CardValidationUtil.getCardID(CardValidationUtil.removeDashes(creditCardNumber));
        String ccType = CardValidationUtil.getCardName(ccID);
        boolean ccValidNum = CardValidationUtil.validCCNumber(CardValidationUtil.removeDashes(creditCardNumber));
        boolean ccValid = false;

        try {
             ccValid = CardValidationUtil.validCC(CardValidationUtil.removeDashes(creditCardNumber));
        } catch (Exception ex) {
            Logger.getLogger(ProcessUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (address1 == null || address1 == "") {
            userRegistrationMessage = "Enter address";
//            url = "/registration/displayUserRegistration";
            url = "/registration/user_registration.jsp";
        } else if (emailAddress == null || emailAddress == "") {
            userRegistrationMessage = "Enter email address";
//            url = "/registration/displayUserRegistration";
            url = "/registration/user_registration.jsp";
        } else if (password == null || password == "") {
            userRegistrationMessage = "Enter password";
//            url = "/registration/displayUserRegistration";
            url = "/registration/user_registration.jsp";
        }
           else if (creditCardNumber == null || creditCardNumber == "") {
            userRegistrationMessage = "Enter valid credit card number";
//            url = "/registration/displayUserRegistration";
            url = "/registration/user_registration.jsp";
        }
        else if (!(ccValid || ccValidNum)){
            userRegistrationMessage = "Enter valid credit card number";
            url = "/registration/user_registration.jsp";
        }
        else if (!creditCardType.equalsIgnoreCase(ccType)){
            userRegistrationMessage = "Enter valid credit card number";
            url = "/registration/user_registration.jsp";
        }        
        else
        {
            if (UserDB.emailExists(newUser.getEmailAddress())) {
                userRegistrationMessage = "User with email address " + newUser.getEmailAddress() + " already exists";
                url = "/registration/user_registration.jsp";
            } else {
                if (UserDB.insert(newUser) == 1) {
                    url = "/registration/user_confirm.jsp";
                    userConfirmMessage = "Congratulations! You have been successfully registered";
                } else {
                    url = "/registration/user_registration.jsp";
                    userRegistrationMessage = "Enter valid data";
                }
            }
        }
        request.setAttribute("newUser", newUser);
        request.setAttribute("userRegistrationMessage", userRegistrationMessage);
        request.setAttribute("userConfirmMessage", userConfirmMessage);

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
