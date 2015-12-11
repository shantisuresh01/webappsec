<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<!-- begin middle column -->

<td>

    <script language="JavaScript">
        var charexp = /./
        var letterexp = /[a-z]/i
        var zipexp = /^\d{5}$|^\d{5}[\-\s]?\d{4}$/
        var emailexp = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/

        var pledgexp = /^\d*$|^\d*\.\d{2}$/
        function isValid(pattern, str) {
            return pattern.test(str)
        }
        function hasLetter(str) {
            return letterexp.test(str)
        }

        function validate(form)
        {
            if (!hasLetter(form.firstName.value)) {
                alert("Invalid First Name: please feel in correct First Name")
                form.firstname.focus()
                return false
            }
            //Check the last name text box for an entry
            else if (!hasLetter(form.lastName.value)) {
                alert("Invalid Last Name: please feel in correct Last Name")
                form.lastname.focus()
                return false
            }
            //Check that the email entry is valid
            else if (!isValid(emailexp,form.emailAddress.value)) {
                alert("Invalid email: Please feel in your email address")
                form.email.focus()
                return false
            }
            else if (form.address1.value=="")
            {
                alert("Please fill in your street address line 1");
                form.address1.focus();
            }
            else if (form.city.value=="")
            {
                alert("Please fill in your city name");
                form.city.focus();
            }

            else if (!isValid(zipexp,form.zip.value)) {
                alert("Invalid ZIP code: Please feel in your Zip code")
                form.zip.focus()

            }

            else if (form.country.value=="")
            {
                alert("Please fill in your country");
                form.country.focus();
            }
            else
            {
                form.submit();
            }
        }
    </script>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <p><i>${userRegistrationMessage}</i></p>
    <h1>Registration: Enter your name and contact information</h1>

    <form action="<c:url value='/registration/processUser' />" method=post>
        <table border="0" cellpadding="5">
            <tr>
                <td></td>
                <td align=left>Required <font color=red>*</font></td>
            </tr>
            <tr>
                <td align=right>First Name</td>
                <td><input type="text" name="firstName"  size="20" maxlength=20
                           value="${newUser.firstName}">
                    <font color=red>*</font></td>
            </tr>
            <tr>
                <td align=right>Last Name</td>
                <td><input type=text name="lastName" size=20
                           value="${newUser.lastName}">
                    <font color=red>*</font></td>
            </tr>
            <tr>
                <td align=right>Email Address</td>
                <td><input type=text name="emailAddress" size=20
                           value="${newUser.emailAddress}">
                    <font color=red>*</font></td>
            </tr>
            <tr>
                <td align=right>Password</td>
                <td><input type=text name="password" size=20>
                    <font color=red>*</font></td>
            </tr>
            <tr>
                <td align=right>What is your favorite color</td>
                <td><input type=text name="answer" size=20>
                    <font color=red>*</font></td>
            </tr>
            <tr>
                <td align=right>Company</td>
                <td><input type=text name="companyName" size=20
                           value="${newUser.companyName}"></td>
            </tr>
            <tr>
                <td align=right>Address1</td>
                <td><input type=text name="address1" size=20
                           value="${newUser.address1}">
                    <font color=red>*</font></td>
            </tr>
            <tr>
                <td align=right>Address2</td>
                <td><input type=text name="address2" size=20
                           value="${newUser.address2}"></td>
            </tr>
            <tr>
                <td align=right>City</td>
                <td><input type=text name="city" size=20
                           value="${newUser.city}">
                    <font color=red>*</font></td>
            </tr>
            <tr>
                <td align=right>State</td>
                <td><input type=text name="state" size=20
                           value="${newUser.state}">
                    <font color=red>*</font></td>
            </tr>
            <tr>
                <td align=right>Zip Code</td>
                <td><input type=text name="zip" size=20
                           value="${newUser.zip}">
                    <font color=red>*</font></td>
            </tr>
            <tr>
                <td align=right>Country</td>
                <td><input type=text name="country" size=20
                           value="${newUser.country}">
                    <font color=red>*</font></td>
            </tr>
            <tr>
                <td align="right"><p>Credit card type</td>
                <td><select name="creditCardType" size="1">
                        <option value="Visa">Visa</option>
                        <option value="Mastercard">Mastercard</option>
                        <option value="AmEx">American Express</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="right">Card number</td>
                <td><input type="text" size="20" name="creditCardNumber"
                           maxlength="25">
                    <font color=red>*</font>
                    <p><i>${msg}</i></p>

                </td>
            </tr>
            <tr>
                <td align="right"><p>Expiration date (mm/yyyy)</td>
                <td><select name="creditCardExpirationMonth">
                        <option value="01">01
                        <option value="02">02
                        <option value="03">03
                        <option value="04">04
                        <option value="05">05
                        <option value="06">06
                        <option value="07">07
                        <option value="08">08
                        <option value="09">09
                        <option value="10">10
                        <option value="11">11
                        <option value="12">12
                    </select>
                    /
                    <select name="creditCardExpirationYear">
                        <c:forEach var="year" items="${creditCardYears}">
                            <option value="${year}">${year}
                            </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td align=right>&nbsp;</td>
                <td><input type="button" value="Continue" onClick="validate(this.form)"></td>
            </tr>
        </table>

    </form>

</td>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />