<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page = "/layout/header.jsp">
	<jsp:param name = "h_title" value = "DOWNLOADS"/>
</jsp:include>
<div align = "center">

                <!--파일업로드일땐 method 꼭 post여야함   ,     파일을 뜻한다-->
<form action="upload" method = "post" enctype="multipart/form-data">
	<table border="1">
		<tr>
			<th>파일1</th>
			<td><input type ="file" name = "file1"></td>
		
		</tr>
		<tr>
			<th>파일2</th>
			<td><input type = "file" name = "file2"></td>
		</tr>
		<tr>
			<th colspan="2">
				<input type="submit" value = "업로드!">
			</th>
		</tr>
	
	</table>


</form>

<jsp:include page ="/layout/footer.jsp"/>