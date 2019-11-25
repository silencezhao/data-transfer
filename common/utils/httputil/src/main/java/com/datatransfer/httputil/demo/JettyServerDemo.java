package com.datatransfer.httputil.demo;


import org.eclipse.jetty.server.Server;

public class JettyServerDemo {


    public static void main(String[] args) throws Exception{
        Server server = new Server(65534);
        server.start();
        server.join();


    }
}
