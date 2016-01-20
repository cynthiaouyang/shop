<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/comms/taglib.jsp"%>
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
<jsp:include page="/jsps/comms/admin_link.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/jsps/comms/admin_header.jsp"></jsp:include>

		<h3 style="margin:40px">Welcome ${admin.empName}!</h3>

	<jsp:include page="/jsps/comms/admin_footer.jsp"></jsp:include>
</body>
</html>
