<%--
  Created by IntelliJ IDEA.
  User: 小老虎
  Date: 2018/11/8
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加客户信息</title>
    <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
    <form action="${pageContext.request.contextPath}/addCus" method="post">
        客户姓名:<input type="text" name="name"/><br/>
        客户性别:<input type="text" name="gender"/><br/>
        客户生日:<input type="text" name="birthday" class="Wdate" onclick="WdatePicker()" readonly="readonly"/><br/>
        客户电话:<input type="text" name="cellphone"/><br/>
        客户邮箱:<input type="text" name="email"/><br/>
        客户爱好:<input type="text" name="preference"/><br/>
        客户类型:<input type="text" name="type"/><br/>
        客户备注:<input type="text" name="description"/><br/>
        <input type="submit" value="添加"/>
    </form>
</body>
</html>
