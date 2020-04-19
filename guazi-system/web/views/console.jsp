<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="../assets/css/layui.css">
    <link rel="stylesheet" href="../assets/css/view.css"/>
    <script src="../assets/echarts.min.js"></script>
    <title></title>
</head>
<body class="layui-view-body">
<div class="layui-content">
    <div class="layui-row layui-col-space20">
        <div class="layui-col-sm6 layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-body chart-card">
                    <div class="chart-header">
                        <div class="metawrap">
                            <div class="meta">
                                <span>用户人数</span>
                            </div>
                            <div class="total">${sessionScope.userNum}</div>
                        </div>
                    </div>
                    <div class="chart-body">
                        <div class="contentwrap">
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="layui-col-sm6 layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-body chart-card">
                    <div class="chart-header">
                        <div class="metawrap">
                            <div class="meta">
                                <span>快递员人数</span>
                            </div>
                            <div class="total">${sessionScope.courierNum}</div>
                        </div>
                    </div>
                    <div class="chart-body">
                        <div class="contentwrap">
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="layui-col-sm6 layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-body chart-card">
                    <div class="chart-header">
                        <div class="metawrap">
                            <div class="meta">
                                <span>总快件数</span>
                            </div>
                            <div class="total">${sessionScope.allExpress}</div>
                        </div>
                    </div>
                    <div class="chart-body">
                        <div class="contentwrap">
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="layui-col-sm6 layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-body chart-card">
                    <div class="chart-header">
                        <div class="metawrap">
                            <div class="meta">
                                <span>待取件数</span>
                            </div>
                            <div class="total">${sessionScope.existExpress}</div>
                        </div>
                    </div>
                    <div class="chart-body">
                        <div class="contentwrap">
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <div class="layui-col-sm12 layui-col-md12">
            <div class="layui-card">
                <div class="layui-tab layui-tab-brief">
                    <ul class="layui-tab-title">
                        <li class="layui-this">实时快件区域分布图</li>
                    </ul>
                    <div class="layui-tab-content">
                        <div class="layui-tab-item layui-show">
                            <iframe src="map.html" style="width:100%;height:600px;"></iframe>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../assets/layui.all.js"></script>
<script src="../js/jquery.min.js" ></script>
<script src="../js/layer/layer.js"></script>
<script>
    var element = layui.element;
</script>

</body>
</html>
