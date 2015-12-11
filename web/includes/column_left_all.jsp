<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script>

    /* Application path */
    function getAppPath() {
        var pathArray = window.location.pathname.split('/');
        var appPath = "/"+pathArray[1] + "/";
        return appPath;
    }
    /* Navigation link */
    function navigateTo(variable){
        var loc = window.location;
        var navLink=loc.protocol+"//"+loc.host+getAppPath()+variable;
        window.location=navLink;
    }

</script>
<style type="text/css">
    .explorer{
        font-family:verdana,arial,helvetica;
        font-size:8pt;
        font-weight:normal;
        text-decoration:none;
        color:#000000;
        background:#DFFFCC;
        cursor:hand;
        width:150px;
        height:30px;
        border:solid #A6C0ED}
    .explorer_over{
        font-family:verdana,arial,helvetica;
        font-size:8pt;
        font-weight:normal;
        text-decoration:none;
        color:#000000;
        background:#A7C0EB;
        cursor:hand;
        width:150px;
        height:30px;
        border-right:solid #5C6980;
        border-bottom:solid #5C6980;
        border-left:1 solid #B8D3FF;
        border-top:1 solid #B8D3FF
    }
    .explorer_down{
        font-family:verdana,arial, helvetica;
        font-size:8pt;
        font-weight:normal;
        text-decoration:none;
        color:#000000;
        background:#A7C0EB;
        cursor:hand;
        width:150px; height:30px;
        border-left:1 solid #5C6980;
        border-top:1 solid #5C6980;
        border-right:1 solid #B8D3FF;
        border-bottom:1 solid #B8D3FF
    }
    .explorer_active{
        font-family:verdana,arial,helvetica;
        font-size:8pt;
        font-weight:normal;
        text-decoration:none;
        color:#000000;
        background:#FFFFFF;
        cursor:hand;
        width:150px;
        height:30px
    }
    .menu{
        font-family:verdana,arial,helvetica;
        font-size:8pt;
        font-weight:normal;
        text-decoration:none;
        color:#000000}

</style>
<tr id="containerrow"><td id="leftcolumn" width="160"  valign="top">
        <div id="menueMain">
            <div id="menuHome"class="explorer"
                 onmouseover="this. className='explorer_over';return true"
                 onmouseout="this.className= 'explorer';return true"
                 onmousedown="this.className= 'explorer_down';return true">
                <a href= "#" onClick="navigateTo('')">Home</a>
            </div>
            <div id="menuOrder"class="explorer"
                 onmouseover="this. className='explorer_over';return true"
                 onmouseout="this.className= 'explorer';return true"
                 onmousedown="this.className= 'explorer_down';return true">
                <a href= "#" onClick="navigateTo('cart')">Order</a>
            </div>
            <% if (request.isUserInRole("user")) {%>
            <div id="menuEmail"class="explorer"
                 onmouseover="this. className='explorer_over';return true"
                 onmouseout="this.className= 'explorer';return true"
                 onmousedown="this.className= 'explorer_down';return true">
                <a href= "#" onClick="navigateTo('email/join_email_list.jsp?name=${fn:escapeXml(user.firstName)}')">Join our email list</a>
            </div>
            <%} else {%>
            <div id="menuEmail"class="explorer"
                 onmouseover="this. className='explorer_over';return true"
                 onmouseout="this.className= 'explorer';return true"
                 onmousedown="this.className= 'explorer_down';return true">
                <a href= "#" onClick="navigateTo('email/join_email_list.jsp?name=guest')">Join our email list</a>
            </div>
            <% }%>
            <div id="menuCust"class="explorer"
                 onmouseover="this. className='explorer_over';return true"
                 onmouseout="this.className= 'explorer';return true"
                 onmousedown="this.className= 'explorer_down';return true">
                <a href= "#" onClick="navigateTo('customer_service.jsp')">Contact customer service</a>
            </div>
            <div id="Account"class="explorer"
                 onmouseover="this. className='explorer_over';return true"
                 onmouseout="this.className= 'explorer';return true"
                 onmousedown="this.className= 'explorer_down';return true">
                <a href= "#" onClick="navigateTo('user/account/displayAccount')">Your Account</a>
            </div>
            <div id="menuPartners"class="explorer"
                 onmouseover="this. className='explorer_over';return true"
                 onmouseout="this.className= 'explorer';return true"
                 onmousedown="this.className= 'explorer_down';return true">
                <a href= "#" onClick="navigateTo('partners')">Partners</a>
            </div>
            <div id="menuRelatedDocs"class="explorer"
                 onmouseover="this. className='explorer_over';return true"
                 onmouseout="this.className= 'explorer';return true"
                 onmousedown="this.className= 'explorer_down';return true">
                <a href= "#" onClick="navigateTo('relatedDocs/relatedDocs.jsp')">Related Docs</a>
            </div>
        </div>
        <% if (request.isUserInRole("user")) {%>
        <div id="menueProduct">
            <div id="menuPartners"class="explorer"
                 onmouseover="this. className='explorer_over';return true"
                 onmouseout="this.className= 'explorer';return true"
                 onmousedown="this.className= 'explorer_down';return true">
                <a href="#" onClick="navigateTo('userAccess.jsp')">User Only</a>
            </div>
        </div>
        <% }%>
    </td>