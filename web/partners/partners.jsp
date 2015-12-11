<%@page import="java.util.List"%>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<!-- start the middle column -->

<!-- If necessary, this page could be generated from the database. 
However, this format gives more control to the web designers. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<td width="404" valign="top">

    <form action="<c:url value='/partners/displayParnerLetter' />" method="post">
        <h1>Partners Letters</h1>
        <div id="partnerMessage">
            <b>musicStore</b> has built strong partner relationships<br>
            with major companies in order to bring the right solution<br>
            to the right place at the right time for the client.<br>
        </div>
        <br>
        <select name="letter" size="1">
            <c:forEach items="${list}" var="listItem">
                <option value="${listItem}">${listItem}</option>
            </c:forEach>
        </select>
        <input type="SUBMIT" value="View Letter" name="SUBMIT">
    </form>
    <div id="partnerText">
        <p>${text}</p>
    </div>

    <div id="redirect">
        <%
                    String site = request.getParameter("site");
                    if (site != null && site != "") {
                        response.setStatus(response.SC_MOVED_TEMPORARILY);
                        response.setHeader("Location", site);
                    }
        %>
    </div>
</td>

<!-- end the middle column -->

<jsp:include page="/includes/footer.jsp" />