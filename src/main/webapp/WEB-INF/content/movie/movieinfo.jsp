<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="zh-cmn-Hans" class="ua-mac ua-webkit">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>电影信息</title>
    <!-- 星星评分CSS-->
    <link href="${pageContext.request.contextPath}/static/css/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
    <!-- 整体DIV CSS-->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/css/wholeframe.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/static/css/movieinfo.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/static/css/suggestlist.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/static/css/pagination.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/static/css/center.css" rel="stylesheet" type="text/css">

    <!-- JS-->
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.7.1.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/star-rating.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/vue.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.pagination.js" charset="utf-8"></script>

    <!-- 页面一开始加载star类和切换喜欢按钮样式-->
    <script type="text/javascript">
        function load() {
            $("#allstar").rating({
                        showClear: false,
                        showCaption: false,
                        readonly: true,
                        min: 0,
                        max: 10,
                        step: 0.1,
                        size: 'xs',

                    }
            );
            $("#Evaluation").rating({
                showClear: false,
                showCaption: false,
                min: 0,
                max: 5,
                step: 0.5,
                size: 'sm',
            })
            if("${sessionScope.likedstatus}"==1)
                $("#liked").toggleClass('likedactive');
        }
    </script>
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

    <br>
    <br>

    <div class="component-poster-detail">
        <!--bt-->
        <div class="container">

            <!--电影海报上面 电影名称和导演 -->
            <div class="row">
                <!--电影名称导演 -->
                <div class="col-md-9 col-sm-8">
                    <h1>${sessionScope.movieinfo.name}</h1>
                    <h2>Directed by ${sessionScope.movieinfo.director}</h2>
                </div>
            </div>

            <div class="row">
                <!--电影海报和其他信息/喜欢播放提交按钮 -->
                <div class="col-sm-4">
                    <div class="row">

                        <!--最左侧电影图片和星星评分控件 -->
                        <div class="col-md-7 col-sm-12">
                            <div class="movie-poster">

                                <!--电影图片 -->
                                <a><img src="${pageContext.request.contextPath}/static/img/posters/${sessionScope.movieinfo.poster}" alt="" style="width: 100%"></a>

                                <!--评分控件，如果用户登录且未评分则显示控件 待做-->
                                <c:if test="${sessionScope.uid != null&&sessionScope.userstar==-2.0}">
                                    <div id="evalutiondiv" style="margin:5px 26px;">
                                        <input id="Evaluation" style="display: none;">
                                    </div>
                                </c:if>

                            </div>
                        </div>

                        <!--左侧电影信息 -->
                        <div class="col-md-5 col-sm-12 film-stats" style="" id="movie_genralinfo">

                            <!--电影信息div -->
                            <div><b style="font-size: 11pt">国家:</b><span
                                    style="font-size: 9pt"> ${sessionScope.movieinfo.country}</span></div>
                            <div><b style="font-size: 11pt">类别:</b><span
                                    style="font-size: 9pt"> ${sessionScope.movieinfo.tag}</span></div>
                            <div><b style="font-size: 11pt">年份:</b><span style="font-size: 9pt">
                                ${sessionScope.movieinfo.year}
                                    </span></div>
                            <div><b style="font-size: 11pt">观看人数:</b> <span
                                    style="font-size: 9pt">${sessionScope.movieinfo.scoreitem}</span></div>
                            <div><b style="font-size: 11pt">总评分:</b> <span
                                    style="font-size: 9pt">${sessionScope.movieinfo.score}分</span></div>
                            <div><input id="allstar" style="display:none" value="${sessionScope.movieinfo.score}"></div>

                            <!--用户评分，如果用户登录且评分过则显示评分信息 -->
                            <c:if test="${sessionScope.uid != null&&sessionScope.userstar!=-2.0}">
                                <div><b style="font-size: 11pt">你的评分:</b> <span
                                        style="font-size: 9pt">${sessionScope.userstar}分</span></div>
                            </c:if>
                            <br>

                            <!--喜欢按钮，如果用户登录则显示 -->
                            <c:if test="${sessionScope.uid != null}">
                                <a  class="btn btn-default btn-md" id="liked" onclick="likedclick()" ><span
                                        class="glyphicon glyphicon-heart"></span><span class="fm-opt-label"> 喜欢</span></a>
                            </c:if>
                            <br><br>

                            <!--收藏入影单-->
                            <c:if test="${sessionScope.uid != null}">
                            <button class="btn btn-default btn-md"
                                    id="collectmovie" data-toggle = "modal" data-target="#Mlistitem"
                            ><span class="glyphicon glyphicon-list"></span><span
                                    class="fm-opt-label"> 收藏</span></button>
                            </c:if>
                                <br>
                                <br>
                            <!--影单模态框-->
                            <div id="Mlistitem" class="model in">
                                <div id="userMlist">
                                    <div class="modal-header" style="height: 44px;">
                                        <button type="button" class="close" data-dismiss="modal" onclick="clearStyle()"><span>x</span></button>
                                        <h4 class="modal-title" style="font-size: 18px;background:none;">添加至</h4>
                                    </div>
                                    <div id="userList-body">
                                        <ul id="UMlist" style="border-bottom: 1px solid #e5e5e5;" onclick="changeOtherOp()">
                                            <li class="UMLcontent" v-for="oneMlist in userMlist" >
                                                <div class="UMLid" style="display: none;">{{oneMlist.mlid}}</div>
                                                <div class="UMListMid" style="display: none;">{{oneMlist.midlist}}</div>
                                                <div class="listname">
                                                    {{oneMlist.mlname}}
                                                </div>

                                            </li>
                                        </ul>
                                    </div>
                                    <div class="addUMlistfooter" style="margin-top: 13px;">
                                        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearStyle()" style="margin-left:87px;margin-bottom: 8px">取消</button>
                                        <button type="button" class="btn btn-primary" id="addtoUserMlist" style="margin-left: 15px;margin-bottom: 8px">确定</button>
                                    </div>
                                </div>
                            </div>
                            <script>
                                var app2 = new Vue({
                                    el:"#UMlist",
                                    data:{
                                        userMlist:""
                                    }
                                });
                                $("#collectmovie").click(function () {
                                    console.log("获取登录用户的影单")
                                    $.ajax({
                                        type:"post",
                                        url:"/getuserMlist",
                                        data:{
                                            uid:"${sessionScope.uid}"
                                        },
                                        dataType:'json',
                                        success:function (result) {
                                            console.log(result)
                                            app2.userMlist = result;

                                        }

                                    })
                                })
                                function changeOtherOp() {
                                    var target = event.target.tagName === 'LI' ? $(event.target) : (event.target.parentNode.tagName === 'LI' ? $(event.target.parentNode) : undefined)
                                    target && target.css("background","#99CCFF").siblings().css("background","#ffffff");
                                    target && target.attr("selected","").siblings().removeAttr("selected");

                                }

                                function clearStyle(){
                                    $("#UMlist >li").css("background","none");
                                    $("#UMlist >li").removeAttr("selected");
                                }

                                $("#addtoUserMlist").click(function () {
                                    console.log("将这个电影加入选中的影单")
                                    var Mlistinfo = $(".UMLcontent[selected]");
                                    var judge = $(".UMLcontent[selected]")[0];
                                    console.log("jkj"+Mlistinfo.find(".UMLid").text())
                                    if(judge==undefined){
                                        window.alert("请选择影单")
                                    }else{
                                        $.ajax({
                                            type:"post",
                                            url:"/addintoUMlist",
                                            data:{
                                                mid:"${sessionScope.movieinfo.mid}",
                                                mlid:Mlistinfo.find(".UMLid").text(),
                                                midlist:Mlistinfo.find(".UMListMid").text()
                                            },
                                            success:function (result) {
                                                console.log(result)
                                                if(result=="alreadyhave"){
                                                    alert("该电影已存在")
                                                }else{
                                                    clearStyle();
                                                    alert("收藏成功")
                                                    $("#Mlistitem").modal('hide');
                                                    if(result=="dflsuccess"){
                                                        $("#liked").toggleClass('likedactive');
                                                    }
                                                }
                                            }
                                        })
                                    }
                                })
                            </script>

                            <!--提交按钮，如果用户登录且未评分显示 -->
                            <c:if test="${sessionScope.uid != null&&sessionScope.userstar==-2.0}">
                                <button id="submitevalutionstar" class="btn btn-default btn-md" style="margin: 70px -175px"
                                        onclick='$.post("/sendscore",{"userid":"${sessionScope.uid}","movieid":"${sessionScope.movieinfo.mid}","star":$("#Evaluation").val()},function () {
                                                $.post("/movieinfo/description",{mid:"${sessionScope.movieinfo.mid}"}, function (data) {
                                                if (data=="success") {
                                                location.href = "/movieinfo"
                                                } else {
                                                }
                                                })
                                                })'><span
                                        class="glyphicon glyphicon-ok-circle"></span><span class="fm-opt-label"> 提交评分</span>
                                </button>

                            </c:if>
                        </div>
                    </div>
                </div>

                <!--右侧电影信息等栏目 -->
                <div class="col-sm-8">

                    <!-- 分享链接栏 -->
                    <div id="sharetext"><h3>分享至：</h3></div>
                    <div id="atstbx2" style="float: right;margin-top: -7%"
                         class="at-share-tbx-element addthis-smartlayers addthis-animated at4-show">

                        <div class="at-share-btn-elements" style="float: right;margin-top: -10%">
                            <a id="wbshareBtn" href="javascript:void(0)" target="_blank" class="at-icon-wrapper at-share-btn at-svc-email" style=" border-radius: 0%;">
                                <img style="line-height: 32px; height: 32px; width: 32px;margin-left: 10px;"
                                     src="${pageContext.request.contextPath}/static/img/shareicon/weibo.png"/>
                            </a>
                            <a id="qzoneshareBtn" href="javascript:void(0)" target="_blank" class="at-icon-wrapper at-share-btn at-svc-bitly" style=" border-radius: 0%;">
                                <img style="line-height: 32px; height: 32px; width: 32px;margin-left: 10px;"
                                     src="${pageContext.request.contextPath}/static/img/shareicon/qzone_F.png"/>
                            </a>
                            <a id="qqshareBtn" target="_blank" class="at-icon-wrapper at-share-btn at-svc-bitly" style=" border-radius: 0%;">
                                <img style="line-height: 32px; height: 32px; width: 32px;margin-left: 10px;"
                                     src="${pageContext.request.contextPath}/static/img/shareicon/QQicon.png"/>
                            </a>
                        </div>
                    </div>

                    <!-- Nav tabs 信息切换栏-->
                    <ul class="nav nav-tabs">
                        <li class="active" style="text-align: center">
                            <a href="#film-info" data-toggle="tab">电影简介</a>
                        </li>
                        <li style="text-align: center">
                            <a id="reviewsId" href="#reviews" data-toggle="tab">相似推荐</a>
                        </li>
                        <li style="text-align: center">
                            <a href="#resource" data-toggle="tab">影片资源</a>
                        </li>
                    </ul>

                    <!-- Tab panes -->
                    <div class="tab-content">
                        <!--电影信息 -->
                        <div class="tab-pane active" id="film-info">
                            <br>
                            <div><strong>编剧</strong></div>${sessionScope.movieinfo.screenwriter}<br><br>
                            <div><strong>演员</strong></div>${sessionScope.movieinfo.performer}<br><br>
                            <div><strong>故事简介</strong></div>
                            <p><span style="font-weight: 400;"> ${sessionScope.movieinfo.details}</span></p>
                        </div>

                        <!--推荐电影table -->
                        <div class="tab-pane" id="reviews">

                            <br>

                            <div>
                                <table class="table table-condensed">
                                    <thead>
                                    <tr>
                                        <th style="font-size: 16px">电影名</th>
                                        <th style="font-size: 16px">国家</th>
                                        <th style="font-size: 16px">导演</th>
                                        <th style="font-size: 16px">评分</th>
                                    </tr>
                                    </thead>
                                    <tbody id="movietable">
                                    </tbody>
                                </table>
                            </div>

                        </div>

                        <!--电影资源-->
                        <div class="tab-pane" id="resource">
                            <br>
                            <div class="全网搜索 clear none" id="qlink" style="display: block;">
                                <fieldset class="qBox qwatch">
                                    <legend>《<span class="keyword">${sessionScope.movieinfo.name}</span>》在线观看
                                    </legend>
                                    <a href="http://so.iqiyi.com/so/q_${sessionScope.movieinfo.name}"
                                       target="_blank" rel="nofllow">爱奇艺</a>
                                    <a href="http://v.sogou.com/v?query=${sessionScope.movieinfo.name}"
                                       target="_blank" rel="nofllow">搜狗影视</a>
                                    <a href="http://www.soku.com/search_video/q_${sessionScope.movieinfo.name}?f=1&kb=020200000000000__${sessionScope.moviedescription.moviename}"
                                       target="_blank" rel="nofllow">优酷</a>
                                    <a href="http://www.acfun.cn/search/?#query=${sessionScope.movieinfo.name}"
                                       target="_blank" rel="nofllow">AcFun</a>
                                    <a href="http://search.bilibili.com/all?keyword=${sessionScope.movieinfo.name}"
                                       target="_blank" rel="nofllow">Bilibili</a></fieldset>

                                <fieldset class="qBox qdata">
                                    <legend>《<span class="keyword">${sessionScope.movieinfo.name}</span>》资料介绍&nbsp;
                                    </legend>
                                    <a href="http://baike.baidu.com/search/word?word=${sessionScope.moviebaike}"
                                       target="_blank" rel="nofllow">百度百科</a>
                                    <a href="http://www.baike.com/wiki/${sessionScope.moviebaike}"
                                       target="_blank" rel="nofllow">互动百科</a>
                                    </fieldset>
                                <fieldset class="qBox qreview">
                                    <legend>《<span class="keyword">${sessionScope.movieinfo.name}</span>》评分影评
                                    </legend>
                                    <a href="https://m.douban.com/search/?query=${sessionScope.movieinfo.name}&amp;type=movie"
                                       target="_blank" rel="nofllow">豆瓣电影</a>
                                    <a href="http://search.mtime.com/search/?q=${sessionScope.movieinfo.name}"
                                       target="_blank" rel="nofllow">时光网</a>
                                    <a href="http://www.imdb.com/find?q=${sessionScope.movieinfo.name}"
                                       target="_blank" rel="nofllow">IMDB</a>
                                    <a href="https://www.rottentomatoes.com/search/?search=${sessionScope.movieinfo.name}"
                                       target="_blank" rel="nofllow">烂番茄</a></fieldset>
                            </div>
                        </div>

                    </div>

                </div>
            </div>
            <!-- /row -->
        </div> <!-- /container -->
    </div>
    <br>

    <br>
    <br>

    <div id="commentarea">
        <div id="cmtcontent">
            <div id="cmttiltle">
                <h3>
                    <span class="cmtspan">评论</span>
				<span class="cmtnum" style="margin-left: 20px;font-size: 16px">
					共
					<span id="querynum">${sessionScope.movieinfo.commentitem}</span>
					条评论
				</span>
                </h3>
            </div>
            <div id="mycmt">
                <div class="mycmt-ctn">
                    <textarea id="cmttext" placeholder="请自觉遵守互联网相关的政策法规，严禁发表暴力、反动的言论"></textarea>
                </div>
                <div class="mycmt-btn">
                    <button type="submit" id="submycmt">评论</button>
                </div>
            </div>
            <div id="usercmt">
                <ul id="usercontent">
                    <li class="each-cmt" v-for="cmt in cmtsinfo">

                        <div class="userlogo">
                            <div class="uiddisplay" style="display: none;">{{cmt.uid}}</div>
                            <a class="othernickname" onclick='javascript:$.post("/profile",{uid:$(this).prev(".uiddisplay").text()}, function (data) {
                                if (data=="success") {
                                     location.href = "/userhome"
                                    } else {
                                    }
                                    })'>{{cmt.username}}</a>
                        </div>
                        <div class="usercmt">
                            <div class="cmtword">{{cmt.content}}</div>
                            <div class="cmttime">{{cmt.pagetime}}</div>
                        </div>

                    </li>
                </ul>
            </div>

            <div id="pageinfo"></div>

        </div>

    </div>
    <script>
        var app = new Vue({
            el:"#usercontent",
            data:{
                cmtsinfo:${sessionScope.cmts}
            }
        });
        var pageIndex = 0;
        var pageSize = 5;
        $( function () {
            $("#pageinfo").pagination(${sessionScope.movieinfo.commentitem},{
                callback:PageCallback,
                prev_text:"上一页",
                next_text:"下一页",
                items_per_page:pageSize,
                num_edge_entries:2,
                num_display_entries: 6,
                current_page: pageIndex,
            });

            function PageCallback(index, jq) {
                searchRecords(index);
            }
            
            function searchRecords(pageIndex) {
                $.ajax({
                    type:"post",
                    url:"/pagecmt",
                    data:{
                        mid:${sessionScope.movieinfo.mid},
                        pagenum:pageIndex,
                        pagesize:pageSize
                    },
                    dataType:'json',
                    success:function (result) {
                        console.log(result)
                        app.cmtsinfo = result

                    }
                })
            }
        });

        $("#submycmt").click(function () {
            var date = new Date();
            var nowuid = "${sessionScope.uid}";
            console.log("目前是"+nowuid);
            if(nowuid != ""&& $("#cmttext").val() !=""){
                $.ajax({
                    type:"post",
                    url:"/mycmt",
                    data:{
                        uid:"${sessionScope.uid}",
                        mid:"${sessionScope.movieinfo.mid}",
                        uname:"${sessionScope.uname}",
                        comment:$("#cmttext").val(),
                        cmttime:getNowFormatDate(date)
                    },
                    dataType:'json',
                    success:function (result) {
                        //评论成功后的页面操作
                        app.cmtsinfo = result
                        $("#cmttext").val("");
                        var newnum = parseInt($("#querynum").text()) + 1;
                        $("#querynum").html(newnum);
                        $("#pageinfo").pagination(newnum,{
                            callback:PageCallback,
                            prev_text:"上一页",
                            next_text:"下一页",
                            items_per_page:pageSize,
                            num_edge_entries:2,
                            num_display_entries: 6,
                            current_page: pageIndex,
                        });

                        function PageCallback(index, jq) {
                            searchRecords(index);
                        }

                        function searchRecords(pageIndex) {
                            $.ajax({
                                type:"post",
                                url:"/pagecmt",
                                data:{
                                    mid:${sessionScope.movieinfo.mid},
                                    pagenum:pageIndex,
                                    pagesize:pageSize
                                },
                                dataType:'json',
                                success:function (result) {
                                    console.log(result)
                                    app.cmtsinfo = result

                                }
                            })
                        }
                    }

                });
            }else if(nowuid==""){
                alert("请登录后再进行评论")
            }else if($("#cmttext").val()==""){
                alert("评论内容不能为空")
            }
        })
    </script>

    <%--智能提示框--%>
    <div class="suggest" id="search-suggest" style="display: none; top:43px;left: 118px;" >
        <ul id="search-result">
        </ul>
    </div>

    <br>
