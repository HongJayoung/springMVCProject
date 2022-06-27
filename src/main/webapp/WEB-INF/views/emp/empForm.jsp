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

<c:set var="path" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${path}/css/common.css">

<script>
$(function() {
	$("#allEmpBtn").click(f1);
	$("#empByIdBtn").click(f2);
	$("#allMgrBtn").click(f3);
	$("#empByMgrBtn").click(f4);
	$("#empByEmailBtn").click(f5);
	$("#allJobBtn").click(f6);
	$("#empByJobBtn").click(f7);
	$("#empByDeptBtn").click(f8);
	$("#empByConditionBtn").click(f9);
	$("#insertBtn").click(f10);
	$("#updateBtn").click(f11);

})

function f12(empid) {
	$.ajax({
		url:"${path}/emp/22empDelete.do/"+empid,
		type:"delete",
		success:function(resData) {
			alert(resData+"건 삭제");
		},
		fail:function() {
		}
	})
} 

function f11() {
	$.ajax({
		url:"${path}/emp/22empUpdate.do",
		type:"put",
		data:makeJson(),
		contentType: "application/json;charset=utf-8",
		success: function(resData) {
			alert(resData+"건 수정");
		}
	});
} 

function f10() {
	$.ajax({
		url:"${path}/emp/22empInsert.do",
		type:"post",
		data:makeJson(),
		contentType: "application/json;charset=utf-8",
		success: function(resData) {
			alert(resData+"건 입력");
		}
	});
} 

function makeJson() {
	//var s = $("#myFrm").serialize(); //employee_id=10&first_name=kim&...
	var arr = $("#myFrm").serializeArray();
	var obj = {};
	$.each(arr, function(index, item) {
		obj[item.name] = item.value;
	})
		
	return JSON.stringify(obj);
}

function f9() {
	var deptid = $("#inputData").val();
	var jobid = $("#inputData2").val();
	var salary = $("#inputData3").val();
	var sdate = $("#inputData4").val();
	$.ajax({
		url:`${path}/emp/22empByCondition.do/\${deptid}/\${jobid}/\${salary}/\${sdate}`,
		success:function(resData) {
			printEmp(resData);
		},
		fail:function() {
		}
	})
}
	
function f8() {
	var deptid = $("#inputData").val();
	$.ajax({
		url:"${path}/emp/22empByDept.do/"+deptid,
		success:function(resData) {
			printEmp(resData);
		},
		fail:function() {
		}
	})
}
	
function f7() {
	var jobid = $("#inputData").val();
	$.ajax({
		url:"${path}/emp/22empByJob.do/"+jobid,
		success:function(resData) {
			printEmp(resData);
		},
		fail:function() {
		}
	})
}

function f6() {
	var email = $("#inputData").val();
	$.ajax({
		url:"${path}/emp/22allJob.do",
		success:function(jobList) {
			var output = "<ul>";
			
			$.each(jobList, function(index, item) {
				output += "<li>";
				for(var prop in item) {
					output += prop+ " => " +item[prop]+"<br>";
				}
				output += "</li>";
			})
			
			$("#here").html(output+"</ul>");
		},
		fail:function() {
		}
	})
}
	
function f5() {
	var email = $("#inputData").val();
	$.ajax({
		url:"${path}/emp/22empByEmail.do/"+email,
		success:function(resData) {
			$("#here").html(resData);
		},
		fail:function() {
		}
	})
}
	
function f4() {
	var mid = $("#inputData").val();
	$.ajax({
		url:"${path}/emp/22empByMgr.do/"+mid,
		success:function(resData) {
			printEmp(resData);
		},
		fail:function() {
		}
	})
}
	
function f3() {
	$.ajax({
		url:"${path}/emp/22allMgr.do",
		success:function(resData) {
			printEmp(resData);
		},
		fail:function() {
		}
	})
}
	
function f2(empid) {
	if(typeof(empid)!="number") empid = $("#inputData").val();

	$.ajax({
		url:"${path}/emp/22empDetail.do/"+empid,
		success:function(resData) {
			
			printEmpOne(resData);
		},
		fail:function() {
		}
	})
}
	
function f1() {
	$.ajax({
		url:"${path}/emp/22empList.do",
		success:function(resData) {
			printEmp(resData);
		},
		fail:function() {
		}
	})
}

function printEmpOne(item) {
	var hdate = new Date(item["hire_date"]);
	var m = hdate.getMonth()+1;
	var d = hdate.getDate();
	
	if(m <= 9) m = "0"+m;
	if(d <= 9) d = "0"+d;
	hdate = hdate.getFullYear() + "-" + m + "-" + d;
	
	$("input[name='employee_id']").val(item["employee_id"]);
	$("input[name='first_name']").val(item["first_name"]);
	$("input[name='last_name']").val(item["last_name"]);
	$("input[name='email']").val(item["email"]);
	$("input[name='phone_number']").val(item["phone_number"]);
	$("input[name='commission_pct']").val(item["commission_pct"]);
	$("select[name='manager_id']").val(item["manager_id"]);
	$("select[name='department_id']").val(item["department_id"]);
	$("select[name='job_id']").val(item["job_id"]);
	$("input[name='hire_date']").val(hdate);
	$("input[name='salary']").val(item["salary"]);
}

function printEmp(empList) {
	var output = `<table>
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
		</tr>`;
	
	$.each(empList, function(index,item) {	
		output += 
			`<tr>
				<td>\${item.employee_id}</td>
				<td>\${item.first_name}</td>
				<td>\${item.last_name}</td>
				<td>\${item.email}</td>
				<td>\${item.phone_number}</td>
				<td>\${item.commission_pct}</td>
				<td>\${item.manager_id}</td>
				<td>\${item.department_id}</td>
				<td>\${item.job_id}</td>
				<td>\${item.hire_date}</td>
				<td>\${item.salary}</td>
				<td><button onclick = f2(\${item.employee_id})>상세보기</button></td>
				<td><button onclick = f12(\${item.employee_id})>삭제</button></td>
			</tr>`;
	})
	
	$("#here").html(output+"</table>");
}
</script>

<style>
table, td, tr {
	border: 1px solid gray;
	border-collapse: collapse;
	text-align: center;
	padding:7px;
}
</style>
</head>
<body>
<h1>RestController 연습</h1>
<img alt="img" src="${path}/images/symbol.gif">
<hr>
조회조건(공통) : <input type="text" id="inputData"><br>
조회조건(jobid) : <input type="text" id="inputData2" value="IT_PROG"><br>
조회조건(salary) : <input type="number" id="inputData3" value="0"><br>
조회조건(sdate) : <input type="date" id="inputData4" value="2007-01-01">
<hr>
<button id="allEmpBtn">모든직원조회</button>
<button id="empByIdBtn">특정직원조회</button>
<button id="allMgrBtn">모든매니저조회</button>
<button id="empByMgrBtn">특정매니저의 부하직원조회</button>
<button id="empByEmailBtn">이메일 존재?</button>
<button id="allJobBtn">모든직책조회</button>
<button id="empByJobBtn">특정직책조회</button>
<button id="empByDeptBtn">특정부서조회</button>
<button id="empByConditionBtn">조건조회</button>
<button id="insertBtn">입력</button>
<button id="updateBtn">수정</button>
<hr>
<div id="here"></div>

	<form id="myFrm">
		<div class="form-group">
			<label>직원번호 : </label>
			<input class="form-control" type="number" name="employee_id" id="employee_id">
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
						<option value="${mgr.employee_id}">${mgr.first_name}</option>
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
	</form>

</body>
</html>