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

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title><g:message code="default.restore.label" args="[entityName]" default="Restore" /></title>
</head>
<body>
<a href="#restore-backup" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
        <li><g:link class="all" action="listAllDay"><g:message code="default.all.label" default="Show All"/></g:link></li>
        <li><g:link class="all" action="listAll"><g:message code="default.allall.label" default="Show All"/></g:link></li>
        <li><g:link class="logout" controller="Logout" action="index"><g:message code="default.logout.label" default="Log Out"/></g:link></li>
    </ul>
</div>
<div id="restore-backup" class="content scaffold-edit" role="main">
    <h1><g:message code="default.restore.label" args="[entityName]" default="Restore" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:form method="post" enctype="multipart/form-data" >
        <g:hiddenField name="id" value="${queueInstance?.id}" />
        <g:hiddenField name="version" value="${queueInstance?.version}" />
        <fieldset class="form">
            <ol class="property-list files">

                <li class="fieldcontain">
                    <span id="upload-label" class="property-label"><g:message code="upload" default="File" /></span>

                    <span class="property-value" aria-labelledby="callNumber-label"><input type="file" name="backup" /></span>

                </li>
                </ol>
        </fieldset>
        <fieldset class="buttons">
            <g:actionSubmit class="update" action="executeImport" value="${message(code: 'default.button.upload.label', default: 'Upload')}" />
        </fieldset>
    </g:form>
</div>
</body>
</html>