package com.wisely.highlight_springmvc4.web.demoAsyncTask;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

/**
 * Description:
 *
 * @author: jhc
 * @create: 2016/12/9
 * @version: 1.0
 */
@Controller
@RequestMapping(value = "notify")
public class NotifyController {

    @RequestMapping(value = "push")
    @ResponseBody
    public String push(HttpServletRequest req) {
        String sessionId = req.getSession(false).getId();
        Calendar calendar = Calendar.getInstance();
        String timeStamp = calendar.get(Calendar.YEAR) + "/"
                + calendar.get(Calendar.MONTH) + "/"
                + calendar.get(Calendar.DAY_OF_MONTH) + " "
                + calendar.get(Calendar.HOUR_OF_DAY) + ":"
                + calendar.get(Calendar.MINUTE) + ":"
                + calendar.get(Calendar.SECOND) + ":";
        String msg = timeStamp + "-" + sessionId;

        AsyncTokenContext.getInstance().notifyAllMembers(msg);

        return msg;
    }
}
