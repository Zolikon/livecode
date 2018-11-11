package com.zolikon.livecode;

import com.zolikon.livecode.model.CodeModel;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Component
public class EditorCache {

    private Map<String, String> map = new HashMap<>();

    public synchronized void put(CodeModel codeModel) throws UnsupportedEncodingException {
        map.put(codeModel.getSessionId(), java.net.URLDecoder.decode(codeModel.getText(), "UTF-8"));
    }

    public synchronized String get(String id) {
        return HtmlUtils.htmlUnescape(map.getOrDefault(id, "You can start coding"));
    }

}
