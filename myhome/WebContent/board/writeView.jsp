<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page = "/layout/header.jsp">
	<jsp:param name = "h_title" value = "Board Write"/>
</jsp:include>


	<div align = "center">
	<form action = "../writeLogic.brd" method = "post">
	<table border=1 style="width:600px">
		<tr>
		<th>TITLE</th>
		<td style="width:80%">
			<input type = "text" name = "brd_title" placeholder="제목을 입력하세요."
					style="width:90%;margin: 0;" required="required">
		</td>
		</tr>
		<tr>
			<td colspan = "2">
				<textarea name = "brd_content" style="height:400px;width:100%;resize:None" required="required"">따뜻한 글은 타인을 웃게합니다.</textarea>
			</td>
		</tr>
		<tr>
			<th colspan = "2">
				<button onclick = "history.back()" class="button" style = "width:100px">뒤로</button>
				<button onclick = "submit()" class="button" style = "width:100px">쓰기</button>
			</th>
		</tr>
	</table>
	</form> 
</div>


<jsp:include page = "/layout/footer.jsp"/>