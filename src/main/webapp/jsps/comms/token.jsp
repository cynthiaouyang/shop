
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
</head>
<body class="mall-container">

	<jsp:include page="/jsps/comms/header.jsp"></jsp:include>
	<div class="main-content">
	<div class="text-center" style="margin-top:30px;color:#ff3333">token ERROR, Please try again later</div></div>
	<jsp:include page="/jsps/comms/footer.jsp"></jsp:include>

</body>
</html>