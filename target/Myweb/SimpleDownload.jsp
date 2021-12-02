<%--
  Created by IntelliJ IDEA.
  User: Atair
  Date: 2021/11/4
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SimpleDownload</title>
</head>
<body>

<%--http://localhost:8080/SimpleUpload.jsp     是访问地址--%>
<form action="http://localhost:8080/hello/helloServlet" method="get">
    下载目标：<input type="text" name="goal" /> <br>
    <input type="submit" value="下载">
</form>

</body>
</html>
