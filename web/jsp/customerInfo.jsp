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
    <title>编辑客户信息</title>
    <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
    <form action="${pageContext.request.contextPath}/update" method="post">
        <input type="hidden" name="id" value="${c.id}"><!--隐藏客户的id信息-->
        客户姓名:<input type="text" name="name" value="${c.name}"><br/>
        客户性别:<input type="text" name="gender" value="${c.gender}"><br/>
        客户生日:<input type="text" name="birthday" value="${c.birthday}" class="Wdate" onclick="WdatePicker()" readonly="readonly"><br/>
        客户电话:<input type="text" name="cellphone" value="${c.cellphone}"><br/>
        客户邮箱:<input type="text" name="email" value="${c.email}"><br/>
        客户爱好:<input type="text" name="preference" value="${c.preference}"><br/>
        客户类型:<input type="text" name="type" value="${c.type}"><br/>
        客户备注:<input type="text" name="description" value="${c.description}"><br/>
        <input type="submit" value="修改"/>
    </form>
</body>
</html>
