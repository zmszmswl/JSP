
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardInsert.jsp</title>
<script>
	function validationForm(){
// 		if(frm.id.value==""){
// 			alert("아이디 입력");
// 		return;
// 		}
		frm.submit(); 
	}

</script>
</head>
<body>


<form name="frm" action="BoardInsertServ" method="post">
	아이디 <input name="id" type="number">
	제목 <input name="title" type="text">
	컨텐츠 <input name="content" type="text">
	작성자 <input name="writer" type="text">
	날짜 <input name="rdt" type="date">
	조회수 <input name="hit" type="number">
	<button type="button" onclick="validationForm()">등록</button>

</form>

</body>
</html>