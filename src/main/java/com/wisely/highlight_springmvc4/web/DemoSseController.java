package com.wisely.highlight_springmvc4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 *
 * @author: jhc
 * @create: 2016/12/5
 * @version: 1.0
 */
@Controller
@RequestMapping("demoSse")
public class DemoSseController {

    Integer id = 0;

    @RequestMapping(value = {"push"}, produces = {"text/event-stream;charset=UTF-8"})
    public @ResponseBody String push(HttpServletRequest req) {
        id++;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss SS");
        String currentTime = sdf.format(new Date());
        System.out.println(currentTime);
        String sessionId = req.getSession(false).getId();

//        return "";
        return "data:("+ id +")当前系统时间 " + currentTime + "sessionId "+ sessionId + "\n\n";
    }
}
