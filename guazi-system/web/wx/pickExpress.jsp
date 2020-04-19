
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>取货助手 -- 快件e栈服务平台</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <link href="css/normalize.css" type="text/css" rel="stylesheet" />
    <link href="css/common.css" type="text/css" rel="stylesheet" />
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <script src="js/common.js" type="text/javascript"></script>
    <script src="js/notice.js" type="text/javascript"></script>
    <script src="js/regexp.js" type="text/javascript"></script>
    <script src="js/jweixin-1.2.0.js" type="text/javascript"></script>
    <script src="../js/layer/layer.js"></script>

    <style type="text/css">
        .expPickCont{
            padding-bottom: 20px;
        }
        .expPickCont .expPickIcon{
            width: 15%;
            margin: 0 auto;
            text-align: center;
        }
        .expPickCont .expPicTitle{
            text-align: center;
            font-weight: bold;
        }

        .searchInfoBtn{
            width: 90%;
            margin: 0 auto;
            text-align: center;
            line-height: 46px;
            border-radius: 23px;
            background: #1f72ff;
            color: #fff;
            font-weight: bolder;
        }

        .expUserInfoCont{
            width: 90%;
            margin: 20px auto 20px;
            font-size: 14px;
        }

        .expUserInfoNull{
            width: 100%;
            line-height: 150px;
            text-align: center;
            color: #888888;
            display: none;
        }
        .expInfoCont{
            width:100%;
            box-sizing: border-box;
            background-color:#fff;
            border-radius: 10px;
            padding: 30px;
        }
        .expInfoCont td:nth-of-type(1){
            text-align: right;
            height:2em;
            line-height: 2em;
        }
        .expInfoCont>.btns{
            border-top: 1px dashed #666;
            padding-top: 20px;
            text-align: right;
            margin-top: 10px;
        }
        .btn-success{
            padding: 5px 15px;
            color: #fff;
            background: #2F9925;
            border: 1px solid #fff;
            border-radius: 5px;
        }
        .status2{
            position: relative;
        }
        .status2_bg{
            position: absolute;
            left:0px;
            top:0px;
            bottom:0px;
            right:0px;
        }
        .status2_bg>img{
            width:30%;
            margin: 0 auto;
            margin-top: 50px;
            opacity: 0.5;

        }



        .status1{
            display: none;
        }
        .status2{
            display: none;
        }
    </style>
</head>
<body>
<div class="content">
    <div class="headerNav">
        <div class="headerNavTop"><div class="headerNavIcon headerNavIconOut"><span></span><span></span></div></div>
        <div class="headerNavCont">
            <a href="../wx/index.jsp">快递首页</a>
            <a href="../wx/wxUserhome.html">个人中心</a>
            <a href="../delivery.html">送货上门</a>
            <a href="../lazyboard.html">懒人排行</a>
            <a href="../expassistant.html">快递助手</a>
        </div>
    </div>

    <div class="expPickCont">
        <div class="expPickIcon">
            <img src="images/pickexpicon.png" width="100%">
        </div>
        <p class="expPicTitle">确认取货</p>

        <div class="expPickInput">
            <form>

                <div class="userInputCont">
                    <div class="inputTypeCont">
                        <div class="inputTitle">取货码</div>
                        <input type="text" id="expPickUserId" class="commonInputFunc" name="username" placeholder="请输入取货码">

                    </div>
                </div>
            </form>
            <div class="searchInfoBtn" onclick="find()">查找信息</div>
        </div>
    </div>

    <div class="expUserInfoCont">
        <div class="expUserInfoNull">
            暂无查到相关信息
        </div>
        <div class="expInfoCont status1">
            <table>
                <tr>
                    <td>快递公司：</td><td><span>顺丰快递</span></td>
                </tr>
                <tr>
                    <td>快递单号：</td><td><span>123456789456789</span></td>
                </tr>
                <tr>
                    <td>到件时间：</td><td><span>2019-10-14 22:00</span></td>
                </tr>
                <tr>
                    <td>取件码：</td><td><span>666666</span></td>
                </tr>
            </table>
            <div class="btns">
                <button class="btn-success" onclick="updateStatus()">确认收货</button>
            </div>
        </div>

        <div class="expInfoCont status2">
            <table>
                <tr>
                    <td>快递公司：</td><td>顺丰快递</td>
                </tr>
                <tr>
                    <td>快递单号：</td><td>123456789456789</td>
                </tr>
                <tr>
                    <td>到件时间：</td><td>2019-10-14 22:00</td>
                </tr>
                <tr>
                    <td>取件时间：</td><td>2019-10-15 12:00</td>
                </tr>
            </table>
            <div class="status2_bg"><img src="images/status2_bg.png"></div>
        </div>
    </div>

</div>
<script>
    var e = null;
    var code = null;
    //根据取件码修改快递为已取,即status=1
    function updateStatus(){
        layer.load();
        //1.    将取件码发送给服务器

        $.post("updateStatus.do","code="+code,function(data){
            layer.closeAll();
            $(".status1,.expUserInfoNull").hide();
            if(data.result == "ok"){
                layer.msg("取件已完成");
            }else{
                layer.msg("取件失败, 请重新操作");
            }
        },"JSON");
    }
    function find(){
        $(".status1,.expUserInfoNull").hide();
        layer.load();
       code = $("#expPickUserId").val();
       //通过ajax 将取件码发送到服务器
        $.post("find.do","code="+code,function(data){
            layer.closeAll();
            if(data.result == "ok"){
                e = data.data;
                layer.msg("查询成功");
                $(".status1").fadeIn(1000);
                $(".status1 span:eq(0)").html(e.company);
                $(".status1 span:eq(1)").html(e.eNumber);
                // 服务器借助了 code属性值 发送了string类型的到件时间
                $(".status1 span:eq(2)").html(e.code);//date
                $(".status1 span:eq(3)").html(code);
            }else{
                layer.msg("查询失败");
                $(".expUserInfoNull").fadeIn(1000);
            }


        },"JSON");
    }
</script>
</body>
</html>