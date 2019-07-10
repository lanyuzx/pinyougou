package cn.lanlan.core.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LLLoginController {

    @RequestMapping("/showName")
    public Map showName() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Map map = new HashMap();
        java.util.Calendar  calendar =java.util.Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String currentTime = simpleDateFormat.format(calendar.getTime());
        map.put("time", currentTime);
        map.put("username", name);
        return map;
    }
}
