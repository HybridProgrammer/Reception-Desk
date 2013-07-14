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

<%@ page import="reception.desk.Queue" %>
<%@ page import="reception.desk.Person" %>
<%@ page import="reception.desk.Major" %>
<%@ page import="reception.desk.Function" %>

<ol class="property-list queue">

<g:if test="${queueInstance?.callNumber}">
    <li class="fieldcontain">
        <span id="callNumber-label" class="property-label"><g:message code="callNumber" default="Call Number" /></span>

        <span class="property-value" aria-labelledby="callNumber-label"><g:fieldValue bean="${queueInstance}" field="callNumber"/></span>

    </li>
</g:if>

</ol>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'name', 'error')} required">
    <label for="username">
        <g:message code="person.name.label" default="name" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="name" required="" value="${personInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'email', 'error')}">
    <label for="email">
        <g:message code="person.email.label" default="email" />
    </label>
    <g:textField name="email" value="${personInstance?.email}"/>
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

<div class="fieldcontain ${hasErrors(bean: queueInstance, field: 'purposeId', 'error')} ">
    <label for="purpose">
        <g:message code="purpose.label" default="purpose" />
    </label>
    <g:select name="purposeId"
              from="${purpose.toList()}"
              value="${queueInstance?.purposeId}"
              optionKey="id"
              optionValue="description"
              noSelection="['':'-Choose Purpose']"
    />
</div>

<div class="fieldcontain ${hasErrors(bean: queueInstance, field: 'additionalInformation', 'error')} ">
    <label for="additionalInformaiton">
        <g:message code="additionalInformation.label" default="Additional Information" />
    </label>
    <g:textArea name="additionalInformation" value="${queueInstance?.additionalInformation}" rows="5" cols="40"/>
</div>

