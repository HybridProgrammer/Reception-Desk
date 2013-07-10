<%@ page import="reception.desk.Function" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="mainFAU">
		<g:set var="entityName" value="${message(code: 'function.label', default: 'Function')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
        <g:javascript library="jquery" />
        <r:require module="jquery-ui"/>

        <script type="text/javascript">
            function clickButton(id) {
                var button = $('#'+id)
                button.click();
            }
        </script>

        <style type="text/css">

                /* LIST #3 */
            .list3 {
                width:450px;
            }
            .list3 ul {
                list-style-type: none;
                margin-left: 12px;
                border-radius: 4px;

            }
            .list3 ul li a, .list3 ul li .button {
                display:block;
                color:#3a5a7a;
                font-weight:bold;
                line-height:29px;
                margin-bottom:-1px;
                text-decoration:none;
                width:450px;
                background-color: #fff;
                padding: 10px 30px 10px 15px;
                border: 1px solid #dddddd;
                font-family: "HelveticaNeue","Helvetica Neue",Helvetica,Arial,sans-serif;
                font-size: 14px;
                line-height: 1.428571429;
                text-align: left;
                cursor: hand; cursor: pointer;
            }

            .list3 ul li a:hover, .list3 ul li .button:hover {
                background-color: #f1f5f9;
            }

            .arrow {
                float: right;
                font-size: 25px;
                margin-top: -9px;
                font-style: normal;
                font-family: FontAwesome;
                font-weight: normal;
                font-style: normal;
                text-decoration: inherit;
                -webkit-font-smoothing: antialiased;
            }

            .arrow:before {

            }


        </style>
	</head>
	<body>
		<a href="#list-function" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<div id="list-function" class="content scaffold-list" role="main">
			<h1>How may I help you?</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>

            <div class="list3">
                <ul>
            <g:each in="${functionInstanceList}" status="i" var="functionInstance">
                <li>
                    <a onclick="clickButton(${functionInstance.id})" ><i class="arrow" >&gt;</i>${fieldValue(bean: functionInstance, field: "description")}</a>

                </li>
            </g:each>
            </div>


			<g:form>
                <g:each in="${functionInstanceList}" status="i" var="functionInstance">
                   <g:submitButton style="visibility: hidden" class="button" id="${functionInstance.id}" name="button" value="${fieldValue(bean: functionInstance, field: "description")}" />
                </g:each>
			</g:form>
			<div class="pagination">
				<g:paginate total="${functionInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
