/*
 * Copyright (C) 20015 MaiNaEr All rights reserved
 */
package com.mainaer.flux.comment.event;

/**
 * 所有store更新事件的父类，更多功能需继承实现
 *
 * @author wangjian
 * @date 2016/3/14.
 */
public class BaseEvent {

    public String type;

    public BaseEvent() {
    }

    public BaseEvent(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
