function save_class(){
	if(frm.class_name.value==""){
		alert("과정명을 입력하세요");
	}
	else if(frm.class_day.value==""){
		alert("학습 일수를 입력하세요");
	}
	else if(Number(frm.class_price.value) < 0 || frm.class_price.value==""){
		alert("정가를 입력하세요");
	}
	else if(Number(frm.class_sales.value) < 0 || frm.class_sales.value==""){
		alert("수량료를 입력하세요");
	}
	else{
		frm.submit();
	}
}