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
<h1>부서상세보기</h1>

<hr>
<form action="deptUpdate.do" method="post">
<div class="form-group">
	<label>부서번호 : ${dept.getDepartment_id()}</label>
	<input class="form-control" type="hidden" name="dept_id" value="${dept.getDepartment_id()}">
</div>

<div class="form-group">
	<label>부서이름 : </label>
	<input class="form-control" type="text" name="dept_name" value="${dept.getDepartment_name()}">
</div>

<div class="form-group">
	<label>매니저 : </label>
	<input class="form-control" type="number" name="mgr_id" value="${dept.getManager_id()}">
</div>

<div class="form-group">
	<label>지역번호 : ${dept.getDepartment_id()}</label>
	<input class="form-control" type="number" name="loc_id" value="${dept.getLocation_id()}">
</div>

<input type="submit" class="btn btn-success" value="수정">
<input type="reset" class="btn btn-secondary" value="취소">
</form>
</body>
</html>