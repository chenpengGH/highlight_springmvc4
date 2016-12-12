package com.wisely.highlight_springmvc4.web.demoAsyncTask;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Description:
 *  异步请求的deferredResult的管理类
 * @author: jhc
 * @create: 2016/12/9
 * @version: 1.0
 */
public final class AsyncTokenContext {

    private ConcurrentLinkedDeque<DeferredResult<String>> resultContainer = null;

    private AsyncTokenContext() {
        resultContainer = new ConcurrentLinkedDeque<DeferredResult<String>>();
    }

    private static AsyncTokenContext instance = null;

    public static AsyncTokenContext getInstance() {
        if (instance == null)
            instance = new AsyncTokenContext();
        return instance;
    }

    public void addMember(DeferredResult<String> member) {
        if (member != null) {
            resultContainer.add(member);
            System.out.println("添加成員" + member + ",成員數量" + resultContainer.size());
        }
    }

    public boolean removeMember(DeferredResult<String> member) {
        boolean b =  resultContainer.remove(member);
        System.out.println("移除成員" + member + ",成員數量" + resultContainer.size());
        return b;
    }

    public void notifyAllMembers(String msg) {
        System.out.println("通知成員，成員數量" + resultContainer.size());
        for (DeferredResult<String> member : resultContainer) {
            member.setResult(msg);
        }
    }
}
