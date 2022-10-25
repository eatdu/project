<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2> list 검색</h2>
<div>
	<form action="list">
		<input type="text" name="sword" value="${param.sword}">
		<input type="submit" value="검색">
	</form>
</div>
<c:forEach var="list" items="${my}">
	${list.no },${list.title },${list.member_name}
</c:forEach>
</body>
</html>