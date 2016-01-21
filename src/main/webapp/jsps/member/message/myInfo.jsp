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
		<form id="mall-form" class="mall-form account-form" method="post"
			action="member/memberAction!uptEntity.action" novalidate>
			<header>
				<span>MY INFO</span>
			</header>
			<%-- 	<div class="error-msg">${registerError}</div> --%>
			<div>
				<div for="username">Email:</div>
				<div>
					<input type="email" id="email" labelName="Email"
						name="member.memberEmail" value="${member.memberEmail}" readonly />
				</div>
				<div>NickName:</div>
				<div>
					<input id="memberNickName" name="member.memberNickname" type="text"
						labelName="NickName" placeholder="Input your NickName here"
						value="${member.memberNickname}" required minlength="6"
						maxlength="16" />
				</div>
				<div>Date of birth:</div>
				<div>
					<input id="memberBirth" name="member.memberBirth" type="date"
						 value="<fmt:formatDate value="${member.memberBirth}" pattern="yyyy-MM-dd"/>"
						labelName="Birthday" placeholder="Input your NickName here"
						required />
				</div>
				<div>
					<span id="gender">Gender:</span> 
				</div>
				<div class="f-radio">
					<span> <input type="radio" name="member.memberSex"
						labelName="gender" value="M" required
						<c:if  test="${member.memberSex=='M'}"> checked  </c:if> />Male
					</span> <span> <input type="radio" name="member.memberSex"
						labelName="gender" value="F" required
						<c:if  test="${member.memberSex=='F'}"> checked  </c:if> />Female
					</span>
				</div>
				<div>Location:</div>
				<div>
					<select name="member.memberLocation" id="memberLocation"
						labelName="Location" class="f-combobox" required
						title="Please choose your location"
						value="${member.memberLocation }">
						<option value="">--Select--</option>
					</select>
				</div>
			</div>
			<footer>
				<input type="submit" name="login" value="SUBMIT" class="form-btn">
				<input type="button" id="reset-form" value="RESET" class="form-btn">
			</footer>
		</form>
		</div>
	<jsp:include page="/jsps/comms/footer.jsp"></jsp:include>
	<script type="text/javascript" src="js/formValidation.js"></script>
</body>
</html>

