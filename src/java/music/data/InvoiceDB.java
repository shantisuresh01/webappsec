package music.data;

import java.sql.*;
import java.util.*;

import music.business.*;

public class InvoiceDB
{
    public static int insert(Invoice invoice)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        //This method adds a record to the Invoices table.
        //To insert the exact invoice date, the SQL SYSDATE function is used.
        int userID = UserDB.selectUserID(invoice.getUser());
        String query = "INSERT INTO v_Invoice (UserID, InvoiceDate, TotalAmount, IsProcessed) "
                + "VALUES (?, SYSDATE, ?, 'n')";

        try
        {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setDouble(2,invoice.getInvoiceTotal());
            ps.executeUpdate();

            //Get the InvoiceID from the last INSERT statement.
            String identityQuery = "SELECT @@IDENTITY AS IDENTITY";
            Statement identityStatement = connection.createStatement();
            ResultSet identityResultSet = identityStatement.executeQuery(identityQuery);
            identityResultSet.next();
            int invoiceID = identityResultSet.getInt("IDENTITY");
            identityResultSet.close();
            identityStatement.close();

            //Write line items to the LineItem table.
            ArrayList<LineItem> lineItems = invoice.getLineItems();
            for (int i = 0; i<lineItems.size(); i++)
            {
                LineItem item = lineItems.get(i);
                LineItemDB.insert(invoiceID, item);
            }            

            return 1;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

 
}