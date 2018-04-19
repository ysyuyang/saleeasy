<%--
  Created by IntelliJ IDEA.
  User: liusijin
  Date: 2016/5/17
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<%@ page isELIgnored="false" %>
    <title>hello</title>
    <script type="text/javascript" src="//cdn.bootcss.com/jquery/2.2.1/jquery.js"></script>
</head>

<br>
<c:forEach  items="${hashMap}" var="entry">

    <span style="${not empty entry.value? "background-color: red" : ""}">
        Key = ${entry.key}, value = ${entry.value} 
    </span>
    <button onclick="trigger('${entry.key}')">trigger</button>
    <button onclick="unlock('${entry.key}')">unlock</button>
    <br>
</c:forEach>

<script type="text/javascript">
function trigger(key){
	url = location.href;
	if (url.indexOf("?") === -1) {
		url = url + "/"+ key+'/run'
	}else{
		url = url.substr(0,url.indexOf("?"))
	}
	$.post(url)
	   .done(function(){location.reload();})
	   .fail(function(jqXHR, textStatus, errorThrown) {
		    alert( textStatus +" "+ errorThrown);
	   })
}

function unlock(key){
    url = location.href;
    if (url.indexOf("?") === -1) {
        url = url + "/"+ key+'/unlock'
    }else{
        url = url.substr(0,url.indexOf("?"))
    }
    $.post(url)
       .done(function(){location.reload();})
       .fail(function(jqXHR, textStatus, errorThrown) {
            alert( textStatus +" "+ errorThrown);
       })
}

</script>
</body>
</html>
