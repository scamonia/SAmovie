<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>影库</title>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/suggestlist.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/static/css/wholeframe.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/static/css/store.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/vue.js" charset="utf-8"></script>

    <script src="${pageContext.request.contextPath}/static/js/jquery-1.7.1.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>

</head>
<body onload="load()">
<!-- 导航栏-->
    <nav class="navbar navbar-default" role="navigation" style="background-color: black;margin-bottom: 0%">
        <a class="navbar-brand" href="/index" style="color: white">SAmovie</a>

        <div class="col-xs-4">
            <input id="inp-query" class="form-control"
                    style="margin-bottom: 8px;margin-top: 8px;border-radius: 5px;border-color: white" name="search_text"
                    maxlength="60" placeholder="搜索电影" value="">
        </div>
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

    <div class="suggest" id="search-suggest" style="display: none; top:43px;left: 118px;" >
        <ul id="search-result">
        </ul>
    </div>
<div id="storemain">
    <%--标签栏--%>
    <div id="typelistdiv">
    <div class="movie-container">
        <div class="plot"></div>
        <input type="checkbox" name="list[]" value="plot" class="movietype" id="pl"/>
        <label for="pl">剧情</label>

    </div>
    <div class="movie-container">
        <div class="comedy"></div>
        <input type="checkbox" name="list[]" value="comedy" class="movietype" id="co"/>
        <label for="co">喜剧</label>
    </div>

    <div class="movie-container">
        <div class="action"></div>
        <input type="checkbox" name="list[]" value="action" class="movietype" id="ac"/>
        <label for="ac">动作</label>
    </div>

    <div class="movie-container">
        <div class="love"></div>
        <input type="checkbox" name="list[]" value="love" class="movietype" id="lo"/>
        <label for="lo">爱情</label>
    </div>

    <div class="movie-container">
        <div class="adventure"></div>
        <input type="checkbox" name="list[]" value="adventure" class="movietype" id="ad"/>
        <label for="ad">冒险</label>
    </div>

    <div class="movie-container">
        <div class="animation"></div>
        <input type="checkbox" name="list[]" value="animation" class="movietype" id="an"/>
        <label for="an">动画</label>
    </div>

    <div class="movie-container">
        <div class="crime"></div>
        <input type="checkbox" name="list[]" value="crime" class="movietype" id="cr"/>
        <label for="cr">犯罪</label>
    </div>

    <div class="movie-container">
        <div class="documentary"></div>
        <input type="checkbox" name="list[]" value="documentary" class="movietype" id="docu"/>
        <label for="docu">纪实</label>
    </div>

    <div class="movie-container">
        <div class="family"></div>
        <input type="checkbox" name="list[]" value="family" class="movietype" id="fa"/>
        <label for="fa">家庭</label>
    </div>

    <div class="movie-container">
        <div class="fantasy"></div>
        <input type="checkbox" name="list[]" value="fantasy" class="movietype" id="fan"/>
        <label for="fan">奇幻</label>
    </div>

    <div class="movie-container">
        <div class="history"></div>
        <input type="checkbox" name="list[]" value="history" class="movietype" id="his"/>
        <label for="his">历史</label>
    </div>

    <div class="movie-container">
        <div class="horrible"></div>
        <input type="checkbox" name="list[]" value="horrible" class="movietype" id="hor"/>
        <label for="hor">恐怖</label>
    </div>

    <div class="movie-container">
        <div class="science"></div>
        <input type="checkbox" name="list[]" value="science" class="movietype" id="sci"/>
        <label for="sci">科幻</label>
    </div>

    <div class="movie-container">
        <div class="song"></div>
        <input type="checkbox" name="list[]" value="song" class="movietype" id="son"/>
        <label for="son">歌舞</label>
    </div>

    <div class="movie-container">
        <div class="suspense"></div>
        <input type="checkbox" name="list[]" value="suspense" class="movietype" id="sus"/>
        <label for="sus">悬疑</label>
    </div>

    <div class="movie-container">
        <div class="youth"></div>
        <input type="checkbox" name="list[]" value="youth" class="movietype" id="you"/>
        <label for="you">青春</label>
    </div>

        <button id="resetbtn">重置</button>
    </div>

    <div id="moviecontent">
        <ul id="eigthmovies">
            <li class="moviecontent" v-for="onemovie in moviesinfo" onclick='javascript:$.post("/movieinfo/description",{mid:$(this).find(".mid").text()}, function (data) {
                                                            if (data=="success") {
                                                                location.href = "/movieinfo"
                                                            } else {
                                                             }
                                                            })'>
                <div style="display: none;" class="mid">{{onemovie.mid}}</div>
                <div class="onemovie">
                <div class="movieposter" :style="{background: 'url(../../static/img/posters/'+onemovie.poster+')'}" style="background-size: 100%;"></div>
                <div class="namedirdet">
                        <div class="movienameandirect"><p class="namedirp">{{onemovie.name}}<span class="directspan">导演:{{onemovie.director}}</span></p></div>
                        <div class="moviedetails">{{onemovie.details}}</div>
                </div>
                </div>
            </li>
        </ul>
        <div class="changebtn" onclick="changemovie()"><p class="changep">换一批</p></div>
    </div>
