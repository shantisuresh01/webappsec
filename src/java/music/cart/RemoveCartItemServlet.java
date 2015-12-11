package music.cart;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import music.business.*;


public class RemoveCartItemServlet extends HttpServlet {    // declare an instance variable for the page

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        
        String productCode = request.getParameter("productCode");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        int quantity = 0;
        removeCartItem(cart, productCode);
        session.setAttribute("cart", cart);
        out.println(quantity);
    }

    public void removeCartItem(Cart cart, String productCode) {
        ArrayList<LineItem> items = cart.getItems();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getProduct().getCode().equals(productCode)) {
                cart.removeItem(lineItem);
            }
        }
    }

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }
}
