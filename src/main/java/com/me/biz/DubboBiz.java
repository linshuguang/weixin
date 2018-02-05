package com.me.biz;

import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.req.BaseReq;
import com.github.sd4324530.fastweixin.message.req.BaseReqMsg;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import name.lsg.common.Message;
import name.lsg.common.TextMsg;
import name.lsg.facade.service.FacadeService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kenya on 2018/1/18.
 */
@Service
public class DubboBiz {

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
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"app_facade_consumer.xml"});
        context.start();

        //获取远程服务代理
        FacadeService facadeService = (FacadeService) context.getBean("facadeService");
        // 调用方法
        Message message = facadeService.handleMessage(transformMsgToMessage(msg));
        return transformMessageToMsg(message);
    }
}
