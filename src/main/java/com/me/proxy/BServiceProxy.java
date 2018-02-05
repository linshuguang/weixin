package com.me.proxy;

import name.lsg.common.Message;

/**
 * Created by kenya on 2018/2/5.
 */
public interface BServiceProxy {
    Message handleMessage(Message msg);
}
