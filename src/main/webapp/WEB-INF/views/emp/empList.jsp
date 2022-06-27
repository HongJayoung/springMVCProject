<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
$(function() {
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
	
	$("#deptBtn").click(function() {
		call("${path}/emp/deptSearch.do", {dept:$("#deptSelect").val()});
	});
	
	$("#mgrBtn").click(function() {
		call("${path}/emp/mgrSearch.do", {mgr:$("#mgrSelect").val()});
	});
	
	$("#jobBtn").click(function() {
		call("${path}/emp/jobSearch.do", {job:$("#jobSelect").val()});
	});
})
</script>

</head>

<body>
<h1>EMPLOYEE LIST</h1>
<hr>
<a class="left" href ="${path}/emp/empInsert.do">신규등록</a>
<hr>

<select name="department_id" id="deptSelect">
	<c:forEach items="${dlist}" var="dept">
		<option value="${dept.department_id}">${dept.department_name}</option>
	</c:forEach>
</select>
<button id="deptBtn">부서로 조회 </button>

<select name="manager_id" id="mgrSelect">
	<c:forEach items="${mgrlist}" var="mgr">
		<option value="${mgr.employee_id}">${mgr.first_name}</option>
	</c:forEach>
</select>
<button id="mgrBtn">매니저로 조회</button>

<select name="job_id" id="jobSelect">
	<c:forEach items="${jlist}" var="jobs">
		<option>${jobs.job_id}</option>
	</c:forEach>
</select>
<button id="jobBtn">직책으로 조회</button>
<hr>
<div id="here">
<table>
	<tr>
		<td>직원번호</td>
		<td>성</td>
		<td>이름</td>
		<td>이메일</td>
		<td>전화번호</td>
		<td>커미션</td>
		<td>매니저</td>
		<td>부서번호</td>
		<td>직책</td>
		<td>입사일</td>
		<td>급여</td>
		<td></td>
	</tr>
	
	<c:forEach items="${emplist}" var="emp" varStatus="status">
	<tr>
		<td><a href="${path}/emp/empDetail.do?employee_id=${emp.employee_id}">
			${emp.employee_id}
		</a></td>
		<td>${emp.first_name}</td>
		<td>${emp.last_name}</td>
		<td>${emp.email}</td>
		<td>${emp.phone_number}</td>
		<td>${emp.commission_pct}</td>
		<td>${emp.manager_id}</td>
		<td>${emp.department_id}</td>
		<td>${emp.job_id}</td>
		<td>${emp.hire_date}</td>
		<td>
			<fmt:formatNumber value="${emp.salary}" type="currency" currencySymbol="$"/>
		</td>
		<td>
			<form action="${path}/emp/empDelete.do" method="post">
				<input type="hidden" name="employee_id" value="${emp.employee_id}">
				<input class="btn btn-outline-secondary" type="submit" value="삭제">
			</form>
		</td>
	</tr>
	</c:forEach>
</table>
</div>

</body>
</html>