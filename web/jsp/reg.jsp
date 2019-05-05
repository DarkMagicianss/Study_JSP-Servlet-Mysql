<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>注册页面</title>
</head>
<body onload="load()">
    <form name="reg_form" action="${pageContext.request.contextPath}/reg" method="post" onsubmit="return run()">
        <table border="1" width="700">
            <tr>
                <td width="100" height="30">用户名</td>
                <td>
                    <input type="text" name="username"/>
                    <font color="red"><span id="uspanID"></span></font>
                </td>
            </tr>
            <tr>
                <td width="100" height="30">密码</td>
                <td>
                    <input type="password" name="password"/>
                    <font color="red"><span id="pspanID"></span></font>
                </td>
            </tr>
            <tr>
                <td width="100" height="30">确认密码</td>
                <td>
                    <input type="password" name="repassword"/>
                    <font color="red"><span id="repspanID"></span></font>
                </td>
            </tr>
            <tr>
                <td width="100" height="30">昵称</td>
                <td>
                    <input type="text" name="nickname"/>
                    <font color="red"><span id="nspanID"></span></font>
                </td>
            </tr>
            <tr>
                <td width="100" height="30">邮箱</td>
                <td>
                    <input type="text" name="email"/>
                    <font color="red"><span id="espanID"></span></font>
                </td>
            </tr>
            <tr>
                <td width="100" height="60">验证码</td>
                <td>
                    <input type="text" name="checkcode"/>
                    <img id="imgId" src="${pageContext.request.contextPath}/checkcode">
                    <a href="#" onclick="refresh_checkcode(); return false;">看不清?</a>
                    <font color="red"><span id="codespanID"></span></font>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="注册"/></td>
            </tr>
        </table>
    </form>
</body>
<script type="text/javascript">
    //完成校验 run()    返回false
    //用户名不能为空
    //密码不能为空,不能少于6位
    //确认密码和密码一致
    //昵称不能为空
    //邮箱格式正确 /.+@.+\.[a-zA-Z]{2,4}$/
    //可以提交
    function run() {
        //用户名不能为空
        //也可以通过document.form1.username.value获取username的内容 form1为form的名称
        var name = document.reg_form.username.value;
        var uspan = document.getElementById("uspanID");
        if(name === "" || name.length === 0){
            uspan.innerText = " 用户名不能为空 ";
            return false;
        } else{
            uspan.innerText = "  ";
        }
        //密码不能为空,不能少于6位
        var psd = document.reg_form.password.value;
        var pspan = document.getElementById("pspanID");
        if(psd === "" || psd.length < 6){
            pspan.innerText = " 密码为空/密码小于6位 ";
            return false;
        }else{
            pspan.innerText = "  ";
        }
        //确认密码和密码一致
        var repsd = document.reg_form.repassword.value;
        var repspan = document.getElementById("repspanID");
        if(repsd !== psd || repsd === ""){
            repspan.innerText = " 确认密码为空/确认密码和密码不一致 ";
            return false;
        }else{
            repspan.innerText = "  ";
        }
        //昵称不能为空
        var nickname = document.reg_form.nickname.value;
        var nspan = document.getElementById("nspanID");
        if(nickname === "" || nickname.length === 0){
            nspan.innerText = " 昵称不能为空 ";
            return false;
        }else{
            nspan.innerText = "  ";
        }
        //验证邮箱格式是否正确
        var reg = /.+@.+\.[a-zA-Z]{2,4}$/;
        var email = document.reg_form.email.value;
        var espan = document.getElementById("espanID")
        if(!reg.test(email)){
            espan.innerText = " 邮箱不能为空/邮箱格式错误 ";
            return false;
        }else{
            espan.innerText = "  ";
        }
        //验证码不能为空
        var checkcode = document.reg_form.checkcode.value;
        var codespan = document.getElementById("codespanID")
        if(checkcode === ""){
            codespan.innerText = " 验证码不能为空 ";
            return false;
        }else{
            nspan.innerText = "  ";
        }
        return true;
    }
    function refresh_checkcode() {
        var image = document.getElementById("imgId");
        image.src = "${pageContext.request.contextPath}/checkcode?"+new Date().getTime();
    }
    function load(){
        var username_error = "${requestScope.username_error}";
        var email_error = "${requestScope.email_error}";
        var checkcode_error = "${requestScope.checkcode_error}";
        if(username_error != "" || email_error != "" ||checkcode_error != "")
        {
            document.getElementById("uspanID").innerText = "${requestScope.username_error}";
            document.getElementById("espanID").innerText = "${requestScope.email_error}";
            document.getElementById("codespanID").innerText = "${requestScope.checkcode_error}";
            //接受request中转发回来的内容
            var username = "${fn:split(requestScope.req_user,"-")[0]}";
            var password = "${fn:split(requestScope.req_user,"-")[1]}";
            var nickname = "${fn:split(requestScope.req_user,"-")[2]}";
            var email = "${fn:split(requestScope.req_user,"-")[3]}";
            //解码
            var new_username = decodeURI(username);
            var new_nickname = decodeURI(nickname);
            //赋值给input框
            document.reg_form.username.value = new_username;
            document.reg_form.password.value = password;
            document.reg_form.repassword.value = password;
            document.reg_form.nickname.value = new_nickname;
            document.reg_form.email.value = email;
            refresh_checkcode();
        }
    }
</script>
</html>
