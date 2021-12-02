package com.CaoSer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Jiabo.Cao
 * @Date 2021/11/30 23:30
 * @E-Mail newaccountc@163.com
 */
public class DownloadListener implements ServletContextListener {

    private MyThread myThread;

//    public static void StartDL(HttpSession session){
//        OutThread test=new OutThread(session);
//        Thread thread=new Thread(test);
//        thread.start();
//        thread.interrupt();
//    }




    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        if(myThread==null){
//            myThread=new MyThread();
//            myThread.start();
//        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if(myThread!=null && myThread.isInterrupted()){
            myThread.interrupt();
        }
    }
}

//class OutThread implements Runnable{
//
//    private int index;
//    private HttpSession httpSession;
//
//    public OutThread(HttpSession httpSession){
//        this.httpSession=httpSession;
//        this.index=10;
//    }
//
//    @Override
//    public void run() {
//        while(index>0){
//            index=index-1;
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
//            sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
//            Date date = new Date();// 获取当前时间
//            httpSession.setAttribute("report",sdf.format(date));
//            System.out.println(httpSession.getAttribute("report")+Integer.toString(index));
//        }
//    }
//}



class MyThread extends Thread{

    private  HttpSession httpSession;

    public HttpSession getHttpSession() {
        return httpSession;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Override
    public void run() {
        while(!this.isInterrupted()){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Fuck"+System.currentTimeMillis());
        }
    }
}
