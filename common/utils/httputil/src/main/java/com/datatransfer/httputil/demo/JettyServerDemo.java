package com.datatransfer.httputil.demo;


import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.webapp.WebAppContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class JettyServerDemo {


    public static void main(String[] args) throws Exception{
        Server server = new Server(8089);
        server.setHandler(new HelloWorldHandler());
        server.start();
        server.join();

    }



    public static class HelloWorldServlet extends HttpServlet {

        @Override
        public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           resp.getWriter().println("This is a jetty server by zhaoheng");
        }
    }


    public static class HelloWorldHandler extends AbstractHandler{

        @Override
        public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
            httpServletResponse.setContentType("text/html");
            httpServletResponse.getWriter().println("<html><script>alert('Hello World')</script></html>");
            request.setHandled(true);
        }
    }
}