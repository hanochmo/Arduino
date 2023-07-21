package com.ruoyi.arduino.socket;

import com.ruoyi.arduino.service.ClientPrintThread;
//import com.ruoyi.arduino.service.ClientServerThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ClientTest {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);

    private static String host = "127.0.0.1";

    private static int port = 8090;

    public static void main(String[] args) {
//        executorService.execute(new ClientServerThread(host,port));
        executorService.execute(new ClientPrintThread(host,port));
//        new Thread(new ClientPrintThread(host,port)).start();
//        new Thread(new ClientHeartThread(host,port)).start();

    }



}
