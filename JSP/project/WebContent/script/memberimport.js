var path;
function getFileName() {
	path = document.getElementById('fileid').files[0].name; //파일명만
	
	alert(path+"를 선택했습니다.");
}

function memberimport() {
	var msg = "사원을 추가하시겠습니까?";
	if(confirm(msg)!=0) {
		location.href = 'Servlet?command=product_memberimportaction&path='+path;
	}
	else {
		location.href = 'Servlet?command=product_memberimport';
	}
}