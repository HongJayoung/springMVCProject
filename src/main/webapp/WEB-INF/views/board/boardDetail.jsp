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

<c:set var="path" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${path}/css/common.css">

</head>
<body>
<h1>BOARD</h1>

<hr>
<form action="${path}/board/boardUpdate.do" method="post">
<div class="form-group">
	<label>번호 : ${board.getBno()}</label>
	<input type="hidden" name="bno" value = "${board.getBno()}">
</div>
<div class="form-group">
	<label>제목 : </label>
	<input class="form-control"  type="text" name="title" value = "${board.getTitle()}">
</div>

<div class="form-group">
	<label>내용 : </label>
	<input class="form-control"  type="text" name="content" value = "${board.getContent()}">
</div>

<div class="form-group">
	<label>작성자 : </label>
	<input class="form-control"  type="text" name="writer" value = "${board.getWriter()}" disabled="disabled">
</div>

<div class="form-group">
	<label>작성일자 : </label>
	<input class="form-control"  type="text" name="writer" value = "${board.getRegdate()}" disabled="disabled">
</div>

<div class="form-group">
	<label>수정일자 : </label>
	<input class="form-control"  type="text" name="writer" value = "${board.getUpdatedate()}" disabled="disabled">
</div>

<div class="form-group">
	<label>이미지 : </label>
	<c:if test="${board.pic == null}">
		등록된 이미지가 없습니다.
	</c:if>
	<c:if test="${board.pic != null}">
		<a href="${path}/download.do?fileName=${board.pic}">
			<img src="${path}/uploads/${board.pic}" alt="이미지" width="250" height="250">
		</a>
	</c:if>
</div>

<input type="submit" class="btn btn-success" value="수정">
<input type="reset" class="btn btn-secondary" value="취소">
</form>
</body>
</html>