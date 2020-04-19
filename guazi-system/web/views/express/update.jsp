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
        <span>修改快递信息</span>
    </div>
    <div class="content">
        <table>
            <tr>
                <td class="text-right">运单号</td><td class="content_right"><input class="input inline-input" placeholder="请输入运单号码"> <span class="btn btn-info" onclick="select()">立即查找</span></td>
            </tr>
        </table>
    </div>


    <div class="header">
        <span>查找信息如下</span>
    </div>
    <div class="content content2" style="display: none">
        <table>
            <tr>
                <td class="text-right">快递单号</td><td class="content_right"><input class="input" placeholder="请输入姓名"></td>
            </tr>
            <tr>
                <td class="text-right">快递公司</td><td class="content_right"><input class="input"  placeholder="请输入姓名"></td>
            </tr>
            <tr>
                <td class="text-right">收货人姓名</td><td class="content_right"><input class="input" placeholder="请输入姓名"></td>
            </tr>
            <tr>
                <td class="text-right">手机号码</td><td class="content_right"><input class="input" placeholder="请输入姓名"></td>
            </tr>
            <tr>
                <td class="text-right">快递状态</td><td class="content_right"><input disabled onclick="layer.msg('改变状态无效')" type="radio" name="status">已签收  <input disabled onclick="layer.msg('改变状态无效')" name="status" type="radio">未签收</td>
            </tr>
            <tr>
                <td></td><td class="content_right"><span class="btn btn-info" onclick="update()">立即修改</span> <span class="btn">重置</span> </td>
            </tr>
        </table>
    </div>
</div>
<script src="../../js/jquery.min.js"></script>
<script src="../../js/layer/layer.js"></script>

<script>
    var e;
    function select() {
        layer.load();
        var num1 = $("input:eq(0)").val();
        $.get("../../express/select.do","number="+num1,function (data) {
            layer.closeAll();
            if(data.result==="ok"){
                e = data.data;
                $("input:eq(1)").val(e.eNumber);
                $("input:eq(2)").val(e.company);
                $("input:eq(3)").val(e.username);
                $("input:eq(4)").val(e.userphone);
                if(e.status === 0){
                    $("input:eq(6)").attr("checked","checked");
                }else{
                    $("input:eq(5)").attr("checked","checked");
                }
                $(".content2").fadeIn(1500);
            }else{
                layer.msg("<span style='color:#fff'>单号不存在</span>");
            }
        },"JSON");
    }
    //将修改后的每个输入框的信息利用ajax发送servlet进行处理，返回修改成功或者失败的结果
    //注意：后端并没有提供修改状态的接口，只有当用户取件时才可以改变该状态
    function update() {
        layer.load();
        var id = e.id;
        var number = $("input:eq(1)").val();
        var company = $("input:eq(2)").val();
        var name = $("input:eq(3)").val();
        var phone = $("input:eq(4)").val();
        $.post("../../express/update.do","id="+id+"&number="+number+"&company="+company+"&name="+name+"&phone="+phone,function (data){
            layer.closeAll();
            if(data.result === "ok"){
                layer.msg("<span style='color:#fff'>修改成功</span>");
                $(".content2").fadeOut(1500);
            }else{
                layer.msg("<span style='color:#fff'>修改失败</span>");
            }
        },"JSON");
    }

</script>
</body>
</html>
