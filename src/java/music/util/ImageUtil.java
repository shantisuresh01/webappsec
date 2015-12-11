package music.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import music.business.Product;

/**
 *
 * @author yulia
 */
public class ImageUtil {

    public static String ImagePath(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Product product = (Product) session.getAttribute("product");
        ServletContext context = session.getServletContext();
        String imagePath = context.getRealPath("/images/no_image.jpg");
        if (product != null) {
            imagePath = context.getRealPath("/images/" + getImageName(product));
            java.io.File file = new java.io.File(imagePath);
            if (file.exists()) {
                imagePath = product.getImageURL();
            } else {
                imagePath = "/yuliana/images/no_image.jpg";
            }
        }
        return imagePath;
    }

    public static String getImageName(Product product) {
        String imageName = "";
        if (product != null) {

            if (!product.getCode().equalsIgnoreCase("")) {
                imageName = product.getCode() + "_cover.jpg";

            }
        }
        return imageName;
    }
}
