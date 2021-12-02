package com.CaoSer;

import com.CaoFun.entity.User;
import com.CaoFun.service.UserService;
import com.CaoFun.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //无文件上传的表单才可以使用getParameter获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");

        if (userService.existsUsername(username)) {
            System.out.println("用户名[" + username + "]已存在!");
            //        跳回注册页面
            req.setAttribute("msg","用户已经存在");
            req.getRequestDispatcher("/page/regist.jsp").forward(req, resp);
        } else {
            userService.registUser(new User(null, username, password, phone));
            //        跳到注册成功页面 regist_success.html
            req.getRequestDispatcher("/page/homepage.jsp").forward(req, resp);
        }
    }
}
