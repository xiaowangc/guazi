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
        <span>添加用户</span>
    </div>
    <div class="content">
        <table>
            <tr>
                <td class="text-right">姓名</td><td class="content_right"><input class="input" placeholder="请输入姓名"></td>
            </tr>
            <tr>
                <td class="text-right">手机号码</td><td class="content_right"><input class="input" placeholder="请输入手机号码"></td>
            </tr>
            <tr>
                <td class="text-right">密码</td><td class="content_right"><input class="input" placeholder="请输入密码"></td>
            </tr>
            <tr>
                <td></td><td class="content_right"><span class="btn btn-info" onclick="add()">立即提交</span> <span class="btn">重置</span> </td>
            </tr>
        </table>


    </div>
</div>
<script src="../../assets/layui.all.js"></script>
<script src="../../js/jquery.min.js" ></script>
<script src="../../js/layer/layer.js"></script>
<script>
    function add() {
        layer.load();
        var name = $("input:eq(0)").val();
        var phone = $("input:eq(1)").val();
        var password = $("input:eq(2)").val();
        $.post("../../courier/add.do","name="+name+"&phone="+phone+"&password="+password+"&flag=0",function (data) {
            layer.closeAll();
            if(data.result === "ok"){
                layer.msg("<span style='color: #fff'>添加成功</span>");
                //当插入成功后清空输入框
                $("input").val("");
            }else{
                layer.msg("<span style='color: #fff'>添加失败</span>")
            }
        },"JSON");



    }

</script>
</body>
</html>

