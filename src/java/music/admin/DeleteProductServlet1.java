package music.admin;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.ArrayList;

import music.business.*;
import music.data.*;

import java.sql.*;

public class DeleteProductServlet1 extends HttpServlet {

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String productCode = request.getParameter("code");
        log(productCode);
        Product product = new Product();
        product.setCode(productCode);
        ProductDB.deleteProduct(product);
        product = ProductDB.selectProduct(productCode);
        session.setAttribute("product", product);
        String url = "/productMain4/displayProducts";
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
