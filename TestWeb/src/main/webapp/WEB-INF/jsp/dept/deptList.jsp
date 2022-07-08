<%@page import="java.util.ArrayList"%>
<%@page import="co.micol.prj.dept.DeptVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deptList</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<h4>부서등록  -  jstl</h4>
<a href="DeptInsert">부서등록</a>
<table border="1">
	<tr><td>부서번호</td><td>부서명</td></tr>
	

<c:forEach items="${list}" var="dept">
	<tr><td>${dept.departmentId}</td>
		<td><a href="DeptUpdate?departmentId=${dept.departmentId}">
				${dept.departmentName}
		</a></td>

</c:forEach>	
</table>
</body>
</html>