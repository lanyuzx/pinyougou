package cn.lanlan.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LLIndexController {

    @RequestMapping("/index")
    public String showIndex() {
        return "index";
    }

}





