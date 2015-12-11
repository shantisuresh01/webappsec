<%@page import="music.util.ImageUtil"%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<!-- begin middle column -->

<td valign="top">
    <script type="text/javascript">

        function getXMLObject()  //XML OBJECT
        {
            var xmlHttp = false;
            try {
                xmlHttp = new ActiveXObject("Msxml2.XMLHTTP")  // For Old Microsoft Browsers
            }
            catch (e) {
                try {
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")  // For Microsoft IE 6.0+
                }
                catch (e2) {
                    xmlHttp = false   // No Browser accepts the XMLHTTP Object then false
                }
            }
            if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
                xmlHttp = new XMLHttpRequest();        //For Mozilla, Opera Browsers
            }
            return xmlHttp;  // Mandatory Statement returning the ajax object created
        }

        var xmlhttp = new getXMLObject();	//xmlhttp holds the ajax object
        function updateQuantity(form) {
            var f_quantity = form.quantity.value;
            var f_productCode = form.productCode.value;
            var q_string = "?productCode="+ f_productCode+"&quantity="+f_quantity;
            var quantity_id = document.getElementById(form.id).quantity.id;
            var f_trID = document.getElementById(form.id).parentNode.id;

            if (f_quantity==0){
                daleteItem(f_trID);
            }
            if(xmlhttp) {
                xmlhttp.open("GET","/yuliana/cart/setQuantity"+q_string,true);
                xmlhttp.onreadystatechange=function()
                {
                    if (xmlhttp.readyState==4 && xmlhttp.status==200)
                    {
                        document.getElementById(quantity_id).innerHTML=f_quantity;
                    }
                }
                xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xmlhttp.send();
            }
        }
        function removeCartItem(form){
            var f_productCode = form.productCode.value;
            var q_string = "?productCode="+ f_productCode;
            var f_trID = document.getElementById(form.id).parentNode.id;
            
            if(xmlhttp) {
                xmlhttp.open("GET","/yuliana/cart/removeCartItem"+q_string,true);
                xmlhttp.onreadystatechange  = function(){
                    if (xmlhttp.readyState==4 && xmlhttp.status==200) {
                        if(xmlhttp.responseText==0){
                            daleteItem(f_trID);
                        }
                    }
                }
                xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xmlhttp.send();
            }

        }
        function daleteItem(f_trID){
            document.getElementById(f_trID).parentNode.removeChild(document.getElementById(f_trID));
            if (document.getElementsByName("displayCart").length==0){
                document.getElementById("table").parentNode.removeChild(document.getElementById("table"));
                document.getElementById("saveCart").parentNode.removeChild(document.getElementById("saveCart"));
                document.getElementById("cartDiv").innerHTML="<h1>You Cart is Empty</h1>";
                document.getElementById("cartText").innerHTML="To continue shopping please click the button.";
            }
        }
    </script>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div id ="cartDiv" ><h1>Your cart</h1></div>

    <div id ="cartText"><b>New Items </b></div>
    <table cellspacing="5" border="0" id="table">
        <tr>
            <th align="left"></th>
            <th align="left">Qty</th>
            <th align="left">Description</th>
            <th align="left">Price</th>
            <th align="left">Amount</th>
        </tr>
        <c:set var="count" value="0"  />
        <c:forEach var="item" items="${cart.items}">
            <tr id="tr${count}">
            <form name = "displayCart" id="displayCart${count}" action="<c:url value='/cart/displayCart' />" method="post">
                <td>
                    <input type="hidden" id="productCode${count}" name="productCode" value="${item.product.code}">
                    <input type="text" size="2" id="quantity${count}" name="quantity" value="${item.quantity}">
                    <input type="button"name="updateButton" value="Update" onclick="updateQuantity(this.form);">
                </td>
                <td>${item.product.description}</td>
                <td>${item.product.priceCurrencyFormat}</td>
                <td>${item.totalCurrencyFormat}</td>
                <td>
                    <input type="button" name="removeButton" value="Remove" onclick="removeCartItem(this.form);">
                </td>
                    <c:set var="count" value="${count + 1}" />
            </form>
        </tr>
    </c:forEach>
</table>
<form name ="saveCart" id ="saveCart"action="<c:url value='/user/order/displayUserCart' />" method="post">
    <input type="submit" value="Save Cart">
</form>
<c:if test="${not empty userCart}">
    <br>
    <hr>
    <p></p>
    <table cellspacing="5" border="0">
        <tr><b>Saved Cart Items</b></tr>
    <tr>
        <th align="left">Qty</th>
        <th align="left">Description</th>
        <th align="left">Price</th>
        <th align="left">Amount</th>
    </tr>

    <c:forEach var="userItem" items="${userCart.userItems}">
        <form action="<c:url value='/cart/displayCart' />" method="post">
            <tr valign="top">
                <td><input type="hidden" name="productCode" value="${userItem.product.code}">
                    ${userItem.quantity}
                </td>
                <td>${userItem.product.description}</td>
                <td>${userItem.product.priceCurrencyFormat}</td>
                <td>${userItem.totalCurrencyFormat}</td>
                <td><input type="submit" name="userRemoveButton" value="Remove"></td>
            </tr>
        </form>
    </c:forEach>
</table>
<br>
<p>To checkout with saved cart items click on the Checkout button.</p>
<form action="<c:url value='/user/order/displayInvoice' />" method="post">
    <input type="submit" value="Checkout">
</form>
</c:if>

<br>
<hr>

<form action="<c:url value='/cart/displayQuickOrder' />" method="post">
    <input type="submit" value="Continue Shopping">
</form>
</td>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />