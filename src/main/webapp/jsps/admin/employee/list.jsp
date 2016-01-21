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

	<c:if test="${not empty page.pageContent}">
		<div class="m-query">
			<form class="form-inline"
				action="employee/empAction!getList.action" method="post">
				<input type="hidden" id="pageNo" name="pageNo"
					value="${page.pageNo}" /> <input type="hidden" id="endNo"
					name="endNo" value="${page.totalPageNum}" />
				<div class="form-group">
					<label for="empId">ID</label> <input type="text"
						class="form-control" id="empId" name="emp.empNo"
						value="${emp.empNo}" placeholder="1">
				</div>
				<div class="form-group">
					<label for="empName">Name</label> <input type="text"
						class="form-control" id="empName" name="emp.empName"
						value="${emp.empName}" placeholder="Jane">
				</div>
				<div class="form-group">
					<label for="empAuth">Authority</label> <select name="emp.empType"
						id="empAuth">
						<option value="E">--Select--</option>
						<option value="A"
							<c:if test="${emp.empType=='A'}"> selected </c:if>>Super
							Admin</option>
						<option value="B"
							<c:if test="${emp.empType=='B'}"> selected </c:if>>Order
							Manage</option>
						<option value="C"
							<c:if test="${emp.empType=='C'}"> selected </c:if>>Delivery</option>
						<option value="D"
							<c:if test="${emp.empType=='D'}"> selected </c:if>>Customer
							Service</option>
					</select>
					<button type="submit" class="btn btn-default">Query</button>
				</div>
			</form>
		</div>
		<div class="m-content">
			<table class="table table-hover table-striped">
				<tr>
					<th>ID</th>
					<th>NAME</th>
					<th>PASSWORD</th>
					<th>GENDER</th>
					<th>EMPLOYEE DATE</th>
					<th>AUTHORITY</th>
					<th>OPERATE</th>
				</tr>
				<c:forEach items="${page.pageContent}" var="employee">

					<tr>
						<td>${employee.empNo}</td>
						<td>${employee.empName}</td>
						<td>${employee.empPwd}</td>
						<td><c:choose>
								<c:when test="${employee.empSex=='M'}">Male</c:when>
								<c:otherwise>Female</c:otherwise>
							</c:choose></td>
						<td><fmt:formatDate pattern="yyyy-MM-dd"
								value="${employee.empRegdate}" /></td>
						<td><c:choose>
								<c:when test="${employee.empType=='A'}">Super Admin</c:when>
								<c:when test="${employee.empType=='B'}">Order Manage</c:when>
								<c:when test="${employee.empType=='C'}">Delivery</c:when>
								<c:otherwise>Customer Service</c:otherwise>
							</c:choose></td>
						<td><a
							href="<c:url value="/employee/empAction!preUpt.action?employee.empNo=${employee.empNo}"/>">Modify</a>
							<a
							href="<c:url value="/employee/empAction!delEntity.action?employee.empNo=${employee.empNo}"/>">Rmove</a>
						</td>
					</tr>

				</c:forEach>
			</table>
		</div>
		<div>
			<%@ include file="/jsps/comms/page.jsp"%>
		</div>
	</c:if>
	<c:if test="${empty page.pageContent}">
		<center>
			<div id="no_rec">
				<span class="failMsg">No Record</span>
				<button onclick="history.go(-1);">Return</button>
			</div>
		</center>
	</c:if>
	<jsp:include page="/jsps/comms/admin_footer.jsp"></jsp:include>
	<script type="text/javascript" src="js/page.js"></script>
</body>
</html>