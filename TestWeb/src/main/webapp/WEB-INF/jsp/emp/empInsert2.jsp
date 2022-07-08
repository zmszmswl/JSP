<%@page import="co.micol.prj.dept.DeptVO"%>
<%@page import="co.micol.prj.emp.EmpVO"%>
<%@page import="co.micol.prj.emp.JobsVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>empInsert.jsp</title>
<script>

	function validateForm() {
		if (window.document.frm.employeeId.value == "") {
			alert("사번입력");
			frm.employeeId.focus();
			return false;
		}

		if (window.document.frm.lastName.value == "") {
			alert("이름입력");
			frm.employeeId.focus();
			return false;
		}

		if (frm.email.value == "") {
			alert("이메일입력");
			frm.employeeId.focus();
			return false;
		}

		if (frm.hireDate.value == "") {
			alert("입사년도입력");
			frm.employeeId.focus();
			return false;
		}

		if (frm.jobId.value == "") {
			alert("직무입력");
			frm.employeeId.focus();
			return false;
		}
		var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		if (regExp.test(frm.email.value) == false) {
			alert("이메일형식");
			frm.email.focus();
			return false;
		}
		return true;
	}
</script>
<style>
	body{
	    width: 500px; /*너비*/
            padding: 15px; /*테두리와 내용 사이의 여백*/
            border:5px solid gray; /*테두리 스타일*/
            border-color: green;
            border-width: 5px;
	}
	
	form > label {
		background-color : beige;
		width:20%;
		display : inline-block;
		text-align: center;
	}
	
	
</style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<h1>사원등록</h1>

<form name="frm" action="empInsert"  method="post" onsubmit="return validateForm()">

<label for="employeeId">사원번호</label>
<input type="number" name="employeeId" id="employeeId"> <br>
<label for="lastName">사원이름</label>
<input type="text" name="lastName" id="lastName"><br>
<label for="email">이메일</label>
<input type="text" name="email" id="email"><br>
<label for="hireDate">입사년도</label>
<input type="date" name="hireDate" id="hireDate"><br>



<label for="departmentId">부서명</label>

<select name = departmentId>
<% ArrayList <DeptVO> listt = (ArrayList <DeptVO>)request.getAttribute("depts");
		for(DeptVO depts : listt) {%>
		<input type="radio" name="departmentId" value="<%=depts.getDepartmentId()%>"><%=depts.getDepartmentName() %>

<% } %>	

</select>

<label for="jobId">직무</label>
<select name="jobId">
	<% ArrayList <JobsVO> list = (ArrayList <JobsVO>)request.getAttribute("jobs");
			for(JobsVO jobs : list ) { 	%>
			<option value="<%=jobs.getJobId()%>"><%=jobs.getJobTitle() %>
			<% } %>	
</select>


<button>사원등록</button>


</body>
</html>