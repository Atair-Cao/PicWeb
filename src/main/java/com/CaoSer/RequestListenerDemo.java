package com.CaoSer;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author Jiabo.Cao
 * @Date 2021/12/1 0:27
 * @E-Mail newaccountc@163.com
 */
public class RequestListenerDemo implements ServletRequestListener {

    private MyThread2 myThread2;
    public static String msg;

    public RequestListenerDemo(){
        msg="test1201";
        System.out.println(msg);
    }


    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println("request被销毁了");

    }

    public static void changemsg(){
        msg="test"+System.currentTimeMillis();
        System.out.println(msg);
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        msg="hahahahaha";
        System.out.println("request被创建了");
        HttpSession httpSession=((HttpServletRequest)servletRequestEvent.getServletRequest()).getSession();
        if(httpSession!=null){
//            httpSession.setAttribute("report","abb");
//            System.out.println(httpSession.getId()+httpSession.getAttribute("report"));
        }
        if(((HttpServletRequest)servletRequestEvent.getServletRequest()).getParameter("username")!=null && myThread2==null){
            myThread2=new MyThread2();
            myThread2.setHttpSession(httpSession);
            myThread2.start();
        }
    }
}


class MyThread2 extends Thread{

    private int Index=0;

    private  HttpSession httpSession;

    public HttpSession getHttpSession() {
        return httpSession;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Override
    public void run() {
        while( !this.isInterrupted()){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RequestListenerDemo.changemsg();
//            httpSession.setAttribute("report","baa");
//            httpSession.getAttribute("report");
//            System.out.println("Fuck"+httpSession.getId()+httpSession.getAttribute("report"));
        }
    }
}