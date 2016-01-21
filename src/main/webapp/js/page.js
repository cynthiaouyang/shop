function switchPage(pageNo, totalPageNum) {
	if (isNaN(pageNo)
			|| (parseInt(pageNo) < 1 || parseInt(pageNo) > totalPageNum))
		return;

	document.forms[0].pageNo.value = pageNo;
	document.forms[0].submit();
}
$(document).ready(function(){
	var pageNo=$('#pageNo').val();
	var endNo=$('#endNo').val();
	$('#li'+pageNo).addClass("active");
	if(pageNo==="1")
		$('#li0').addClass("disabled");
	else if(pageNo===endNo)
		$('#lii').addClass("disabled");
});
