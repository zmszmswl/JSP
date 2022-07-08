<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
	
		<div>
			<h1>회원가입</h1>
		</div>
		
		<div>
			<form id="frm" action="memberJoin.do" onsubmit="return formCheck();" method="post">
				<div>
				<table border="1">
					<tr>
						<th width="150">아이디</th>
						<td width="300">
							<input type="text" id="memberId" name="memberId" size="20">&nbsp;
							<input type="hidden" id="checkId" value="No">
							<button type="button" id="btn" onclick="idCheck()">중복체크</button>
						</td>
					</tr>
					
					<tr>
						<th width="150">비밀번호</th>
						<td width="300">
							<input type="password" id="memberPassword" name="memberPassword" size="20">&nbsp;
						
						</td>
					</tr>
					
					<tr>
						<th width="150">비밀번호 확인</th>
						<td width="300">
							<input type="password" id="password" size="20"> 
						</td>
					</tr>
					
					<tr>
						<th width="150">이름</th>
						<td width="300">
							<input type="text" id="memberName" name="memberName" size="20"> 
						</td>
					</tr>
				</table>
				</div><br>
				<div>
					<input type="submit" value="회원가입">&nbsp;&nbsp;&nbsp;
					<input type="reset" value="취 소">&nbsp;&nbsp;&nbsp;
					<input type="button" value="홈 가기" onclick="location.href='main.do'">  
					
				</div>
			</form>
		</div>
		
	</div>
	<script type="text/javascript">
	//사용자 아이디가 비어있는지
	 function formCheck() {		//submit을 눌렀을때
		if(frm.memberId.value == ""){	//내 안에서 frm 찾고 input 타입아이디 memberId가 null이면
			alert("사용자 아이디를 입력하세요.");
			frm.memberId.focus();	//form 객체가 가진 memberId 아래
		 		return false;
		}
		 // 아이디 중복체크
		 if(frm.checkId.value == "No"){		//값이 No와 같으면
			 alert("아이디 중복체크를 해주세요");
		 return false;
		 }
		 // 
		 if(frm.memberPassword.value != frm.password.value){
			 alert("비밀번호가 일치하지 않습니다.");
			 frm.memberPassword.value = "";
			 frm.password.value = "";
			frm.memberPassword.focus();
			return false;
		 }
		 if(frm.memberName.value == ""){
			 alert("사용자 이름을 입력하세요.");
			 frm.memberName.focus();
			 return false;
		 }
		 
		 return true;
	}
	
	function idCheck(){
		let id = frm.memberId.value;
		if (id == ""){
			alert("아이디 입력 후 중복체크 해주세요.");
			frm.memberId.focus();
			
		} else {
			// ajax를 이용하여 아이디 중복체크를 수행한다.
			const xht = new XMLHttpRequest();	// ajax 객체를 생성
			 xht.onload = function() {	//결과를 받아 처리하는 함수
			if(this.readyState == 4 && this.status == 200){
				htmlConvertAjax(this.responseText);		//성공 했을 때 수행하는 함수
			} else {
				errorAjaxCall();	//실패 했을 때 수행하는 함수
			}
			
				  }
			xht.open("GET","ajaxMemberIdCheck.do?id="+id);	//호출할 주소와 방식을 설정 . 어떤 방식으로 호출할지 , URL 주소
			xht.send();	// 호출 . POST 방식일땐 ()에 형식을 넣어줘야함
		}
	}
	
	function htmlConvertAjax(str){
		if(str == "Used"){
			alert('사용가능한 아이디 입니다.');
			frm.checkId.value = "Yes";	//중복체크 했다는 의미로 Yes로 바꿈
			frm.btn.disabled = true;
			frm.memberPassword.focus();
		} else {
			alert('사용 할 수 없는 아이디 입니다.');
			frm.memberId.value = "";	//초기화시켜줌
			frm.memberId.focus();
		}
	}
	
	function errorAjaxCall(){
		alert("네트워크 통신 장애로인해 처리할 수 없습니다. /n 잠시후 다시 해 보세요.");
	}
	
	</script>


</body>
</html>