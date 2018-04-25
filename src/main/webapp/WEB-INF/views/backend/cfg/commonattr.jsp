<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	    <%@ include file="../res.jsp" %>
		<style>
		</style>
		<title>工资管理</title>
	</head>
	<body>
	<div class="all">
		<div class="header">
			<!-- <div class="xxylogo">
				<img src="./image/lala.png"/>
			</div> -->
			<div class="headerright">
			<span><!-- <img src="./image/group.png" style="margin-left:20px;"/> --> 超级导购.管理平台</span>
			<span></span>
			</div>
		</div>
		<div class="main">

			<div id="main-content"  class="content allleft">
				<div class="brandtitle">
						<div class="breadtitle">
							<span><img src="./image/bread.png"/>  基本配置> </span>
						</div>
				</div>
		   </div>
		</div>
	</div>
	</body>
<script type="text/javascript">
	var arrId = [];
	$(function(){
		$("#main-content").before(menuhtml());
		$("#userId").val(getParameterByName('userId'));
		$("#token").val(getParameterByName('token'));
		
	})

	
	</script>
</html>
