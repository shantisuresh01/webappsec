<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<td width="404" valign="top">
            <h1>Products</h1>
    <table cellpadding="5" border="1" name="products" id="Products">
        <tr valign="bottom">
            <th align="left">Code</th>
            <th align="left">Description</th>
            <th align="left">Price</th>
            <th align="left">&nbsp;</th>
            <th align="left">&nbsp;</th>
        </tr>

        <c:forEach var="product" items="${products}">
            <tr valign="top">

                <td>
                    ${product.code}
                </td>
                <td>
                    ${product.description}
                </td>
                <td>${product.priceCurrencyFormat}</td>

                <td>
                    <a href="
                       <c:url value='updateProduct?productCode=${product.code}'/>">
                        Edit
                    </a>
                </td>
                <td>
                    <a href="
                       <c:url value='deleteProduct?productCode=${product.code}'/>">
                        Delete
                    </a>
                </td>
            </tr>
        </c:forEach>

    </table>
    <br>
    <form action="<c:url value='addProduct.jsp'/>" method="post">
        <input type="submit" value=" Add Product ">
    </form>
</td>
</body>
</html>
