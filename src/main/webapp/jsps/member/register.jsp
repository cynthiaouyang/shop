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
		action="member/memberAction!register.action" novalidate>
		<header>
			<span>SIGN UP</span>
		</header>
		<div class="error-msg">${registerError}</div>
		<div>
			<div for="username">Email:</div>
			<div>
				<input type="email" id="email" labelName="Email"
					name="member.memberEmail" placeholder="Input your email here"
					required minlength="6" maxlength="30" />
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
					placeholder="Input your password here again" required minlength="6"
					maxlength="16" />
			</div>
			<div>NickName:</div>
			<div>
				<input id="memberNickName" name="member.memberNickname" type="text"
					labelName="NickName" placeholder="Input your NickName here"
					required minlength="6" maxlength="16" />
			</div>
			<div>Date of birth:</div>
			<div>
				<input id="memberBirth" name="member.memberBirth" type="date"
					labelName="Birthday" placeholder="Input your NickName here"
					required />
			</div>
			<div>
				<span id="gender">Gender:</span>
			</div>
			<div class="f-radio">
				<span> <input type="radio" name="member.memberSex"
					labelName="gender" value="M" required />Male
				</span> <span> <input type="radio" name="member.memberSex"
					labelName="gender" value="F" required />Female
				</span>
			</div>
			<div>Location:</div>
			<div>
				<select name="member.memberLocation" id="memberLocation" labelName="Location"
					class="f-combobox" required title="Please choose your location">
					<option value="">--Select--</option>
				</select>
			</div>
			<div>Security Question:</div>
			<div>
				<input type="text" name="member.memberpwdQuestion" id="pwdQuestion"
					labelName="Security Question"
					placeholder="Input your Security Question here" required
					minlength="6" maxlength="16" />
			</div>
			<div>Security Answer:</div>
			<div>
				<input type="text" name="member.memberPwdAnswer" id="pwdAnswer"
					labelName="Security Answer"
					placeholder="Input your Security Answer here" required
					minlength="6" maxlength="16" />
			</div>
		</div>
		<footer>
			<input type="submit" name="login" value="SIGN UP" class="form-btn">
			<input type="button" id="reset-form" value="RESET" class="form-btn">
		</footer>

	</form>
	<jsp:include page="/jsps/comms/footer.jsp"></jsp:include><script
		type="text/javascript" src="js/formValidation.js"></script>
</body>
</html>
