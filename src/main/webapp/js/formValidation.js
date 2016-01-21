$(document)
		.ready(
				function() {
					// validate form
					validate("#mall-form");
					// validate again when submit the form
					$("#mall-form")
							.submit(
									function() {
										var valid = true;
										$
												.each(
														$(this)[0],
														function(i, input) {
															if (!input
																	.checkValidity()) {

																if ($("#hint").length === 0) {
																	$(
																			"<em class='hint' id='hint'>Please make sure all your inputs are correct</em>")
																			.insertAfter(
																					$("header"));
																}
																input.focus();
																// console.log(i);
																return valid = false;
																;
															}
														});
										return valid;
									});
					// reset the form remove all the message
					$('#reset-form').click(function() {
						$("#mall-form")[0].reset();
						$(".init").remove();
						$(".error").remove();
						$(".error-img").remove();
						$(".success-img").remove();
						$(".hint").remove();
					});
					$.getJSON("json/state.json", function(data) {
						var items = [];
						$.each(data, function(key, val) {
							items.push(new myStates(key, val));
							$(
									'<option value="' + key + '">' + val
											+ '</option>').appendTo(
									$('#memberLocation'));
						});
						var val=$('#memberLocation').attr('value');
						if(val!=undefined){
							$('#memberLocation').val(val);
						}
					});
					

				});

function myStates(name, abbr) {
	this.name = name;
	this.abbr = abbr;
}

function validate(formId) {
	validateInput($(formId).find(':input:text'));
	validateInput($(formId).find(':input[type="email"]'));
	validateInput($(formId).find(':input:password'));
	validateSelect($(formId).find(':input[type="radio"]'), [ "gender" ],
			"radio");
	validateDate($(formId).find(':input[type="date"]'));
	validateInput($(formId).find('select'));
	// validateInput($(formId).find(':input[type="color"]'));
	// validateSelect($(formId).find(':input[type="checkbox"]'), ["reason"],
	// "checkbox");
	// validateInput($(formId).find('textarea'));
}

// validate function for radio and checkbox element
function validateSelect(data, selectName, stype) {
	for (var i = 0; i < selectName.length; i++) {
		var sRequire = false;
		$.each(data, function(j, input) {
			var labelName = $(this).attr('labelName');
			var id = selectName[i];
			if (labelName === selectName[i]) {
				if (!sRequire) {
					sRequire = $(this).attr('required');
				}
				if (sRequire) {
					$(this).click(
							function() {
								if (stype === "radio") {
									setSuccess(id);
									// if ($("#" + id + "-img").length === 0)
									// $("<em class='success-img' id='" + id +
									// "-img'></em>").insertAfter($("#" + id));
								} else if (stype === "checkbox") {
									var checked = "error-img";
									if ($(
											"input[name='"
													+ $(this).attr('name')
													+ "']").is(':checked')) {
										setSuccess(id);
									} else {
										setError(id, undefined);
									}

								}

							});
				}
			}

		});
	}
}

// validate function for input element
function validateInput(data) {
	$.each(data, function(i, input) {
		var id = $(this).attr('id');
		var required = $(this).attr('required');
		var minlength = $(this).attr('minlength');
		var maxlength = $(this).attr('maxlength');
		var labelName = $(this).attr('labelName');
		var title = $(this).attr('title');
		$(this).focus(function() {
			if ($("#" + id + "-img").length === 0) {
				initialMessage(id, labelName, minlength, maxlength, title);
			}
		});
		$(this).on(
				'keyup change',
				function(e) {
					var code = (e.keyCode ? e.keyCode : e.which);
					if (code !== 9) {
						$("#" + id + "-init").remove();
						var len = $(this).val().length;
						addMessage(this, id, labelName, minlength, maxlength,
								len, required);
					}
				});
		$(this).focusout(function() {
			$("#" + id + "-init").remove();
		});
		$(this).change(function() {
			$("#" + id + "-init").remove();
		});
	});
}