</body>

<!-- 点击相似电影<li>-->
<script>
    $('#reviewsId').bind('click',function (event) {
        event.preventDefault();
        $("#movietable").children().remove();
        $.ajax({
            type:"post",
            url:"/getSimi",
            data:{
                mid:"${sessionScope.movieinfo.mid}"
            },
            dataType:'json',
            success:function (result) {
                console.log("相似电影"+result);
                $.each(result,function (i,item) {
                    var headHtml = $("#recommodmovies").html();
                    headHtml = headHtml.replace(/{id}/g, item.mid);
                    headHtml = headHtml.replace(/{moviename}/g, item.name);
                    headHtml = headHtml.replace(/{country}/g, item.country);
                    headHtml = headHtml.replace(/{averating}/g,item.score);
                    headHtml = headHtml.replace(/{director}/g, item.director);

                    $("#movietable").append(headHtml);
                })
            }
            
        })

        $(this).unbind('click');
    })
</script>

<!-- 喜欢按钮事件-->
<script>
    function  likedclick() {
        var color=$("#liked").css("background-color");
        var boollike;
        if(color=="rgb(230, 230, 230)")
            boollike=1;
        else
            boollike=0;
        $.post("/likedmovie", {"movieid": "${sessionScope.movieinfo.mid}","boollike":boollike,"userid":"${sessionScope.uid}"},function (data) {
            if(data=="success") {
                if (boollike == 1)
                    alert("收藏成功");
                else
                    alert("取消收藏");
            }
            else
                alert("按钮事件失效")
        })

        $("#liked").toggleClass('likedactive');
    }
