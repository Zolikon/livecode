var stompClient = null;
var socket = null;

const TAB_KEY_CODE = 9;

function getSessionId(){
    return document.getElementById("sessionId").innerHTML;
}

function connect() {
	socket = new SockJS('/livecode');
	
    stompClient = Stomp.over(socket);
    
    stompClient.connect({}, function (frame) {
        stompClient.subscribe("/topic/edits/" + getSessionId(), function (message) {
            body = JSON.parse(message.body);
        	showEdit(body);
        });

    });
}

function showEdit(message) {
    editor = document.getElementById("editor");;
    startTemp = editor.selectionStart;
    endTemp = editor.selectionEnd;
    editor.value = decodeURI(message.text);
    editor.selectionStart = startTemp;
    editor.selectionEnd = endTemp;
}

$(function () {

    send = function(e){
        text = document.getElementById("editor").value;
        stompClient.send("/editor/edit", {}, JSON.stringify({'text': encodeURI(text),'sessionId': encodeURI(getSessionId())}));
    }
    $("#editor").keyup(send);

});

$(document).delegate('#editor', 'keydown', function(e) {
  var keyCode = e.keyCode || e.which;
  if (keyCode == TAB_KEY_CODE) {
    var tab = "    ";
    e.preventDefault();
    var start = this.selectionStart;
    var end = this.selectionEnd;

    $(this).val($(this).val().substring(0, start)
                + tab
                + $(this).val().substring(end));

    this.selectionStart = this.selectionEnd = start + tab.length;
  }
});



connect();

