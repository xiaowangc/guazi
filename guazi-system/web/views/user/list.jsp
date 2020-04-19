
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="../../css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/list.css"/>
</head>
<body>
<script src="../../js/jquery.min.js"></script>
<script src="../../js/layer/layer.js"></script>
<table class="tab tab-hover">
    <tr class="active">
        <th>编号</th><th>姓名</th><th>手机号码</th><th>密码</th><th>注册时间</th><th>上次登录时间</th><th>操作</th>
    </tr>
    <c:forEach items="${sessionScope.userList}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.phoneNumber}</td>
            <td>${user.password}</td>
            <td>${user.createTime}</td>
            <c:if test="${user.loginTime == null}">
                <td>从未登录</td>
            </c:if>
            <c:if test="${user.loginTime != null}">
                <td>${user.loginTime}</td>
            </c:if>
            <td><span class="btn-danger" onclick="deleteUser(${user.id},this)">删除</span></td>
        </tr>
    </c:forEach>
    <script>
        function deleteUser(id,span) {
            layer.load();
            $.post("../../user/delete.do","id="+id,function (data) {
                layer.closeAll();
                if(data.result === "ok"){
                    layer.msg("<span style='color: #fff'>删除成功</span>");
                    $(span).parent().parent().remove();
                }else{
                    layer.msg("<span style='color: #fff'>删除失败</span>");
                }
            },"JSON")
        }

    </script>

</table>
</body>
</html>
