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
			<form id="mall-form" class="mall-form account-form">
				<header>
					<span>ACCOUNT INFO</span>
				</header>
				<div>
					<div>Points：${member.memberRank}</div>
					<div style="font-size: 1em;">Rank：VIP${member.memberType}</div>
					<div>Money：${member.moneyLeft}</div>					
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="/jsps/comms/footer.jsp"></jsp:include>
</body>
</html>