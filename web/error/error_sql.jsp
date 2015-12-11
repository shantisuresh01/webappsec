<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<!-- start the middle column -->

<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<td width="404" valign="top">

    <h1>Database error</h1>
    <p>You have encountered an error with the Music Store database. 
        You may need to install this database as described in appendix A.</p>
    <p>To continue, click the back button or select a link from this page.</p>
    <br>
    
</td>

<!-- end the middle column -->

<jsp:include page="/includes/footer.jsp" />