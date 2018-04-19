<%@ page import="cn.supstore.core.base.exception.AppException" %>
<%@ page import="cn.supstore.core.base.exception.GeneralException" %><%--
  Created by IntelliJ IDEA.
  User: liusijin
  Date: 2016/5/23
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"   isErrorPage="true" %>
<html>
<head>
    <title>粗大事啦..................</title>
</head>
<body>
<h1>粗大事啦...</h1>
<p>
    发现服务端错误........<%=((GeneralException)request.getAttribute("exception")).getMsg()%>
</p>
<p>错误栈: </p>
<div>
    <pre>
        <jsp:scriptlet>
          exception.printStackTrace(new java.io.PrintWriter(out));
        </jsp:scriptlet>
    </pre>
</div>
</body>
</html>
