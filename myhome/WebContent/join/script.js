function showPasswordMessage(){
	var form = document.joinForm;
	var pw1 = form.user_password.value;
	var pw2 = form.user_re_password.value;
	var messageDiv = document.getElementById("pmessage");
	var message = pw1 == pw2 ? "두 비밀번호가 일치합니다." : "두 비밀번호가 일치하지 않습니다.";
	messageDiv.innerHTML = message;
	console.log("message : " + message);
	console.log("pw1 : " + pw1);
	console.log("pw2 : " + pw2);
}
function checkForm () {
	// 비밀번호 2개가 일치하는 지 확인 
	// 일치하지 않을 경우 alert()으로 "두 비밀번호가 일치하지 않습니다." 출력
	var form = document.joinForm;
	var pw1 = form.user_password;
	var pw2 = form.user_re_password;
	
	if(pw1.value != pw2.value){
		alert("두 비밀번호가 일치하지 않습니다.");
		
		pw1.value = "";
		pw2.value = ""; 
		
		pw1.focus();
		return;
	}
	form.submit();
}

function openWindow() {
	var id = joinForm.user_id.value;
	if(id == ""){
		alert("아이디를 입력하세요.");
		return;
	}
	window.open("checkId.jsp?id="+id, 
				"", 
				"width=350 height=200 left=500 top=200"
	);
}















