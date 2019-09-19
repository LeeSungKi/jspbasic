<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page = "/layout/header.jsp">
	<jsp:param name = "h_title" value = "DOWNLOADS"/>
</jsp:include>
<div align = "center">

<!--req.setAttribute("orginName1", orginName1);
	req.setAttribute("orginName2", orginName2);

	req.setAttribute("changedName1", changedName1);
	req.setAttribute("changedName2", changedName2);
	
	req.setAttribute("contentType", contentType);
	
	req.setAttribute("fileSize1", fileSize1);
	req.setAttribute("fileSize2", fileSize2);  -->
	<p>FILE1 정보</p>
	<ul>
		<li>원본 파일명: ${orginName1 }</li>
		<li>병경된 파일명 :  ${changedName1 }</li>
		<li>파일 크기 : ${fileSize1} byte</li>
	</ul>
	<p>FILE2 정보</p>
	<ul>
		<li>원본 파일명: ${orginName2 }</li>
		<li>병경된 파일명 :  ${changedName2 }</li>
		<li>파일 크기 : ${fileSize2} byte</li>
	</ul>
	<p>Content-Type : ${contentType}</p>
</div>

<jsp:include page ="/layout/footer.jsp"/>