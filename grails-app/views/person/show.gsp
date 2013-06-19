
<%@ page import="reception.desk.Person" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'Person')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-user" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list user">
			
				<g:if test="${personInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="person.name" default="name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${personInstance}" field="name"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${personInstance?.name}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="person.email" default="email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${personInstance}" field="email"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${personInstance?.zNumber}">
				<li class="fieldcontain">
					<span id="zNumber-label" class="property-label"><g:message code="person.zNumber" default="zNumber" /></span>
					
						<span class="property-value" aria-labelledby="zNumber-label"><g:fieldValue bean="${personInstance}" field="zNumber"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${personInstance?.room}">
				<li class="fieldcontain">
					<span id="room-label" class="property-label"><g:message code="person.room" default="room" /></span>
					
						<span class="property-value" aria-labelledby="room-label"><g:fieldValue bean="${personInstance}" field="room"/></span>
					
				</li>
				</g:if>
				<g:else>
				<li class="fieldcontain">
					<span id="room-label" class="property-label">room</span>
					
						<span class="property-value" aria-labelledby="room-label"></span>
					
				</li>
				</g:else>
				
				<g:if test="${personInstance?.majorId}">
				<li class="fieldcontain">
					<span id="majorId-label" class="property-label"><g:message code="person.majorId" default="major" /></span>
					
						<span class="property-value" aria-labelledby="majorId-label"><g:fieldValue bean="${major}" field="displayName"/></span>
					
				</li>
				</g:if>
				<g:else>
				<li class="fieldcontain">
					<span id="majorId-label" class="property-label">major</span>
					
						<span class="property-value" aria-labelledby="major-label"></span>
					
				</li>
				</g:else>
			
				
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${personInstance?.id}" />
					<g:link class="edit" action="edit" id="${personInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
