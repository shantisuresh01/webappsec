<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<!-- begin middle column -->
<td valign="top">

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <h1>Registration Confirmation</h1>
    <p><i>${userConfirmMessage}</i></p>

    <div id="userDetail"><p>${newUser.addressHTMLFormat}</p></div>
    <br>
    
    <form action="<c:url value='/registration/continueUser'/>" method="post">
        <input type="submit" value="Continue">
    </form>
        
</td>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />