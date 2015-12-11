
package job;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import music.business.*;
import music.data.*;

public class UpdateProductServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String productCode = request.getParameter("productCode");
        String updateProductMessage="";

        Product product = new Product();

        product = ProductDB.selectProduct(productCode);
        int productID = ProductDB.selectProductID(product);

        session.setAttribute("product", product);
        session.setAttribute("productID", productID);
        session.setAttribute("updateProductMessage", updateProductMessage);

        String url = "/productMain4/updateProduct.jsp";
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

