<%@ page import="reception.desk.Function" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="mainFAU">
    <g:set var="entityName" value="${message(code: 'function.label', default: 'Function')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
    <g:javascript library="jquery" />
    <r:require module="jquery-ui"/>
</head>
<body>
<a href="#list-function" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div id="list-function" class="content scaffold-list" role="main">
    <h1>Please fill in the following information:</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <g:form>
        <fieldset class="form">
            <div class="fieldcontain required">
                <label>Name:  <span class="required-indicator">*</span></label>
                <g:textField name="name" value="${myValue}" /></label>
            </div>

            <div class="fieldcontain required">
                <label>Z Number: <span class="required-indicator">*</span></label>
                <g:textField name="zNumber" value="${myValue}" /></label>
            </div>

            <div class="fieldcontain required">
                <label><span class="required-indicator">*</span></label>
                <g:select name="majorId"
                          from="${major.toList()}"
                          optionKey="id"
                          optionValue="displayName"
                          noSelection="['':'-Choose Your Major']"
                />
            </div>

        </fieldset>
		<fieldset class="buttons">
            <g:submitButton name="save" value="Save" />
            <g:submitButton name="cancel" value="Cancel" />
        </fieldset>
    </g:form>
    </div>
</div>
</body>
</html>

