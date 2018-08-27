<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    System.out.println(basePath);
%>
<html>


<head>
    <meta charset="utf-8" />
    <title>samovie</title>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/boxoffice.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/vue.js" charset="utf-8"></script>
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
    <br>
    <br>
    <div id="general">
        <div id="leftinfo" style="border-right: 1px solid #e5e5e5;">
            <p id="big-num">
                <span id="total-boxNum">{{bigNum}}</span> 万
            </p>
            <p id="real-timeshow">
                截至北京时间<label id="real-time"></label>
            </p>
            <script>
                function mytime() {
                    var a = new Date();
                    var b = a.toLocaleTimeString();
                    document.getElementById("real-time").innerHTML = b;
                }
                setInterval(function() {
                    mytime()
                }, 1000);
            </script>
            <p id="predict-num">票房预测:万</p>
        </div>
        <div id="rightinfo">
            <ul id="threeNum">
                <li class="left-li" style="border-right: 1px solid #e5e5e5;">
                    <p class="p-up"><span id="total-ticketNum">{{ticketnum}}</span>万</p>
                    <p class="p-down">人次</p>
                </li>
                <li class="center-li" style="border-right: 1px solid #e5e5e5;">
                    <p class="p-up"><span id="total-scheNum">{{schenum}}</span>万</p>
                    <p class="p-down">场次</p>
                </li>
                <li class="right-li">
                    <p class="p-up"><span id="total-avgPrice">{{avgprice}}</span>元</p>
                    <p class="p-down">平均票价</p>
                </li>
            </ul>
        </div>
    </div>
    <div id="detail">
        <h3 class="detail-outline" style="border-bottom: 1px solid #e5e5e5;">影片明细</h3>
        <dl id="movie-table">
            <dt id="table-title">
            <div class="colm-1">影片</div>
            <div class="colm-2">实时票房</div>
            <div class="colm-3">票房占比</div>
            <div class="colm-4">排片占比</div>
            <div class="colm-5">上座率</div>
            <div class="colm-6">排座占比</div>
            <div class="colm-7">场次</div>
            <div class="colm-8">人次</div>
            <div class="colm-9">场均人次</div>
            <div class="colm-10">场均收入</div>
            <div class="colm-11">平均票价</div>
            </dt>

            <dd class="each-movie" v-for="detail in movieInfos">

                <div class="colm-1">
                    <h4 class="movie-title">{{detail.name}}</h4>
                    <ul class="movie-info">
                        <li class="days">{{detail.days}}</li>
                        <li class="totals"><span>{{detail.totals}}</span></li>
                    </ul>
                </div>
                <div class="colm-2">{{detail.realtime}}</div>
                <div class="colm-3">{{detail.boxofficeRatio}}</div>
                <div class="colm-4">{{detail.platoonRatio}}</div>
                <div class="colm-5">{{detail.seatRate}}</div>
                <div class="colm-6">{{detail.seatRatio}}</div>
                <div class="colm-7">{{detail.screenings}}</div>
                <div class="colm-8">{{detail.ticketNum}}</div>
                <div class="colm-9">{{detail.avgticketNum}}</div>
                <div class="colm-10">{{detail.avgIncome}}</div>
                <div class="colm-11">{{detail.avgticketPrice}}</div>
            </dd>
        </dl>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/boxoffice.js" ></script>
</body>
</html>