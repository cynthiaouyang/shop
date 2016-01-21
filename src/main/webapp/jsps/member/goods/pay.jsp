
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
<link rel="stylesheet" type="text/css" href="css/shopcart.css">
<link rel="stylesheet" type="text/css" href="css/payform.css">
</head>

<body class="mall-container">
	<jsp:include page="/jsps/comms/header.jsp"></jsp:include>
	<form action="goodsOrder/orderAction!addEntity.action" method="post"
		onsubmit="return checkPay()" class="pay-form">

		<h4>Your order will be shipped to:</h4>

		<table class="table table-striped table-hover accounttable"
			id="addrTable">

			<tr>
				<th>Select</th>
				<th>Name</th>
				<th>Address</th>
				<th>Tel</th>
			</tr>
			<c:forEach items="${addrs}" var="addr" varStatus="loop">
				<tr>
					<td><input type="radio" name="addrBook.bookNo"
						<c:if test="${loop.first==true }">checked="checked" </c:if>
						value="${addr.bookNo}" /></td>
					<td>${addr.rev}</td>
					<td>${addr.revAddr}</td>
					<td>${addr.revPhone}</td>
				</tr>
			</c:forEach>
		</table>
		<div class="pay-bottom">
			<div>
				<a class="btn btn-default"
					href="<c:url value="addrBook/addrAction!getMemberAddrs.action?forward=info"/>">Manage
					Address</a>
			</div>
			<div>MoneyLeft:&nbsp;&nbsp;$${loginMember.moneyLeft}</div>
			<div>${shopCar.orderQuantity}Item(s)&nbsp;&nbsp;&nbsp;
				SubTotal:${shopCar.orderAmount}</div>
			<c:if test="${loginMember.moneyLeft<shopCar.orderAmount}">
				<div>Your money is not enough</div>
			</c:if>
			<c:if test="${loginMember.moneyLeft>=shopCar.orderAmount}">
				<div>
					<input name="enter" type="submit" id="enter" value="Place Order"
						class="btn btn-default">
					<button onclick="history.go(-1);" class="btn btn-default">Return</button>
				</div>
			</c:if>
		</div>
	</form>
</body>
<jsp:include page="/jsps/comms/footer.jsp"></jsp:include>
<script type="text/javascript">
	function checkPay() {
		var addr = $("input[name='addrBook.bookNo']");
		if (addr!=undefined&&addr[0]!=undefined) {
			return true;
		}
		alert("Please choose one address!");
		return false;
	}
</script>
</body>
</html>