// validate function for date element
function validateDate(data) {
	$.each(data, function(i, input) {
		var id = $(this).attr('id');
		var required = $(this).attr('required');
		var labelName = $(this).attr('labelName');
		var minDate = $(this).attr('minDate');
		var title = $(this).attr('title');
		$(this).focus(function() {
			if ($("#" + id + "-img").length === 0) {
				initialMessage(id, labelName, undefined, undefined, title);
			}
		});
		$(this).change(
				function() {
					$("#" + id + "-init").remove();
					var maxDate = new Date();
					var chooseDate = new Date($(this).val());
					var error = true;
					if (chooseDate < maxDate) {
						if ((minDate != undefined && chooseDate > new Date(
								minDate))
								|| minDate === undefined) {
							error = false;
						}
					}
					// var show = "success";
					if (error) {
						// show = "error";
						setError(id, undefined)
					} else {
						setSuccess(id);
					}
					// $("#" + id + "-img").remove();
					// $("<em class='" + show + "-img' id='" + id +
					// "-img'></em>").insertAfter($("#" + id));

				});
		$(this).focusout(function() {
			$("#" + id + "-init").remove();
		});

	});

}

// show the hint message
function initialMessage(id, labelName, minlength, maxlength, title) {
	var initialMsg = "";
	if (title != undefined) {
		initialMsg = title;
	} else {
		if (minlength != undefined && maxlength != undefined) {
			if (minlength === maxlength) {
				initialMsg = "Your " + labelName + " should be " + minlength
						+ " characters";
			} else {
				initialMsg = "Your " + labelName + " should be at least "
						+ minlength + " and at most " + maxlength
						+ " characters";
			}
		} else if (minlength != undefined) {
			initialMsg = "Your " + labelName + " should be at least "
					+ minlength + " characters";
		} else if (maxlength != undefined) {
			initialMsg = "Your " + labelName + " should be at most "
					+ maxlength + " characters";
		}
	}
	if (initialMsg != "") {
		$("<em class='init' id='" + id + "-init'>" + initialMsg + "</em>")
				.insertBefore($("#" + id));
	}
}

// add the error/success message
function addMessage(input, id, labelName, minlength, maxlength, len, required) {
	var errorMsg = "";
	if (minlength != undefined && len < minlength && len != 0) {
		errorMsg = "Your " + labelName + " is too short";
	} else if (maxlength != undefined && len > maxlength) {
		errorMsg = "Your " + labelName + " is too long";
	} else if (len == 0 && required) {
		errorMsg = "Your " + labelName + " is required";
	} else if (!input.checkValidity()) {
		errorMsg = "Your " + labelName + " is illegal";
	} else if ($(input).attr('type') === 'password') {
		var pwd1 = $('#password').val();
		var pwd2 = $('#pwdCheck').val();
		if (pwd1 !== "" && pwd2 !== undefined && pwd2 !== "")
			if (pwd1 !== pwd2) {
				id = "pwdCheck";
				errorMsg = "Password not matched";
			} else {
				setSuccess('password');
				setSuccess('pwdCheck');
			}
	}
	if (errorMsg != "") {
		setError(id, errorMsg);
	} else {
		setSuccess(id);
	}
}

// add the error message
function setError(id, errorMsg) {
	$("#" + id + "-error").remove();
	$("#" + id + "-img").remove();
	if (errorMsg != undefined) {
		$("<em class='error' id='" + id + "-error'>" + errorMsg + "</em>")
				.insertBefore($("#" + id));
	}
	$("<em class='error-img' id='" + id + "-img'></em>").insertAfter(
			$("#" + id));
}

// add the success message
function setSuccess(id) {
	$("#" + id + "-error").remove();
	$("#" + id + "-img").remove();
	$("<em class='success-img' id='" + id + "-img'></em>").insertAfter(
			$("#" + id));
}
