package com.zolikon.livecode.model;

public class CodeModel {

    private String text;
    private String sessionId;

    public CodeModel() {
    }

    public CodeModel(String text, String sessionId) {
        this.text = text;
        this.sessionId = sessionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
