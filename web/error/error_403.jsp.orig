<%@page import="music.util.UserUtil"%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<!-- start the middle column -->

<%@ page isErrorPage="true" %>

<td width="404" valign="top">
    <%
                boolean isAuth = UserUtil.isAuth(request);
    %>
    <h1>403 Error</h1>
    <p>There has been authentication problem.</p>
    <p>To continue, click
        <%
                    if (!isAuth) {
        %><a href="/yuliana/validation/login">Log In</a>
        <%} else {%>
        <a href="/yuliana/validation/logout">Log out</a>
        <% }%> or select a link from this page.
    </p>
    <br>


</td>

<!-- end the middle column -->

<jsp:include page="/includes/footer.jsp" />