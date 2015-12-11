package music.cart;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import music.business.*;
import music.data.*;

public class SetQuantityServlet extends HttpServlet {    // declare an instance variable for the page


    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String quantityString = request.getParameter("quantity");
        String productCode = request.getParameter("productCode");
        HttpSession session = request.getSession();

        PrintWriter out = response.getWriter();
        int quantity = 0;
        
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        try {
            quantity = Integer.parseInt(quantityString);
         
        if (quantity > 0) {
            updateCartItem(cart, quantity, productCode);
            Product product = ProductDB.selectProduct(productCode);
            session.setAttribute("product", product);
        } else if (quantity == 0) {
            removeCartItem(cart, productCode);
        }
        session.setAttribute("cart", cart);

        
        }
        catch (Exception e) {
        }
        out.println(quantity);


    }

    void updateCartItem(Cart cart, int quantity, String productCode) {
        ArrayList<LineItem> items = cart.getItems();

        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getProduct().getCode().equals(productCode)) {
                lineItem.setQuantity(quantity);
                cart.updateItem(lineItem);
            }
        }
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
