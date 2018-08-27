<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>SAmovie</title>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/wholeframe.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/static/css/index.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/vue.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.7.1.js"></script>
</head>
<body>
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

    <div id="moviecontent">
            <ul id="fivemovie" v-for="onemovie in movieInfos">
                <li class="movieitem"  onclick='javascript:$.post("/movieinfo/description",{mid:$(this).find(".movieid").text()}, function (data) {
                                                            if (data=="success") {
                                                                location.href = "/movieinfo"
                                                            } else {
                                                             }
                                                            })'>
                    <div class="movieposter" style="background-repeat: no-repeat;background-size: 100% auto;" :style="{background: 'url(../../static/img/posters/'+onemovie.poster+')'}"></div>
                    <div class="movieid" style="display: none">{{onemovie.mid}}</div>
                    <div class="moviename">{{onemovie.name}}</div>
                    <div class="moviecon">{{onemovie.tag}}</div>
                    <div class="moviecon">{{onemovie.director}}</div>
                    <div class="moviecon">{{onemovie.country}}</div>
                    <div class="moviecon">{{onemovie.year}}年</div>
                </li>
            </ul>
    </div>

    <div id="refresh" style="z-index: 1000;cursor: pointer"></div>
    <script>
        var app = new Vue({
            el:"#moviecontent",
            data:{
                movieInfos:[]
            },
            mounted:function(){
                console.log("成功进入mounted app")
                this.$nextTick(function () {
                    this.detailData()
                })
            },
            methods:{
                detailData:function () {
                    var self = this;
                    console.log("成功进入detailData")
                    fetch('/index/movieinfo',{
                        method:'GET',
                        headers:{
                            'Accept':'application/json',
                            'Content-Type':'application/json;charset=utf-8'
                        },
                    }).then(function (res) {
                        console.log("detail成功")
                        res.json().then(function(json){
                            console.log(json);
                            self.movieInfos = json
                        })
                    }).then(function (json) {

                    })
                }
            }
        });

        $("#refresh").click(function () {
            $.ajax({
                type:"get",
                url:"/index/movieinfo",
                dataType:'json',
                success:function (result) {
                    console.log(result)
                    app.movieInfos=result;
                }
            })
        })
    </script>
</body>
</html>