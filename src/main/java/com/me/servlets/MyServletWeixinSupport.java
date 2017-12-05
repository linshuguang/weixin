package com.me.servlets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.github.sd4324530.fastweixin.servlet.WeixinSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kenya on 2017/12/5.
 */
@PropertySource("classpath:config/config.properties")
public class MyServletWeixinSupport extends WeixinSupport {
    private static final Logger log = LoggerFactory.getLogger(MyServletWeixinSupport.class);

    @Value("${weixin.appId}")
    private String appId;

    @Override
    protected String getToken() {
        return appId;
    }

    @Override
    protected BaseMsg handleTextMsg(TextReqMsg msg) {
        String content = msg.getContent();
        log.debug("用户发送到服务器的内容:{}", content);
        return new TextMsg("服务器回复用户消息!");
    }




}
