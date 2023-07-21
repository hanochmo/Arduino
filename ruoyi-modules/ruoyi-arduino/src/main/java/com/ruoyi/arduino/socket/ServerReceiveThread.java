package com.ruoyi.arduino.socket;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.arduino.SpringContextUtil;
import com.ruoyi.arduino.domain.bo.ArduinoCGQBo;
import com.ruoyi.arduino.service.UinoSqlService;
import com.ruoyi.common.core.constant.Cmd;
import com.ruoyi.common.core.web.domain.AjaxResult;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;


public class ServerReceiveThread implements Runnable {


    //因为Runnable不支持@Autowired，手动注册UinoSqlService这个server
    private UinoSqlService uinoSqlService = SpringContextUtil.getApplicationContext().getBean(UinoSqlService.class);
    private Socket socket;

    private static Logger log = LoggerFactory.getLogger(ServerReceiveThread.class);

    public ServerReceiveThread(Socket socket) {
        this.socket = socket;
    }




    @SneakyThrows
    @Override
    public void run() {
        try {

//            boolean flag = true;

            //输入流接收数据
//            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //新建一个输入流，用来读取客户端发来的数据
            DataInputStream ois = new DataInputStream(socket.getInputStream());
            //输出流发送数据
//            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //新建一个输出流，用来向客户端发送数据
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());

//            log.info("-----------" + ois.readObject());

            while (true) {
//                JSONObject jsonObject = (JSONObject) ois.readObject();
                byte[] msg = new byte[1000];//声明一个数组用于接收客户端8266发来的数据
//                String s = ois.readUTF();//注意8266发给服务器的数据在这里一定要使用read函数来接收，并且把接收到的数据存储到一个数组里面，不能再使用readUTF函数来读取了，可能单片机通过串口发送的数据不是UTF格式
                ois.read(msg);//注意8266发给服务器的数据在这里一定要使用read函数来接收，并且把接收到的数据存储到一个数组里面，不能再使用readUTF函数来读取了，可能单片机通过串口发送的数据不是UTF格式
                String jsonStr = new String(msg);
//                log.info(jsonStr);
                JSONObject jsonObject = JSONObject.parseObject(jsonStr);
                log.info(jsonObject.toJSONString());
                String message = jsonObject.getString("msg");
                String type = jsonObject.getString("type");
                if ("close".equals(message)) {
                    oos.writeUTF("close");
                    oos.flush();
                    break;
                } else {
                    oos.writeUTF("接收数据成功" + message);
                    if (Cmd.INSERT_MAP.equals(type)){
                        ArduinoCGQBo arduinoCGQBo = JSONObject.parseObject(message, ArduinoCGQBo.class);
                        AjaxResult ajaxResult = uinoSqlService.insertMap(arduinoCGQBo);
                        log.info(ajaxResult.toString());
                    }else if (Cmd.GENGXING_MAP.equals(type)){
                        ArduinoCGQBo arduinoCGQBo = JSONObject.parseObject(message, ArduinoCGQBo.class);
                        AjaxResult ajaxResult = uinoSqlService.updateMap(arduinoCGQBo);
                        log.info(ajaxResult.toString());
                    }else {
                        String resStr = "cmd代码错误";
                        InputStream bytes = new ByteArrayInputStream(resStr.getBytes("UTF-8"));
                        String res = new BufferedReader(new InputStreamReader(bytes)).readLine();//程序停在这里,等待用户在控制台上输入要发给客户端的数据
                        oos.writeUTF(res);//注意8266发给服务器的数据在这里一定要使用read函数来接收，并且把接收到的数据存储到一个数组里面，不能再使用readUTF函数来读取了，可能单片机通过串口发送的数据不是UTF格式
                        log.info(resStr);
                    }
                    oos.flush();
                }
            }
            log.info("服务端关闭客户端[{}]", socket.getRemoteSocketAddress());
            oos.close();
            ois.close();
            socket.close();
        } catch (Exception e) {
            log.info("接收数据异常socket关闭");
            e.printStackTrace();
            socket.close();
        }
    }
}

