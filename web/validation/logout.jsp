<html>
    <head>
        <title>Vulnerabilities Testing Web Application</title>
        <link rel="stylesheet" href="/yuliana/musicStoreStyle.css">
    </head>

    <body>
        <table cellpadding="5" cellspacing="0" border="0" width="756">
            <tr>
                <td colspan="3"align="center" bgcolor="#DFFFCC">
                    <h1>Vulnerabilities Testing Web Application</h1>
                </td>
            </tr>
            <tr>
                <td colspan="3" align="right" bgcolor="#DFFFCC">
                    Welcome
                    <a href="/yuliana/user/validation/validateUser">Log In</a>
                    <a href="/yuliana/cart/displayCart">Show cart</a>
                </td>
            </tr>
            <jsp:include page="/includes/column_left_all.jsp" />
            <td valign="top">
                <p>You have been successfully logged out.</p>
            </td>

            <!-- end the middle column -->

            <jsp:include page="/includes/footer.jsp" />
