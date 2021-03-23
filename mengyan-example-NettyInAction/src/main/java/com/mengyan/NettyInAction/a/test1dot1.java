package com.mengyan.NettyInAction.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 1.1 Java网络编程
 *  1-1 阻塞I/O 示例
 *
 *  ServerSocket上的accept方法会一直阻塞到一个新的连接建立,随后返回一个新的Socket用于客户端和服务器之间的通信.
 *  该ServerSocket将继续监听传入的连接.
 *
 *  BufferedReader与PrintWriter都衍生自Socket的输入输出流.
 *  BufferedReader从一个字节输入流中读取文本
 *  PrintWriter打印一个对象的格式化的表示到文本输出流
 *
 *  readLine()方法将会阻塞,直到由换行符或者回车符结尾的字符串被读取
 *
 *  这个示例只能同时处理一个连接,要管理多个并发客户端,需要为每个新的客户端Socket创建一个新的Thread(线程)
 *
 */
public class test1dot1 {

    public static void main(String[] args) throws IOException {
        int portNumber = 6969;
        // 创建一个ServerSocket用来监听指定端口上的连接请求
        ServerSocket serverSocket = new ServerSocket(portNumber);
        // 调用accept()后将被阻塞,直到一个连接建立
        Socket clientSocket = serverSocket.accept();
        // 获得接收信息的流对象
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream())
        );
        // 获得输出信息的流对象
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String request, response;
        // 开始循环处理
        while ((request = in.readLine()) != null) {
            // 如果客户端发送了Done就退出处理循环
            if ("Done".equals(request)) {
                break;
            }
            // 模拟服务器处理
            response = processRequest(request);
            // 服务器响应客户端
            out.println(response);
        }


    }

    /**
     * 模拟服务器处理方法
     * @param request
     * @return
     */
    public static String processRequest(String request) {
        String response = "OK";
        return response;
    }
}
