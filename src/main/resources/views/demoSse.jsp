<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>demoSse</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <style type="text/css">
        a {
            text-decoration: none;
        }
        #get {
            background: deepskyblue;
            color: #fff;
            text-align: center;
        }
        #content {
            background: seagreen;
            color: #fff;
        }
    </style>
    <script type="application/javascript" src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>
</head>
<body>
<a href="javascript:void(0);"><div id="get">获取服务端推送内容</div></a>
<div id="content"></div>
</body>
<script type="application/javascript">

    if(!!window.EventSource) {
        $("#get").on("click", function () {
            var source = new EventSource("demoSse/push"),
            s = '';
            if(!!source) {
                source.addEventListener('message', function(e) {//2
                    s+=e.data+"<br/>";
                    $("#content").html(s);

                });

                source.addEventListener('open', function(e) {
                    console.log("连接打开.");
                }, false);

                source.addEventListener('error', function(e) {
                    if (e.readyState == EventSource.CLOSED) {
                        console.log("连接关闭");
                    } else {
                        console.log(e.readyState);
                    }
                }, false);
            }
        });
    } else {
        alert("不支持SSE");
    }
</script>
</html>