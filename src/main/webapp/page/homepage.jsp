<%@ page import="com.CaoSer.LoginServlet" %>
<%@ page import="com.CaoSer.RequestListenerDemo" %><%--
  Created by IntelliJ IDEA.
  User: Atair
  Date: 2021/11/25
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>网络图片收集器</title>


    <%--<%@include file="/common/head.jsp"%>--%>
    <%--这是静态包含的方法--%>
    <jsp:include page="/common/head.jsp" flush="true" />
    <%--这是动态包含的方法.在请求处理阶段执行--%>

    <script type="text/javascript">
        var i=0;
        i=1;
        var timer=null;
        window.onload=function () {
            var btnObj=document.getElementById("timed");
            var word=document.getElementById("to_change");
            var word2=document.getElementById("mpy");

            btnObj.onclick=function () {
                timer=setInterval(function () {
                    <%System.out.println("我在定时");%>
                    <%System.out.println(session.getId());%>
                    i++;
                    word.innerHTML=i;
                    <%--word2.innerHTML=<%=session.getAttribute("report")==null?"3":session.getAttribute("report")%>;--%>
                    <%--word2.innerHTML=<%=RequestListenerDemo.msg%>;--%>
                    $.ajax({
                        type:"get",
                        url:"loginServlet",
                        data:{useless:$("#useless").val()},
                        datatype:"json",
                        success :function(returnd,textStatus){
                            console.log(returnd);

                            var itme=returnd.first;
                            console.log(itme);

                            var str=itme.birthdate;
                            // alert(str)
                            console.log(str);
                            word2.innerHTML=str;
                            <%--<%--%>
                                <%--System.out.println(str);--%>
                            <%--%>--%>
                            // word2.innerHTML=returnd;

                            <%--word2.innerHTML=<%=session.getAttribute("report")==null?"3":session.getAttribute("report")%>;--%>
                        }
                    })
                },1000)
                <%--word.innerHTML=<%=session.getAttribute("report")==null?"3":session.getAttribute("report")%>;--%>
                <%--$(".login_word").html('<%=session.getAttribute("report")==null?"3":session.getAttribute("report")%>');--%>
            }
        }

        <%--// 页面加载完成之后--%>
        <%--$(function () {--%>

            <%--var getData=function () {--%>
                <%--//获取report信息并且显示到指定区域--%>
                <%--$(".login_word").html('<%=session.getAttribute("report")==null?"3":session.getAttribute("report")%>');--%>
                <%--<%System.out.println("我在定时");%>--%>
            <%--};--%>

            <%--timer=setInterval(getData(),1);//正确开启定时器的方法？？？很奇怪为什么不用加--%>

            <%--$("#timed").click(function () {--%>
                <%--<%System.out.println("按钮");%>--%>
                <%--// $("#for").submit();--%>
                <%--$(".login_word").text('a');--%>
                <%--$(".login_word").text('<%=session.getAttribute("report")==null?"2":session.getAttribute("report")%>');--%>
                <%--&lt;%&ndash;$(".login_word").html('<%=session.getAttribute("report")==null?"3":session.getAttribute("report")%>');//可以运行！！&ndash;%&gt;--%>
                <%--// timer=setInterval(getData(),500);//正确开启定时器的方法？？？很奇怪为什么不用加--%>
            <%--});--%>

        <%--});--%>
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
        <span class="login_word" id="to_change">
            <%--<%=session.getAttribute("report")==null?"hhhhh":session.getAttribute("report")%>--%>
        </span>
    </div>

    <div id="mpy">
        <%=RequestListenerDemo.msg%>
    </div>

    <form id="for" action="loginServlet"  method="post">
        <label>无名测试数值：</label>
        <input class="itxt" type="text"  autocomplete="off" tabindex="1" name="useless" id="useless"
               value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"/>
        <br />
        <label>用户密码：</label>
        <input class="itxt" type="password"  autocomplete="off" tabindex="1" name="password" id="password" />
    </form>

    <button id="timed" value="abc">
        Submit
    </button>

</div>

</body>
</html>
