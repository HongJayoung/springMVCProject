<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="listSize" value="${boardDatas.size()}"></c:set>
	
<c:if test="${empty boardDatas}">
<p>검색결과가 없습니다.</p>
</c:if>	

<c:if test="${!empty boardDatas}">
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
	<c:forEach items="${boardDatas}" var="board" varStatus="rowStatus">
		<td>${listSize - rowStatus.index}</td>
		<td>${board.bno}</td>
		<td><a href="${path}/board/boardDetail.do?bno=${board.bno}">${board.title}</a></td>
		<td>${board.content}</td>
		<td>${board.writer}</td>
		<td>${board.regdate}</td>
		<td>${board.updatedate}</td>
		<td><button class="btnDel btn btn-outline-secondary"
				data-bno="${board.bno}">삭제</button></td>
		</tr>
	</c:forEach>
</table>
</c:if>	