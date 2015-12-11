<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<td width="404" valign="top">
    <h1>Are you sure you want to delete this product? </h1>
    <form action="<c:url value='deleteProduct1' />" method="post">
        <table cellspacing="5" cellpadding="5" name="Product" id="Product">
            <tr>
                <td align="right">Product Code:</td>
                <td>
                    ${product.code}
                </td>
            </tr>
            <tr>
                <td align="right">Product Description:</td>
                <td>${product.description}</td>
            </tr>
            <tr>
                <td align="right">Product Price:</td>
                <td>${product.price}</td>
            </tr>
        </table>

    </form>
    <table border="0" cellpadding="5" cellspacing="5" >

        <tr>
            <td></td>
            <td></td>
            <td>
                <form action="<c:url value='deleteProduct1?code=${product.code}'/>" method="post">
                    <input type="submit" value="Yes">
                </form>
            </td>
            <td>

                <form action="<c:url value='displayProducts'/>" method="post">
                    <input type="submit" value="No">
                </form>
            </td>
        </tr>
    </table>
</td>


</body>
</html>
