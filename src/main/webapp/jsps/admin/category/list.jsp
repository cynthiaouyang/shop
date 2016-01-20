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
	<div class="m-query">
		<form class="form-inline"
			action="category/ctgAction!getList.action" method="post">

			<c:if test="${not empty page.pageContent}">
				<input type="hidden" id="pageNo" name="pageNo"
					value="${page.pageNo}" />
				<input type="hidden" id="endNo" name="endNo"
					value="${page.totalPageNum}" />
			</c:if>
			<div class="form-group">
				<label for="firstCtg">First Category</label><select name="firstCtg"
					id="firstCtg" onchange="findChildrenCtg('firstCtg','1');">
					<option value="xx">--Select--</option>
					<c:forEach items="${firstCategories}" var="ctg">
						<option value="${ctg.ctgNo}">${ctg.ctgName}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="secondCtg">Second Category</label> <select
					onchange="findChildrenCtg('secondCtg','2');" id="secondCtg"
					name="secondCtg">
					<option value="xx">--Select--</option>
				</select>
			</div>

			<div class="form-group">
				<label for="thirdCtg">Third Category</label> <select
					onchange="findChildrenCtg('thirdCtg','3');" id="thirdCtg"
					name="thirdCtg">
					<option value="xx">--Select--</option>
				</select>
			</div>
			<div class="form-group">
				<label for="forthCtg">Forth Category</label> <select id="forthCtg"
					name="forthCtg">
					<option value="xx">--Select--</option>
				</select>
			</div>
			<div class="form-group">
				<label>&nbsp;</label>
				<button type="submit" class="btn btn-default" onclick="query();">Query
					Category</button>
				<button type="submit" class="btn btn-default"
					onclick="querychild();">Query Sub Category</button>
			</div>
		</form>
	</div>
	<c:if test="${not empty page.pageContent}">
		<div class="m-content">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>NAME</th>
						<th>PARENT NAME</th>
						<th>AMOUNT</th>
						<th>DETAIL</th>
						<th>CREATER</th>
						<th>CREATE TIME</th>
						<th>OPERATE</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.pageContent}" var="category">
						<tr>
							<td><span>${category.ctgNo}</span></td>
							<td><span>${category.ctgName}</span></td>
							<td><span>${category.category.ctgName}</span></td>
							<td><span>${category.ctgAmount}</span></td>
							<td><span>${category.ctgMemo}</span></td>
							<td><span>${category.employee.empName}</span></td>
							<td><span>${category.ctgCreateTime}</span></td>
							<td><c:if test="${category.ctgAmount>0}">
									<a
										href="<c:url value="/category/ctgAction!preAdd.action?category.ctgNo=${category.ctgNo}"/>">Add
										Sub Category</a>
									<span class="m-insert">|</span>
								</c:if> <a
								href="<c:url value="/category/ctgAction!preUpt.action?category.ctgNo=${category.ctgNo}"/>">Modify</a><span
								class="m-insert">|</span> <a
								href="<c:url value="/category/ctgAction!delEntity.action?category.ctgNo=${category.ctgNo}&category.category.ctgNo=${category.category.ctgNo}"/>">DELETE</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div>
				<%@ include file="/jsps/comms/page.jsp"%>
			</div>
		</div>
	</c:if>



	<c:if test="${empty page.pageContent}">
		<c:if test="${not empty category.ctgName}">
			<div class="m-content">
				<table class="table table-hover table-striped">
					<thead>
						<tr>
							<th>ID</th>
							<th>NAME</th>
							<th>PARENT NAME</th>
							<th>AMOUNT</th>
							<th>DETAIL</th>
							<th>CREATER</th>
							<th>CREATE TIME</th>
							<th>OPERATE</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><span>${category.ctgNo}</span></td>
							<td><span>${category.ctgName}</span></td>
							<td><span>${category.category.ctgName}</span></td>
							<td><span>${category.ctgAmount}</span></td>
							<td><span>${category.ctgMemo}</span></td>
							<td><span>${category.employee.empName}</span></td>
							<td><span>${category.ctgCreateTime}</span></td>
							<td><c:if test="${category.ctgAmount>0}">
									<a
										href="<c:url value="/category/ctgAction!preAdd.action?category.ctgNo=${category.ctgNo}"/>">Add
										Sub Category</a>
									<span class="m-insert">|</span>
								</c:if> <a
								href="<c:url value="/category/ctgAction!preUpt.action?category.ctgNo=${category.ctgNo}"/>">Modify</a><span
								class="m-insert">|</span> <a
								href="<c:url value="/category/ctgAction!delEntity.action?category.ctgNo=${category.ctgNo}&category.category.ctgNo=${category.category.ctgNo}"/>">DELETE</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</c:if>
	</c:if>



	<jsp:include page="/jsps/comms/admin_footer.jsp"></jsp:include>
	<script type="text/javascript" src="js/page.js"></script>
	<script type="text/javascript" src="js/category.js"></script>

	<script type="text/javascript">
		function query() {
			document.forms[0].action = "category/ctgAction!getList.action?act=query";
			forms[0].submit();
		}
		function querychild() {
			document.forms[0].action = "category/ctgAction!getList.action?act=querychild";
			forms[0].submit();
		}
	</script>
</body>
</html>

