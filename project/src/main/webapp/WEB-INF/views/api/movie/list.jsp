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
<h2>Naver 영화 검색</h2>
<div>
	<form action="list">
		<input type="text" name="sword" value="${param.sword}">
		<input type="submit" value="검색">
	</form>
</div>
<c:forEach var="list" items="${movies}">
	<img src="${list.image}"><a href="${list.link}">${list.title}</a><br>
</c:forEach>
</body>
</html>