</div>

<script>
    $("#resetbtn").click(function () {
        $(".movietype").attr("checked",false);
        $.ajax({
            type:"post",
            url:"/store/gettypemovie",
            data:{
                typelist:[]
            },
            traditional:true,
            dataType:'json',
            success:function (result) {
                console.log(result);
                app.moviesinfo=result;
            }
        })
    })

    function load() {
        $(".movietype").attr("checked",false);
        $.ajax({
            type:"post",
            url:"/store/gettypemovie",
            data:{
                typelist:[]
            },
            traditional:true,
            dataType:'json',
            success:function (result) {
                console.log(result);
                app.moviesinfo=result;
            }
        })
    }

    var app = new Vue({
        el:"#eigthmovies",
        data:{
            moviesinfo:""
        }
    });
    
    $(".movietype").click(function () {
        var selecteditem = new Array();
        $(':input[class=movietype][checked]').each(function () {
            selecteditem.push($(this).next().text());
        })
        console.log(selecteditem)

        $.ajax({
            type:"post",
            url:"/store/gettypemovie",
            data:{
                typelist:selecteditem
            },
            traditional:true,
            dataType:'json',
            success:function (result) {
                console.log(result);
                app.moviesinfo=result;
            }
        })
    })

    function changemovie() {
        var changeitem = new Array();
        $(':input[class=movietype][checked]').each(function () {
            changeitem.push($(this).next().text());
        })
        $.ajax({
            type:"post",
            url:"/store/gettypemovie",
            data:{
                typelist:changeitem
            },
            traditional:true,
            dataType:'json',
            success:function (result) {
                console.log(result);
                app.moviesinfo=result;
            }
        })
    }
</script>



<%--搜索栏--%>
    <script>

    $("#inp-query").bind("keyup",function (e) {
        var width = document.getElementById("inp-query").offsetWidth+"px";
        $("#search-suggest").show().css({
            width:width
        });
        $(document).one("click", function(){
            $("#search-suggest").hide().css({
                width:width
            });
        });
        e.stopPropagation();
        //在搜索框输入数据，提示相关搜索信息
        var searchText=$("#inp-query").val();

        $("#search-result").children().remove();
        $.post("/search",{"search_text":searchText},function (data) {
            console.log(data)
            if (data == null) {
               // $("#search-result").html("查无此片");
            } else {
                $.each(data, function (i, item) {
                    var headHtml = $("#movie-tmpl").html();

                    headHtml = headHtml.replace(/{id}/g, item.mid);
                    headHtml = headHtml.replace(/{cover}/g, item.poster);
                    headHtml = headHtml.replace(/{moviename}/g, item.name);
                    headHtml = headHtml.replace(/{showyear}/g, item.year);
                    headHtml = headHtml.replace(/{director}/g, item.director);
                    headHtml = headHtml.replace(/{averating}/s, item.score);
                    $("#search-result").append(headHtml);
                })
            }



        })
    });


    </script>

    <%--智能提示框模板--%>
    <script type="text/tmpl"  id="movie-tmpl">
        <li id="searchResult">
            <div>
            <a value="{id}" style="text-decoration:none" onclick='javascript:$.post("/movieinfo/description",{mid:$(this).attr("value")}, function (data) {
                if (data=="success") {
                location.href = "/movieinfo"
                } else {
                }
                })'>
            <div style="float:left">
                <img src="${pageContext.request.contextPath}/static/img/posters/{cover}" style="width:80px;height:120px;border-radius:5px;">
            </div>
            <div  style="padding:12px">
                <span style="font-size:14px">&nbsp;&nbsp;&nbsp;&nbsp;{moviename}</span>
                <br>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;年份:&nbsp;&nbsp;{showyear}</span>
                <br>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;导演：{director}</span>
                <br>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;评分：{averating}</span>
            </div>
            </a>
            </div>
        </li>

    </script>
</body>
</html>
