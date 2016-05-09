/*
 * Copyright (C) 20015 MaiNaEr All rights reserved
 */
package com.mainaer.flux.comment.event;

/**
 * 自定义event，必须继承BaseEvent
 *
 * @author wangjian
 * @date 2016/3/15.
 */
public class OtherEvent extends BaseEvent {

    public OtherEvent() {
    }

    public OtherEvent(String type) {
        super(type);
    }
}
