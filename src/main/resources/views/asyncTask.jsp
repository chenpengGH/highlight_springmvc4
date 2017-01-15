<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="application/javascript" src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>
</head>
<body>
<div id="content"></div>
<script type="text/javascript" charset="utf-8">
    pot();
    function pot() {
        $.get('<%=contextPath %>/asyncTask', function (data) {
            console.log(data);
            $("#content").html(data);
            pot();
        })
    }
</script>
</body>
</html>