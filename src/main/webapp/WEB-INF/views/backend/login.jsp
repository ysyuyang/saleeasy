<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Insert title here</title>
</head>
	<!-- <script src="res/jquery/jquery-1.11.2.min.js"></script> -->
	
<body>
	login
	<%=1 %>
	<input id="username"/>
	<input id="pwd"/>
	<input type="button" value="login"onclick="login()";
</body>
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<script>
	function login(){
		/* $.ajax({
			type:'POST',
			 
			url:"api/auth/logon",
			data:{name:"1111",pwd:"222"},
			success: function(msg){
			     
			   }
		}) */
		$.post("api/auth/logon",{name:"1111",pwd:"222"},function(data){
			debugger;
			if(data.code=="200"){
				console.log(document.cookie);
				window.location.href='main';
				console.log(document.cookie);
			}
		})
	}

</script>
</html>