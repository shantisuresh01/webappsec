<%@page import="music.util.ImageUtil"%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="product" scope="session" class="music.business.Product"/>
<td width="404" valign="top">
    <table cellspacing="5" cellpadding="5"  >

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
    <h1>Add Review</h1>
    <form action="<c:url value='/user/review/addReview' />" method="post">

        <table cellspacing="5" cellpadding="5" border="0">
            <tr>
                <td>Title: </td>
                <td><input type="TEXT" value="" name="title"></td>
            </tr>
            <tr><td valign="TOP">Message: </td>
                <td><textarea rows="5" name="message" cols="60" value =""></textarea></td>
            </tr>
        </table>
        <p>
            <input type="SUBMIT" value="Submit" name="SUBMIT">
        </p>
    </form>
    <br>
    <p>${reviewResult}</p>
    <a href="
       <c:url value='/catalog/displayProduct?productCode=${product.code}'/>">
        Continue to product details
    </a>
    <br>
</td>
<!-- end the middle column -->

<jsp:include page="/includes/footer.jsp" />