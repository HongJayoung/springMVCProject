<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="../css/insertDetailCommon.css">

</head>
<body>
<h1>NEW BOARD</h1>

<hr>
<form action="boardInsert.do" method="post" enctype="multipart/form-data">
<div class="form-group">
	<label>제목 : </label>
	<input class="form-control"  type="text" name="title" value = "">
</div>

<div class="form-group">
	<label>내용 : </label>
	<input class="form-control"  type="text" name="content" value = "">
</div>

<div class="form-group">
	<label>작성자 : </label>
	<input class="form-control"  type="text" name="writer" value = "100">
</div>

<div class="form-group">
	<label>이미지 : </label>
	<input type="file" name="photos">
</div>

<input type="submit" class="btn btn-success" value="등록">
<input type="reset" class="btn btn-secondary" value="취소">
</form>
</body>
</html>