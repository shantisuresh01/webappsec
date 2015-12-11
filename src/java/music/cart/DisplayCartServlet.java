package music.cart;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import music.business.*;
import music.data.*;
import music.util.UserUtil;

public class DisplayCartServlet extends HttpServlet {    // declare an instance variable for the page

    int quantity; // instance variables are not thread-safe

    public void init() throws ServletException {
        quantity = 0; // initialize the instance variable
    }

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String quantityString = request.getParameter("quantity");
        String productCode = request.getParameter("productCode");
        String removeButtonValue = request.getParameter("removeButton");
        String updateButtonValue = request.getParameter("updateButton");
        String userRemoveButtonValue = request.getParameter("userRemoveButton");
        HttpSession session = request.getSession();

        //If Cart is empty create an empty cart object
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

//        if (removeButtonValue != null) {
//            quantity = 0;
//            removeCartItem(cart, productCode);
//            session.setAttribute("cart", cart);
//        }

//        //If user updates the quantity of the item by clicking update button
//        if (updateButtonValue != null) {
//            try {
//                quantity = Integer.parseInt(quantityString);
//            } catch (Exception e) {
//                quantity = getCartItemQuantity(cart, productCode);
//
//            }
//            if (quantity > 0) {
//                updateCartItem(cart, quantity, productCode);
//            } else if (quantity == 0) {
//                removeCartItem(cart, productCode);
//            }
//            Product product = ProductDB.selectProduct(productCode);
//            session.setAttribute("product", product);
//
//        }
        
        //Check if the product is already in cart, then incrementing quantity
        if (updateButtonValue == null&&removeButtonValue==null&&userRemoveButtonValue == null) {
            ArrayList<LineItem> items = cart.getItems();
            if (cart.getCount() != 0) {
                for (int i = 0; i < items.size(); i++) {
                    LineItem lineItem = items.get(i);
                    if (lineItem.getProduct().getCode().equals(productCode)) {
                        quantity = lineItem.getQuantity() + 1;
                        break;
                    } else {
                        quantity = 1;
                    }
                }
            } else {
                // The cart is empty and Remove button in Saved Cart is not pressed
                if (userRemoveButtonValue == null) {
                    quantity = 1;
                }
            }
            // Get product from product code
            Product product = ProductDB.selectProduct(productCode);

            session.setAttribute("product", product);

            // If product exists, add or remove from cart
            if (product != null) {
                LineItem lineItem = new LineItem();
                lineItem.setProduct(product);
                lineItem.setQuantity(quantity);
                cart.addItem(lineItem);
            }
            
        }

        session.setAttribute("cart", cart);

        // Saved Cart section
        // If user is logged in, show the user cart

        User authUser = UserUtil.AuthUser(request);
        UserCart userCart = new UserCart();

        if (authUser != null) {
            userCart = UserCartDB.selectUserCart(authUser);
            //Remove button in Saved Cart is pressed
            if (userRemoveButtonValue != null) {
                ArrayList<LineItem> userItems = userCart.getUserItems();
                for (int i = 0; i < userItems.size(); i++) {
                    LineItem userlineItem = userItems.get(i);
                    if (userlineItem.getProduct().getCode().equals(productCode)) {
                        int userCartID = userCart.getUserCartNumber();
                        CartLineItemDB.deleteCartLineItem(userCartID, userlineItem);
                        userCart = UserCartDB.selectUserCart(authUser);
                    }
                }

            }
            session.setAttribute("userCart", userCart);

        }

        // If no items exist in cart, forward to the Empty cart page.
        // Otherwise, forward to the Cart page.
        String url = "";
        if (cart.getItems().size() <= 0 && userCart.getUserItems().size() <= 0) {
            url = "/cart/cart_empty.jsp";
        } else {
            url = "/cart/cart.jsp";
        }




        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);

        dispatcher.forward(request, response);
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
        public int getCartItemQuantity(Cart cart, String productCode) {
        ArrayList<LineItem> items = cart.getItems();
        int q = 1;
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getProduct().getCode().equals(productCode)) {
                q = lineItem.getQuantity();
                
            }
        }
        return q;

    }

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        doPost(request, response);
    }
}
