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
<h1>직원상세내역</h1>

<hr>
<form action="empDetail.do" method="post">
<div class="form-group">
	<label>직원번호 : ${emp.employee_id}</label>
	<input class="form-control" type="hidden"  name="employee_id" value="${emp.employee_id}">
</div>

<div class="form-group">
	<label>first name :</label>
	<input class="form-control" type="text"  name="first_name" value="${emp.first_name}">
</div>

<div class="form-group">
	<label>last name :</label>
	<input class="form-control" type="text"  name="last_name" value="${emp.last_name}">
</div>

<div class="form-group">
	<label>email :</label>
	<input class="form-control" type="text"  name="email" value="${emp.email}">
</div>

<div class="form-group">
	<label>phone :</label>
	<input class="form-control" type="text"  name="phone_number" value="${emp.phone_number}">
</div>

<div class="form-group">
	<label>commission :</label>
	<input class="form-control" type="text"  name="commission_pct" value="${emp.commission_pct}">
</div>

<div class="form-group">
	<label>manager id :</label>
	<input class="form-control" type="text"  name="manager_id" value="${emp.manager_id}">
</div>

<div class="form-group">
	<label>department id :</label>
	<input class="form-control" type="text"  name="department_id" value="${emp.department_id}">
</div>

<div class="form-group">
	<label>job id :</label>
	<input class="form-control" type="text"  name="job_id" value="${emp.job_id}">
</div>

<div class="form-group">
	<label>hire date :</label>
	<input class="form-control" type="date"  name="hire_date" value="${emp.hire_date}">
</div>

<div class="form-group">
	<label>salary :</label>
	<input class="form-control" type="text"  name="salary" value="${emp.salary}">
</div>

<input type="submit" class="btn btn-success" value="수정">
<input type="reset" class="btn btn-secondary" value="취소">
</form>

</body>
</html>