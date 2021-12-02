<%--
  Created by IntelliJ IDEA.
  User: Atair
  Date: 2021/11/25
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>曹家博应用登录</title>


    <%--<%@include file="/common/head.jsp"%>--%>
    <%--这是静态包含的方法--%>
    <jsp:include page="/common/head.jsp" flush="true" />
    <%--这是动态包含的方法.在请求处理阶段执行--%>

    <script type="text/javascript">
        // 页面加载完成之后
        $(function () {
            // 给注册绑定单击事件
            $("#sub_btn").click(function () {
                // 去掉错误信息
                $("span.errorMsg").text("");
            });
        });
    </script>


    <style type="text/css">
        .login_form{
            height:420px;
            margin-top: 25px;
        }
    </style>

</head>
<body>


<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎使用</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>登录界面</h1>
                    <span class="errorMsg">
                        <%=request.getAttribute("msg")==null?"请输入用户名密码":request.getAttribute("msg")%>
                    </span>
                </div>
                <div class="form">
                    <form action="loginServlet"  target="_self" method="post">
                        <label>用户名称：</label>
                        <input class="itxt" type="text"  autocomplete="off" tabindex="1" name="username" id="username"
                               value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"/>
                        <br />
                        <label>用户密码：</label>
                        <input class="itxt" type="password"  autocomplete="off" tabindex="1" name="password" id="password" />
                        <br />
                        <input type="submit" value="登录" id="sub_btn" />
                    </form>
                </div>

                <div class="LetsRegist">
                    <a href="/hello/page/regist.jsp">进行注册</a>
                </div>


            </div>
        </div>
    </div>
</div>

</body>
</html>
