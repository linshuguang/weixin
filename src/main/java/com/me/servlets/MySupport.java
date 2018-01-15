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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
    private final String appSecretConfName = "weixin.appSecret";
    private final String tokenConfName = "weixin.token";

    private String getProperty(String confName){
        Properties prop = new Properties();
        InputStream input = null;
        String property = null;
        try {
            input = MySupport.class.getClassLoader().getResourceAsStream(filename);
            prop.load(input);
            property = prop.getProperty(confName);
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
        return property;
    }

    @Override
    protected String getAppId() {
        return getProperty(appIdConfName);
    }

    @Override
    protected String getToken(){
        return getProperty(tokenConfName);
    }

    @Override
    protected BaseMsg handleTextMsg(TextReqMsg msg) {
        String content = msg.getContent();
        logger.debug("content:{}", content);
        return new TextMsg("hello world");
    }

    @Override
    public boolean isLegal(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        return SignUtil.checkSignature(this.getToken(), signature, timestamp, nonce);
    }

    @Override
    public void bindServer(HttpServletRequest request, HttpServletResponse response) {
        if(this.isLegal(request)) {
            try {
                PrintWriter pw = response.getWriter();
                pw.write(request.getParameter("echostr"));
                pw.flush();
                pw.close();
            } catch (Exception var4) {
                logger.error("绑定服务器异常1", var4);
            }
        }else{
            try {
                PrintWriter pw = response.getWriter();
                pw.write("not valid");
                pw.flush();
                pw.close();
            } catch (Exception var4) {
                logger.error("绑定服务器异常2", var4);
            }
        }

    }

}
