<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- start the middle column -->

<td width="404" valign="top">

<h1>Login Form - Error</h1>
<p>You did not log in successfully.</p>
<p>Please check your username and password and try again.</p>

<form action="j_security_check" method="post">
<table cellspacing="5" border="0">
    <tr>
        <td align="right"><p>Email Address: </p></td>
        <td><input type="text" name="j_username"></td>
    </tr>
    <tr>
        <td align="right"><p>Password: </p></td>
        <td><input type="password" name="j_password"></td>
    </tr>
    <tr>
      <td><input type="submit" value="Login"></td>
    </tr>
</table>
            <input type="hidden" name="from" value="${param.from}">
        <input type="hidden" name="to" value="${param.to}">
</form>
    <br>
    <p>Or you can register a new account. </p>
    <form action="/yuliana/registration/displayUserRegistration" method="post">
        <input type="submit" value="Register here">
    </form>
    <br>
        <p>Forgot your password?</p>
    <form action="<c:url value='/validation/displayPasswordRecovery'/>" method="post">
        <input type="submit" value="Recover your password">
    </form>
</td>

<!-- end the middle column -->
<jsp:include page="/includes/footer.jsp" />