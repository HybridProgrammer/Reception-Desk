<%@ page import="reception.desk.Person" %>



<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'name', 'error')} required">
	<label for="username">
		<g:message code="person.name.label" default="name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${personInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="person.email.label" default="email" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="email" required="" value="${personInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'zNumber', 'error')} required">
	<label for="zNumber">
		<g:message code="person.zNumber.label" default="zNumber" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="zNumber" required="" value="${personInstance?.zNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'room', 'error')} required">
	<label for="room">
		<g:message code="person.room.label" default="room" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="room" required="" value="${personInstance?.room}"/>
</div>



