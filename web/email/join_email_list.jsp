<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<td width="404" valign="top">

    <script language="JavaScript">

        function getXMLObject()  //XML OBJECT
        {
            var xmlHttp = false;
            try {
                xmlHttp = new ActiveXObject("Msxml2.XMLHTTP")  // For Old Microsoft Browsers
            }
            catch (e) {
                try {
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")  // For Microsoft IE 6.0+
                }
                catch (e2) {
                    xmlHttp = false   // No Browser accepts the XMLHTTP Object then false
                }
            }
            if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
                xmlHttp = new XMLHttpRequest();        //For Mozilla, Opera Browsers
            }
            return xmlHttp;  // Mandatory Statement returning the ajax object created
        }

        var xmlhttp = new getXMLObject();	//xmlhttp holds the ajax object
        function addToEmailList(){
            var firstname=document.getElementById("firstName").value;
            var lastname = document.getElementById("lastName").value;
            var emailaddress = document.getElementById("emailAddress").value;
            var query_string = "firstName="+firstname+"&lastName="+lastname+"&emailAddress="+emailaddress;

            if(xmlhttp) {
                xmlhttp.open("GET","/yuliana/email/addToEmailList?"+query_string,true);

                xmlhttp.onreadystatechange=function()
                {
                    if (xmlhttp.readyState==4 && xmlhttp.status==200)
                    {
                        document.getElementById("details").innerHTML=xmlhttp.responseText;
                    }
                }
                xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xmlhttp.send();
            }

        }
    </script>
    <div id="details"></div>
    <h1>Join our email list</h1>
    <p>If you do, we'll send you announcements about new releases and special offers.</p>
    <div>
        Hello
        <SCRIPT>
            var pos=document.URL.indexOf("name=")+5;
            document.write(unescape(document.URL.substring(pos,document.URL.length)));
        </SCRIPT>
        <BR>
    </div>

    <table cellpadding="5" border="0">
        <tr>
            <td align="right"><p>First name:</td>
            <td><input id ="firstName" type="text" name="firstName" value="">

            </td>
        </tr>
        <tr>
            <td align="right"><p>Last name:</td>
            <td><input id="lastName" type="text" name="lastName" value="">

            </td>
        </tr>
        <tr>
            <td align="right"><p>Email address:</td>
            <td><input type="text" id="emailAddress" name="emailAddress" value="">

            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="button" value="Submit"
                       onClick="addToEmailList()">
            </td>
        </tr>
    </table>

</td>

<jsp:include page="/includes/footer.jsp" />