<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<!-- begin middle column -->

<td>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<h1>Your invoice</h1>

<table border="0" cellspacing="5">
  <tr><td><b>Date:</b></td>
      <td width="400">${invoice.invoiceDateDefaultFormat}</td>
      <td></td>
  </tr>
  <tr valign="top">
    <td><b>Ship To:</b></td>
    <td>
        ${fn:escapeXml(user.firstName)}, ${fn:escapeXml(user.firstName)}<br>
        ${fn:escapeXml(user.companyName)}<br>
        ${fn:escapeXml(user.address1)}<br>
        ${fn:escapeXml(user.address2)}<br>
        ${fn:escapeXml(user.city)}, ${fn:escapeXml(user.state)}, ${fn:escapeXml(user.zip)}, ${user.country}<br>
    </td>
    <td></td>
  </tr>
  <tr><td colspan="3"><hr></td></tr>
  <tr><td><b>Qty</b></td>
      <td><b>Description</b></td>
      <td><b>Price</b></td>
  </tr>

  <c:forEach var="item" items="${invoice.lineItems}">
  <tr>
    <td><p>${item.quantity}</td>
    <td><p>${item.product.description}</td>
    <td><p>${item.totalCurrencyFormat}</td>
  </tr>
  </c:forEach>

  <tr>
    <td><b>Total:</b></td>
    <td></td>
    <td>${invoice.invoiceTotalCurrencyFormat}</td>
  </tr>
</table>

<button onclick = "window.location='/yuliana/user/order/completeOrder'">Complete Order</button>

</td>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />