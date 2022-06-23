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
	$(".btnDel").on("click",f);
	
	var resultMsg = "${resultMsg}";
	if(resultMsg != "") alert(resultMsg);
})

function f() {
	var dept_id = $(this).attr("data-deptid");
	
	if(confirm(dept_id + "번 부서 삭제?")) {
		location.href = "${path}/dept/deptDelete.do?dept_id="+dept_id;
	}
}
</script>

</head>
<body>
<h1>부서목록</h1>
<hr>
<div><span><a class="left" href="${path}/dept/deptInsert.do">신규부서등록</a></span></div>
<hr>

<table>
	<tr>
		<td>부서번호</td>
		<td>부서이름</td>
		<td>매니저</td>
		<td>지역번호</td>
		<td></td>
	</tr>
	<c:forEach items="${deptList}" var="dept" varStatus="status">
	<tr>
		<td>${dept.department_id}</td>
		<td>
			<a href="${path}/dept/deptUpdate.do?dept_id=${dept.department_id}">
				${dept.department_name}
			</a>
		</td>
		<td>${dept.manager_id}</td>
		<td>${dept.location_id}</td>
		<td><button class="btnDel btn btn-outline-secondary" data-deptid="${dept.department_id}">삭제하기</button></td>
	</tr>
	</c:forEach>
</table>
</body>
</html>