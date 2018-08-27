<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh" data-theme="light">

<head>
    <meta charset="utf-8"/>
    <title data-react-helmet="true">个人主页</title>

    <link href="${pageContext.request.contextPath}/static/css/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/css/douban.main.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/suggestlist.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/static/css/center.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.7.1.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/star-rating.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/WdatePicker/WdatePicker.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/vue.js" charset="utf-8"></script>

    <style>
        .component-poster-detail .nav-tabs > li {
            width: 50% !important;
        }
    </style>

</head>

<body class="Entry-body">
<div id="root">

    <div data-reactid="5">
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
    </div>

    <main role="main" class="App-main" data-reactid="48">
        <div data-reactid="49">

            <div id="ProfileHeader" class="ProfileHeader" data-reactid="61">
                <div class="Card" data-reactid="62">
                    <div class="ProfileHeader-userCover" data-reactid="63">
                        <div class="UserCoverEditor" data-reactid="64">
                            <!-- 背景图片 -->
                            <div data-reactid="65">
                                <div class="UserCover" data-reactid="71">
                                    <!-- 背景图片 -->
                                    <div class="VagueImage UserCover-image" data-src="${pageContext.request.contextPath}/static/img/center/tmp.jpg"
                                         data-reactid="72">
                                        <img src="${pageContext.request.contextPath}/static/img/center/tmp.jpg">
                                        <div class="VagueImage-mask" data-reactid="73"></div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="ProfileHeader-wrapper" data-reactid="75">

                        <!-- 背景图片一下的用户信息部分-->
                        <div class="ProfileHeader-main" data-reactid="76" style="margin-bottom: 0px;">

                            <!-- 用户头像 -->
                            <div class="UserAvatarEditor ProfileHeader-avatar" style="top:-57px;margin-left: 20px"
                                 data-reactid="77">
                                <div class="UserAvatar" data-reactid="78">
                                    <img class="Avatar Avatar--large UserAvatar-inner" width="160" height="160"
                                         src="${pageContext.request.contextPath}/static/img/center/user.jpg" srcset="${pageContext.request.contextPath}/static/img/center/user.jpg 2x"
                                         data-reactid="79"/>
                                </div>
                            </div>

                            <div class="ProfileHeader-content" data-reactid="87">

                                <!-- 用户名称 -->
                                <div class="ProfileHeader-contentHead" data-reactid="88">
                                    <h1 class="ProfileHeader-title" data-reactid="89">
                                        <span class="ProfileHeader-name"
                                              data-reactid="90">${sessionScope.cname}</span>
                                    </h1>
                                </div>

                                <!-- 头像下的留白空间 -->
                                <div style="overflow:hidden;transition:height 300ms ease-out;"
                                     class="ProfileHeader-contentBody" data-reactid="93">
                                    <div data-reactid="94">
                                        <div class="ProfileHeader-info" data-reactid="95">
                                            <div class="ProfileHeader-infoItem" data-reactid="96">
                                                <div class="ProfileHeader-iconWrapper" data-reactid="97">
                                                </div>
                                                <div class="ProfileHeader-divider" data-reactid="102"></div>
                                                <div class="ProfileHeader-divider" data-reactid="104"></div>
                                                <div class="ProfileHeader-iconWrapper" data-reactid="105">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!--编辑个人资料按钮 -->
                                <c:if test="${sessionScope.uname == sessionScope.cname}">
                                <div class="ProfileHeader-contentFooter" data-reactid="109">

                                    <div class="ProfileButtonGroup ProfileHeader-buttons" data-reactid="115"
                                         style="bottom: 30px;">
                                        <a href="#" class="Button Button--grey" data-toggle="modal"
                                           data-target="#userEditDialog"
                                           <%--onclick="editUser(${sessionScope.uid})"--%>>
                                            编辑个人资料
                                        </a></div>
                                </div>
                                </c:if>
                            </div>
                        </div>

                    </div>

                </div>
            </div>

            <div class="Profile-main" data-reactid="120">
                <div class="Profile-mainColumn" data-reactid="121">
                    <div class="Card ProfileMain" id="ProfileMain" data-reactid="122">

                        <div class="ProfileMain-header" data-reactid="123">

                            <!-- 滑动标签<li> sa 添加我的影单和个人用户指数选项卡-->
                            <ul class="nav nav-tabs" >
                                <li class="active" style="text-align: center">
                                    <a id="userinfoget" href="#userinfo" data-toggle="tab">个人信息</a>
                                </li>
                                <li style="text-align: center">
                                    <a id="getmlist" href="#film-info" data-toggle="tab">我的影单</a>
                                </li>
                                <li style="text-align: center">
                                    <a id="reviewsId" href="#reviews" data-toggle="tab">我的评论</a>
                                </li>
                            </ul>

                            <div class="tab-content">
                                <!-- 用户个人信息<li> -->
                                <div class="tab-pane active" id="userinfo" >
                                    <div class="userinfomation">
                                        <div class="infoitem">
                                            <div class="itemvalue"><span class="itemtitle">性别：</span>${sessionScope.pageinfo.gender}</div>
                                        </div>

                                        <div class="infoitem">
                                            <div class="itemvalue"><span class="itemtitle">生日：</span>${sessionScope.pageinfo.birthday}</div>
                                        </div>

                                        <div class="intro">
                                            <div class="itemvalue"><span class="itemtitle">简介：</span>${sessionScope.pageinfo.intro}</div>
                                        </div>
                                    </div>
                                </div>
                                <!-- 我的影单<li> -->
                                <div class="tab-pane" id="film-info">

                                    <div class="movielistcontent">
                                        <div class="mlistcontent">
                                            <c:if test="${sessionScope.uname == sessionScope.cname}">
                                            <div id="addbtn">
                                                <button id="addmlist" data-toggle = "modal" data-target="#addMlistArea"></button><span class="itemtitle" style="position: relative;top: -11px;margin-left: 10px;">新建影单</span>
                                            </div>
                                            </c:if>
                                            <%--新建影单模态框--%>
                                            <div id="addMlistArea" class="model in">
                                                <div class="addMlistdialog">
                                                    <div class="modal-header" style="height: 55px;">
                                                        <button type="button" class="close" data-dismiss="modal"><span>x</span></button>
                                                        <h4 class="modal-title">新建影单</h4>
                                                    </div>
                                                    <div id="addMlistbody">
                                                        <h4 style="margin-left: 50px;position: relative;top: 30px;">影单名：</h4>
                                                        <textarea id="addMlistName" style="width: 300px;height:40px;margin-left: 50px;position: relative;top: 35px;border-radius: 5px;" placeholder="15个字以内"></textarea>
                                                        <span id="Mlistwordnum" style="font-size: 14px;display: block;margin-top: 35px;margin-left: 335px;"></span>
                                                        <script>
                                                            $("#addMlistName").keyup(function(){
                                                                if($("#addMlistName").val().length>15){
                                                                    $("#addMlistName").val( $("#addMlistName").val().substring(0,15));
                                                                }
                                                                $("#Mlistwordnum").text(15-$("#addMlistName").val().length);
                                                            });
                                                        </script>
                                                    </div>
                                                    <div class="addMlistfooter" style="margin-top: 13px;">
                                                        <button type="button" class="btn btn-default" data-dismiss="modal" style="margin-left: 240px;">取消</button>
                                                        <button type="button" class="btn btn-primary" id="decideMlist" style="margin-left: 20px;">确定</button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="listinfo">
                                                <ul id="listnamecontent">
                                                    <li class="each-listname"  v-for="mlist in movielistinfo">
                                                        <div style="display: none;" class="mlid">{{mlist.mlid}}</div>
                                                        <div style="display: none;" class="midlist">{{mlist.midlist}}</div>
                                                        <div class="listname" onclick="getOneMovieList()">
                                                            {{mlist.mlname}}
                                                        </div>
                                                        <div style="display: none;" class="mltype">{{mlist.mltype}}</div>
                                                        <%--<c:if test="${sessionScope.centerid != sessionScope.uid}">
                                                        <div class="collectlist"><button class="colbtn"></button>收藏</div>
                                                        </c:if>--%>

                                                        <div class="creatorname">{{mlist.creator}}创建</div>
                                                        <c:if test="${sessionScope.centerid == sessionScope.uid}">
                                                            <div class="deletebtn" onclick="deleteMlistItem()">删除</div>
                                                        </c:if>

                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="listcontent">
                                        <ul id="moviecontent">
                                            <li class="onemoviecont"  v-for="movie in moviecontentinfo">
                                                <div class="amoviecontent">
                                                    <div style="display: none;" class="mid">{{movie.mid}}</div>

                                                    <div class="moviename" onclick='javascript:$.post("/movieinfo/description",{mid:$(this).prev(".mid").text()}, function (data) {
                                                            if (data=="success") {
                                                                location.href = "/movieinfo"
                                                            } else {
                                                             }
                                                            })'><a class="mnametopage" >{{movie.name}}</a></div>
                                                    <div class="moviecountry">国家:{{movie.country}}    导演:{{movie.director}}    评分:{{movie.score}}</div>
                                                    <c:if test="${sessionScope.centerid == sessionScope.uid}">
                                                    <div class="deletebtn" onclick="deleteMovieItem()" style="float: right;">删除</div>
                                                    </c:if>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <script>
                                    var app = new Vue({
                                        el:"#listnamecontent",
                                        data:{
                                            movielistinfo:""
                                        }
                                    });
                                    var app2 = new Vue({
                                        el:"#moviecontent",
                                        data:{
                                            moviecontentinfo:""
                                        }
                                    });
                                    $("#getmlist").click(function () {
                                        console.log("我的影单点击事件")
                                        $.ajax({
                                            type:"post",
                                            url:"/getmovielist",
                                            data:{
                                                cid:"${sessionScope.centerid}"
                                            },
                                            dataType:'json',
                                            success:function (result) {
                                                console.log(result)

                                                app.movielistinfo=result.movieLists;
                                                app2.moviecontentinfo=result.movies;
                                            }
                                        })
                                    })

                                    function getOneMovieList () {
                                        var target = event.currentTarget
                                        console.log("获得一个影单的内容")

                                        $.ajax({
                                            type:"post",
                                            url:"/getonemovielist",
                                            data:{
                                                mlid:target.parentNode.getElementsByClassName('mlid')[0].innerHTML,
                                                midlist: target.parentNode.getElementsByClassName('midlist')[0].innerHTML,
                                                mltype: target.parentNode.getElementsByClassName('mltype')[0].innerHTML
                                            },
                                            dataType:'json',
                                            success:function (result) {
                                                console.log(result)
                                                app2.moviecontentinfo=result;
                                                $(target.parentNode).css("opacity","1").siblings().css("opacity","0.6")

                                            }
                                        })
                                    }

                                    function deleteMlistItem() {
                                        var deletetarget = event.currentTarget
                                        console.log("删除一个影单")

                                        $.ajax({
                                            type:"post",
                                            url:"/deleteMlist",
                                            data:{
                                                mlid:deletetarget.parentNode.getElementsByClassName('mlid')[0].innerHTML
                                            },
                                            dataType:'json',
                                            success:function (result) {
                                                console.log(result)
                                                app.movielistinfo=result;
                                                app2.moviecontentinfo=null;
                                            }
                                        })

                                    }
                                    function deleteMovieItem() {
                                        var deleteTarget = event.currentTarget
                                        console.log("删除一个电影项目内容")

                                        $.ajax({
                                            type:"post",
                                            url:"/delonemovie",
                                            data:{
                                                mid:deleteTarget.parentNode.getElementsByClassName('mid')[0].innerHTML
                                            },
                                            dataType:'json',
                                            success:function (result) {
                                                console.log(result)
                                                app2.moviecontentinfo=result;
                                            }
                                        })
                                    }

                                    $("#decideMlist").click(function () {
                                        console.log("我们来新建影单吧！")
                                        $.ajax({
                                            type:"post",
                                            url:"/addNewMlist",
                                            data:{
                                                uid:'${sessionScope.uid}',
                                                mlname:$("#addMlistName").val()
                                            },
                                            dataType:'json',
                                            success:function (result) {
                                                console.log("新建影单"+result)
                                                $("#addMlistName").val("")
                                                app.movielistinfo=result;
                                                $("#addMlistArea").modal('hide');
                                            }
                                        })
                                    })
                                    function changeOtherOp() {

                                        var target = event.target.tagName === 'li' ? $(event.target) : (event.target.parentNode.tagName === 'li' ? $(event.target.parentNode) : undefined)
                                        target && target.css("background","#99CCFF").siblings().css("background","#ffffff")
                                    }

                                </script>
                                <!-- 评价过的电影<li> -->
                                <div class="tab-pane" id="reviews">
                                    <div class="reviewcontent">
                                        <div id="usercmt">
                                            <ul id="usercontent">
                                                <li class="each-cmt" v-for="onecmt in usercmtinfo">
                                                    <div style="display: none;" class="cmtid">{{onecmt.cmtid}}</div>
                                                    <div style="display: none;" class="mid">{{onecmt.mid}}</div>
                                                    <div class="moviename"  style="cursor: pointer;text-align: center" onclick='javascript:$.post("/movieinfo/description",{mid:$(this).prev(".mid").text()}, function (data) {
                                                            if (data=="success") {
                                                                location.href = "/movieinfo"
                                                            } else {
                                                             }
                                                            })'><a class="mnametopage" >{{onecmt.mname}}</a>
                                                    </div>
                                                    <div class="cmtword" style="font-size: 16px;">{{onecmt.content}}</div>
                                                    <div class="cmttime" style="float: right;">{{onecmt.pagetime}}</div>
                                                    <c:if test="${sessionScope.centerid == sessionScope.uid}">
                                                    <div class="deletebtn" style="float: right;margin-right: 0px;" onclick="deleteonecmt()">删除</div>
                                                    </c:if>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <script>
                                    var app3 = new Vue({
                                        el:"#usercontent",
                                        data:{
                                            usercmtinfo:""
                                        }
                                    });
                                    $("#reviewsId").click(function () {
                                        $.ajax({
                                            type:"post",
                                            url:"/getcentercmt",
                                            data:{
                                                centerid:'${sessionScope.centerid}'
                                            },
                                            success:function (result) {
                                                console.log(result)
                                                app3.usercmtinfo=result;
                                            }
                                        })
                                    })

                                    function deleteonecmt() {
                                        var delcmtTarget = event.currentTarget;
                                        console.log("删除一条评论");
                                        $.ajax({
                                            type:"post",
                                            url:"/delcentercmt",
                                            data:{
                                                mid:delcmtTarget.parentNode.getElementsByClassName("mid")[0].innerHTML,
                                                centerid:"${sessionScope.centerid}",
                                                cmtid:delcmtTarget.parentNode.getElementsByClassName("cmtid")[0].innerHTML
                                            },
                                            success:function (result) {
                                                console.log(result)
                                                alert("删除评论成功")
                                                app3.usercmtinfo=result;
                                            }
                                        })
                                    }
                                </script>
                            </div>
                        </div>

                    </div>
                </div>
                <!-- 右侧模块 -->
                <c:if test="${sessionScope.uname == sessionScope.cname}">
                <div class="Profile-sideColumn" data-za-module="RightSideBar" data-reactid="294">
                    <div class="Card">
                        <div class="Card-header Profile-sideColumnTitle" data-reactid="296">
                            <div class="Card-headerText" data-reactid="297">推荐电影</div>
                        </div>
                    </div>
                    <!-- 右侧电影推荐列表 -->
                    <div class="Profile-lightList">

                        <!-- 右侧电影推荐列表 -->
                        <c:if test="${sessionScope.recmovies != null}">
                            <c:forEach var="item" items="${sessionScope.recmovies}">
                                <a class="Profile-lightItem" onclick='javascript:$.post("/movieinfo/description",{mid:$(this).attr("value")}, function (data) {
                                    if (data=="success") {
                                    location.href = "/movieinfo"
                                    } else {
                                    }
                                    })' value="${item.mid}">
                                    <span class="Profile-lightItemName" >${item.name}</span>
                                    <span class="Profile-lightItemValue">${item.score}分</span></a>
                            </c:forEach>
                        </c:if>

                    </div>

                </div>
                </c:if>
            </div>
        </div>
    </main>

