<%--
  Created by IntelliJ IDEA.
  User: Atair
  Date: 2021/11/24
  Time: 23:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
%>
<%=basePath%>
<!--写 base 标签，永远固定相对路径跳转的结果-->
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="webjars/jquery/3.5.1/jquery.min.js"> </script>
<%--<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>--%>
