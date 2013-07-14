
<ol class="property-list queue" xmlns="http://www.w3.org/1999/html">

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

<g:if test="${queueInstance?.callNumber}">
        <li class="fieldcontain">
            <span id="callNumber-label" class="property-label"><g:message code="callNumber" default="Call Number" /></span>

            <span class="property-value" aria-labelledby="callNumber-label"><g:fieldValue bean="${queueInstance}" field="callNumber"/></span>

        </li>
    </g:if>

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

    <g:if test="${queueInstance?.purpose}">
        <li class="fieldcontain">
            <span id="purpose-label" class="property-label"><g:message code="purpose.description" default="purpose" /></span>

            <span class="property-value" aria-labelledby="purpose-label"><g:fieldValue bean="${queueInstance?.purpose}" field="description"/></span>

        </li>
    </g:if>
    <g:else>
        <li class="fieldcontain">
            <span id="purpose-label" class="property-label">major</span>

            <span class="property-value" aria-labelledby="purpose-label"></span>

        </li>
    </g:else>

    <g:if test="${queueInstance?.additionalInformation}">
        <li class="fieldcontain">
            <span id="additionalInformation-label" class="property-label"><g:message code="additionalInformation.description" default="Additional Information" /></span>

            <span class="property-value" aria-labelledby="purpose-label"><textarea disabled="true" rows="5" cols="40"/><g:fieldValue bean="${queueInstance}" field="additionalInformation"/></textarea></span>

        </li>
    </g:if>
    <g:else>
        <li class="fieldcontain">
            <span id="purpose-label" class="property-label">Additional Information</span>

            <span class="property-value" aria-labelledby="purpose-label"></span>

        </li>
    </g:else>


</ol>