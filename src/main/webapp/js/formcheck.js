$(function() {
	$("#writeForm").on("submit", function() {
		if($("#writer").val().length <= 0) {
			alert("작성자를 입력해주세요.");
			$("#writer").focus();
			return false;
		}
		if($("#title").val().length <= 0) {
			alert("제목을 입력해주세요.");
			$("#title").focus();
			return false;
		}
		if($("#pass").val().length <= 0) {
			alert("비밀번호를 입력해주세요.");
			$("#pass").focus();
			return false;
		}
		if($("#content").val().length <= 0) {
			alert("내용을 입력해주세요.");
			$("#content").focus();
			return false;
		}
	})
	
	$("#update").on("click", function() {
		var pass = $("#pass").val();
		if(pass.length <= 0) {
			alert("비밀번호를 입력해주세요.");
			return false;
		}
		
		$("#rPass").val(pass);
		$("#checkForm").attr("action", "updateForm");
		$("#checkForm").attr("method", "post");
		$("#checkForm").submit();
	})
	
	$("#updateForm").on("submit", function() {
		if($("#writer").val().length <= 0) {
			alert("작성자를 입력해주세요.");
			$("#writer").focus();
			return false;
		}
		if($("#title").val().length <= 0) {
			alert("제목을 입력해주세요.");
			$("#title").focus();
			return false;
		}
		if($("#pass").val().length <= 0) {
			alert("비밀번호를 입력해주세요.");
			$("#pass").focus();
			return false;
		}
		if($("#content").val().length <= 0) {
			alert("내용을 입력해주세요.");
			$("#content").focus();
			return false;
		}
	})
	
	$("#delete").on("click", function() {
		var pass = $("#pass").val();
		if(pass.length <= 0) {
			alert("비밀번호를 입력하세요.");
			return false;
		}
		
		$("#rPass").val(pass);
		$("#checkForm").attr("action", "deleteProcess");
		$("#checkForm").attr("method", "post");
		$("#checkForm").submit();
	})
	
	$("#searchForm").on("submit", function() {
		var keyword = $("#keyword").val();
		if(keyword.length <= 0) {
			alert("검색어가 입력되지 않았습니다.\n검색어를 입력해주세요");
			return false;
		}		
		$(this).attr("method", "post");
		$(this).attr("action", "boardList");
	});
})