<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<!-- begin middle column -->

<td>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <h1>Your Cart</h1>
    <table cellspacing="5" border="0" name="Products" id="Products">
        <tr><b>Saved Cart Items</b></tr>
    <tr>
        <th align="left">Qty</th>
        <th align="left"></th>
        <th align="left">Description</th>
        <th align="left">Price</th>
        <th align="left">Amount</th>
    </tr>

    <c:forEach var="userItem" items="${userCart.userItems}">
        <form action="<c:url value='/user/order/displayUserCart' />" method="post">
            <tr valign="top">
                <td><input type="hidden" name="userProductCode" value="${userItem.product.code}">
                    <input type="text" size="2" name="userQuantity" value="${userItem.quantity}">
                    
                </td>
                <td><input type="submit" name ="userUpdateButton" value="Update"></td>
                <td>${userItem.product.description}</td>
                <td>${userItem.product.priceCurrencyFormat}</td>
                <td>${userItem.totalCurrencyFormat}</td>
                <td><input type="submit" name="userRemoveButton" value="Remove"></td>
            </tr>
        </form>
    </c:forEach>
</table>

<form action="<c:url value='/user/order/displayInvoice' />" method="post">
    <input type="submit" value="Checkout">
</form>
    <form action="<c:url value='/cart/displayQuickOrder' />" method="post">
    <input type="submit" value="Continue Shopping">
</form>

</td>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />