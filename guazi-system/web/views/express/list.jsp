<%@ page import="util.DateFormatUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="../../css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/list.css"/>
    <script src="../../js/jquery.min.js"></script>
    <script src="../../js/layer/layer.js"></script>
</head>
<body>
<table class="tab tab-hover">



    <tr class="active">
        <th>编号</th><th>单号</th><th>快递公司</th><th>收货人</th><th>收货电话</th><th>取件码</th><th>状态</th><th>状态变更时间</th><th>操作</th>
    </tr>
    <c:if test="${sessionScope.expressAll.size() == 0}">
        <h1>暂无快递信息</h1>
    </c:if>

    <c:forEach var="express" items="${sessionScope.expressAll}">
        <tr>
            <td>${express.getId()}</td>
            <td>${express.getENumber()}</td>
            <td>${express.getCompany()}</td>
            <td>${express.getUsername()}</td>
            <td>${express.getUserphone()}</td>
            <td>${express.getCode()}</td>
            <c:choose>
                <c:when test="${express.getStatus()==1}">
                    <td>已取件</td>
                </c:when>
                <c:when test="${express.getStatus() == 0}">
                    <td>待取件</td>
                </c:when>
                <c:when test="${express.getStatus() == 2}">
                    <td>已退回</td>
                </c:when>
                <c:otherwise>
                    <td>滞留件</td>
                </c:otherwise>
            </c:choose>
<%-- 如果未取件，则显示到件时间；如果已取件，则显示取件时间           --%>
            <td>
                ${express.getStatus()==0?DateFormatUtil.format(express.getInTime()):DateFormatUtil.format(express.getOutTime())}
            </td>
            <td><span class="btn-danger" onclick="deleteE(${express.getId()},this)">删除</span></td>
        </tr>
    </c:forEach>
    <script>
        function deleteE(id,span) {
            layer.load();
            $.get("../../express/delete.do","id="+id,function (data) {
                layer.closeAll();
                if(data.result == "ok"){
                    layer.msg("<span style='color:#fff'>删除成功</span>");
                    $(span).parent().parent().remove();
                }else{
                    layer.msg("<span style='color:#fff'>删除失败</span>");
                }
            },"JSON")

        }
    </script>

</table>
</body>
</html>
