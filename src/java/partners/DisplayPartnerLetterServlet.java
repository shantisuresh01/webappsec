package partners;

import music.cart.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

import music.business.*;
import music.data.*;

public class DisplayPartnerLetterServlet extends HttpServlet {    // declare an instance variable for the page

    int quantity; // instance variables are not thread-safe

    public void init() throws ServletException {
        quantity = 0; // initialize the instance variable
    }

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String letter_name = request.getParameter("letter");
        ServletContext sc = this.getServletContext();
        HttpSession session = request.getSession();

        String path = sc.getRealPath("/letters");
        String url = "/partners/partners.jsp";      
        String text = "";
        String filepath = path + "/" + letter_name;
        
        File f = new File(filepath);
        if (f.isFile() && f.exists()) {
            if (f.length() > 80000) {
                log("File too large");
            }
            String fileData = getFileText(new BufferedReader(new FileReader(f)), false);
            if (fileData.indexOf(0x00) != -1) {
                log("FileBinary");
            }
            fileData.replaceAll(System.getProperty("line.separator"), "<br>");
            fileData.replaceAll("(?s)<!DOCTYPE.*/head>", "");
            fileData.replaceAll("<br><br>", "<br>");
            fileData.replaceAll("<br>\\s<br>", "<br>");
            fileData.replaceAll("<\\?", "&lt;");
            fileData.replaceAll("<(r|u|t)", "&lt;$1");
            text = fileData;

        }
        session.setAttribute("text", text);
        session.setAttribute("filepath", f.getCanonicalPath());

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);

        dispatcher.forward(request, response);

    }

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        doPost(request, response);
    }

    public static String getFileText(BufferedReader reader, boolean numbers) {
        int count = 0;
        StringBuffer sb = new StringBuffer();

        try {
            String line;

            while ((line = reader.readLine()) != null) {
                if (numbers) {
                    sb.append("  ");
                }
                sb.append(line + System.getProperty("line.separator"));
            }

            reader.close();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

        return (sb.toString());
    }
}
