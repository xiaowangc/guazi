<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="../../css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/add.css"/>
</head>
<body>
<div id="app">
    <div class="header">
        <span>删除用户</span>
    </div>
    <div class="content">
        <table>
            <tr>
                <td class="text-right">手机号码</td><td class="content_right"><input class="input inline-input" placeholder="请输入手机号码"> <span class="btn btn-info" onclick="findOne()">立即查找</span></td>
            </tr>
        </table>
    </div>


    <div class="header">
        <span>查找信息如下</span>
    </div>
    <div class="content content2" style="display: none">
        <table>
            <tr>
                <td class="text-right">姓名</td><td class="content_right"><input readonly class="input" placeholder="请输入姓名"></td>
            </tr>
            <tr>
                <td class="text-right">手机号码</td><td class="content_right"><input readonly class="input" placeholder="请输入姓名"></td>
            </tr>

            <tr>
                <td class="text-right">密码</td><td class="content_right"><input readonly class="input" placeholder="请输入姓名"></td>
            </tr>
            <tr>
                <td></td><td class="content_right"><span class="btn btn-info" onclick="deleteOne()">立即删除</span> <span class="btn">重置</span> </td>
            </tr>
        </table>
    </div>
</div>

<script src="../../js/jquery.min.js"></script>
<script src="../../js/layer/layer.js"></script>
<script>

    var user;
    function findOne() {
        layer.load();
        var phone  = $("input:eq(0)").val();
        $.post("../../user/select.do","phone="+phone+"&flag=0",function (data) {
            layer.closeAll();
            if(data.result === "ok"){
                layer.msg("<span style='color: #fff'>查找成功</span>");
                user = data.data;
                // 将输入框填入找到的用户信息
                $("input:eq(1)").val(user.username);
                $("input:eq(2)").val(user.phoneNumber);
                $("input:eq(3)").val(user.password);
                $(".content2").fadeIn(1500);
            }else{
                layer.msg("<span style='color: #fff'>查找失败</span>");
            }
        },"JSON");

    }
    function deleteOne() {
        layer.load();
        $.post("../../user/delete.do","id="+user.id,function (data) {
            layer.closeAll();
            if(data.result === "ok"){
                layer.msg("<span style='color: #fff'>删除成功</span>");
                $(".content2").fadeOut(1500);
            }else{
                layer.msg("<span style='color: #fff'>删除失败</span>");
            }
        },"JSON")
    }


</script>

</body>
</html>

