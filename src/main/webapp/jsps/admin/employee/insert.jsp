<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/comms/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
	<jsp:include page="/jsps/comms/admin_link.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/jsps/comms/admin_header.jsp"></jsp:include>
	<div class="breadcrumb">
		<form class="m-form"
			action="employee/empAction!addEntity.action" method="post">
			<div class="page-header">
				<h3>Add Employee</h3>
			</div>
			<div class="form-group">
				<label class="control-label" for="employee.empName">Name</label> <input
					type="text" class="form-control" id="employee.empName"
					name="employee.empName">
			</div>
			<div class="form-group">
				<label class="control-label" for="employee.empPwd">Password</label>
				<input type="text" class="form-control" id="employee.empName"
					name="employee.empPwd">
			</div>
			<div class="form-group">
				<label class="control-label" for="empSex">Gender</label>
				<div id="empSex">
					<label class="radio-inline"><input type="radio"
						name="employee.empSex" value="M" checked />Male </label> <label
						class="radio-inline"><input type="radio"
						name="employee.empSex" value="F" />Female </label>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label" for="empType">Authority</label>
				<div id="empType">
					<label class="radio-inline"> <input type="radio"
						name="employee.empType" value="A" checked />Super Admin
					</label> <label class="radio-inline"><input type="radio"
						name="employee.empType" value="B" />Order Manage</label> <label
						class="radio-inline"> <input type="radio"
						name="employee.empType" value="C" />Delivery
					</label> <label class="radio-inline"> <input type="radio"
						name="employee.empType" value="D" />Customer Service
					</label>
				</div>
			</div>

			<div class="form-group">
				<input type="submit" class="btn btn-primary" value="Add" />
			</div>
		</form>
	</div>
	<jsp:include page="/jsps/comms/admin_footer.jsp"></jsp:include>
</body>
</html>

