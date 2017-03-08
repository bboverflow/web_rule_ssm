<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>json交互测试</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript">
        function requestJson() {
            $.ajax({
                type:'post',
                url:'${pageContext.request.contextPath}/requestJson.action',
                contentType:'application/json;charset=utf-8',
                data:'{"name":"手机","price":900}',
                success:function (data) {//返回json结果
                    alert(data);
                }
            })
        }

        function responseJson() {
            $.ajax({
                type:'post',
                url:'${pageContext.request.contextPath}/responseJson.action',
                data:'name=手机&price=999',
                success:function (data) {//返回json结果
                    alert(data.name);
                }
            })
        }

    </script>
</head>
<body>
    <input type="button" value="请求Json，输出Json" onclick="requestJson()"/>
    <input type="button" value="请求key/value，输出Json" onclick="responseJson()"/>
</body>
</html>
