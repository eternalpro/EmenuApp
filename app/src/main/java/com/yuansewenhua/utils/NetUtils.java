package com.yuansewenhua.utils;

import android.content.Context;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by YangJD on 2014/12/5.
 */

/**
 * 关于网络连接服务端的工具类
 */
public class NetUtils extends IoHandlerAdapter{
    /**
     * 向服务器发送一个请求，获得响应的字符串
     * 通过android模拟器访问本地pc服务端，不能使用localhost和127.0.0.1
     * 请使用Android系统为实现通信将PC的IP设置为10.0.2.2
     *
     * @param httpAddress http://10.0.2.2:9999/XXX/xxxxx/
     * @return 从服务端返回的字符串值
     */
    public static String getProductionFromServer(Context context, final String httpAddress) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        String result = "";
        try {
            result = executorService.submit(new Callable<String>() {
                InputStreamReader in = null;
                HttpURLConnection connection = null;
                URL serverUrl = null;

                @Override
                public String call() throws Exception {
                    try {
                        serverUrl = new URL(httpAddress);
                        connection = (HttpURLConnection) serverUrl.openConnection();
                        in = new InputStreamReader(connection.getInputStream());
                        BufferedReader bufferedReader = new BufferedReader(in);
                        StringBuilder builder = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            builder.append(line);
                        }
                        in.close();
                        return builder.toString();
                    } catch (Exception e) {
                        throw e;
                    } finally {
                        if (connection != null) {
                            connection.disconnect();
                        }
                        if (in != null) {
                            in = null;
                        }
                    }
                }
            }).get(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw e;
        } finally {
            executorService.shutdownNow();
            executorService = null;
            System.gc();
        }
        return result;
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        session.setAttribute("key","GFS");
        session.write("aaaaaaa");
        System.out.println("create");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("open");;
    }

    /**
     * 消息到达时
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {

        System.out.println("葛方帅来了！");
        System.out.println("他说："+message.toString()+"----"+session.toString());
    }

    /**
     * 消息传送至客户端时
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        System.out.println("消息已达客户端");

    }

    /**
     * 当客户端关闭时
     * @param session
     * @throws Exception
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("我自己关闭了");
    }

    /**
     * 抛异常时
     * @param session
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {

        System.out.println("我抛异常了");
        cause.printStackTrace();
    }
}
