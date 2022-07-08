<%@page import="co.micol.prj.board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardList.jsp</title>
</head>
<body>
boardList페이지입니다.

<form>
</form>
<table border>
	
	<thead><tr><th>아이디</th><th>제목</th><th>컨텐츠</th><th>글쓴이</th><th>날짜</th><th>조회수</th></tr></thead>
	<tbody>
<c:forEach var="vo" items="${list}">
	<tr>
		<td>${vo.id}</td>
		<td>${vo.title}</td>
		<td>${vo.content}</td>
		<td>${vo.writer}</td>
		<td>${vo.rdt}</td>
		<td>${vo.hit}</td>
		

	</tr>
</c:forEach>	
	
	</tbody></table>
</body>
</html>