</div>

    <!-- 用户编辑资料框 -->
    <div class="modal" id="userEditDialog" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" style="margin-top: 160px;margin-left: 240px;">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                    </button>
                <h4 class="modal-title" id="myModalLabel">修改用户信息</h4>
                </div>
            <div class="modal-body">
                <form class="form-horizontal" id="edit_user_form">
                    <div class="reviseitem">
                        <div class="recontent">
                            <label class="retitle">性别：</label>
                            <input type="radio" name="gender" value="男" id="gender1"><label for="gender1" style="cursor: pointer" class="genderlabel">男</label>
                            <input type="radio" name="gender" value="女" id="gender2"><label for="gender2" style="cursor: pointer" class="genderlabel">女</label>
                        </div>
                    </div>
                    <div class="reviseitem">
                        <div class="recontent">
                            <label class="retitle">生日：</label>
                            <input type="text" class="Wdate" id="getbirthday" onclick="WdatePicker()">
                        </div>
                    </div>
                    <div class="reviseitem">
                        <div class="mycmt-ctn recontent">
                            <label class="retitle" style="position: relative;top: -104px;">简介：</label>
                            <textarea id="introself" placeholder="适可而止，100字就够了" style="width: 340px;height: 120px;margin-left: 10px;margin-top: 10px;border-radius: 3px;font-size: 14px;"></textarea>
                            <span id="wordnum" style="font-size: 14px;"></span>
                        </div>
                        <script>
                            $("#introself").keyup(function(){
                                if($("#introself").val().length>100){
                                    $("#introself").val( $("#introself").val().substring(0,100));
                                }
                                $("#wordnum").text(100-$("#introself").val().length);
                            });
                        </script>
                    </div>
                </form>
            </div>
                <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="updateinfo" >保存</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        $("#updateinfo").click(function () {
            $.ajax({
                type:"post",
                url:"/updateuserinfo",
                data:{
                    uid:"${sessionScope.uid}",
                    gender:$("input[name='gender']:checked").val(),
                    birthday:$("#getbirthday").val(),
                    introself:$("#introself").val()
                },
                success:function (result) {
                    //修改成功后的页面操作
                    if(result=="success"){
                        setTimeout(function(){
                            $.ajax({
                                type:"post",
                                url:"/profile",
                                data:{
                                    uid:"${sessionScope.uid}"
                                },
                                success:function (result) {
                                    console.log(result)
                                    window.location.href = "/userhome"
                                }
                            });
                        },1*1000);
                    }else {
                        alert("请检查填写信息")
                    }
                }
            })
        })
    </script>
</body>
</html>