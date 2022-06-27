<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="path" value="${pageContext.request.contextPath}"/>

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
			<input type="button" class="empDetailView btn btn-outline-primary" value="상세보기" 
					data-empid="${emp.employee_id}" data-toggle="modal" data-target="#myModal">
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