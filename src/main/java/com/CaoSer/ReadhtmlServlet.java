package com.CaoSer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

/**
 * @Author Jiabo.Cao
 * @Date 2021/11/26 0:56
 * @E-Mail newaccountc@163.com
 */
public class ReadhtmlServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("OK");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        URL url = new URL("http://www.yiibai.com");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        BufferedWriter writer = new BufferedWriter(new FileWriter("g:\\Cao_HttpServiceBase\\"+"save2yiibai-index.html"));
        String line;

        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            writer.write(line);
            writer.newLine();
        }
        reader.close();
        writer.close();


        System.out.println("开始下载");
        String to_download=request.getParameter("goal");//获取要下载的文件名，直接获取参数
        System.out.println(to_download);
        // 2、读取要下载的文件内容 (通过 ServletContext 对象可以读取)
        ServletContext servletContext = getServletContext();
        // 获取要下载的文件类型
        String mimeType = servletContext.getMimeType("/file/" + to_download);
        System.out.println("下载的文件类型：" + mimeType);
        // 4、在回传前，通过响应头告诉客户端返回的数据类型
        response.setContentType(mimeType);
        // 5、还要告诉客户端收到的数据是用于下载使用（还是使用响应头）
        // Content-Disposition 响应头，表示收到的数据怎么处理
        // attachment 表示附件，表示下载使用
        // filename= 表示指定下载的文件名
        response.setHeader("Content-Disposition", "attachment; filename=" + to_download);
        /**
         * /斜杠被服务器解析表示地址为 http://ip:prot/工程名/ 映射 到代码的 Web 目录
         */
        InputStream resourceAsStream = servletContext.getResourceAsStream("/file/" + to_download);
        // 获取响应的输出流
        OutputStream outputStream = response.getOutputStream();
        // 3、把下载的文件内容回传给客户端
        // 读取输入流中全部的数据，复制给输出流，输出给客户端
        IOUtils.copy(resourceAsStream,outputStream);
    }
}
