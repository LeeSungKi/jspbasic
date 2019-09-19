<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page = "/layout/header.jsp">
	<jsp:param name = "h_title" value = "Board Read"/>
</jsp:include>
	<div align = "center">
	<table border=1 style="width:600px">
		<c:if test = "${requestScope.vo.writer == sessionScope.currentId }">
			<button onclick = "location.href='/myhome/updateLogic.brd?num=${vo.num}'" class="button" style = "width:100px; margin:10px">수정</button>
			<button onclick = "location.href='/myhome/deleteLogic.brd?num=${vo.num}'" 
					class="button" style = "width:100px; margin:10px">삭제</button>
		</c:if>
		<tr>
			<th>TITLE</th>
			<td style="width:60%">${vo.title }</td>
			<th>HIT</th>
			<td align = "center">${vo.hit }</td>
		</tr>
		<tr>
			<td colspan = "4">
				<textarea style="height:400px;width:100%" required="required" readonly="readonly">${vo.content }</textarea>
			</td>
		</tr>
		<tr>
			<th colspan = "4" align="center">
				<button onclick = "history.back()" class="button" style = "width:100px">뒤로</button>
			</th>
		</tr>
	</table>
	<p>댓글리스트</p>
	<c:if test = "${replyList != null }">
		<script>
			function showForm(name) {
				  var x = document.getElementById(name);
				  if (x.style.display === "none") {
				    x.style.display = "block";
				  } else {
				    x.style.display = "none";
				  }
			}		
		</script>
		<c:forEach var = "reVo" items = "${replyList }">
		<div style = "margin-left: ${reVo.reDepth * 50 }px;width:600px;border:1px solid black;text-align:left">
			ㄴ>${reVo.reWriter } : ${reVo.reContent } (${reVo.reRegdate }) 
			<c:if test = "${currentId != null}"> <!-- 로그인 중이면 답글 달기 가능 -->
				<input type = "button" onclick = "showForm('myForm_${reVo.reNum }')" value = "답글달기" class = "button" style = "width:100px">
				<div id = "myForm_${reVo.reNum }" style="display: None;">
					<form action = "/myhome/writeReply.brd" method = "post">
						<table style = 'width:600px'>
							<tr style="height:100px">
								<th>${currentId }</th>
								<td><textarea style="width:100%;height:100px" name = "reContent" placeholder="악플 ㄴㄴ" required="required"></textarea>
								<td><input type = "submit" value = "저장" style = "width:100%;"></td>
							</tr>
						</table>
						<!-- 이 댓글이 부모 댓글이 될 것임 -->
						<input type = "hidden" name = "reParentReNum" value = "${ reVo.reNum }">
						<!-- 이 댓글의 깊이 (현재 댓글의 대댓글이므로 깊이 +1-->
						<input type = "hidden" name = "reDepth" value = "${reVo.reDepth + 1 }">
						<!-- 이 댓글의 게시글 번호 -->
						<input type = "hidden" name = "reBoardNum" value = "${reVo.reBoardNum }">
						
					</form>
				</div>
			</c:if>
		</div>
		</c:forEach>	
		
	</c:if>
	<form action = "/myhome/writeReply.brd" method = "post">
		<!-- 부모 댓글을 없음 -->	
		<input type = "hidden" name = "reParentReNum" value = "0">
		<!-- 이 댓글의 깊이 없음-->
		<input type = "hidden" name = "reDepth" value = "0">
		<!-- 이 댓글의 게시글 번호 -->
		<input type = "hidden" name = "reBoardNum" value = "${vo.num }">
		<table style = 'width:600px'>
			<tr style="height:100px">
				<th>${currentId }</th>
				<td><textarea style="width:100%;height:100px" name = "reContent" placeholder="악플 ㄴㄴ" required="required"></textarea>
				<td><input type = "submit" value = "저장" style = "width:100%;"></td>
			</tr>
		</table>			
	</form>
</div>

<jsp:include page = "/layout/footer.jsp"/>


