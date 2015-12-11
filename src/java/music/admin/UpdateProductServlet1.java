package music.admin;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


import music.business.*;
import music.data.*;

public class UpdateProductServlet1 extends HttpServlet {

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String productCode = request.getParameter("code");
        String productDescription = request.getParameter("description");
        String productPrice = request.getParameter("price");


        String updateProductMessage = "";
        String url = "/productMain4/updateProduct.jsp";

        Product product = new Product();

        try {
            double price = Double.valueOf(productPrice.trim()).doubleValue();
            product.setPrice(price);
            log("double d = " + price);
        } catch (NumberFormatException nfe) {
            updateProductMessage = "The price value should be in Number format";
            log("NumberFormatException: " + nfe.getMessage());

        }
        product.setCode(productCode);
        product.setDescription(productDescription);

        if (productCode.length() == 0 || productCode == null
                || productDescription.length() == 0 || productDescription == null
                || productPrice.length() == 0 || productPrice == null
                || updateProductMessage != "") {
            // forward to the view to get missing parameters
            url = "/productMain4/updateProduct.jsp";
        } else {

            if (ProductDB.updateProduct(product) != 0) {
                updateProductMessage = "Product details and price are updated";
                product = ProductDB.selectProduct(productCode);
            } else {
                updateProductMessage = "Product details and price are not updateded";
            }
        }
        session.setAttribute("updateProductMessage", updateProductMessage);
        session.setAttribute("product", product);

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
