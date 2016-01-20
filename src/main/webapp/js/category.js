function findChildrenCtg(categoryNo, level) {
	var ctgNo = $('#' + categoryNo).val();
	if (ctgNo === 'xx') {
	} else {
		$.ajax({
			url : "category/ctgAction!getChildren.action?ctgNo=" + ctgNo,
			success : function(xhr) {
				if (level == '1') {
					$("#secondCtg").empty();
					$("#secondCtg").append(xhr);
				} else if (level == '2') {
					$("#thirdCtg").empty();
					$("#thirdCtg").append(xhr);
				} else if (level == '3') {
					$("#forthCtg").empty();
					$("#forthCtg").append(xhr);
				}
			}
		});
	}

}