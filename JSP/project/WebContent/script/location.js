function selectcarry() {
	var value = document.getElementById("group");
	var val = value.options[value.selectedIndex].value;


	if(val == 1) {
		location.href = 'Servlet?command=product_search';
	}else if(val == 2) {
		location.href = 'Servlet?command=product_depsearch';
	}
}
