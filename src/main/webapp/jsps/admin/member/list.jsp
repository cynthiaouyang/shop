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
				action="member/memberAction!getList.action" method="post">
				<input type="hidden" id="pageNo" name="pageNo"
					value="${page.pageNo}" /> <input type="hidden" id="endNo"
					name="endNo" value="${page.totalPageNum}" />
				<div class="form-group">
					<label for="email">Email</label> <input type="email"
						class="form-control" id="email" name="memberHelper.memberEmail"
						value="${memberHelper.memberEmail}" placeholder="abc@gmail.com">
				</div>
				<div class="form-group">
					<label for="nickName">NickName</label> <input type="text"
						class="form-control" id="nickName"
						name="memberHelper.memberNickname"
						value="${memberHelper.memberNickname}" placeholder="Jane">
				</div>
				<div class="form-group">
					<label for="memberType">Member Type</label> <select
						name="memberHelper.memberType" id="memberType">
						<option value="6">--Select--</option>
						<option value="0"
							<c:if test="${memberHelper.memberType=='0'}"> selected </c:if>>VIP0</option>
						<option value="1"
							<c:if test="${memberHelper.memberType=='1'}"> selected </c:if>>VIP1</option>
						<option value="2"
							<c:if test="${memberHelper.memberType=='2'}"> selected </c:if>>VIP2</option>
						<option value="3"
							<c:if test="${memberHelper.memberType=='3'}"> selected </c:if>>VIP3</option>
						<option value="4"
							<c:if test="${memberHelper.memberType=='4'}"> selected </c:if>>VIP4</option>
						<option value="5"
							<c:if test="${memberHelper.memberType=='5'}"> selected </c:if>>VIP5</option>
					</select>
					<button type="submit" class="btn btn-default">Query</button>
				</div>
			</form>
		</div>
		<div class="m-content">
			<table class="table table-hover table-striped">
				<tr>
					<th>Email</th>
					<th>NickName</th>
					<th>Point</th>
					<th>Rank</th>
					<th>Money Left</th>
					<th>Register Date</th>
				</tr>
				<c:forEach items="${page.pageContent}" var="member">

					<tr>
						<td>${member.memberEmail}</td>
						<td>${member.memberNickname}</td>
						<td>${member.memberRank}</td>
						<td>VIP${member.memberType}</td>
						<td>${member.moneyLeft}</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd"
								value="${member.memberRegtime}" /></td>
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
