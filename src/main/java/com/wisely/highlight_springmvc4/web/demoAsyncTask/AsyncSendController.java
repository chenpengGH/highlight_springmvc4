package com.wisely.highlight_springmvc4.web.demoAsyncTask;

import com.wisely.highlight_springmvc4.web.ch4_5.AysncController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 *  服务端推送事件control demo，异步方式
 * @author: jhc
 * @create: 2016/12/9
 * @version: 1.0
 */
@Controller
@RequestMapping(value = "asyncSend")
public class AsyncSendController {

    @RequestMapping(value = "defer", produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public DeferredResult<String> defer(HttpServletRequest req) {
        final DeferredResult<String> result = new DeferredResult<String>(1000 * 60 * 5);

        System.out.println("defer,添加成員" + result);
        AsyncTokenContext.getInstance().addMember(result);
        result.onCompletion(new Runnable() {
            @Override
            public void run() {
                System.out.println("defer completion,移除成員" + result);
                AsyncTokenContext.getInstance().removeMember(result);

            }
        });
        result.onTimeout(new Runnable() {
            @Override
            public void run() {
                System.out.println("defer timeout,移除成員" + result);
                AsyncTokenContext.getInstance().removeMember(result);

            }
        });

        return result;
    }
}
