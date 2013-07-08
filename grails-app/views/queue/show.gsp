
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
			<ol class="property-list User">
			
				<g:if test="${queueInstance?.person.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="person.name" default="name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${queueInstance.person}" field="name"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${queueInstance?.person.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="person.email" default="email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${queueInstance.person}" field="email"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${queueInstance?.person.zNumber}">
				<li class="fieldcontain">
					<span id="zNumber-label" class="property-label"><g:message code="person.zNumber" default="zNumber" /></span>
					
						<span class="property-value" aria-labelledby="zNumber-label"><g:fieldValue bean="${queueInstance?.person}" field="zNumber"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${queueInstance?.person.majorId}">
				<li class="fieldcontain">
					<span id="majorId-label" class="property-label"><g:message code="person.majorId" default="major" /></span>
					
						<span class="property-value" aria-labelledby="majorId-label"><g:fieldValue bean="${queueInstance.person}" field="majorId"/></span>
					
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
					<g:hiddenField name="id" value="${queueInstance?.id}" />
					<g:link class="edit" controller="Person" action="edit" id="${queueInstance?.person.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:link class="button call" controller="Queue" action="call" id="${queueInstance?.id}"><g:message code="default.button.call.label" default="Call" /></g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
