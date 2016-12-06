<%--
  Created by IntelliJ IDEA.
  User: androidjp
  Date: 2016/11/22
  Time: 上午10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>图片验证码</title>
  </head>
  <body>
  <script>
    function reloadImage() {
      document.getElementById('btn').disabled = true;
      document.getElementById('identity').src='servlet/LoginServlet?ts='+new Date().getTime();
    }
  </script>

  <img src="servlet/LoginServlet" id="identity" onload="btn.disabled=false;">

  <input type="button" value="换一张" onclick="reloadImage()" id="btn">

  </body>
</html>
