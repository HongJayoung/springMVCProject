<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="../css/insertDetailCommon.css">

<script>
$(function() {
	$("#empCheckBtn").click(function() {
		var empid = $("#employee_id").val();
		if(empid==""||empid==null) {
			alert("직원번호는 필수입력");
			$("#employee_id").focus(); return;
		}
		$.ajax({
			url:"duplicateCheck.do",
			data:{"empid":empid},
			type:"get",
			success: function(resData) {
				//중복:1, 중복안됨:0
				$("#empMsg").html(resData==0?"사용가능":"사용불가");
			},
			fail: function() {}
		});
	});
	
	$("#emailCheckBtn").click(function() {
		var email = $("#email").val();
		if(email==""||email==null) {
			alert("이메일은 필수입력");
			$("#email").focus(); return;
		}
		$.ajax({
			url:"emailDuplicateCheck.do",
			data:{"email":email},
			type:"get",
			success: function(resData) {
				//중복:1, 중복안됨:0
				$("#emailMsg").html(resData==0?"사용가능":"사용불가");
			},
			fail: function() {}
		});
	});
});
</script>
</head>
<body>
<h1>NEW EMPLOYEE</h1> 

<hr>
<form action="empInsert.do" method="post">
<div class="form-group">
	<label>직원번호 : </label>
	<input class="form-control" type="number" name="employee_id" id="employee_id">
	<input class="btn btn-info" type="button" id="empCheckBtn" value="중복체크">
	<span id="empMsg"></span>
</div>

<div class="form-group">
	<label>first name :</label>
	<input class="form-control" type="text"  name="first_name">
</div>

<div class="form-group">
	<label>last name :</label>
	<input class="form-control" type="text"  name="last_name">
</div>

<div class="form-group">
	<label>email :</label>
	<input class="form-control" type="text"  name="email"  id="email">
	<input class="btn btn-info" type="button" id="emailCheckBtn" value="중복체크">
	<span id="emailMsg"></span>
</div>

<div class="form-group">
	<label>phone :</label>
	<input class="form-control" type="text"  name="phone_number">
</div>

<div class="form-group">
	<label>commission :</label>
	<input class="form-control" type="text"  name="commission_pct">
</div>

<div class="form-group">
	<label>manager id :</label>
		<select class="form-control" name="manager_id">
		<c:forEach items="${mgrlist}" var="mgr">
			<option value="${mgr.key}">${mgr.value}</option>
		</c:forEach>
	</select>
</div>

<div class="form-group">
	<label>department id :</label>
	<select class="form-control" name="department_id">
		<c:forEach items="${dlist}" var="dept">
			<option value="${dept.department_id}">${dept.department_name}</option>
		</c:forEach>
	</select>
</div>

<div class="form-group">
	<label>job id :</label>
	<select class="form-control" name="job_id">
		<c:forEach items="${jlist}" var="jobs">
			<option>${jobs.job_id}</option>
		</c:forEach>
	</select>
</div>

<div class="form-group">
	<label>hire date :</label>
	<input class="form-control" type="date"  name="hire_date" >
</div>

<div class="form-group">
	<label>salary :</label>
	<input class="form-control" type="text"  name="salary" >
</div>

<div>
<input type="submit" class="btn btn-success" value="등록">
<input type="reset" class="btn btn-secondary" value="취소">
<input type="button" class="btn btn-light" value="목록" id="emplist">
</div>
</form>

<script>
$(function() {
	$("#emplist").click(function() {
		location.href="emplist.do";
	});
});
</script>
</body>
</html>