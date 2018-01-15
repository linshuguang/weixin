package com.me.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by kenya on 2017/12/5.
 */
public class WeixinServlet extends HttpServlet{

    private static final Logger logger = LoggerFactory.getLogger(WeixinServlet.class);

    private final MySupport support = new MySupport();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("to bindServer");
        this.support.bindServer(request, response);
        logger.debug("bindServer OK");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("post");
        if(this.support.isLegal(request)) {
            logger.debug("valid weixin request");
            response.setCharacterEncoding("UTF-8");
            String resp = this.support.processRequest(request);
            PrintWriter pw = response.getWriter();
            pw.write(resp);
            pw.flush();
            pw.close();
        }else{
            logger.debug("not valid weixin request");
        }
    }

}
