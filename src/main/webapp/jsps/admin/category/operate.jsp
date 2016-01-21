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
			action="category/ctgAction!addEntity.action" method="post">
			<div class="page-header">
				<c:if test="${not empty  add}">
					<h3>Add Category</h3>
				</c:if>
				<c:if test="${empty  add}">
					<h3>Modify Category</h3>
				</c:if>
			</div>
			
			<div class="form-group">
				<label class="control-label" for="ctgNo">ID</label> <input
					id="ctgNo" name="category.ctgNo" type="text" class="form-control" 
					value="${category.ctgNo}" readonly>
			</div>
			<div class="form-group">
				<label class="control-label" for="category.ctgName">Name</label> <input
					id="category.ctgName" class="form-control"  name="category.ctgName" type="text"
					value="${category.ctgName}">
			</div>
			<div class="form-group">
				<label class="control-label" for="category.category.ctgNo">Parent
					Name</label> <input id="category.category.ctgNo"
					name="category.category.ctgNo" type="text" class="form-control" 
					value="${category.category.ctgNo}" readonly>
			</div>
			<div class="form-group">
				<label class="control-label" for="category.ctgMemo">Detail</label> <input
					id="category.ctgMemo" name="category.ctgMemo" type="text" class="form-control" 
					value="${category.ctgMemo}">
			</div>
			<div class="form-group">
				<c:if test="${not empty  add}">
					<input type="submit" class="btn btn-primary" name="add" value="Add" />
				</c:if>
				<c:if test="${empty  add}">
					<input type="submit" class="btn btn-primary" name="update"
						value="Modify" onclick="upt();" />
				</c:if>
			</div>

		</form>
	</div>
	<jsp:include page="/jsps/comms/admin_footer.jsp"></jsp:include>
	<script type="text/javascript">
		function upt() {
			document.forms[0].action = "category/ctgAction!uptEntity.action";
			forms[0].submit();
		}
	</script>
</body>
</html>


