package com.ruoyi.arduino;

import com.ruoyi.arduino.socket.SocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 系统模块
 * 
 * @author ruoyi
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class ArduinoApplication
{
    public static void main(String[] args){

        SpringApplication.run(ArduinoApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  硬件模块启动成功   ლ(´ڡ`ლ)ﾞ");

    }
}
