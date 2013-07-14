%{--
  - Copyright 2013 Jason Heithoff
  -
  -    Licensed under the Apache License, Version 2.0 (the "License");
  -    you may not use this file except in compliance with the License.
  -    You may obtain a copy of the License at
  -
  -        http://www.apache.org/licenses/LICENSE-2.0
  -
  -    Unless required by applicable law or agreed to in writing, software
  -    distributed under the License is distributed on an "AS IS" BASIS,
  -    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  -    See the License for the specific language governing permissions and
  -    limitations under the License.
  --}%

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