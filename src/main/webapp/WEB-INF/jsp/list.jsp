<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Test Page</title>
</head>
<body>
	<h2>Hello!</h2>
	<div>JSP List Test</div>
	<c:forEach var="item" items="${list}" varStatus="idx"> 
		${item.svr_name}st, Hello! ${item.queue_name} <br />
	</c:forEach>
	
	
	<form action="/insertQueue" method="post">
    	Server 이름 : <input type="text" name="svr_name" value="server1" size="30"><br>
        Queue 이름 : <input type="text" name="queue_name" value="dst_queue_server1" size="50"> <br>
        
        <input type="submit" value="Queue 등록">
    </form>
	
</body>

</html>
