<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>登入页面</title>
</head>
<body onload="run()"><!--一旦加载就运行run方法-->
<h3>恭喜你，注册成功！欢迎您的到来！</h3>
<!--将cookie中经过UTF-8编码的中文字符解码有三种方式
    1.使用java代码.获取cookie其中的username的编码后的字符串 URLDecode.decode(字符串,"UTF-8");
    2.使用自定义EL函数 创建一个java类 将1的方法封装到一个静态方法中
      然后在WEB-INF下创建对应的tld文件 配置之后
      在jsp文件中导入后即可直接使用myfn:decode(cookie.username.value)的EL表达式即可
      其中在IDEA中引入tld文件的方法可以参考一下http://www.cnblogs.com/junhuawang/p/6953177.html
    3.使用javascript中的方法 创建一个一旦onload就执行的方法 进行解析后给标签中的内容赋值
-->
<form action="${pageContext.request.contextPath}/login" method="post">
    账号:<input type="text" id="nameId" name="username"/><br/>
    密码:<input type="password" id="pasId" name="password"/><br/>
    <input type="checkbox" name="remember" value="remember" checked="checked"/>记住账号密码<br/>
    <input type="submit" value="登录">${requestScope.login_error}
</form>
<script type="text/javascript">
    function run() {
        var error = "${requestScope.login_error}";
        if(error == "")
        {
            //接受cookie内容
            var username = "${fn:split(cookie.log_user.value,"-")[0]}";
            var password = "${fn:split(cookie.log_user.value,"-")[1]}";
            //解码
            var new_username = decodeURI(username);
            //赋值给username input框
            document.getElementById("nameId").value = new_username;
            //赋值给password input框
            document.getElementById("pasId").value = password;
        }
    }
</script>
</body>
</html>
