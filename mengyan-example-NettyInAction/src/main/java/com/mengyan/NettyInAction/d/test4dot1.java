package com.mengyan.NettyInAction.d;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * 4-1 未使用Netty的阻塞网络编程
 * 简单的接受连接并向客户端写"Hi!",然后关闭连接
 */
public class test4dot1 {

    public static void main(String[] args) throws IOException {
        PlainOioServer server = new PlainOioServer();
        server.serve(9999);
    }

    public static class PlainOioServer {

        public void serve(int port) throws IOException {
            // 将服务器绑定至指定端口
            final ServerSocket socket = new ServerSocket(port);
            try {
                // 不断循环来获取处理新的连接
                for(;;) {
                    // 获取新进入的连接
                    final Socket clientSocket = socket.accept();
                    System.out.println("Accepted connection from " + clientSocket);
                    // 创建一个新的线程来处理该连接
                    new Thread(new Runnable() {
                        public void run() {
                            OutputStream out;
                            try {
                                // 获取输出流
                                out = clientSocket.getOutputStream();
                                // 将消息写给已连接的客户端
                                out.write("Hi\r\n".getBytes(Charset.forName("UTF-8")));
                                out.flush();
                                // 关闭连接
                                clientSocket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    clientSocket.close();
                                } catch (IOException e) {
                                    // ignore on close 关闭时忽略
                                }
                            }
                        }
                    }).start(); // 启动线程
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
