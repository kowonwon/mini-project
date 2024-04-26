<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>게시 글 리스트</title>
		<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
		<script src="js/jquery-3.7.1.min.js"></script>
		<script src="js/formcheck.js"></script> 
	</head>
	<body class="bg-success-subtle">
		<div class="container">
			<!-- header -->
			<%@ include file="../pages/header.jsp" %>
			<!-- content -->
			<form name="searchForm" id="searchForm" action="#"
				class="row justify-content-center my-3">
				<div class="col-auto">
					<select name="type" class="form-select">
						<option value="title">글제목</option>
						<option value="writer">작성자</option>
						<option value="bookTitle">책제목</option>
						<option value="author">작가</option>
						<option value="content">내용</option>
					</select>
				</div>
				<div class="col-2">
					<input type="text" name="keyword" class="form-control" id="keyword"/>
				</div>
				<div class="col-auto">
					<input type="submit" value="검 색" class="btn btn-primary"/>
				</div>
			</form>
			<!-- 검색 요청일 경우 -->
			<c:if test="${searchOption}">
				<div class="row my-3">
					<div class="col text-center">
						"${keyword}" 검색 결과
					</div>
				</div>
				<div class="row my-3">
					<div class="col">
						<a href="boardList" class="btn btn-outline-success">리스트</a>
					</div>
					<div class="col text-end">
						<a href="writeForm" class="btn btn-outline-success">글쓰기</a>
					</div>
				</div>
			</c:if>
			<!-- 검색 요청이 아닐 경우 -->
			<c:if test="${not searchOption}">
				<div class="row text-end">
					<div class="col">
						<a href="writeForm" class="btn btn-outline-success">글쓰기</a>
					</div>
				</div>
			</c:if>
			<div class="row">
				<div class="col-2 text-center border border-success h-25">
					<h5>카테고리</h5>
					<a href="#" class="text-decoration-none link-secondary">일상</a><br>
					<a href="#" class="text-decoration-none link-secondary">독서</a>
				</div>
				<div class="col-10">
					<table class="table table-hover">
						<thead>
							<tr class="table-warning">
								<th>no</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
							</tr>
						</thead>
						<tbody>
						<!-- 검색 요청 + 게시 글이 있는 경우 -->
							<c:if test="${searchOption and not empty bList}">
								<c:forEach var="b" items="${bList}">
									<tr>
										<td>${b.no}</td>
										<td><a href="boardDetail?no=${b.no}&pageNum=${currentPage}&type=${type}&keyword=${keyword}"
											class="text-decoration-none link-secondary">${b.title}</a></td>
										<td><a href="boardDetail?no=${b.no}&pageNum=${currentPage}&type=${type}&keyword=${keyword}"
											class="text-decoration-none link-secondary">${b.writer}</a></td>
										<td><fmt:formatDate value="${b.regDate}"
											pattern="yyyy-MM-dd HH:mm:ss"/></td>
									</tr>
								</c:forEach>
							</c:if>
							<!-- 일반 글 + 게시 글이 있는 경우 -->
							<c:if test="${not searchOption and not empty bList}">
								<c:forEach var="b" items="${bList}">
									<tr>
										<td>${b.no}</td>
										<td><a href="boardDetail?no=${b.no}&pageNum=${currentPage}"
											class="text-decoration-none link-secondary">${b.title}</a></td>
										<td><a href="boardDetail?no=${b.no}&pageNum=${currentPage}"
											class="text-decoration-none link-secondary">${b.writer}</a></td>
										<td><fmt:formatDate value="${b.regDate}"
											pattern="yyyy-MM-dd HH:mm:ss"/></td>
									</tr>
								</c:forEach>
							</c:if>
							
							<!-- 검색 요청 + 게시 글이 없는 경우 -->
							<c:if test="${searchOption and empty bList}">
								<tr>
									<td colspan="6" class="text-center">게시 글이 존재하지 않습니다.</td>
								</tr>
							</c:if>
							
							<!-- 일반 글 + 게시 글이 없는 경우 -->
							<c:if test="${not searchOption and empty bList}">
								<tr>
									<td colspan="6" class="text-center">게시 글이 존재하지 않습니다.</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			
			<!-- 페이지네이션: 검색 요청 + 리스트 존재 -->
			<c:if test="${searchOption and not empty bList}">
				<div class="row">
					<div class="col">
						<nav aria-label="Page navigation">
							<ul class="pagination justify-content-center">
								<c:if test="${startPage > pageGroup}">
									<li class="page-item">
										<a class="page-link" href="boardList?pageNum=${startPage - pageGroup}&type=${type}&keyword=${keyword}">Pre</a>
									</li>
								</c:if>
								<c:forEach var="i" begin="${startPage}" end="${endPage}">
									<c:if test="${i == currentPage}">
										<li class="page-item active" aria-current="page">
											<span class="page-link">${i}</span>
										</li>
									</c:if>
									<c:if test="${i != currentPage}">
										<li class="page-item">
											<a class="page-link" href="boardList?pageNum=${i}&type=${type}&keyword=${keyword}">${i}</a>
										</li>
									</c:if>
								</c:forEach>
								<c:if test="${endPage < pageCount}">
									<li class="page-item">
										<a class="page-link" href="boardList?pageNum=${startPage + pageGroup}&type=${type}&keyword=${keyword}">Next</a>
									</li>
								</c:if>
							</ul>
						</nav>
					</div>
				</div>
			</c:if>
			
			<!-- 페이지네이션: 일반 글 + 리스트 존재 -->
			<c:if test="${not searchOption and not empty bList}">
				<div class="row">
					<div class="col">
						<nav aria-label="Page navigation">
							<ul class="pagination justify-content-center">
								<c:if test="${startPage > pageGroup}">
									<li class="page-item">
										<a class="page-link" href="boardList?pageNum=${startPage - pageGroup}">Pre</a>
									</li>
								</c:if>
								<c:forEach var="i" begin="${startPage}" end="${endPage}">
									<c:if test="${i == currentPage}">
										<li class="page-item active" aria-current="page">
											<span class="page-link">${i}</span>
										</li>
									</c:if>
									<c:if test="${i != currentPage}">
										<li class="page-item">
											<a class="page-link" href="boardList?pageNum=${i}">${i}</a>
										</li>
									</c:if>
								</c:forEach>
								<c:if test="${endPage < pageCount}">
									<li class="page-item">
										<a class="page-link" href="boardList?pageNum=${startPage + pageGroup}">Next</a>
									</li>
								</c:if>
							</ul>
						</nav>
					</div>
				</div>
			</c:if>
			
		</div>
		<script src="bootstrap/bootstrap.bundle.min.js"></script>
	</body>
</html>