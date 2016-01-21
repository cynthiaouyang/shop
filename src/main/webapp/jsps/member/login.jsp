<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../comms/taglib.jsp"%>
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
</head>

<body class="mall-container">
	<jsp:include page="/jsps/comms/header.jsp"></jsp:include>
	<form id="mall-form" class="mall-form" method="post"
		action="member/memberAction!getEntity.action" novalidate>
		<header>
			<span>SIGN IN</span>
		</header>
		<div class="error-msg">${loginError}</div>
		<div>
			<div for="username">Email:</div>
			<div>
				<input type="text" id="email" labelName="Email"
					name="member.memberEmail" placeholder="Input your email here"
					required minlength="6" maxlength="30" />
			</div>
			<div>Password:</div>
			<div>
				<input id="password" name="member.memberPwd" type="password"
					labelName="Password" placeholder="Input your password here"
					required minlength="6" maxlength="16" />
			</div>
		</div>
		<footer>
			<input type="submit" name="login" value="SIGN IN" class="form-btn">
			<input type="button"  id="reset-form" value="RESET"
				class="form-btn">
		</footer>

	</form>
	<jsp:include page="/jsps/comms/footer.jsp"></jsp:include>
	<script type="text/javascript" src="js/formValidation.js"></script>
</body>
</html>
