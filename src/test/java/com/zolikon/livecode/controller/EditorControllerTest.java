package com.zolikon.livecode.controller;

import com.zolikon.livecode.EditorCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EditorControllerTest {

    private static final String ID = "someId";
    private static final String TEXT = "message";

    @Mock
    private EditorCache cache;

    @Mock
    private Model model;

    @InjectMocks
    public EditorController underTest;

    @Test
    public void testHandle(){
        //GIVEN
        when(cache.get(ID)).thenReturn(TEXT);
        //WHEN
        String result = underTest.handle(model, ID);
        //THEN
        assertThat(result).describedAs("template name").isEqualTo("editor");
        verify(model).addAttribute("id", ID);
        verify(model).addAttribute("text", TEXT);
    }
}