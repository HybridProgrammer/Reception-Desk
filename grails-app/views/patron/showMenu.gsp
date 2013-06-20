<%@ page import="reception.desk.Function" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'function.label', default: 'Function')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-function" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-function" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:form>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="description" title="${message(code: 'function.description.label', default: 'description')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${functionInstanceList}" status="i" var="functionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:submitButton id="${functionInstance.id}" name="button" value="${fieldValue(bean: functionInstance, field: "description")}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			</g:form>
			<div class="pagination">
				<g:paginate total="${functionInstanceTotal}" />
			</div>
			<g:link event="viewPersons">View Persons</g:link>
		</div>
	</body>
</html>
