
<%@ page import="reception.desk.Queue" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="displaySign">
		<g:set var="entityName" value="${message(code: 'queue.label', default: 'Queue')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<g:javascript library="jquery" />
		<r:require module="jquery-ui"/>
		<atmosphere:resources/>
        <script type="text/javascript">
        $(document).ready(function() {
                // jquery.atmosphere.response
                function callback(response) {
                    if (response.status == 200) {
                        var data = response.responseBody;
                        if (data.length > 0) {
                            try {
                                var msgObj = jQuery.parseJSON(data);
                                if (msgObj.id > 0) {
                                    var row = '<tr><td>' + msgObj.id + '</td><td>' + msgObj.body + '</td><td></td></tr>'
                                    $('tbody').append(row);
                                }
                            } catch (e) {
                                // Atmosphere sends commented out data to WebKit based browsers
                            }
                        }
                    }
                }

                var location = 'http://localhost:8080/reception-desk/atmosphere/messages';
                $.atmosphere.subscribe(location, callback, $.atmosphere.request = {transport: 'websocket', fallbackTransport: 'long-polling'});
        });
        </script>
	</head>
	<body>
		<a href="#list-queue" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="list-queue" class="content scaffold-list" role="main">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="callNumber" title="${message(code: 'queue.callNumber.label', default: '#')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'queue.person.name.label', default: 'Name')}" />
						
						<g:sortableColumn property="email" title="${message(code: 'queue.purpose.name.label', default: 'Purpose')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:set var="now" value="${new Date()}" />
				<g:each in="${queueInstanceList}" status="i" var="queueInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<!-- http://user.xmission.com/~goodhill/dates/deltaDates.html -->
					
						<td>${fieldValue(bean: queueInstance, field: "callNumber")}</td>
					
						<td>${fieldValue(bean: queueInstance, field: "person.name")}</td>
						
						<td>${fieldValue(bean: queueInstance, field: "purpose.description")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${queueInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
