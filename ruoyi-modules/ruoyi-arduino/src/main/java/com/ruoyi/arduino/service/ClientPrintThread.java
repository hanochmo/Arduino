package com.ruoyi.arduino.service;


import com.alibaba.fastjson.JSONObject;
import com.ruoyi.arduino.domain.bo.ArduinoCGQBo;
import com.ruoyi.arduino.socket.ServerReceiveThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientPrintThread implements Runnable{

    private static final long serialVersionUID = 1L;
    private static Logger log = LoggerFactory.getLogger(ClientPrintThread.class);

    private String host;

    private int port;

    public ClientPrintThread(String host, int port){
        this.host = host;
        this.port = port;
    }
    @Override
    public void run() {

        try {
            Socket socket = new Socket(host,port);
            log.info("心跳socket链接成功");
            //输出流写数据
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            //输入流读数据
            DataInputStream ois = new DataInputStream(socket.getInputStream());
            int i =0;
            while (true){

                Thread.sleep(5001);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("type","10000");
                ArduinoCGQBo arduinoCGQBo = new ArduinoCGQBo();
                arduinoCGQBo.setInputStr("ceshi" + i);
                arduinoCGQBo.setIp("127.0.0.1");
                arduinoCGQBo.setRemarks("测试");
                //Java对象转换成JSON字符串
                String userStr = JSONObject.toJSONString(arduinoCGQBo);
//                jsonObject.put("msg","第"+i+"次心跳");
                jsonObject.put("msg",userStr);
                log.info("发送心跳socket");
                byte[] reqStr = JSONObject.toJSONString(jsonObject).getBytes(StandardCharsets.UTF_8);
                oos.write(reqStr);
                oos.flush();
                i++;
                String message = ois.readUTF();
                log.info("接收到服务端响应"+message);
                if("close".equals(message)){
                    break;
                }
            }
            ois.close();
            oos.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type","10000");
        ArduinoCGQBo arduinoCGQBo = new ArduinoCGQBo();
        arduinoCGQBo.setInputStr("ceshi" + 1);
        arduinoCGQBo.setIp("127.0.0.1");
        arduinoCGQBo.setRemarks("测试");
        //Java对象转换成JSON字符串
        String userStr = JSONObject.toJSONString(arduinoCGQBo);
//                jsonObject.put("msg","第"+i+"次心跳");
        jsonObject.put("msg",userStr);
        log.info("发送心跳socket");
        String reqStr = JSONObject.toJSONString(jsonObject);
        System.out.println(reqStr);
        byte[] reqStrbyte = JSONObject.toJSONString(jsonObject).getBytes(StandardCharsets.UTF_8);
        System.out.println(reqStrbyte);
        String strJson = new String(reqStrbyte);
        log.info(strJson);
        JSONObject jsonObject1 = JSONObject.parseObject(reqStr);

        log.info(jsonObject1.toJSONString());
    }
}
