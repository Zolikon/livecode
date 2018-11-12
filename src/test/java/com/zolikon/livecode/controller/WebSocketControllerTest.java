package com.zolikon.livecode.controller;

import com.zolikon.livecode.EditorCache;
import com.zolikon.livecode.model.CodeModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WebSocketControllerTest {

    @InjectMocks
    private WebSocketController underTest;

    @Mock
    private EditorCache cache;

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @Test
    public void testHandleException() {
        //GIVEN
        String exceptionMessage = "testMessage";
        Exception exception = new Exception(exceptionMessage);
        //WHEN
        String result = underTest.handleException(exception);
        //THEN
        assertThat(result).isEqualTo(exceptionMessage);
    }

    @Test
    public void testHandleEdit() throws Exception {
        //GIVEN
        CodeModel codeModel = new CodeModel("sessionId", "text");
        //WHEN
        underTest.handleEdit(codeModel);
        //THEN
        verify(cache).put(codeModel);
        verify(messagingTemplate).convertAndSend("/topic/edits/sessionId", codeModel);
    }


}