package com.zolikon.livecode.model;

public class CodeModel {

    private String sessionId;
    private String text;

    public CodeModel() {
    }

    public CodeModel(String sessionId, String text) {
        this.sessionId = sessionId;
        this.text = text;
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
