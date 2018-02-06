package com.me.biz;

import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.req.BaseReq;
import com.github.sd4324530.fastweixin.message.req.BaseReqMsg;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.me.proxy.BServiceProxy;
import com.me.proxy.BServiceProxyImpl;
import com.me.servlets.MySupport;
import name.lsg.common.Message;
import name.lsg.common.TextMsg;
import name.lsg.facade.service.FacadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kenya on 2018/1/18.
 */
//@Service
public class DubboBiz {

    private static final Logger logger = LoggerFactory.getLogger(DubboBiz.class);

    //@Autowired
    BServiceProxy bServiceProxy;

    public DubboBiz(){

        bServiceProxy = new BServiceProxyImpl();
        logger.debug("dubbobiz init");
    }



    private List<String> makeList(String str){
        List<String > fromList = new ArrayList<String>();
        fromList.add(str);
        return fromList;
    }

    private BaseMsg transformMessageToMsg(Message msg){
        BaseMsg result = new BaseMsg();

        if(msg instanceof TextMsg){
            result = new com.github.sd4324530.fastweixin.message.TextMsg(((TextMsg) msg).getMsg());
        }
        //BaseReq baseReq = (BaseReq) result;
        //baseReq.from = makeList(baseReq.getFromUserName());
        //result.to   = makeList(baseReq.getToUserName());
        return result;
    }

    private Message transformMsgToMessage(BaseReqMsg msg){
        Message result = new Message();

        if(msg instanceof TextReqMsg){
            result = new TextMsg(((TextReqMsg) msg).getContent());
        }
        BaseReq baseReq = (BaseReq) msg;
        result.from = makeList(baseReq.getFromUserName());
        result.to   = makeList(baseReq.getToUserName());

        return result;
    }

    public BaseMsg handleMessage(BaseReqMsg msg) throws Exception {

        // 调用方法
        Message message = bServiceProxy.handleMessage(transformMsgToMessage(msg));
        return transformMessageToMsg(message);
    }
}
