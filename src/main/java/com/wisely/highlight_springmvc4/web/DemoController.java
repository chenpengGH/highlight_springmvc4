package com.wisely.highlight_springmvc4.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author: jhc
 * @create: 2016/12/5
 * @version: 1.0
 */
@Controller
@RequestMapping("demo")
public class DemoController {

    @ModelAttribute
    public void addAttributes(Model model, String msg) {
        model.addAttribute("msg", "[" + msg + "]"); //3
    }

    @ExceptionHandler(value = Exception.class)
    public @ResponseBody Object ExceptionHandler(Exception exception, WebRequest req) {
        Map<String, Object> result = new HashMap<>();
        String type = exception.toString();
        System.out.println(type);
        StackTraceElement[] trace = exception.getStackTrace();
        for(StackTraceElement traceElement: trace) {
            System.out.println("\tat " + traceElement);
        }
        result.put("exceptionType", type);
        return result;
    }

    @RequestMapping(value = "m1")
    public @ResponseBody String m1(@ModelAttribute("msg") String msg, HttpServletRequest req, HttpServletResponse resp, Model model) {
        System.out.println(msg);
        Long l = System.currentTimeMillis();
        return l + ":" + msg;
    }

    @RequestMapping(value = "m3")
    public @ResponseBody Object m3(Long id, String msg) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(id + "," + msg);
        map.put("id", id);
        map.put("msg", msg);

        return map;
    }

    @RequestMapping(value = "m4", produces = {"application/json;charset=UTF-8"})
    public @ResponseBody Object m4(Long id, String msg) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(id + "," + msg);
        map.put("id", id);
        map.put("msg", msg);

        return map;
    }

    @RequestMapping(value = "m5", produces = {"application/xml;charset=UTF-8"})
    public @ResponseBody Object m5(Long id, String msg) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(id + "," + msg);
        map.put("id", id);
        map.put("msg", msg);

        return map;
    }

    @RequestMapping(value = "m6")
    public @ResponseBody Object m6(Integer a, Integer b) throws Exception {
        Integer c = null;
        try {
            c = a/b;
        } catch (Exception e) {
            System.out.println("异常:");
            throw new Exception(e);
        }
        System.out.println(c);
        return c;
    }
}
