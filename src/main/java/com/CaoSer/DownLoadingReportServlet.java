package com.CaoSer;

import com.CaoFun.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author Jiabo.Cao
 * @Date 2021/11/30 21:08
 * @E-Mail newaccountc@163.com
 */
public class DownLoadingReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.setAttribute("report", "1");
        req.getRequestDispatcher("/page/homepage.jsp").forward(req, resp);

    }
}
