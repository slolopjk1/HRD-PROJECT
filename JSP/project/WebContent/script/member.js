function loginCheck() {
	if(document.frm.enumber.value.length == 0) {
		alert("사원번호를 써주세요");
		frm.enumber.focus();
		return false;
	}
	if(document.frm.password.value == "") {
		alert("암호는 반드시 입력해야 합니다.");
		frm.password.focus();
		return false;
	}
	return true;
}

/*function idCheck() {
	if(document.frm.enumber.value == "") {
		alert('사원번호를 입력하여 주십시오.');
		document.frm.enumber.focus();
		return;
	}
	var url = "Servlet?command=product_enumbercheck";
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");
}*/

