<%--
  Created by IntelliJ IDEA.
  User: scamonia
  Date: 2018/4/21
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>注册(●'◡'●)</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/register.css" />
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/wholeframe.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.1.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/vue.js" charset="utf-8"></script>
</head>
<body>
<div id="main">
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
        <span class="tit" style="font-size: 38px;">注册</span>
    </div>
    <div id="register-container">
        <form id="registerForm" method="post">
            <div class="center-div">
                <input type="text" name="userName" required="required" placeholder="昵称(例:SAmovie)" id="username" min="4" max="30"/>
            </div>
            <div class="checkinfo-div">
                <p id="checkinfo">{{info}}</p>
            </div>
            <script type="text/javascript">
                var app = new Vue({
                    el:"#checkinfo",
                    data:{
                        info:"",
                    }
                });
                $("#username").blur(function(){
                    if($("#username").val()==""){
                        app.info="用户名不能为空";
                    }else{
                    $.ajax({
                        type:"post",
                        url:"/register/judgeuserName",
                        data:{
                            userName:$("#username").val()
                        },

                        success:function(result){
                        console.log(result);
                          app.info = result;
                        }
                });}
                })

            </script>

            <div class="password-div">
                <input type="password" name="userPassword" required="required" placeholder="密码(5-16个字符组成，区分大小写)" id="pwd"  min="6" max="16"  />
            </div>
            <div class="checkinfo-div">
                <p id="pwdinfo">{{info}}</p>
            </div>
            <script>
                var app2 = new Vue({
                    el:"#pwdinfo",
                    data:{
                        info:"",
                    }
                });
                $("#pwd").blur(function () {
                    if($("#pwd").val()==""){
                        app2.info="密码不能为空"
                    }
                    else if($("#pwd").val().length<5){
                        app2.info="密码不得小于5位"
                    }
                    else {
                        app2.info="密码符合要求"
                    }
                })

            </script>
            <div class="selecttitle">
                <p>请选择您感兴趣的电影种类</p>
            </div>
            <div id="typelist">
                <div class="movie-container">
                    <div class="plot"></div>
                    <input type="checkbox" name="list[]"  value="plot" class="movietype" />
                    <span>剧情</span>

                </div>
                <div class="movie-container">
                    <div class="comedy"></div>
                    <input type="checkbox" name="list[]" value="comedy" class="movietype"/>
                    <span>喜剧</span>
                </div>

                <div class="movie-container">
                    <div class="action"></div>
                    <input type="checkbox" name="list[]" value="action" class="movietype"/>
                    <span>动作</span>
                </div>

                <div class="movie-container">
                    <div class="love"></div>
                    <input type="checkbox" name="list[]" value="love" class="movietype"/>
                    <span>爱情</span>
                </div>

                <div class="movie-container">
                    <div class="adventure"></div>
                    <input type="checkbox" name="list[]" value="adventure" class="movietype"/>
                    <span>冒险</span>
                </div>

                <div class="movie-container">
                    <div class="animation"></div>
                    <input type="checkbox" name="list[]" value="animation" class="movietype"/>
                    <span>动画</span>
                </div>

                <div class="movie-container">
                    <div class="crime"></div>
                    <input type="checkbox" name="list[]" value="crime" class="movietype"/>
                    <span>犯罪</span>
                </div>

                <div class="movie-container">
                    <div class="documentary"></div>
                    <input type="checkbox" name="list[]" value="documentary" class="movietype"/>
                    <span>纪实</span>
                </div>

                <div class="movie-container">
                    <div class="family"></div>
                    <input type="checkbox" name="list[]" value="family" class="movietype"/>
                    <span>家庭</span>
                </div>

                <div class="movie-container">
                    <div class="fantasy"></div>
                    <input type="checkbox" name="list[]" value="fantasy" class="movietype"/>
                    <span>奇幻</span>
                </div>

                <div class="movie-container">
                    <div class="history"></div>
                    <input type="checkbox" name="list[]" value="history" class="movietype"/>
                    <span>历史</span>
                </div>

                <div class="movie-container">
                    <div class="horrible"></div>
                    <input type="checkbox" name="list[]" value="horrible" class="movietype"/>
                    <span>恐怖</span>
                </div>

                <div class="movie-container">
                    <div class="science"></div>
                    <input type="checkbox" name="list[]" value="science" class="movietype"/>
                    <span>科幻</span>
                </div>

                <div class="movie-container">
                    <div class="song"></div>
                    <input type="checkbox" name="list[]" value="song" class="movietype"/>
                    <span>歌舞</span>
                </div>

                <div class="movie-container">
                    <div class="suspense"></div>
                    <input type="checkbox" name="list[]" value="suspense" class="movietype"/>
                    <span>悬疑</span>
                </div>

                <div class="movie-container">
                    <div class="youth"></div>
                    <input type="checkbox" name="list[]" value="youth" class="movietype"/>
                    <span>青春</span>
                </div>

            </div>
            <div id="regbtn-div">
                <input type="button"  id="registerbtn" value="注册" class="regbtn"/>
            </div>
        </form>
    </div>
    <script>
        $("#registerbtn").click(function(){
            if($("#checkinfo").text()=="该用户名可用，真是个好名字呢"&&$("#pwdinfo").text()=="密码符合要求"){
            console.log("已进入ajax")
            var selecteditems = new Array();
            $(':input[class=movietype][checked]').each(function () {
                selecteditems.push($(this).val());
            });
            console.log($("#username").val()+$("#pwd").val()+selecteditems)
            $.ajax({
                type:"post",
                url:"/register/submit",
                data:{
                    userName:$("#username").val(),
                    userPassword:$("#pwd").val(),
                    likelist:selecteditems

                },
                traditional: true,
                success:function (result) {
                    console.log(result)
                    if(result == 'success'){
                        window.location.href="/store"}

                }

            });}else {
                alert("请检查用户名及密码")
            }

        })
    </script>
</div>
</body>
</html>
