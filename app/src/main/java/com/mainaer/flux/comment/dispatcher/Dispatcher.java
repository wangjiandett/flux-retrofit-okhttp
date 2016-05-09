package com.mainaer.flux.comment.dispatcher;

import android.text.TextUtils;

import com.mainaer.flux.comment.action.Action;

import org.greenrobot.eventbus.EventBus;

/**
 * 事件分发器
 *
 * @author wangjian
 * @date 2016/3/14.
 */
public final class Dispatcher {

    private static Dispatcher instance;
    private EventBus mEventBus = EventBus.getDefault();

    /**
     * 获取Dispatcher实例
     *
     * @return
     */
    public static Dispatcher getInstance() {
        if (instance == null) {
            synchronized (Dispatcher.class) {
                instance = new Dispatcher();
            }
        }
        return instance;
    }

    /**
     * 将指定对象注册到dispatcher中，以实现数据的分发
     * 注册的view(exp: activity,fragment)必须在onPause调用 {@link #unRegister(Object)}
     * 防止数据分发混乱.
     *
     * @param store
     */
    public void register(Object store) {
        mEventBus.register(store);
    }

    /**
     * 使用完解除注册
     *
     * @param store
     */
    public void unRegister(Object store) {
        mEventBus.unregister(store);
    }

    /**
     * 进行事件分发（将action分发到所有监听Action的store）
     *
     * @param action
     */
    public void dispatch(Action action) {
        if (action == null) {
            throw new IllegalArgumentException("action must not be null");
        }
        if (!TextUtils.isEmpty(action.getType())) {
//            for (Store store : stores) {
//                store.onAction(action);
//            }
            mEventBus.post(action);
        }
        else {
            throw new IllegalArgumentException("the value of action's type must not be null or length is zero");
        }
    }

    /**
     * 用于分发各种change event到view界面
     *
     * @param event
     */
    public void dispatch(Object event) {
        mEventBus.post(event);
    }
}
