<%--
  Created by IntelliJ IDEA.
  User: 小老虎
  Date: 2018/11/8
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>显示用户的信息</title>
</head>
<script type="text/javascript">
    function select_all() {
        //1.得到id为main的checkbox
        var main_checkbox = document.getElementById("main");
        var flag = main_checkbox.checked;
        //2.得到所有name为ck的checkbox
        var cks = document.getElementsByName("ck");
        //3.将cks中所有的checkbox的值checked设置为和main_checkbox一样
        for (var i = 0; i < cks.length; i++) {
            cks[i].checked = flag;
        }
    }

    function del_some() {
        var form1 = document.getElementById("form_1");
        form1.submit();
    }

    function change_pageNum(value) {
        location.href = "${pageContext.request.contextPath}/findAll?pageNum=" + value;
    }
</script>
<body>
<c:if test="${empty requestScope.pb}">
    无客户信息
</c:if>
<c:if test="${not empty requestScope.pb}">
    <form method="post" action="${pageContext.request.contextPath}/delSome" id="form_1">
        <table border="1" align="center" width="80%">
            <tr>
                <td>
                    <input type="checkbox" id="main" onclick="select_all()">
                </td>
                <td>编号</td>
                <td>姓名</td>
                <td>性别</td>
                <td>生日</td>
                <td>电话</td>
                <td>邮箱</td>
                <td>爱好</td>
                <td>类型</td>
                <td>备注</td>
                <td>操作&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/jsp/customerAdd.jsp">添加</a></td>
            </tr>
            <c:forEach items="${requestScope.pb.cs}" var="c">
                <tr>
                    <td><input type="checkbox" name="ck" value="${c.id}"></td>
                    <td>${c.id}</td>
                    <td>${c.name}</td>
                    <td>${c.gender}</td>
                    <td>${c.birthday}</td>
                    <td>${c.cellphone}</td>
                    <td>${c.email}</td>
                    <td>${c.preference}</td>
                    <td>${c.type}</td>
                    <td>${c.description}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/findById?id=${c.id}">编辑</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/delById?id=${c.id}">删除</a>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="2"><a href="javascript:void(0)" onclick="del_some()">删除选中</a></td>
                <td colspan="9" align="center">
                    <a href="${pageContext.request.contextPath}/findAll?currentPage=0&pageNum=${pb.pageNum}">首页</a>&nbsp;&nbsp;&nbsp;
                    <c:if test="${pb.currentPage == 0}">
                        <a href="javascript:void(0)">上一页</a>&nbsp;&nbsp;&nbsp;
                    </c:if>
                    <c:if test="${pb.currentPage != 0}">
                        <a href="${pageContext.request.contextPath}/findAll?currentPage=${pb.currentPage-1}&pageNum=${pb.pageNum}">上一页</a>&nbsp;&nbsp;&nbsp;
                    </c:if>
                    <c:if test="${pb.currentPage == pb.allPageNum-1}">
                        <a href="javascript:void(0)">下一页</a>&nbsp;&nbsp;&nbsp;
                    </c:if>
                    <c:if test="${pb.currentPage != pb.allPageNum-1}">
                        <a href="${pageContext.request.contextPath}/findAll?currentPage=${pb.currentPage+1}&pageNum=${pb.pageNum}">下一页</a>&nbsp;&nbsp;&nbsp;
                    </c:if>
                    <a href="${pageContext.request.contextPath}/findAll?currentPage=${pb.allPageNum-1}&pageNum=${pb.pageNum}">尾页</a>&nbsp;&nbsp;&nbsp;
                    <select name="pageNum" onchange="change_pageNum(this.value)">
                        <option>选择每页显示条数</option>
                        <option value="2">2</option>
                        <option value="4">4</option>
                        <option value="6">6</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="center" colspan="11">
                    <c:forEach begin="0" end="${pb.allPageNum-1}" var="n" step="1">
                        <c:if test="${n == pb.currentPage}">
                            <a href="${pageContext.request.contextPath}/findAll?currentPage=${n}&pageNum=${pb.pageNum}"><font color="red">第${n+1}页</font></a>&nbsp;&nbsp;
                        </c:if>
                        <c:if test="${n != pb.currentPage}">
                            <a href="${pageContext.request.contextPath}/findAll?currentPage=${n}&pageNum=${pb.pageNum}">第${n+1}页</a>&nbsp;&nbsp;
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
        </table>
    </form>
</c:if>
</body>

</html>
