//package com.ruoyi.arduino.service;
//
//
//import com.alibaba.fastjson.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.Socket;
//
//public class ClientServerThread implements Runnable{
//
//    private static final Logger log = LoggerFactory.getLogger(ClientServerThread.class);
//
//    private String ip;
//    private int port;
//
//    public  ClientServerThread(String ip,int port){
//        this.ip = ip;
//        this.port = port;
//
//    }
//
//
//
//    @Override
//    public void run() {
//
//        try {
//            Socket socket = new Socket(ip,port);
//            log.info("心跳socket链接成功");
//            //输出流写数据
//            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//            //输入流读数据
//            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//            int i =0;
//            while (true){
//                Thread.sleep(5001);
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("type","heart");
//                jsonObject.put("msg","第"+i+"次心跳");
//                log.info("发送心跳socket");
//                oos.writeObject(jsonObject);
//                oos.flush();
//                i++;
//                String message = ois.readUTF();
//                log.info("接收到服务端响应"+message);
//
//                if("close".equals(message)){
//                    break;
//                }
//            }
//            ois.close();
//            oos.close();
//            socket.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//}
