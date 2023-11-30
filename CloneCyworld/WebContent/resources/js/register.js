function registerCheck() {
	var id = document.frm.id.value;
	var pw = document.frm.pw.value;
	var form = document.querySelector("form");
	var pwCheck = document.frm.pwcheck.value;
	var dupliId = document.frm.dupliId.value;
	var existingErrorMsg = document.querySelector("#errorMsg");
	
	if(existingErrorMsg) {
		form.removeChild(existingErrorMsg);
	}
	
	if(id === "") {
		alert("아이디를 입력해주세요.");
		document.frm.id.focus();
		return;
	}
	
	if(pw === "") {
		alert("비밀번호를 입력해주세요.");
		document.frm.pw.focus();
		return;
	}
	
	if(pwCheck === "") {
		alert("비밀번호 확인을 입력해주세요.");
		document.frm.pwcheck.focus();
		return;
	}
	
	if(pw !== pwCheck) {
		var errorMsg = document.createElement("p");
		errorMsg.id = "errorMsg";
		errorMsg.style.color = "red";
		errorMsg.textContent = "비밀번호가 맞지 않습니다.";
		form.insertBefore(errorMsg, form.querySelector(".email-wrapper"));
		return false;
	}
	
	if(id != dupliId) {
		alert("아이디 중복 체크를 다시 해주세요");
		document.frm.id.focus();
		return;
	}
	
	if(dupliId.length === 0) {
		alert("아이디 중복 체크를 해주세요");
		document.frm.id.focus();
		return;
	}
	
	document.frm.submit();
	
}

function idCheck() {
	var url = "idCheckServlet?id=" + document.frm.id.value;
	window.open(url, "_blank", "toolbar=no, menubar=no, scrollbar=yes, resizable=no, width=450, height=200");
}

function idOk() {
	opener.frm.id.value=document.idCheckForm.id.value;
	opener.frm.dupliId.value=document.idCheckForm.id.value;
	self.close();
}
