$(function() {
	$('#summernote').summernote({
		  height: 300,                 // 에디터 높이
		  minHeight: null,             // 최소 높이
		  maxHeight: null,             // 최대 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정
		  placeholder: '최대 2048자까지 쓸 수 있습니다'	//placeholder 설정
          
	});
	
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
		if($("#bookTitle").val().length <= 0) {
			alert("책 제목을 입력해주세요.");
			$("#bookTitle").focus();
			return false;
		}
		if($("#author").val().length <= 0) {
			alert("작가를 입력해주세요.");
			$("#author").focus();
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
		if($("#bookTitle").val().length <= 0) {
			alert("책 제목을 입력해주세요.");
			$("#bookTitle").focus();
			return false;
		}
		if($("#author").val().length <= 0) {
			alert("작가를 입력해주세요.");
			$("#author").focus();
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