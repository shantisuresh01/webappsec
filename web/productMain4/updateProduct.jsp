<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="product" scope="session" class="music.business.Product"/>
<%@ taglib prefix="mma" uri="/WEB-INF/storeTagLib.tld" %>

<td width="404" valign="top">
    <h1>Update Product</h1>

    <div id="updateProductMessage"><p><i>${updateProductMessage}</i></p></div>

    <form action="<c:url value='updateProduct1?code=${product.code}' />" method="post">
        <table cellspacing="5" cellpadding="5"  >
            <tr>
                <td align="right">Product Code:</td>
                <td>
                    ${product.code}
                </td>
            </tr>
            <tr>
                <td align="right">Product Description:</td>
                <td>
                    <input type="text"  name="description" value="${product.description}">
                    <mma:ifEmptyMark color="blue" field="<%= product.getDescription()%>"/>

                </td>

            </tr>
            <tr>
                <td align="right">Product Price:</td>
                <td>
                    <input type="text"  name="price" value="${product.price}">
                </td>
            </tr>
        </table>
        <br>
        <div>
            <div align="left" >To Update the product please select Update product button</div>

        </div>
        <div>
            <div align="left">
                <input type="submit" value="Update Products">
            </div>
        </div>

    </form>

    <br>

    <form action="<c:url value='displayProducts'/>" method="post">
        <div>
            <div align="left">
                To Return to Products table please select View Products button
            </div>
        </div>
        <div>
            <div><input type="submit" value="View Products"></div>
        </div>
    </form>
</td>
</body>
</html>
