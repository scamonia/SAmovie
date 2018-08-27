<%--
  Created by IntelliJ IDEA.
  User: scamonia
  Date: 2018/4/25
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>登录( $ _ $ )</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/login.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/vue.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.1.js" charset="utf-8"></script>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/wholeframe.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div id="main">
        <!-- 导航栏-->
        <nav class="navbar navbar-default" role="navigation" style="background-color: black;margin-bottom: 0%">
            <a class="navbar-brand" href="/index" style="color: white">SAmovie</a>

            <a class="navbar-brand" href="/index" style="color: white">首页</a>
            <a class="navbar-brand" href="/boxoffice" style="color: white">票房</a>
            <a class="navbar-brand" href="/store" style="color: white">影库</a>
            <c:if test="${sessionScope.uid == null}">
                <a class="dream" href="javascript:window.location.href='/register'" id="register"
                   style="float: right;color: white;font-size: 13pt;margin-top: 10px;margin-right: 10px"><span
                        style="color: white" class="glyphicon glyphicon-user"></span> 注册</a>
                <a class="dream" href="javascript:window.location.href='/login'"
                   style="float: right;color: white;font-size: 13pt;margin-top: 10px;margin-right: 10px"><span
                        style="color: white" class="glyphicon glyphicon-log-in"></span> 登录</a>
            </c:if>
            <c:if test="${sessionScope.uid != null}">

                <a class="dream" id="logout" href="javascript:window.location.href='/logout'"
                   style="float: right;color: white;font-size: 13pt;margin-top: 10px;margin-right: 10px"><span
                        style="color: white" class="glyphicon glyphicon-log-out"></span> 退出</a>
                <a class="dream" id="usercenter" onclick='$.post("/profile",{"uid":"${sessionScope.uid}"}, function (data) {
                        if (data=="success") {
                        location.href = "/userhome"
                        } else {
                        }
                        })'
                   style="float: right;color: white;font-size: 13pt;margin-top: 10px;margin-right: 10px"><span style="color: white" class="glyphicon glyphicon-user"></span> ${sessionScope.uname}</a>
            </c:if>
        </nav>
    <div id="title-line">
        <span class="tit" style="font-size: 38px;">登录</span>
    </div>
    <div id="login-container">
        <div class="center-div">
            <input type="text" name="userName" placeholder="请填写您的昵称（用户名）" id="loginname" min="4" max="30"/>
        </div>
        <div class="center-div">
            <input type="password" name="userPassword" placeholder="请输入您的密码" id="loginpwd"  min="6" max="16"  />
        </div>
        <div id="reglogbtn">
            <input type="button" value="登录" id="loginbtn"/>
            <input type="button" value="注册" id="registerbtn" />
        </div>
        <div class="info-div">
            <p id="loginfo">{{info}}</p>
        </div>
        <script type="text/javascript">
            var app = new Vue({
                el:"#loginfo",
                data:{
                    info:" ",
                }
            });
            $("#registerbtn").click(function(){
                window.location.href="/register"
            })
            $("#loginbtn").click(function(){
                if($("#loginname").val()==""||$("#loginpwd").val()==""){
                    alert("用户名或密码不能为空")
                }
                else{
                $.ajax({
                    type:"post",
                    url:"/login/judgeaccount",
                    data:{
                        loginname:$("#loginname").val(),
                        password:$("#loginpwd").val()
                    },
                    success:function (result) {
                        console.log(result)
                        if(result == true){
                            app.info = "登录成功，即将跳转页面"
                            setTimeout(function(){window.location.href="/store"},3*1000);
                        }else {
                            app.info = "用户名或密码错误，请重新核对"
                        }
                    }

                });
                }
            })
        </script>
    </div>
</div>
</body>
</html>