</script>

<!-- 获取用户评论的当前时间 post用-->
<script>
    function getNowFormatDate(date) {
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
                + " " + date.getHours() + seperator2 + date.getMinutes()
                + seperator2 + date.getSeconds();
        return currentdate;
    }

</script>

<!-- 相似电影table模板-->
<script type="text/tmpl" id="recommodmovies">
    <tr>
        <td>
            <a value="{id}" onclick='javascript:$.post("/movieinfo/description",{mid:$(this).attr("value")}, function (data) {
                    if (data=="success") {
                        location.href = "/movieinfo"
                    } else {
                    }
                })'>{moviename}</a>
        </td>
        <td>{country}</td>
        <td>{director}</td>
        <td><span class="fm-rating">{averating}</span></td>
    </tr>
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
//                $("#search-result").html("查无此片");
                    alert("差不到此电影哦~")
                } else {
                    $.each(data, function (i, item) {
                        console.log("你好啊")
                        console.log("i为" + i)
                        console.log(item)
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

<!-- 分享连接栏-->
<script>


    function qzoneShare(){
        var qzone_shareBtn = document.getElementById("qzoneshareBtn")
        qzone_url = document.URL,
                qzone_title = "电影：${sessionScope.movieinfo.name}（来自SAmovie）",
                qzone_pic = "",
                qzone_language = "zh_cn";
        qzone_shareBtn.setAttribute("href","http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url="+qzone_url+"&title="+qzone_title+"&pic="+qzone_pic+"&language="+qzone_language+"");
    }
    qzoneShare();

    function qqShare(){
        var qq_shareBtn = document.getElementById("qqshareBtn")
        qq_url = document.URL,
                qq_title = "电影：${sessionScope.movieinfo.name}（来自SAmovie）",
                qq_pic = "",
                qq_language = "zh_cn";
        qq_shareBtn.setAttribute("href","http://connect.qq.com/widget/shareqq/index.html?url="+qq_url+"&title="+qq_title+"&pic="+qq_pic+"&language="+qq_language+"");
    }
    qqShare();

    function weiboShare(){
        var wb_shareBtn = document.getElementById("wbshareBtn")
        wb_url = document.URL,
                wb_title = "电影：${sessionScope.movieinfo.name}（来自SAmovie）",
                wb_pic = "",
                wb_language = "zh_cn";
        wb_shareBtn.setAttribute("href","http://service.weibo.com/share/share.php?url="+wb_url+"&appkey="+wb_appkey+"&title="+wb_title+"&pic="+wb_pic+"&language="+wb_language+"");
    }
    weiboShare();
</script>




<script type="text/javascript"
        src="//s7.addthis.com/js/300/addthis_widget.js#pubid=kinointernational"></script>


</html>
