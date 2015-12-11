<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="product" scope="session" class="music.business.Product"/>
<%@ taglib prefix="mma" uri="/WEB-INF/storeTagLib.tld" %>
<td width="404" valign="top">

    <h1>Add Product</h1>
        <p><i>${message}</i></p>
        
    <form action="<c:url value='/productMain4/addProduct' />" method="post">
        <table cellspacing="5" cellpadding="5"  >
            <tr>
                <td align="right">Product Code:</td>
                <td>
                    <input type="test" name="code" value="<%= product.getCode() %>">
                    <mma:ifEmptyMark color="blue" field="<%= product.getCode() %>"/>
                </td>
            </tr>
            <tr>
                <td align="right">Product Description:</td>
                <td>
                    <input type="text"  name="description" value="${product.description}">
                <mma:ifEmptyMark color="blue" field="<%= product.getDescription() %>"/>
                </td>

            </tr>
            <tr>
                <td align="right">Product Price:</td>
                <td>
                    <input type="text"  name="price" value="${product.price}">
                <mma:ifEmptyMark color="blue" field="<%= product.getPriceCurrencyFormat() %>"/>    

                </td>
            </tr>
            <tr>
                <td></td>
                <td><br><input type="submit" value="Add Product"></td>
            </tr>
        </table>
    </form>
</td>

</body>
</html>
