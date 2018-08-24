<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<body>
<form action="${pageContext.request.contextPath }/login.action" name="frmLogin" method="post">
    用户名： <input type="text" name="name"> <br/>
    密码： <input type="text" name="pwd"> <br/>
    <input type="submit" value="登陆"> <br/>
</form>
</body>
</html>
