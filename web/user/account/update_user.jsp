<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!-- start the middle column -->


<td valign="top">
    <h1>Welcome</h1>

    <div id="userData">${fn:escapeXml(user.firstName)}, ${fn:escapeXml(user.firstName)}<br>
        ${fn:escapeXml(user.companyName)}<br>
        ${fn:escapeXml(user.address1)}<br>
        ${fn:escapeXml(user.address2)}<br>
        ${fn:escapeXml(user.city)}, ${fn:escapeXml(user.state)}, ${fn:escapeXml(user.zip)}, ${user.country}<br>
    </div>
    <br>
    <div id="updateMessage"><p><i>${update_message}</i></p></div>
    <br>
    <p>
        To update your account details, please
    </p>
    <form action="<c:url value='/user/account/displayAccountDetails'/>" method="post">
        <input type="submit" value="Update Details">
    </form>
    <br>
    <br>
    <p>
        To update your account password and answer to secret question , please
    </p>
    <form action="<c:url value='/user/account/displayAccountPassword'/>" method="post">
        <input type="submit" value="Update Password">
    </form>
</td>


<!-- end the right column -->

<jsp:include page="/includes/footer.jsp" />