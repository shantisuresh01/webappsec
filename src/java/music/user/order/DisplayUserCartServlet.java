package music.user.order;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

import music.business.*;
import music.data.CartLineItemDB;
import music.data.UserCartDB;
import music.util.UserUtil;

public class DisplayUserCartServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();

        String url = "/user/order/user_cart.jsp";

        String userQuantityString = request.getParameter("userQuantity");
        String userProductCode = request.getParameter("userProductCode");
        String userRemoveButtonValue = request.getParameter("userRemoveButton");
        String userUpdateButtonValue = request.getParameter("userUpdateButton");

        User authUser = UserUtil.AuthUser(request);
        System.out.println("Email: " + authUser.getEmailAddress());
        UserCart userCart = UserCartDB.selectUserCart(authUser);
        System.out.println("UserCart: " + userCart.toString());


        Cart cart = (Cart) session.getAttribute("cart");
        System.out.println("Cart: " + cart.getCount());

        if (cart != null) {
            ArrayList<LineItem> items = cart.getItems();

            if (cart.getCount() != 0) {
                for (int i = 0; i < items.size(); i++) {
                    LineItem lineItem = items.get(i);
                    System.out.println("lineItem.getProduct().getCode(): " + lineItem.getProduct().getCode());
                    userCart.addItem(lineItem);
                    System.out.println("userCart.getCount(): " + userCart.getCount());
                    if (userCart.getUserCartNumber() != 0) {
                        int updated = UserCartDB.updateUserCart(userCart);
                        if (updated == 1) {
                            System.out.println("UserCartDB updated");
                        }
                    } else {
                        userCart.setUser(authUser);
                        int inserted = UserCartDB.insert(userCart);
                        if (inserted == 1) {
                            System.out.println("UserCartDB updated");
                        }
                    }
                    userCart = UserCartDB.selectUserCart(authUser);
                }
            }
            cart.setItems(new ArrayList<LineItem>());
        } else if (authUser != null) {
            userCart = UserCartDB.selectUserCart(authUser);
        }

        if (userRemoveButtonValue != null) {
            removeUserItem(userCart, userProductCode, authUser);
        }
        if (userUpdateButtonValue != null) {
            int userQuantity = -1;
            try {
                userQuantity = Integer.parseInt(userQuantityString);
            } catch (Exception e) {
            }
            if (userQuantity > 0) {
                updateUserItem(userCart, userQuantity, userProductCode, authUser);

            } else if (userQuantity == 0) {
                removeUserItem(userCart, userProductCode, authUser);
            }
        }

        session.setAttribute("userCart", userCart);

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    public void removeUserItem(UserCart userCart, String userProductCode, User authUser) {
        ArrayList<LineItem> userItems = userCart.getUserItems();
        for (int i = 0; i < userItems.size(); i++) {
            LineItem userlineItem = userItems.get(i);
            if (userlineItem.getProduct().getCode().equals(userProductCode)) {
                userCart.removeItem(userlineItem);
                int userCartID = userCart.getUserCartNumber();
                CartLineItemDB.deleteCartLineItem(userCartID, userlineItem);
                userCart = UserCartDB.selectUserCart(authUser);
            }
        }

    }

    public void updateUserItem(UserCart userCart, int userQuantity, String userProductCode, User authUser) {
        ArrayList<LineItem> userItems = userCart.getUserItems();

        for (int i = 0; i < userItems.size(); i++) {
            LineItem userlineItem = userItems.get(i);
            if (userlineItem.getProduct().getCode().equals(userProductCode)) {
                userlineItem.setQuantity(userQuantity);
                userCart.updateItem(userlineItem);

                if (userCart.getUserCartNumber() != 0) {
                    UserCartDB.updateUserCart(userCart);
                } else {
                    userCart.setUser(authUser);
                    UserCartDB.insert(userCart);
                }
                userCart = UserCartDB.selectUserCart(authUser);
            }
        }

    }

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        doPost(request, response);
    }
}
