<%@page import="java.net.URLDecoder"%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<!-- start the middle column -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<td width="404" valign="top">

    <h1>Password Recovery</h1>
    <p>Please input your email address and answer the secret question</p>
    <form action="<c:url value='/validation/passwordRecovery'/>" method="post">
        <table cellspacing="5" border="0">
            <tr>
                <td align=right>Email Address</td>
                <td>
                    <input type=text name="emailAddress" size=20>
                    <font color=red>*</font>
                </td>
            </tr>
            <tr>What is your favorite color? </tr>
            <tr>
                <td align="right"><p>Answer</p> </td>
                <td>
                    <input type=text name="answer" size=20>
                    <font color=red>*</font>
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="Submit"></td>
            </tr>
        </table>

    </form>
    <div id="Result">
        ${result}
    </div>

    <br>



</td>

<!-- end the middle column -->

<jsp:include page="/includes/footer.jsp" />