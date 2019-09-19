<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param name = "h_title" value = "Board Update" />
</jsp:include>

<div align = "center">
	<form action = "/myhome/updateLogic2.brd" method = "post">
	<input type = "hidden" name ="num" value = "${vo.num }">
		<table border = "1" style = "width:600px">
			<tr>
				<th>TITLE</th>
				<td style="width:80%">
					<input type = "text" name = "title"
						value = "${vo.title }" required style="width:80%">
				</td>
			</tr>
			<tr style = "height:400px">
				<td colspan = "2" >
					<textarea style = "width:100%;height:400px" 
							name = "content">${vo.content }</textarea>
				</td>
			</tr>
			<tr>
				<td colspan = "2" align ="center">
					<input type = "submit" class = "button" value = "수정!">
				</td>
			</tr>
		</table>
	</form>
</div>

<jsp:include page="/layout/footer.jsp"/>



