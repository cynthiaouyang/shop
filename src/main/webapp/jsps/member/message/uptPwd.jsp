<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../comms/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<jsp:include page="/jsps/comms/link.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/form.css">
<link rel="stylesheet" type="text/css" href="css/accountmenu.css">
</head>

<body class="mall-container">
	<jsp:include page="/jsps/comms/header.jsp"></jsp:include>
	<div class="account-main">

		<jsp:include page="accountmenu.jsp"></jsp:include>
		<div class="account-form">
			<form id="mall-form" class="mall-form account-form" novalidate
				action="member/memberAction!uptPwd.action" method="post">
				<input name="member.memberEmail" type="hidden" id="memberEmail"
					value="${loginMember.memberEmail}" />
				<header>
					<span>CHANGE PASSWORD</span>
				</header>
				<div class="error-msg">${registerError}</div>
				<div>
					<div for="oldMemberPwd">OLD PASSWORD:</div>
					<div>
						<input name="oldMemberPwd" type="password" id="oldMemberPwd"
							placeholder="Input your old password here" required minlength="6"
							maxlength="30" />
					</div>
					<div>Password:</div>
					<div>
						<input id="password" name="member.memberPwd" type="password"
							labelName="Password" placeholder="Input your password here"
							required minlength="6" maxlength="16" />
					</div>
					<div>Confirm Password:</div>
					<div>
						<input id="pwdCheck" name="memberPwdCheck" type="password"
							labelName="Confirm Password"
							placeholder="Input your password here again" required
							minlength="6" maxlength="16" />
					</div>

					<footer>
						<input type="submit" name="login" value="SUBMIT" class="form-btn">
						<input type="button" id="reset-form" value="RESET"
							class="form-btn">
					</footer>
			</form>
		</div>
	</div>
	<jsp:include page="/jsps/comms/footer.jsp"></jsp:include>
	<script type="text/javascript" src="js/formValidation.js"></script>
	<script type="text/javascript">
		$("#oldMemberPwd").on(
				'keyup change',
				function(e) {
					var pwd = $(this).val();
					if (pwd.length >= 6) {
						$.get(
								"member/memberAction!checkPwd.action?oldMemberPwd="
										+ pwd, function(xhr) {
									if (xhr != "")
										setError('oldMemberPwd',
												'Old Password is not correct');
									else
										setSuccess('oldMemberPwd');
								});
					}

				});
	</script>
</body>
</html>
