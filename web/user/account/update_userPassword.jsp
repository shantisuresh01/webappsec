<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<!-- begin middle column -->

<td>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <h1>Password Update</h1>
    <div id="updatePasswordMessage"><p><i>${updatePassword_message}</i></p></div>
    <form action="<c:url value='/user/account/updateUserPassword' />" method=post>
        <table border="0" cellpadding="5">
            <tr>
                <td></td>
                <td align=left>Required <font color=red>*</font></td>
            </tr>
            <tr>
                <td align=right>Password</td>
                <td><input type=text name="password" size=20>
                    <font color=red>*</font></td>
            </tr>
            <tr>
                <td align=right>What is your favorite color?</td>
                <td><input type=text name="answer" size=20 value="${user.answer}">
                    <font color=red>*</font></td>
            </tr>
            <tr>
                <td align=right>&nbsp;</td>
                <td><input type="button" value="Continue" onClick="form.submit()"></td>
            </tr>
        </table>
    </form>
</td>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />