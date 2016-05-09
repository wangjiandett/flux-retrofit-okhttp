package com.mainaer.flux.comment.store;


import com.mainaer.flux.comment.action.Action;
import com.mainaer.flux.comment.dispatcher.Dispatcher;
import com.mainaer.flux.comment.event.BaseEvent;
import com.mainaer.flux.comment.event.LoadFailEvent;
import com.mainaer.flux.comment.event.LoadSuccessEvent;
import com.mainaer.flux.comment.event.OtherEvent;

import org.greenrobot.eventbus.Subscribe;

/**
 * Store基类 store中的操作全部是同步的<br\>
 * 可处理数据缓存，数据更新等操作
 *
 * @author wangjian
 * @date 2016/3/14.
 */
public abstract class Store<T> {

    protected BaseEvent mBaseEvent;
    protected Action mAction;
    protected Dispatcher dispatcher;

    protected Store(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    /**
     * 处理接收到不同的事件
     */
    @Subscribe
    public void onAction(Action action) {
        if (action != null) {
            mAction = action;
            switch (action.getType()) {
                case Action.ACTION_LOADING_SUCCESS:
                    mBaseEvent = new LoadSuccessEvent();
                    break;
                case Action.ACTION_LOADING_FAIL:
                    mBaseEvent = new LoadFailEvent();
                    break;
                default:
                    // 此处封装action type接收处可在OtherEvent中获取
                    mBaseEvent = new OtherEvent(mAction.getType());
                    break;
            }
            postEvent();
        }
    }

    /**
     * 发送store改变事件
     */
    protected void postEvent() {
        if (mBaseEvent != null && dispatcher != null) {
            dispatcher.dispatch(mBaseEvent);
        }
    }

    /**
     * 获取发送的action
     *
     * @return
     */
    protected Action getAction() {
        return mAction;
    }

    /**
     * 活动加载后action中的数据
     *
     * @return
     */
    protected T getData() {
        return (T)getAction().getData();
    }

    /**
     * 获取加载后action中的错误信息
     *
     * @return
     */
    protected String getError() {
        return getAction().getError();
    }
}
