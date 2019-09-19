<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param name = "h_title" value = "Join Page"/>
</jsp:include>
<style>
	th {
		text-align: right;
		padding-right: 15px;
		color: #5e9bff;
		border-bottom: solid 1.7px #5e9bff;
	}
	input[type=text]{
		width:100%;
	}
	input[type=password]{
		width:100%;
	}
	select {
		height:100%;
	}
	
</style>
<script src = "script.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('addr1').value = data.zonecode;
            document.getElementById("addr2").value = roadAddr;
            
            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("addr3").placeholder = extraRoadAddr;
            } else {
                document.getElementById("addr3").value = '';
            }
        }
    }).open();
}
</script>
<div align="center">
	<form action = "join" name = "joinForm" method = "post">
	
	<table>
		
		<tr>
			<th>
				ID
			</th>
			<td>
				<input name = "user_id" type = "text" placeholder="아이디를 입력하세요." required >
				<input type = "button" class = "button" 
						onclick = "openWindow()" value = "중복확인">
			</td>
		</tr>
		<tr>
			<th rowspan = "2">
				PASSWORD
			</th>
			<td>
				<input type = "password" name = "user_password" placeholder="비밀번호를 입력하세요." required
				onkeyup="showPasswordMessage()">
			</td>
		</tr>
		<tr>
			<td>
				<input type = "password" name = "user_re_password" placeholder="비밀번호를 한 번 더 입력하세요." required
				onkeyup="showPasswordMessage()">
				<div id = "pmessage" style="color:Red">
				</div>
			</td>
		</tr>
		<tr>
			<th>EMAIL</th>	
			<td>
				<input name = "user_email1" type = "text" placeholder="이메일  ex)test01" style = "width:60%">
				@
				<select name = "user_email2" class="custom-select">
					<option value = "None" selected="selected">직접입력</option>
					<option value = "naver.com">naver.com</option>
					<option value = "nate.com">nate.com</option>
					<option value = "gmail.com">gmail.com</option>
					<option value = "hanmail.net">hanmail.net</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>ADDR</th>
			<td>
				<div>
					<input name = "user_addr1" id = "addr1" type = "text" readonly="readonly" placeholder="우편번호" style= "width:30%"> 
					<input type = "button" class = "button" value = "검색" style = "width:30%"
					onclick = "sample4_execDaumPostcode()">
				</div>
				
				<div>
					<input name = "user_addr2" id = "addr2" type = "text" readonly="readonly" placeholder="지번, 도로명 주소">
					<input name = "user_addr3" id = "addr3"type = "text" placeholder="상세주소">
				</div>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<input type = "button" value = "가입!" 
						class ="button" style = "width:40%" onclick = "checkForm()">
				<input type = "button" value = "돌아가기 "class ="button" style = "width:45%" onclick = "history.back()">
			</td>
		</tr>
	</table>
	</form>
</div>

<jsp:include page="/layout/footer.jsp"/>
