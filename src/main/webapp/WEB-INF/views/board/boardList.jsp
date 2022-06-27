<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- <link rel="stylesheet" href="../css/listCommon.css"> -->

<c:set var="path" value="${pageContext.request.contextPath}"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(function() {
	//#은 id, id는 문서 내에서 유일
	//.은 class
	$(".btnDel").click(function() {
		var bno = $(this).attr("data-bno");
		if(confirm(bno + "번 삭제?")) {
			location.href = "${path}/board/boardDelete.do?bno="+bno;
		}
	})
	
	var resultMsg = "${resultMsg}";
	if(resultMsg != "") alert(resultMsg);
	
	
	function call(url, sendData) {
		$.ajax({
			url:url,
			data:sendData,
			success:function(resData) {
				$("#here").html(resData);
			},
			fail:function() {
				
			}
		})
	}
	
	//SPA(Single Page Application)
	$("#titleBtn").click(function() {
		//alert("titleBtn 누름");
		call("${path}/board/titleSearch.do", {title:$("#inputData").val()});
	});
	
	$("#writerBtn").click(function() {
		//alert($("#inputData").val());
		call("${path}/board/writerSearch.do", {writer:$("#inputData").val()});
	});
	
	$("#dateBtn").click(function() {
		//alert("dateBtn 누름");
		call("${path}/board/dateSearch.do", {sdate:$("#sdate").val(), edate:$("#edate").val()});
	});
})
</script>

</head>
<body>
<h1>BOARD LIST</h1>
<hr>
<a class="left" href="${path}/board/boardInsert.do">게시글 작성하기</a> <br>
<input type="text" id="inputData">
<button id="titleBtn">title로 조회</button>
<button id="writerBtn">writer로 조회</button> <br>
<input type="date" id="sdate"> ~ <input type="date" id="edate">
<button id="dateBtn">작성일로 조회</button>
<hr>

<div id="here">
	<table>
		<tr>
			<td>순서</td>
			<td>번호</td>
			<td>제목</td>
			<td>내용</td>
			<td>작성자</td>
			<td>작성일</td>
			<td>수정일</td>
			<td></td>
		</tr>
		<c:set var="listSize" value="${boardDatas.size()}"></c:set>
		<c:forEach items="${boardDatas}" var="board" varStatus="rowStatus">
		<tr>
			<%-- <td>${boardSize-rowStatus.index}</td> --%>
			<td>${listSize - rowStatus.index}</td>
			<td>${board.bno}</td>
			<td><a href="${path}/board/boardDetail.do?bno=${board.bno}">${board.title}</a></td>
			<td>${board.content}</td>
			<td>${board.writer}</td>
			<td>${board.regdate}</td>
			<td>${board.updatedate}</td>
			<td><button class="btnDel btn btn-outline-secondary" data-bno="${board.bno}">삭제</button></td>
		</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>