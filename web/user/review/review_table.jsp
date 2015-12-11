<br>
<hr>
<br>
<h1>Reviews</h1>
<br>
<table border="0">
    <tr>
        <td width="100"><b>Title</b></td>
        <td width="150"><b>Customer Name</b></td>
        <td><b>Review Date</b></td>
    </tr>
    <c:forEach var="reviewItem" items="${reviewsByProduct}">
        <tr>
            <td><a href="displayReview?reviewNumber=${reviewItem.reviewNumber}">
                    ${reviewItem.title}
                </a>
            </td>
            <td>${reviewItem.userID}</td>
            <td>${reviewItem.reviewDate}</td>
        </tr>
    </c:forEach>

</table>
