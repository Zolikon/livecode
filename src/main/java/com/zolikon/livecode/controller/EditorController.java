package com.zolikon.livecode.controller;

import com.zolikon.livecode.EditorCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EditorController {

    @Autowired
    private EditorCache cache;

    @GetMapping("/livecode/{id}")
    public String handle(Model model, @PathVariable("id") String id) {
        model.addAttribute("id", id);
        model.addAttribute("text", cache.get(id));
        return "editor";
    }

}
