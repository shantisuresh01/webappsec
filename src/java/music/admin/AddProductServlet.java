package music.admin;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import music.business.*;
import music.data.*;

import java.util.*;

public class AddProductServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String productCode = request.getParameter("code");
        String productDescription = request.getParameter("description");
        String productPrice = request.getParameter("price");

        String message = "";

        Product product = new Product();


        try {
            double price = Double.valueOf(productPrice.trim()).doubleValue();
            product.setPrice(price);
            log("double d = " + price);
        } catch (NumberFormatException nfe) {
            message = "The price value should be in Number format";

            log("NumberFormatException: " + nfe.getMessage());
        }
        if (productCode!=null){
            productCode=cleanXSS(productCode);
            product.setCode(productCode);
        }
        if (productDescription!=null){
            productDescription=cleanXSS(productDescription);
            product.setDescription(productDescription);
        }

        String url = "";
        session.setAttribute("product", product);
        session.setAttribute("message", message);

        if (productCode.length() == 0 || productCode == null
                || productDescription.length() == 0 || productDescription == null
                || productPrice.length() == 0 || productPrice == null
                || message != "") {
            // forward to the view to get missing parameters
            url = "/productMain4/addProduct.jsp";
        } else {

            ProductDB.addProduct(product);


            ArrayList<Product> products = ProductDB.selectProducts();
            session.removeAttribute("product");

            session.setAttribute("products", products);

            url = "/productMain4/displayProducts.jsp";
        }

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
    private String cleanXSS(String value) {

                //You'll need to remove the spaces from the html entities below
        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
       
        return value;

    }

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
