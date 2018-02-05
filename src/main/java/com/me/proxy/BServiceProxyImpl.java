package com.me.proxy;

import name.lsg.common.Message;
import name.lsg.facade.service.FacadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by kenya on 2018/2/5.
 */
@Service
public class BServiceProxyImpl implements BServiceProxy{
    private static final Logger LOGGER = LoggerFactory.getLogger(BServiceProxyImpl.class);

    private static FacadeService facadeService = null;

    public BServiceProxyImpl() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"app_facade_consumer.xml"});
        context.start();
        //获取远程服务代理
        FacadeService facadeService = (FacadeService) context.getBean("facadeService");
    }

    public Message handleMessage(Message msg){
        return facadeService.handleMessage(msg);
    }

}
