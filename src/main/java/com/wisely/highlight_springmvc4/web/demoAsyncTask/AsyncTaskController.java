package com.wisely.highlight_springmvc4.web.demoAsyncTask;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description:
 *
 * @author: jhc
 * @create: 2016/12/8
 * @version: 1.0
 */
@Controller
public class AsyncTaskController {

    @Autowired
    public LongTimeAsyncCallService longTimeAsyncCallService;

    @RequestMapping(value = "/asyncTask")
    public @ResponseBody DeferredResult<Object> asyncTask() {
        final DeferredResult<Object> deferredResult = new DeferredResult<Object>(1000*310); // 加上连接超时时间
        System.out.println("/asynctack 调用！thread id is:" + Thread.currentThread().getId());
        longTimeAsyncCallService.makeRemoteCallAndUnknownWhenFinish(new LongTermTaskCallback() {
            @Override
            public void callback(Object result) {
                System.out.println("异步调用完成，thread id is:" + Thread.currentThread().getId());
                deferredResult.setResult(result);
            }
        });

        return deferredResult;
    }
}
