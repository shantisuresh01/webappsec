package music.user.order;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;
import javax.mail.*;


import music.business.*;
import music.data.*;
import music.util.*;

public class CompleteOrderServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String url = "/user/order/complete.jsp";

        HttpSession session = request.getSession();
        User user = UserUtil.AuthUser(request);
        Invoice invoice = (Invoice) session.getAttribute("invoice");
        if (invoice != null) {

            InvoiceDB.insert(invoice);

            Cart cart = (Cart) session.getAttribute("cart");
            cart.setItems(new ArrayList<LineItem>());

            String emailTo = user.getEmailAddress();
            String emailFrom = "m_julia1@yahoo.com";
            String emailSubject = "Order Confirmation";
            String emailBody = "Dear " + user.getFirstName() + ",\n\n" + "Thanks for ordering from us. " + "You should receive your order in 3-5 business days. " + "Please contact us if you have any questions.\n" + "Have a great day and thanks again!\n\n" + "Yuliana M.";
            boolean isBodyHTML = false;
            try {
                MailUtilYahoo.sendMail(emailTo, emailFrom, emailSubject, emailBody, isBodyHTML);
            } catch (MessagingException e) {
                this.log("Unable to send email. \n"
                        + "You may need to configure your system as "
                        + "described in chapter 15. \n"
                        + "Here is the email you tried to send: \n"
                        + "=====================================\n"
                        + "TO: " + emailTo + "\n"
                        + "FROM: " + emailFrom + "\n"
                        + "SUBJECT: " + emailSubject + "\n" + "\n"
                        + emailBody + "\n\n");
            }
        }
        else {
             url = "/user/order/incomplete.jsp";
        }


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
