<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>글 수정하기</title>
		<link href="bootstrap/bootstrap.min.css" rel="stylesheet" >
		<script src="js/jquery-3.7.1.min.js"></script>
		<script src="js/formcheck.js"></script>
	</head>
	<body>
		<div class="container">
			<!-- header -->
			<%@ include file="../pages/header.jsp" %>
			<!-- content -->
			<div class="row my-5" id="global-content">
				<div class="col">
					<div class="row text-center">
						<div class="col">
							<h2 class="fs-3 fw-bold">글 수정하기</h2>
						</div>
					</div>
					<form name="updateForm" action="updateProcess" id="updateForm"
						class="row g-3 border-primary justify-content-center" method="post"
							${not empty board.file1 ? "" : "enctype='multipart/form-data'"}>
						<input type="hidden" name="no" value="${board.no}">
						<input type="hidden" name="pageNum" value="${pageNum}">
						<div class="col-5">
							<label for="writer" class="form-label">작성자</label>
							<input type="text" class="form-control" name="writer" id="writer"
								value="${board.writer}">
						</div>
						<div class="col-5">
							<label for="pass" class="form-label">비밀번호</label>
							<input type="password" class="form-control" name="pass" id="pass">
						</div>
						<div class="col-10">
							<label for="title" class="form-label">제 목</label>
							<input type="text" class="form-control" name="title" id="title"
								value="${board.title}">
						</div>
						<div class="col-10">
							<label for="content" class="form-label">내 용</label>
							<textarea class="form-control" name="content"
								id="content" rows="10">${board.content}</textarea>
						</div>
						<c:if test="${empty board.file1}">
							<div class="col-10">
								<label for="file1" class="form-label">파 일</label>
								<input type="file" class="form=control" name="file1" id="file1">
							</div>
						</c:if>
						<div class="col-8 text-center mt-5">
							<input type="submit" value="수정하기" class="btn btn-primary"/>
							&nbsp;&nbsp;<input type="button" value="목록보기"
								onclick="location.href='boardList?pageNum=${pageNum}'" class="btn btn-primary"/>
						</div>
					</form>
				</div>
			</div>
		</div>
		<script src="bootstrap/bootstrap.bundle.min.js"></script>
	</body>
</html>