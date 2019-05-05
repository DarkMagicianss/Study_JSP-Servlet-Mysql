<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MapleStory</title>
</head>
<h3>恭喜你，登录成功！</h3>
账号:${sessionScope.success_user.username}<br/>
昵称:${sessionScope.success_user.nickname}<br/>
邮箱:${sessionScope.success_user.email}<br/>
<a href="${pageContext.request.contextPath}/findAll">查看所有客户信息</a>

</body>
</html>
