package com.CaoSer;

import com.CaoFun.entity.User;
import com.CaoFun.service.UserService;
import com.CaoFun.service.impl.UserServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;


class Test implements Runnable{

    private int index;
    private HttpSession httpSession;

    public Test(HttpSession httpSession){
        this.httpSession=httpSession;
        this.index=10;
    }

    @Override
    public void run() {
        while(index>0){
            index=index-1;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
            Date date = new Date();// 获取当前时间
            httpSession.setAttribute("report",sdf.format(date));
            System.out.println(httpSession.getAttribute("report")+Integer.toString(index));
        }
    }
}

public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        super.doGet(req, resp);
        req.getSession().setAttribute("report","test3333"+System.currentTimeMillis());
        System.out.println(req.getSession().getAttribute("report"));

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        PrintWriter out=resp.getWriter();

        JsonObject object=new JsonObject();
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("birthdate",new Date());
        JsonElement mapped= new JsonParser().parse(new Gson().toJson(data)).getAsJsonObject();
        object.add("first",mapped);
        System.out.println(object);
        out.print(object.toString());
        //out.write(object.toString());
        out.close();


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        Test test=new Test(session);
//        Thread thread=new Thread(new Test(session));
//        thread.start();

//        DownloadListener.StartDL(req.getSession());

        //  1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println(username+"获得参数！！！！！");

        // 调用 userService.login()登录处理业务
        User loginUser = userService.login(new User(null, username, password, null));
        // 如果等于null,说明登录 失败!
        if (loginUser == null) {
            //   跳回登录页面
            System.out.println("用户名[" + username + "]密码[" + username + "]错误" );
            //        跳回注册页面
            req.setAttribute("msg","用户名密码错误");

            req.getRequestDispatcher("/page/login.jsp").forward(req, resp);
        } else {
            // 登录 成功
            //跳到成功页面login_success.html
            req.getRequestDispatcher("/page/homepage.jsp").forward(req, resp);
        }
    }
}
