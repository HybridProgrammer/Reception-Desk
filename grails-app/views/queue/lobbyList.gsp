
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
		<meta name="layout" content="displaySign">
		<g:set var="entityName" value="${message(code: 'queue.label', default: 'Queue')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<g:javascript library="jquery" />
		<r:require module="jquery-ui"/>
		<atmosphere:resources/>

        <script type="text/javascript" src="${resource(dir: 'js/noty', file: 'jquery.noty.js')}"></script>
        <script type="text/javascript" src="${resource(dir: 'js/noty/themes', file: 'default.js')}"></script>
        <script type="text/javascript" src="${resource(dir: 'js/noty/layouts', file: 'top.js')}"></script>
        <script type="text/javascript" src="${resource(dir: 'js/noty/layouts', file: 'topLeft.js')}"></script>
        <script type="text/javascript" src="${resource(dir: 'js/noty/layouts', file: 'topRight.js')}"></script>
        <script type="text/javascript" src="${resource(dir: 'js/noty/layouts', file: 'topCenter.js')}"></script>
        <script type="text/javascript" src="${resource(dir: 'js/noty/layouts', file: 'center.js')}"></script>
        <script type="text/javascript" src="${resource(dir: 'js/noty/layouts', file: 'inline.js')}"></script>

        <script type="text/javascript">
        var socket = {}
        var nMessages = 0; //When this equals 700 we refresh the webpage
        var maxMessages = 700;
        var isRefreshing = false;
        var timeDelay = 180000; //3 minutes = 180000 milliseconds
        var msgDelay = 60000; //1 minute = 60000 milliseconds
        var textToSpeechDelay = 2000;
        var removePatronTimerArr = new Array(); //Contains a list of all listed items that are scheduled to be removed
        var removePatronMsgTimerArr = new Array(); //Contains a list of all messages that are scheduled to be removed
        var notyMsgArr = new Array(); //Contains a list of all messages that are scheduled to be removed

        $(document).ready(function() {

                // jquery.atmosphere.response
                function callback(response) {
                    if (response.status == 200) {
                        console.log(response.responseBody);
                        var data = response.responseBody;
                        //data = data.substr(data.indexOf('<script>'), data.length-data.indexOf('<script>')) /* strip comment buffer from Atmosphere */
                        //data = data.substr(data.indexOf('<script>parent.callback(\'')+'<script>parent.callback(\''.length, data.length-(data.indexOf('<script>')+'<script>parent.callback(\''.length))
                        //data = data.substr(0, data.length-(data.indexOf('<\script>')+13))
                        if (data.length > 0) {
                            try {
                                var msgObj = jQuery.parseJSON(data);

                                //switch message type (msgObj.type)
                                switch(msgObj.type)     {
                                    case 'msg.call':
                                            callPatron(msgObj);
                                            window.setTimeout(callTextToSpeech, textToSpeechDelay, msgObj);
                                        break;
                                    case 'msg.enqueue':
                                        enqueuePatron(msgObj);
                                        break;
                                    case 'msg.inLine':
                                        inLinePatron(msgObj);
                                        window.setTimeout(inLineTextToSpeech, textToSpeechDelay, msgObj);
                                        break;
                                }
                            } catch (e) {
                                // Atmosphere sends commented out data to WebKit based browsers
                                console.log(e);
                            }
                            fixRowHighlighting()
                            nMessages = nMessages + 1;
                            if(nMessages > maxMessages && !isRefreshing)   {
                                window.location.reload(true);
                                isRefreshing = true;
                            }
                            socket.closeSuspendedConnection();
                            //socket.unsubscribe();
                            //socket.subscribe(location, callback, $.atmosphere.request = {transport: 'websocket', fallbackTransport: 'websocket'});
                            //$.atmosphere.subscribe(location, callback, $.atmosphere.request = {transport: 'websocket', fallbackTransport: 'long-polling'});
                        }
                    }
                }


            function fixRowHighlighting() {
                $('tbody tr').each(function(index){
                    var rowHighlight =  (index % 2) == 0 ? 'even'  : 'odd';
                    $(this).removeClass().addClass(rowHighlight);
                });
            }

            function callPatron(msgObj) {
                try {
                    var queueInstance = jQuery.parseJSON(msgObj.queue)
                    var personInstance = jQuery.parseJSON(msgObj.person)
                    var purposeInstance = jQuery.parseJSON(msgObj.purpose)
                    if (msgObj.id > 0) {
                        var person = queueInstance.person
                        var rowHighlight =  ($('tbody tr').size() % 2) == 0 ? 'even'  : 'odd';
                        var row = '<td>' + queueInstance.callNumber + '</td><td>' + personInstance.name + '</td><td>' + purposeInstance.description + '</td><td>' + queueInstance.goToRoom + '</td>'

                        updatePatronListItem(queueInstance.id, row);
                        removePatronListItem(queueInstance.id)

                        var msg = noty({
                            text: '#' + queueInstance.callNumber + ' ' + personInstance.name + ' Go To ' + queueInstance.goToRoom,
                            layout: 'topCenter'
                        });

                        notyMsgArr[queueInstance.id] = msg.options.id

                        removePatronMsgTimerArr[queueInstance.id] = window.setTimeout(function(id) {
                            $.noty.close(id);
                        }, msgDelay, msg.options.id);

                        audioElement.load();
                        audioElement.play();


                        console.log(row);
                    }
                }
                catch (e) {
                    console.log(e);
                }

            }

            function inLinePatron(msgObj) {
                try {
                    var queueInstance = jQuery.parseJSON(msgObj.queue)
                    var personInstance = jQuery.parseJSON(msgObj.person)
                    var purposeInstance = jQuery.parseJSON(msgObj.purpose)
                    if (msgObj.id > 0) {
                        var person = queueInstance.person
                        var rowHighlight =  ($('tbody tr').size() % 2) == 0 ? 'even'  : 'odd';
                        var row = '<td>' + queueInstance.callNumber + '</td><td>' + personInstance.name + '</td><td>' + purposeInstance.description + '</td><td></td>'

                        $.noty.close(notyMsgArr[queueInstance.id])

                        //Check if row still exists
                        var rowItem = $('tbody tr[name="' + queueInstance.id  + '"]')
                        if(rowItem.length > 0) {
                            rowItem.html(row)
                        }
                        else { //Row doesn't exist. We need to now reinsert it into the proper location
                            row = '<tr name="' + queueInstance.id + '">' + row + '</tr>';
                            if($('tbody tr')[0] == undefined ) {     //check to see if we have any elements in the table.
                                $('tbody').append(row);
                            }
                            else {
                                $('tbody tr').each(function(index){
                                    if(parseInt($(this).attr("name")) > queueInstance.id) {  //We should insert above this row
                                        var parent = $(this).before(row);
                                        return false;
                                    }
                                });
                            }

                        }

                        window.clearTimeout(removePatronTimerArr[queueInstance.id])
                        window.clearTimeout(removePatronMsgTimerArr[queueInstance.id])

                        audioElement.load();
                        audioElement.play();


                        console.log(row);
                    }
                }
                catch (e) {
                    console.log(e);
                }

            }

            function callTextToSpeech(msgObj) {
                try {
                    var queueInstance = jQuery.parseJSON(msgObj.queue)
                    var personInstance = jQuery.parseJSON(msgObj.person)
                    var purposeInstance = jQuery.parseJSON(msgObj.purpose)
                    if (msgObj.id > 0) {
                        var person = queueInstance.person
                        var rowHighlight =  ($('tbody tr').size() % 2) == 0 ? 'even'  : 'odd';
                        var row = '<td>' + queueInstance.callNumber + '</td><td>' + personInstance.name + '</td><td>' + purposeInstance.description + '</td><td>' + queueInstance.goToRoom + '</td>'

                        //audioElement.load();
                        //audioElement.play();

                        //Call Student
                        //var googleTextToSpeechURL = 'http://translate.google.com/translate_tts?ie=UTF-8&q=Student%20number%20' + queueInstance.callNumber + '%20please%20proceed%20to%20room%20' + queueInstance.goToRoom + '&tl=en';
                        //var googleTextToSpeechURL = 'http://translate.google.com/translate_tts?ie=UTF-8&q=Student%20number%2015%20please%20proceed%20to%20room%20102A&tl=en';
                        var text = 'Student number ' + queueInstance.callNumber + ' please proceed to ' + queueInstance.goToRoom
                        var textToSpeechURL = 'http://tts-api.com/tts.mp3?q=' + encodeURI(text);
                        var audioElement2 = document.createElement('audio');
                        audioElement2.setAttribute('src', textToSpeechURL);
                        audioElement2.setAttribute('autoplay', 'autoplay');
                        audioElement2.load()
                        //$.get();
                        //audioElement2.addEventListener("load", function() {
                        //    audioElement2.play();
                        //}, true);

                        //audioElement2.play();
                        console.log(row);
                    }
                }
                catch (e) {
                    console.log(e);
                }

            }

            function inLineTextToSpeech(msgObj) {
                try {
                    var queueInstance = jQuery.parseJSON(msgObj.queue)
                    var personInstance = jQuery.parseJSON(msgObj.person)
                    var purposeInstance = jQuery.parseJSON(msgObj.purpose)
                    if (msgObj.id > 0) {
                        var person = queueInstance.person
                        var rowHighlight =  ($('tbody tr').size() % 2) == 0 ? 'even'  : 'odd';
                        var row = '<td>' + queueInstance.callNumber + '</td><td>' + personInstance.name + '</td><td>' + purposeInstance.description + '</td><td>' + queueInstance.goToRoom + '</td>';

                        var text = 'Student number ' + queueInstance.callNumber + ' please return to the waiting room.';
                        var textToSpeechURL = 'http://tts-api.com/tts.mp3?q=' + encodeURI(text);
                        var audioElement2 = document.createElement('audio');
                        audioElement2.setAttribute('src', textToSpeechURL);
                        audioElement2.setAttribute('autoplay', 'autoplay');
                        audioElement2.load()

                        console.log(row);
                    }
                }
                catch (e) {
                    console.log(e);
                }

            }

            function removePatronListItem(id) {
                        console.log($('tbody tr[name='+id+']').html());
                removePatronTimerArr[id] = window.setTimeout(function(id) {
                    $('tbody tr[name='+id+']').fadeOut(2000, function () {
                        $(this).remove();
                    });
                }, timeDelay, id);
                //$('tbody tr[name='+id+']').slideUp(300).delay(timeDelay).fadeOut(300).remove();


            }

            function updatePatronListItem(id, html) {
                console.log($('tbody tr[name='+id+']').html());
                $('tbody tr[name='+id+']').html(html);


            }

            function enqueuePatron(msgObj) {
                try {
                    var queueInstance = jQuery.parseJSON(msgObj.queue)
                    var personInstance = jQuery.parseJSON(msgObj.person)
                    var purposeInstance = jQuery.parseJSON(msgObj.purpose)
                    if (msgObj.id > 0) {
                        //var row = '<tr><td>' + msgObj.id + '</td><td>' + msgObj.body + '</td><td></td></tr>'
                        var person = queueInstance.person
                        var rowHighlight =  ($('tbody tr').last().hasClass('even') == true ? 'odd'  : 'even');
                        var row = '<tr class="' + rowHighlight + '" name="' + queueInstance.id + '"><td>' + queueInstance.callNumber + '</td><td>' + personInstance.name + '</td><td>' + purposeInstance.description + '</td><td></td></tr>'
                        $('tbody').append(row);

                        console.log(row);
                    }
                }
                catch (e) {
                    console.log(e);
                }

            }

            function soundAlert() {

            }

                var location = '${createLink(uri: '/atmosphere/messages', absolute: "true")}';   //'http://localhost:8080/reception-desk/atmosphere/messages';
                socket = $.atmosphere;
                //$.atmosphere.subscribe(location, callback, $.atmosphere.request = {transport: 'websocket', fallbackTransport: 'long-polling'});
                socket.subscribe(location, callback, $.atmosphere.request = {transport: 'websocket', fallbackTransport: 'websocket', maxRequest: 750});
            //var socket = $.atmosphere;
            //var request = { url: location,
            //                contentType : "application/json",
            //                             logLevel : 'debug',
            //                             transport : 'websocket' ,
            //                             fallbackTransport: 'long-polling'};
            //request.onMessage = callback;

            //var subSocket = socket.subscribe(request);
            //subSocket.onMessage(caller);


            var audioElement = document.createElement('audio');
            audioElement.setAttribute('src', '${resource(dir: 'audio', file: 'chime.mp3')}');
            //audioElement.setAttribute('autoplay', 'autoplay');
            //audioElement.load()
            $.get();
            audioElement.addEventListener("load", function() {
                audioElement.play();
            }, true);


            //Generated Javascript Code
            var calledPatron = {};
            <g:each in="${queueInstanceList}" status="i" var="queueInstance">
                <g:if test="${queueInstance.timeCalled != null}">
                    calledPatron = {};
                    calledPatron.queue = '${queueInstance as grails.converters.JSON}';
                    calledPatron.person = '<g:getPersonJSON queueInstance="${queueInstance}"></g:getPersonJSON>';
                    calledPatron.purpose = '<g:getPurposeJSON queueInstance="${queueInstance}"></g:getPurposeJSON>';
                    calledPatron.id = 1;

                    callPatron(calledPatron);
                </g:if>
            </g:each>



            var j;
            $(document).mousemove(function() {
                if (!justHidden) {
                    console.log('move');
                    clearTimeout(j);
                    $('html').css({cursor: 'default'});
                    j = setTimeout('hide();', 1000);
                }
            });

            //On first load we want to hide it
            $('html').css({cursor: 'none'});


        });

        var justHidden = false;

        function hide() {
            $('html').css({cursor: 'none'});
            justHidden = true;
            setTimeout(function() {
                justHidden = false;
            }, 500);
        }
        </script>
	</head>
	<body>
		<a href="#list-queue" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="list-queue" class="content scaffold-list" role="main">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="callNumber" title="${message(code: 'queue.callNumber.label', default: '#')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'queue.person.name.label', default: 'Name')}" />
						
						<g:sortableColumn property="email" title="${message(code: 'queue.purpose.name.label', default: 'Purpose')}" />

                        <g:sortableColumn property="goToRoom" title="${message(code: 'queue.goToRoom.name.label', default: 'Go To Room')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:set var="now" value="${new Date()}" />
				<g:each in="${queueInstanceList}" status="i" var="queueInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}" name="${fieldValue(bean: queueInstance, field: "id")}">
					<!-- http://user.xmission.com/~goodhill/dates/deltaDates.html -->
					
						<td>${fieldValue(bean: queueInstance, field: "callNumber")}</td>
					
						<td>${fieldValue(bean: queueInstance, field: "person.name")}</td>
						
						<td>${fieldValue(bean: queueInstance, field: "purpose.description")}</td>

                        <td>${fieldValue(bean: queueInstance, field: "goToRoom")}</td>
					
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
