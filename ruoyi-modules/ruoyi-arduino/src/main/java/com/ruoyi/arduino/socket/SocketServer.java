package com.ruoyi.arduino.socket;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Set;


@Component
public class SocketServer {


    private final static Logger log = LoggerFactory.getLogger(SocketServer.class);


    //解码buffer
    private Charset cs = Charset.forName("UTF-8");
    //接受数据缓冲区
    private static ByteBuffer sBuffer = ByteBuffer.allocate(3072);
    //发送数据缓冲区
    private static ByteBuffer rBuffer = ByteBuffer.allocate(3072);
    //选择器/监听器
    private static Selector selector;



    //启动socket服务，开始监听
    public void startSocketServer(int port) {

        try {
            //打开通信信道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //设置为非阻塞
            serverSocketChannel.configureBlocking(false);
            //获取Socket
            ServerSocket serverSocket = serverSocketChannel.socket();

            //打开监听器
            selector = Selector.open();
            //保存了所有已组册的key
            log.info("key" + selector.keys().toString());
            //将通信信道注册到监听器
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            //监听器会一直监听，如果客户端有Request就会进入到相应的事件处理
            while (true) {
                int select = selector.select();//select方法会一直阻塞直到相关事情发生或超时
                log.info("选择一组键 -------   阻塞线程直到有事件发生或者超时" + select);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();//监听到的事件
                for (SelectionKey key : selectionKeys) {
                    handle(key);
                }
                log.info("处理时间" + new Date());
                selectionKeys.clear();//清理处理过的事件
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //具体的事件处理方法
    private void handle(SelectionKey selectionKey)throws IOException {
        ServerSocketChannel serverSocketChannel = null;
        SocketChannel socketChannel = null;
        String requestMsg = "";
        int count = 0;
        if(selectionKey.isAcceptable()){
            //每有客户端连接，就注册通信信道为可读
            serverSocketChannel=(ServerSocketChannel)selectionKey.channel();
            socketChannel=serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector,SelectionKey.OP_READ);
        }else if(selectionKey.isReadable()){
            socketChannel=(SocketChannel)selectionKey.channel();
            rBuffer.clear();
            count=socketChannel.read(rBuffer);
            //读取数据
            if(count>0){
                rBuffer.flip();
                requestMsg=String.valueOf(cs.decode(rBuffer).array());
            }
            String responseMsg="已经收到客户端的消息："+requestMsg;
            log.info("客户端数据: " + responseMsg);
            //返回数据
            sBuffer=ByteBuffer.allocate(responseMsg.getBytes("UTF-8").length);
            sBuffer.put(responseMsg.getBytes("UTF-8"));
            sBuffer.flip();
            socketChannel.write(sBuffer);
            socketChannel.close();
        }
    }



}
