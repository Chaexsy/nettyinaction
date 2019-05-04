package com.chaexsy.nettyinaction.c01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 这段代码同时只能处理1个连接
 * 要管理多个并发客户端，需要给每个新的客户端socket创建一个新的线程
 */
public class IOServer {


    public static void main(String[] args)throws IOException {
        // 创建一个新的ServerSocket，用于监听指定端口上的连接请求
        ServerSocket serverSocket = new ServerSocket(8088);

        // 1. 对accept()方法的调用将会被阻塞，直到一个连接建立
        // 随后返回一个新的Socket用于客户端和服务器之间的通信
        Socket clientSocket = serverSocket.accept();

        // 2.这些流对象都派生于该套接字的流对象
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        String request, response;

        // 3.处理循环开始
        // readLine()方法会阻塞，直到读取到换行符或者回车符结尾的字符串
        while ((request = in.readLine()) != null){
            if("Done".equals(request)){
                // 如果客户端发了 Done， 则推出处理循环
                break;
            }

            // 4.请求被传递给服务器的处理方法，进行业务处理
            response = processRequest(request);
            out.print(response);
        }
    }

    private static String processRequest(String request){
        return "";
    }

}
