var stompClient = null;
var socket = null;

sendEdit = function(e){
        text = document.getElementById("editor");
        sessionId = document.getElementById("sessionId");
        stompClient.send("/editor/edit", {}, JSON.stringify({'text': encodeURI(text.value),'sessionId': encodeURI(sessionId.innerHTML)}));
    }

function connect() {
	socket = new SockJS('/livecode');
	
    stompClient = Stomp.over(socket);
    
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        
        stompClient.subscribe("/topic/edits", function (message) {
            body = JSON.parse(message.body);
        	showEdit(body);
        });

    });

}

function showEdit(message) {
    sessionId = document.getElementById("sessionId").innerHTML;
    if(sessionId === message.sessionId){
        t = document.getElementById("editor");
        t.value = decodeURI(message.text);
    }
}

$(function () {

    send = function(e){
        text = document.getElementById("editor");
        sessionId = document.getElementById("sessionId");
        stompClient.send("/editor/edit", {}, JSON.stringify({'text': encodeURI(text.value),'sessionId': encodeURI(sessionId.innerHTML)}));
    }
    $("#editor").keyup(send);

});

$(document).delegate('#editor', 'keydown', function(e) {
  var keyCode = e.keyCode || e.which;
  var tab = "    ";
  if (keyCode == 9) {
    e.preventDefault();
    var start = this.selectionStart;
    var end = this.selectionEnd;

    // set textarea value to: text before caret + tab + text after caret
    $(this).val($(this).val().substring(0, start)
                + tab
                + $(this).val().substring(end));

    // put caret at right position again
    this.selectionStart = this.selectionEnd = start + tab.length;
  }
});



connect();

