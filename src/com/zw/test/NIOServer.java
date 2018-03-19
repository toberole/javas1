package com.zw.test;

import java.lang.String;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {
    private static Selector selector;

    public static void initServer(int port) throws Exception {
        // 获取一个与serverSocket关联的通道
        ServerSocketChannel channel = ServerSocketChannel.open();

        // 设置通道为非阻塞模式
        channel.configureBlocking(false);

        // 将该通道对应的socket绑定到一个端口
        channel.socket().bind(new InetSocketAddress(port));

        // 获取一个通道管理器
        selector = Selector.open();

        //将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
        //当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
        channel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public static void listen() throws Exception {
        System.out.println("服务端启动成功！");
        // 轮询访问selector
        while (true) {
            // 当注册的事件到达时，方法返回
            // 否则,该方法会一直阻塞
            selector.select();
            // 获得selector中选中的项的迭代器，选中的项为注册的事件
            Iterator ite = selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = (SelectionKey) ite.next();
                // 删除已选的key,以防重复处理
                ite.remove();
                // 客户端请求连接事件
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key
                            .channel();

                    // 获得和客户端连接的通道
                    SocketChannel channel = server.accept();

                    // 设置成非阻塞
                    channel.configureBlocking(false);

                    //在这里可以给客户端发送信息
                    channel.write(ByteBuffer.wrap(new java.lang.String("向客户端发送了一条信息").getBytes()));

                    //在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
                    channel.register(selector, SelectionKey.OP_READ);

                } else if (key.isReadable()) { // 获得了可读的事件
                    read(key);
                }
            }
        }
    }

    private static void read(SelectionKey key) throws Exception {
        // 服务器可读取消息:得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel) key.channel();

        // 创建读取的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(100);
        channel.read(buffer);
        byte[] data = buffer.array();
        java.lang.String msg = new java.lang.String(data, "UTF-8").trim();
        System.out.println("服务端收到信息：" + msg);
        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
        channel.write(outBuffer);// 将消息回送给客户端
    }

    /**
     * 启动服务端测试
     */
    public static void main(String[] args) {
        try {
            NIOServer server = new NIOServer();
            server.initServer(8000);
            server.listen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
