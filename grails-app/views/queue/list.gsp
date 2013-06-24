
<%@ page import="reception.desk.Queue" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'queue.label', default: 'Queue')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-queue" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-queue" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="callNumber" title="${message(code: 'queue.action.label', default: 'Action')}" />
					
						<g:sortableColumn property="callNumber" title="${message(code: 'queue.callNumber.label', default: '#')}" />
											
						<g:sortableColumn property="name" title="${message(code: 'queue.dateCreated.label', default: 'Wait Time')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'queue.person.name.label', default: 'Name')}" />
						
						<g:sortableColumn property="email" title="${message(code: 'queue.purpose.name.label', default: 'Purpose')}" />
						
						<g:sortableColumn property="email" title="${message(code: 'queue.dateCreated.label', default: 'Date Created')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:set var="now" value="${new Date()}" />
				<g:each in="${queueInstanceList}" status="i" var="queueInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<!-- http://user.xmission.com/~goodhill/dates/deltaDates.html -->
					
						<td><g:link class="call" action="call" id="${queueInstance.id}"><g:message code="default.button.call.label" default="Call" /></g:link></td>
						
						<td>${fieldValue(bean: queueInstance, field: "callNumber")}</td>
					
						<td><g:waitTime queueInstance="${queueInstance}"></g:waitTime></td>
					
						<td>${fieldValue(bean: queueInstance, field: "person.name")}</td>
						
						<td>${fieldValue(bean: queueInstance, field: "purpose.description")}</td>
						
						<td>${fieldValue(bean: queueInstance, field: "dateCreated")}</td>
					
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
