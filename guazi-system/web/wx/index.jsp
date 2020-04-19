<%@ page import="bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User u = (User)session.getAttribute("user");
    if(u == null){
        //非登录成功用户访问
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>快件e栈服务平台</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <link href="css/normalize.css" type="text/css" rel="stylesheet" />
    <link href="css/main.css" type="text/css" rel="stylesheet" />
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script src="js/notice.js"  type="text/javascript"></script>
    <script src="js/common.js" type="text/javascript"></script>

</head>
<body>
<div class="content">
    <div class="top autoH">
        <div class="logo autoH">
            <img src="images/logo.png" width="100%">
        </div>
    </div>

    <div class="funContdiv">
        <div class="funCont">
            <div class="userFun">
                <p class="funIcon userFunColor1"></p>
                <p class="funTxt">个人中心</p>
            </div>
            <div class="userFun">
                <p class="funIcon userFunColor2"></p>
                <p class="funTxt">懒人排行</p>
            </div>
            <div class="userFun">
                <p class="funIcon userFunColor3"></p>
                <p class="funTxt">我的快递</p>
            </div>
            <div class="userFun">
                <p class="funIcon userFunColor4"></p>
                <p class="funTxt">退出</p>
            </div>
            <%
                if(u.getFlag() == 1){
                    %>
                <div class="userFun">
                    <p class="funIcon userFunColor5"></p>
                    <p class="funTxt">快递助手</p>
                </div>
            <%
                }
            %>

        </div>
    </div>

    <div class="footer">
        <p class="copytxt">&copy;ChiGe版权所有</p>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        var pageContextVal = $("#PageContext").val();
        $(".userFun").click(function(){
            var clickNum = $(this).index();
            switch(clickNum){
                //个人中心
                case(0):
                    window.location.href= "wxUserhome.html";
                    break;
                case(1):
                    window.location.href= "lazyboard.html";
                    break;
                case(2): window.location.href= "expressList.jsp"; break;
                case(3): rzAlert("温馨提示","新功能正在开发中，敬请期待！"); break;
                case(4): window.location.href= "expressAssist.html"; break;
            }
        });

        var initFunc = function(){
            var windowH = $(document).height();
            var windowW = $(document).width();

            $(".content").css({"height": windowH+"px"});

            var contentW = $(".content").width();
            var contentH = $(".content").height();

            var contH = windowH / 3;
            $(".autoH").css({"height": contH+"px"});

            // userFunH  content
            var middleH = $(".middle").height() + 40;
            var userFunH = contentH - contH - middleH - 30 -20; //footer,userfun(margin)
            $(".funContdiv").css({"height":userFunH+"px"});

            var funIconH = $(".funIcon").width();
            $(".funIcon").css({"height": funIconH+"px","line-height": funIconH+"px","border-radius": funIconH/2 +"px","font-size":funIconH/2 +"px"});

        };

        initFunc();

        $(window).resize(function(){
            initFunc();
        });

        // input focus
        $(".middle .forminput input").focus(function(){
            $(".middle .forminput").css({"border":"1px solid rgba(31,114,255,.75)","box-shadow":"0 0 8px rgba(31,114,255,.5)"});
        });

        // input blur
        $(".middle .forminput input").blur(function(){
            $(".middle .forminput").css({"border":"1px solid #333333","box-shadow":"none"});
        });

    });
</script>
</body>
</html>