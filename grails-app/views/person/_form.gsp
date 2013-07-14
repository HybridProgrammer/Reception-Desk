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

<%@ page import="reception.desk.Person" %>
<%@ page import="reception.desk.Major" %>



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

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'majorId', 'error')} ">
	<label for="majorId">
		<g:message code="person.majorId.label" default="major" />
	</label>
	<g:select name="majorId"
          from="${major.toList()}"
          value="${personInstance?.majorId}"
          optionKey="id"
          optionValue="displayName"
          noSelection="['':'-Choose Your Major']"
           />
</div>



