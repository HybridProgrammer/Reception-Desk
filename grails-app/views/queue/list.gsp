
<%@ page import="reception.desk.Queue" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'queue.label', default: 'Queue')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>

        <g:javascript library="jquery" />
        <r:require module="jquery-ui"/>
        <atmosphere:resources/>

        <script type="text/javascript">

            $(document).ready(function() {
                var refreshDelay = $('#refresh-time').val();

                var refresh = {};
                setRefreshTimer(refreshDelay);

                $('#refresh-time').live('change',function(){
                    var select = $(this);
                    var newVal = select.val();

                    window.clearTimeout(refresh);
                    setRefreshTimer(newVal);

                    $.ajax({
                        type: "POST",
                        url: "${createLink(uri: '/user/updateRefreshRate')}",
                        data: { refreshRate: newVal }
                    }).done(function( msg ) {
                                console.log("Data Saved: " + msg );
                        });
                });

                function setRefreshTimer(delay) {
                    refresh = window.setTimeout(function() {
                        window.location.reload(true);
                    }, delay);

                }
            });
        </script>
    </head>
	<body>
		<a href="#list-queue" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li>
                    <select id='refresh-time'>
                        <g:if test="${userInstance.refreshRate == 120000}">
                            <option value='120000' selected="selected" >Refresh every 2 minutes</option>
                        </g:if>
                        <g:else>
                            <option value='120000'>Refresh every 2 minutes</option>
                        </g:else>

                        <g:if test="${userInstance.refreshRate == 300000}">
                            <option value='300000' selected="selected">Refresh every 5 minutes</option>
                        </g:if>
                        <g:else>
                            <option value='300000'>Refresh every 5 minutes</option>
                        </g:else>


                    </select>
                </li>
			</ul>
		</div>
		<div id="list-queue" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="callNumber" title="${message(code: 'queue.action.label', default: 'Action')}" />
					
						<g:sortableColumn property="callNumber" title="${message(code: 'queue.callNumber.label', default: '#')}" />
											
						<g:sortableColumn property="waitTime" title="${message(code: 'queue.dateCreated.label', default: 'Wait Time')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'queue.person.name.label', default: 'Name')}" />
						
						<g:sortableColumn property="purpose" title="${message(code: 'queue.purpose.name.label', default: 'Purpose')}" />
						
						<g:sortableColumn property="workingWith" title="${message(code: 'queue.dateCreated.label', default: 'Working With')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:set var="now" value="${new Date()}" />
				<g:each in="${queueInstanceList}" status="i" var="queueInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<!-- http://user.xmission.com/~goodhill/dates/deltaDates.html -->

						<td>
                        <g:if test='${queueInstance.inLine} == true' ><g:link class="call" action="call" id="${queueInstance.id}"><g:message code="default.button.call.label" default="Call" /></g:link> | </g:if>
                        <g:else></g:else>
                        <g:link class="view" action="show" id="${queueInstance.id}"><g:message code="default.button.view.label" default="View" /></g:link>
                    </td>
						
						<td>${fieldValue(bean: queueInstance, field: "callNumber")}</td>
					
						<td><g:waitTime queueInstance="${queueInstance}"></g:waitTime></td>
					
						<td>${fieldValue(bean: queueInstance, field: "person.name")}</td>
						
						<td>${fieldValue(bean: queueInstance, field: "purpose.description")}</td>

                        <td><g:workingWith queueInstance="${queueInstance}"></g:workingWith></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${queueInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
