<%@page import="co.micol.prj.board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>



<script>
	function validationForm(){
			if(frm.id.value==""){
				alert("아이디 입력");
			return;
			}
			frm.submit(); 
		}
	</script>
	</head>
<body>
	<%BoardVO vo = (BoardVO)request.getAttribute("list"); %>
	<form name="frm" action="BoardListServ" method="post">
	아이디 <input name="id" type="number" value="<%=vo.getId()%>">
	제목 <input name="title" type="text" value="<%=vo.getTitle() %>">
	컨텐츠 <input name="content" type="text" value= "<%=vo.getContent() %>">
	작성자 <input name="writer"type="text"  value= "<%=vo.getWriter()%>">
	날짜 <input name="rdt" type="date" value= "<%=vo.getRdt()%>">
	조회수 <input name="hit" type="number" value= "<%=vo.getHit()%>">
	<button type="button" onclick="validationForm()">등록</button>
	
</body>	
</html>