<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>ajax开发---xml返回</title>

<script type="text/javascript">
	var xml; //声明全局，因为要在多个函数中使用.
	window.onload = function() {
		//第一步:得到XMLHttpRequest对象.
        var xmlHttp = null;
        if (window.XMLHttpRequest) {// code for all new browsers
            xmlhttp = new XMLHttpRequest();
        } else if (window.ActiveXObject) {// code for IE5 and IE6
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        //2.设置回调函数
		xmlhttp.onreadystatechange = function() {
			//5.处理响应数据  当信息全部返回，并且是成功
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				xml = xmlhttp.responseXML; //得到的数据将province中信息添加
				//得到xml是一个Document对象，它代表的是服务器端返回的xml.
				//将xml中所有province的name子元素中的文本获取到.
				var ps = xml.getElementsByTagName("province");
				for ( var i = 0; i < ps.length; i++) {
					//得到每一个province元素下的第一个name子元素
					var nameElement = ps[i].getElementsByTagName("name")[0];
					//得到name元素中的文本信息,就是省份名称
					var pname = nameElement.firstChild.nodeValue;
					//创建option标签，将其添加到省份下拉框中.
					var option = document.createElement("option");
					option.text = pname;
					//将创建的option添加到省份下拉框中
					document.getElementById("province").add(option);
				}
			}
		};
		//post请求方式，参数设置
		xmlhttp.open("GET", "${pageContext.request.contextPath}/ajax");
		xmlhttp.send(null);

	};

	//创建一个函数，用于向城市下拉框中添加值.
	function fillCity() {
		var province=document.getElementById("province");//省份下拉框
		var city=document.getElementById("city");//城市下拉框.
		//每一次向城市中添加信息时，将信息重置。不然会一直重复添加 不删减
		city.innerHTML="<option>--请选择城市--</option>";
		//1.得到选中的省份名称.
		//这里的操作是html Dom里面的操作 基于xml Dom 方便但是有一些不兼容
		//这里text获取的是option标签内部的信息
		//而标签本身的value是.....value
		var pname=province.options[province.selectedIndex].text;
		var ps = xml.getElementsByTagName("province");
		for ( var i = 0; i < ps.length; i++) {
			//得到每一个province元素下的第一个name子元素
			var nameElement = ps[i].getElementsByTagName("name")[0];
			//得到name元素中的文本信息,就是省份名称
			var pElementName = (nameElement.firstChild.nodeValue);
			if(pname==pElementName){
				//判断选择的省份名称与xml文件中的省份名称一致.
				var citys=ps[i].getElementsByTagName("city");
				//得到所有省份的城市
				for(var j=0;j<citys.length;j++){
					//得到所有的城市名称
					var cname=citys[j].getElementsByTagName("name")[0].firstChild.nodeValue;
					//创建option添加到城市下拉框中
					var option = document.createElement("option");
					option.text = cname;
					city.add(option);
				}
			}
		}
	}
</script>

</head>

<body>
	<select id="province" onchange="fillCity()">
		<option>--请选择省份--</option>
	</select>
	<select id="city">
		<option>--请选择城市--</option>
	</select>
</body>
</html>
