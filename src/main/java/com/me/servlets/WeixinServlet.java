package com.me.servlets;

import com.github.sd4324530.fastweixin.servlet.WeixinServletSupport;
import com.github.sd4324530.fastweixin.servlet.WeixinSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by kenya on 2017/12/5.
 */
public class WeixinServlet extends WeixinServletSupport {

    @Override
    protected WeixinSupport getWeixinSupport() {
        return new MyServletWeixinSupport();
    }
}
