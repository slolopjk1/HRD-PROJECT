function setApproval(enumber, password) {
	location.href = 'Servlet?command=product_setapproval&enumber='+enumber+'&password='+password;
}

function dropApproval(enumber, password) {
	location.href = 'Servlet?command=product_dropapproval&enumber='+enumber+'&password='+password;
}

function disableapproval(button_approval, button_delete) {
	document.getElementById(button_approval).disabled = true;
	document.getElementById(button_delete).disabled = false;
}

function disabledrop(button_approval, button_delete) {
	document.getElementById(button_approval).disabled = false;
	document.getElementById(button_delete).disabled = true;
}