package com.wisely.highlight_springmvc4.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Date;

@Service
public class PushService {
    private DeferredResult<String> deferredResult; //1

    public DeferredResult<String> getAsyncUpdate() {
        deferredResult = new DeferredResult<String>();
        System.out.println("2," + deferredResult.getResult() + ":" + deferredResult + ":" + new Date().toLocaleString());
        return deferredResult;
    }

    @Scheduled(fixedDelay = 5000 * 20)
    public void refresh() {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if (deferredResult != null) {
            deferredResult.setResult(new Long(System.currentTimeMillis())
                    .toString());
            System.out.println("1," + deferredResult.getResult() + ":" + deferredResult + ":" + new Date().toLocaleString());
        }
    }


}