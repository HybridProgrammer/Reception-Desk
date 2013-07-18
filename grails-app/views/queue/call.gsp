
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
                <li><g:link class="all" action="listAllDay"><g:message code="default.all.label" default="Show All"/></g:link></li>
                <li><g:link class="all" action="listAll"><g:message code="default.allall.label" default="Show All"/></g:link></li>
                <li><g:link class="logout" controller="Logout" action="index"><g:message code="default.logout.label" default="Log Out"/></g:link></li>
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
                    <g:if test="${queueInstance?.isInLine == false}">
                        <g:link class="inLine button" controller="Queue" action="inLine" id="${queueInstance?.id}"><g:message code="default.button.inLine.label" default="Place In Line" /></g:link>
                    </g:if>
                    <g:else>
                        <g:link class="button call" controller="Queue" action="call" id="${queueInstance?.id}"><g:message code="default.button.call.label" default="Call" /></g:link>
                    </g:else>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
