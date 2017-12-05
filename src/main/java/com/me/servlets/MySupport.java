package com.me.servlets;

import com.github.sd4324530.fastweixin.util.SignUtil;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.github.sd4324530.fastweixin.servlet.WeixinSupport;
import org.codehaus.plexus.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by kenya on 2017/12/5.
 */
//@PropertySource("classpath:config/config.properties")
//@Configuration
public class MySupport extends WeixinSupport {
    private static final Logger logger = LoggerFactory.getLogger(MySupport.class);

    //@Value("${weixin.appId}")
    private String appId;

    private final String filename = "config/config.properties";
    private final String appIdConfName = "weixin.appId";

    @Override
    protected String getToken(){
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = MySupport.class.getClassLoader().getResourceAsStream(filename);
            prop.load(input);
            appId = prop.getProperty(appIdConfName);
        }catch (Exception e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error(ExceptionUtils.getFullStackTrace(e));
                }
            }
        }
        return appId;
    }

    @Override
    protected BaseMsg handleTextMsg(TextReqMsg msg) {
        String content = msg.getContent();
        logger.debug("用户发送到服务器的内容:{}", content);
        return new TextMsg("服务器回复用户消息!");
    }

    @Override
    public boolean isLegal(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        return SignUtil.checkSignature(this.getToken(), signature, timestamp, nonce);
    }

}
