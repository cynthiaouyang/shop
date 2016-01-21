
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
		action="employee/empAction!checkAdmin.action" novalidate>
		<header>
			<span>Admin Login</span>
		</header>
		<div class="error-msg">${loginError}</div>
		<div>
			<div for="username">ID</div>
			<div>
				<input type="text" id="ID" labelName="ID" name="employee.empNo"
					placeholder="Input your ID here" required minlength="1"
					maxlength="10" />
			</div>
			<div>Password:</div>
			<div>
				<input id="password" name="employee.empPwd" type="password"
					size="20" placeholder="Input your password here" required
					minlength="6" maxlength="10" />
			</div>
		</div>
		<footer>
			<input type="submit" name="login" value="LOGIN" class="form-btn">
			<input type="button" id="reset-form" value="RESET" class="form-btn">
		</footer>

	</form>
	<jsp:include page="/jsps/comms/footer.jsp"></jsp:include>
	<script type="text/javascript" src="js/formValidation.js"></script>
</body>
</html>
