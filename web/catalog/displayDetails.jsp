<%@page import="music.util.ImageUtil"%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<td width="404" valign="top">
    <h1>Display Product Details</h1>
    <table cellspacing="5" cellpadding="5" name="productDetails" id="productDetails">
        <tr><img src="<%=ImageUtil.ImagePath(request)%>" width="100" height="100"></tr>
        <tr>
            <td align="right"><b>Product Code:</b></td>
            <td>
                ${product.code}
            </td>
        </tr>
        <tr>
            <td align="right" width="110"><b>Product Description:</b></td>
            <td>
                ${product.description}

            </td>
        </tr>
        <tr>
            <td align="right" width="110"><b>Product Price:</b></td>
            <td>
                ${product.price}
            </td>
        </tr>

    </table>
    <br>

    <hr>
    <br>
    <h1>Reviews</h1>
    <table name ="Reviews" border="0">
        <tr>
            <td width="100"><b>Title</b></td>
            <td width="150"><b>Customer Name</b></td>
            <td><b>Review Date</b></td>
        </tr>
        <c:forEach var="reviewItem" items="${reviews}">
            <tr>
                <td><a href="displayProduct?productCode=${product.code}&reviewNumber=${reviewItem.reviewNumber}">
                        ${reviewItem.title}
                    </a>
                </td>
                <td>${reviewItem.userID}</td>
                <td>${reviewItem.reviewDate}</td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${not empty reviewByNumber}">
        <hr>
        <br>
        <h1>Review Content</h1>
        <table name="ReviewContent" cellspacing="0" cellpadding="0" border="0">
            <tr><td><b>User &nbsp;&nbsp;&nbsp;</b></td>
                <td>${reviewByNumber.userID}</td>
            </tr>
            <tr><td><b>Title &nbsp;&nbsp;&nbsp;</b></td>
                <td>${reviewByNumber.title}</td>
            </tr>
            <tr><td><b>Message &nbsp;&nbsp;&nbsp;</b> </td>
                <td>${reviewByNumber.message}</td>
            </tr>
            <tr><td><b>Date &nbsp;&nbsp;&nbsp;</b> </td>
                <td>${reviewByNumber.reviewDate}</td>
            </tr>
        </table>
    </c:if>
        <br>
        <form action="<c:url value='/user/review/displayReview' />" method="post">
        <input type="submit" value="Review Product">
    </form>

</td>
<jsp:include page="/includes/footer.jsp" />
