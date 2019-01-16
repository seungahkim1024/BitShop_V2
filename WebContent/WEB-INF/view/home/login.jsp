<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그룹웨어</title>
<link rel="stylesheet" href="resources/css/style.css" />
</head>
<body>
	<div style="width: 100%">
		<section>
			<article>
				<h1 id="title">회원 전용 시스템</h1>
				<table id="login-outer-tab">
					<tr>
						<td colspan="5">
							<form id="login-form">
								<table id="login-inner-tab">
									<tr>
										<td>
										<input id="uid" name="uid" type="text" placeholder="ID" tabindex="1" />
										</td>
										<td rowspan="2">
											<button id="login-btn">LOGIN</button>
										</td>
									</tr>
									<tr>
										<td>
										<input id="upw" name="upw" type="password" placeholder="PASSWORD" tabindex="2" /> 
										<input type="hidden" name="cmd" value="login" /> 
										<input type="hidden" name="dir" value="home" /> 
										<input type="hidden" name="dest" value="welcome" />
										</td>
									</tr>
								</table>
							</form> <a id="admin-link" href="#"> 관리자 </a> |
										<a id="join-link" href="#"> 회원가입 </a>
						</td>
					</tr>
				</table>
			</article>
		</section>
		</div>
	<script>
	var submit = document.getElementById("login-btn");
	submit.addEventListener('click', function(){
		
		var uid = document.getElementById("uid");
		var upw = document.getElementById("upw");
				
		if(uid.value==="" && upw.value===""){
			alert('아이디 입력값이 없어요!');
		} else {
			alert('아이디 입력값이 있어요!');
			var form = document.getElementById("login-form");
			form.action ="member.do";
			form.method = "post";
			form.submit();
		}
	});
	
	var form = document.getElementById("login-form");
	
	document.getElementById('join-link') //like 싱글톤패턴
		.addEventListener('click', function(){ //function 뒤에 이름이 없다. = 어나니머스function(이름이 없다.) =콜백(callback)패턴 = 옵저버패턴
			alert('회원가입 클릭!!');
			location.assign('member.do?dest=join-form');//맨 앞에 window.가 디폴트로 생략되어있다. ->그래서 document는 생략 불가.
		});
		// move면 클릭이벤트가 리스닝되고,
		// move()면 클릭없이 즉시 실행된다.
		// 'click', function(){} 하면 콜백함수가 호출된다.
		
	</script>
</body>
</html>