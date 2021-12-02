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
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

/**
 * @Author Jiabo.Cao
 * @Date 2021/11/4 22:22
 * @E-Mail newaccountc@163.com
 */
public class HelloServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("OK");
        System.out.println(request.getParameter("username"));//这里只会返回null，因为是post类型请求不能直接读取参数


        //判断是否包含多段数据（代表文件）
        if(ServletFileUpload.isMultipartContent(request)){
            FileItemFactory fileItemFactory=new DiskFileItemFactory();
            ServletFileUpload servletFileUpload=new ServletFileUpload(fileItemFactory);
            try {
                //得到每一个表单的项
                List<FileItem> list=servletFileUpload.parseRequest(request);
                for(FileItem fileItem:list){
                    if(fileItem.isFormField()){
                        //如果是普通表单项
                        System.out.println(fileItem.getFieldName());//获取表单名
                        System.out.println(fileItem.getString("UTF-8"));//获取表单值
                    }else{
                        System.out.println(fileItem.getFieldName());//获取表单名
                        System.out.println(fileItem.getName());//获取上传文件名

                        fileItem.write(new File("g:\\Cao_HttpServiceBase\\"+fileItem.getName()));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
