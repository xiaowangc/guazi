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
        <span>删除快递信息</span>
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
                <td class="text-right">快递单号</td><td class="content_right"><input readonly class="input" placeholder="请输入快递单号"></td>
            </tr>
            <tr>
                <td class="text-right">快递公司</td><td class="content_right"><input readonly class="input" placeholder="请输入快递公司"></td>
            </tr>
            <tr>
                <td class="text-right">收货人姓名</td><td class="content_right"><input readonly class="input" placeholder="请输入收货人姓名"></td>
            </tr>
            <tr>
                <td class="text-right">手机号码</td><td class="content_right"><input readonly class="input" placeholder="请输入手机号码"></td>
            </tr>
            <tr>
                <td class="text-right">快递状态</td><td class="content_right"><input disabled type="radio" name="status">已签收  <input disabled name="status" type="radio">未签收</td>
            </tr>
            <tr>
                <td></td><td class="content_right"><span class="btn btn-info" onclick="deleteE()">立即删除</span> <span class="btn">重置</span> </td>
            </tr>
        </table>
    </div>
</div>
<script src="../../js/jquery.min.js"></script>
<script src="../../js/layer/layer.js"></script>
<script>
    //根据输入的单号进行查找并显示


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


    // 当查找到运单号后点击了删除按钮
    function deleteE() {
        layer.load();
        // 找到第一个输入框的值--->单号值

        $.post("../../express/delete.do","id="+e.id,function (data) {
            layer.closeAll();
            if(data.result === "ok"){
                layer.msg("<span style='color:#fff'>删除成功</span>");
                $(".content2").fadeOut(1500);
            }else{
                layer.msg("<span style='color:#fff'>删除失败</span>");
            }
        },"JSON")
    }



</script>
</body>
</html>

