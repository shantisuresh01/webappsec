package music.user.order;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

import music.business.*;
import music.data.UserCartDB;
import music.util.UserUtil;

public class DisplayInvoiceServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();

        String url = "/user/order/invoice.jsp";
        String cartEmptyMessage = "";

        User user = UserUtil.AuthUser(request);
        UserCart userCart = null;
        if (user!=null){
        userCart = UserCartDB.selectUserCart(user);
//        Cart cart = (Cart) session.getAttribute("cart");
        }
        java.util.Date today = new java.util.Date();

        Invoice invoice = new Invoice();
        if (user != null && userCart != null) {
            if ((userCart.getUserCartNumber()!=0)){
            invoice.setUser(user);
            invoice.setInvoiceDate(today);
            invoice.setLineItems(userCart.getUserItems());
            UserCartDB.deleteUserCart(userCart);
            userCart.setUserItems(new ArrayList<LineItem>());
            }else{
                cartEmptyMessage = "Save your cart and try again";
                session.setAttribute ("cartEmptyMessage", cartEmptyMessage);
                url = "/user/order/incomplete.jsp";
            }
        }
        else{
            url = "/user/order/incomplete.jsp";
        }

        session.setAttribute("invoice", invoice);

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
