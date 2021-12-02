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
    <title>曹家博应用注册</title>


    <%--<%@include file="/common/head.jsp"%>--%>
    <%--这是静态包含的方法--%>
    <jsp:include page="/common/head.jsp" flush="true" />
    <%--这是动态包含的方法.在请求处理阶段执行--%>

    <script type="text/javascript">
        // 页面加载完成之后
        $(function () {
            // 给注册绑定单击事件
            $("#sub_btn").click(function () {
                // 验证用户名：必须由字母，数字下划线组成，并且长度为4到12位
                //1 获取用户名输入框里的内容
                var usernameText = $("#username").val();
                //2 创建正则表达式对象
                var usernamePatt = /^\w{4,12}$/;
                //3 使用test方法验证
                if (!usernamePatt.test(usernameText)) {
                    //4 提示用户结果
                    $("span.errorMsg").text("用户名不合法！");

                    return false;
                }

                // 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
                //1 获取用户名输入框里的内容
                var passwordText = $("#password").val();
                //2 创建正则表达式对象
                var passwordPatt = /^\w{4,12}$/;
                //3 使用test方法验证
                if (!passwordPatt.test(passwordText)) {
                    //4 提示用户结果
                    $("span.errorMsg").text("密码不合法！");

                    return false;
                }

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
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <span class="errorMsg">
                        <%=request.getAttribute("msg")==null?"请设置用户名密码":request.getAttribute("msg")%>
                    </span>
                </div>
                <div class="form">
                    <form action="registServlet" method="post">
                        <label>用户名称：</label>
                        <input class="itxt" type="text"  autocomplete="off" tabindex="1" name="username" id="username"
                        value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"/>
                        <br />
                        <label>用户密码：</label>
                        <input class="itxt" type="password"  autocomplete="off" tabindex="1" name="password" id="password" />
                        <br />
                        <label>手机号码：</label>
                        <input class="itxt" type="text" autocomplete="off" tabindex="1" name="phone" id="phone" />
                        <br />
                        <input type="submit" value="注册" id="sub_btn" />
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>

</body>
</html>
