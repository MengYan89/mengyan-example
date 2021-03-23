package com.mengyan.NettyInAction.d;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 4-2 未使用Netty的异步网络编程
 * 简单的接受连接并向客户端写"Hi!",然后关闭连接
 */
public class test4dot2 {

    public static void main(String[] args) throws IOException {
        PlainNioServer server = new PlainNioServer();
        server.server(9999);
    }

    public static class PlainNioServer {

        public void server(int port) throws IOException {
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            ServerSocket ssocket = serverChannel.socket();
            // 将服务器绑定至指定端口
            InetSocketAddress address = new InetSocketAddress(port);
            ssocket.bind(address);
            // 打开Selector来处理Channel
            Selector selector = Selector.open();
            // 将ServerSocket注册到Selector以接受链接
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            final ByteBuffer msg = ByteBuffer.wrap("Hi\r\n".getBytes());
            for (;;) {
                try {
                    // 等待需要处理的新事件,阻塞将一直持续到下一个传入事件
                    selector.select();
                } catch (IOException e) {
                    e.printStackTrace();
                    // handle exception
                    break;
                }
                // 获取所有接收事件的selectedKey实例
                Set<SelectionKey> readyKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = readyKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    try {
                        // 检查一个事件是否是一个新的已经就绪可以被接受的连接
                        if (key.isAcceptable()) {
                            ServerSocketChannel server = (ServerSocketChannel) key.channel();
                            SocketChannel client = server.accept();
                            client.configureBlocking(false);
                            // 接受客户端并将他注册到选择器
                            client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate());
                            System.out.println("Accepted connection from " + client);
                        }
                        // 检查套接字是否已经准备好写数据
                        if (key.isWritable()) {
                            SocketChannel client = (SocketChannel)key.channel();
                            ByteBuffer buffer = (ByteBuffer) key.attachment();
                            while (buffer.hasRemaining()) {
                                // 将数据写到已连接的客户端
                                if (client.write(buffer) == 0) {
                                    break;
                                }
                            }
                            // 关闭连接
                            client.close();
                        }
                    } catch (IOException e) {
                        key.cancel();
                        try {
                            key.channel().close();
                        } catch (IOException cex) {
                            // ignore on close 关闭时忽略
                        }
                    }
                }


            }

        }

    }
}
