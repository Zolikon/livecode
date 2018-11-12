package com.zolikon.livecode.controller;

import com.zolikon.livecode.EditorCache;
import com.zolikon.livecode.model.CodeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @Autowired
    private EditorCache cache;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/edit")
    public void handleEdit(CodeModel message) throws Exception {
        cache.put(message);
        messagingTemplate.convertAndSend("/topic/edits/" + message.getSessionId(), message);
    }

    @MessageExceptionHandler
    @SendTo("/topic/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }

}