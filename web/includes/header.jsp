<%--
    Document   : header
    Created on : Jul 29, 2011, 10:43:28 PM
    Author     : yulia
--%>

<%@page import="music.util.UserUtil"%>
<%@page import="music.util.CookieUtil"%>
<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>MusicStore: Vulnerable online shopping for CDs, DVDs & more</title>
        <link rel="stylesheet" href="/yuliana/musicStoreStyle.css">
        <script type="text/javascript">
            function headerNavigateTo(variable){
                window.location.href = variable;
            }
        </script>
    </head>

    <body>

        <table cellpadding="5" cellspacing="0" border="0" width="756">

            <tr>
                <td colspan="3"align="center" bgcolor="#DFFFCC">
                    <h1>MusicStore: Vulnerable online shopping for CDs, DVDs & more</h1>
                </td>
            </tr>

            <tr>
                <td colspan="3" align="right" bgcolor="#DFFFCC">
                    Welcome
                    <%
                                boolean isAuth = UserUtil.isAuth(request);
                                if (!isAuth) {
                    %><a href="javascript:headerNavigateTo('${pageContext.request. scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/yuliana/'+'user/validation/validateUser')">Log In</a>
                    <%} else {%>
                    <a href="javascript:headerNavigateTo('${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/yuliana/'+'user/account/displayAccount')">${user.emailAddress}</a> !
                    <a href="javascript:headerNavigateTo('${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/yuliana/'+'validation/logout')">Log out</a>
                    <% }%>
                    <a href="javascript:headerNavigateTo('${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/yuliana/'+'cart/displayCart')">Show cart</a>

                </td>
            </tr>
