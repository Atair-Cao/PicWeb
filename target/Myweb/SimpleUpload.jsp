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
    <title>SimpleUpload</title>
</head>
<body>

<%--http://localhost:8080/SimpleUpload.jsp     是访问地址--%>
<form action="http://localhost:8080/hello/helloServlet" method="post" enctype="multipart/form-data">
    用户名：<input type="text" name="username" /> <br>
    My头像：<input type="file" name="photo" > <br>
    <input type="submit" value="上传">
</form>

</body>
</html>
