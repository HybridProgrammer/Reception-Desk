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

<%@ page import="reception.desk.Function" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="mainFAU">
    <g:set var="entityName" value="${message(code: 'function.label', default: 'Function')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
    <g:javascript library="jquery" />
    <r:require module="jquery-ui"/>
    <script src="${resource(dir: 'js', file: 'bootstrap.js')}"></script>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'prettyPhoto.css')}" type="text/css" media="screen" title="prettyPhoto main stylesheet" charset="utf-8" >
    <script src="${resource(dir: 'js', file: 'jquery.prettyPhoto.js')}" type="text/javascript" charset="utf-8"></script>

    <script>
        $(document).ready(function() {
            var toolTipOptions = {animation: true, placement: 'right'};

            $('#znumber').tooltip(toolTipOptions);

            $("a[rel^='prettyPhoto']").prettyPhoto();
        });
    </script>

    <style>
        .input-prepend input {
            width: 180px;
        }
        .tooltip-inner {
            max-width: 350px;
        }
    </style>
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
                <g:textField name="name" value="${myValue}" />
            </div>

            <!-- need to include help text issue #2 -->
            <div class="fieldcontain required">
                <label>Z Number: (<a href="https://banner.fau.edu/FAUPdad/lwgkznum.P_DisplayID&iframe=true&width=100%&height=100%" rel="prettyPhoto[iframes]">Lookup</a>) <span class="required-indicator">*</span></label>
            <div class="input-prepend">
                <span class="add-on">Z</span>
                <g:textField id="znumber" name="zNumber" value="${myValue}" title="Click Lookup to lookup your Z# if you don't know it." />
                    </div>
            </div>

            <div class="fieldcontain required">
                <label>Email (FAU): <span class="required-indicator">*</span></label>
                <div class="input-prepend">
                    <span class="add-on"><i class="icon-envelope"></i> </span>
                    <g:textField name="email" value="${myValue}" />
                </div>
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

            <div class="fieldcontain">
                <label>Additional Information: </label>
                <g:textArea name="additionalInformation" value="${myValue}" rows="5" cols="40"/>
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

