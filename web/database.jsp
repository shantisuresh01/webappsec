
<%@page import="music.util.UserUtil"%>
<%@page import="music.util.CookieUtil"%>
<%@page import="music.util.DB_Info"%>
<%@page contentType="text/html" pageEncoding="MacRoman"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Database Page</title>
</head>
<body>
    <h1> Database Information </h1>
    <% out.println(DB_Info.initConnection()); %>
</body>
</html>
