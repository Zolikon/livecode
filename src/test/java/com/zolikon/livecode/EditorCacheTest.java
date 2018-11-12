package com.zolikon.livecode;

import com.zolikon.livecode.model.CodeModel;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;

public class EditorCacheTest {

    private static final String RAW_TEXT = "some text";
    private static final String HTTP_ENCODED_TEXT = "some%20text";
    private static final String DEFAULT_TEXT = "You can start coding";
    private static final String SESSION_ID = "sessionId";

    private EditorCache underTest = new EditorCache();

    @Test
    public void testPut() throws UnsupportedEncodingException {
        //GIVEN
        CodeModel codeModel = new CodeModel(SESSION_ID, HTTP_ENCODED_TEXT);
        //WHEN
        underTest.put(codeModel);
        String result = underTest.get(SESSION_ID);
        //THEN
        assertThat(result).describedAs("decoded text").isEqualTo(RAW_TEXT);
    }

    @Test
    public void testGet_ForNonExistingId() {
        //WHEN
        String result = underTest.get(SESSION_ID);
        //THEN
        assertThat(result).describedAs("default text").isEqualTo(DEFAULT_TEXT);
    }

}