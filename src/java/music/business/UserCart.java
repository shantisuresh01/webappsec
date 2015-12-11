package music.business;

import java.util.*;
import java.io.Serializable;

public class UserCart implements Serializable {

    private ArrayList<LineItem> userItems;
    private User user;
    private int userCartNumber;

    public UserCart() {
        userItems = new ArrayList<LineItem>();
        user = new User();
        userCartNumber = 0;
    }

    public void setUserItems(ArrayList<LineItem> lineItems) {
        userItems = lineItems;
    }

    public ArrayList<LineItem> getUserItems() {
        return userItems;
    }

    public int getCount() {
        return userItems.size();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUserCartNumber() {
        return userCartNumber;
    }

    public void setUserCartNumber(int userCartNumber) {
        this.userCartNumber = userCartNumber;
    }

    public void addItem(LineItem item) {
        //If the item already exists in the cart, only the quantity is changed.
        String code = item.getProduct().getCode();
        int quantity = item.getQuantity();
        for (int i = 0; i < userItems.size(); i++) {
            LineItem lineItem = userItems.get(i);
            int userItemQuantity = lineItem.getQuantity();

            if (lineItem.getProduct().getCode().equals(code)) {
                lineItem.setQuantity(quantity+userItemQuantity);
                return;
            }
        }
        userItems.add(item);
    }
        public void updateItem(LineItem item) {
        //If the item already exists in the cart, only the quantity is changed.
        String code = item.getProduct().getCode();
        int quantity = item.getQuantity();
        for (int i = 0; i < userItems.size(); i++) {
            LineItem lineItem = userItems.get(i);
            if (lineItem.getProduct().getCode().equals(code)) {
                lineItem.setQuantity(quantity);
                return;
            }
        }
        userItems.add(item);
    }

    public void removeItem(LineItem item) {
        String code = item.getProduct().getCode();
        for (int i = 0; i < userItems.size(); i++) {
            LineItem lineItem = userItems.get(i);
            if (lineItem.getProduct().getCode().equals(code)) {
                userItems.remove(i);
                return;
            }
        }
    }
}
