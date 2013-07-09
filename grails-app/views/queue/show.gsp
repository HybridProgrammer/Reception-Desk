
<%@ page import="reception.desk.Queue" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'queue.label', default: 'Queue')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-queue" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-queue" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
            <g:render template="showProperties"/>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${queueInstance?.id}" />
					<g:link class="edit" controller="Queue" action="edit" id="${queueInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:link class="button call" controller="Queue" action="call" id="${queueInstance?.id}"><g:message code="default.button.call.label" default="Call" /></g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
