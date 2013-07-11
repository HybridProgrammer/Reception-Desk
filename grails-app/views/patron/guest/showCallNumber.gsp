<%@ page import="reception.desk.Function" %>
<%@ page import="reception.desk.Queue" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="mainFAU">
    <title>Call Number</title>
    <g:javascript library="jquery" />
    <r:require module="jquery-ui"/>

    <script type="text/javascript">

    $(document).ready(function() {
        var redirectDelay = 60000; //1 minute = 60000 milliseconds

        window.setTimeout(function(id) {
            window.location = '${createLink(uri: '/patron/showMenu', absolute: "true")}';
        }, redirectDelay);
    });
    </script>
</head>
<body>
<a href="#list-function" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div id="list-function" class="content scaffold-list" role="main">
    <h1 style="color: #000000">You have been placed in line.</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <div style="height: 150px">
        <h1 style="color: black;">Your number is <strong>${queueInstance.callNumber}</strong>.<br /><br /> Please have a seat and wait for your number to be called.</h1>
    </div>

<g:form>
    <fieldset class="buttons">
        <g:submitButton name="return" value="Start Over" />
    </fieldset>
    </g:form>
</div>
</body>
</html>