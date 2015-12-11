<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<!-- begin middle column -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<td valign="top">

    <h1>You Cart is Empty</h1>

    <p>To continue shopping please click the button.</p>
    <form action="<c:url value='/cart/displayQuickOrder' />" method="post">
        <input type="submit" value="Continue Shopping">
    </form>

</td>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />