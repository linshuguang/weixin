package com.me.servlets;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
/**
 * Created by kenya on 2017/12/4.
 */
public class HelloWorldServlet extends HttpServlet
{
    @Override
    public void doGet (HttpServletRequest req,
                       HttpServletResponse res)
            throws ServletException, IOException
    {
        PrintWriter out = res.getWriter();
        out.println("Hello, world");
        out.close();
    }
}
