
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
</head>

<body class="mall-container">
	<jsp:include page="/jsps/comms/header.jsp"></jsp:include>
	<form class="f-cart"
		action="<c:url value="goodsOrder/orderAction!pay.action"/>"
		method="post">
		<h3>My Shopping Bag</h3>
		<table class="table">

			<c:if test="${empty shopCar}">
				<tr>
					<td class="text-center">
						<h4>THERE ARE NO ITEMS IN YOUR SHOPPING BAG.</h4>
					</td>
				</tr>
				<tr>
					<td class="text-center" style="border-top:none">
						<button onclick="history.go(-1);" class="btn btn-default">Return</button>
					</td>
				</tr>
			</c:if>

			<c:if test="${not empty shopCar}">

				<tr>
					<th style="border-top: none;">Item Description</th>
					<th class="text-center" style="border-top: none;">Color</th>
					<th class="text-center" style="border-top: none;">Size</th>
					<th class="text-center" style="border-top: none;">Qty</th>
					<th class="text-center" style="border-top: none;">Price</th>
				</tr>

				<c:forEach items="${shopCar.orderItems}" var="item" varStatus="loop">
					<tr>

						<c:forEach items="${fn:split(item.goods.goodsPics,'|')}"
							var="picName" begin="0" end="0">
							<td><a href="<c:url value="/goods/goodsAction!getEntity.action?goods_no=${item.goods.goodsNo}"/>"><img src="<c:url value="/pics/${picName}"/>"
								width="100px" border=0 id="img${picName}"></a>

								<div>  <a href="<c:url value="/goods/goodsAction!getEntity.action?goods_no=${item.goods.goodsNo}"/>">${item.goods.goodsName}</a>
									<div>
										<a
											href="<c:url value="/orderItem/itemAction!removef.action?index=${loop.index}"/>">REMOVE</a>
									</div>
								</div></td>
						</c:forEach>
						<td class="text-center text-uppercase">${item.itemColor}</td>
						<td class="text-center">${item.itemSize}</td>
						<td class="text-center">${item.itemQuantity}</td>
						<td class="text-center">$${item.goods.goodsPrice}X${item.itemQuantity}<br>$${item.itemAmount}
						</td>
					</tr>
				</c:forEach>

				<tr>
					<td colspan='5' class="text-right bottom-total">
							<span>${shopCar.orderQuantity}&nbsp;item(s)</span><span>Subtotal:<span>$${shopCar.orderAmount}</span></span>
							<span><input name="submit" type="submit"
							class="btn btn-default" value="Continue" /></span>
					</td>
				</tr>
			</c:if>
		</table>
	</form>
	<jsp:include page="/jsps/comms/footer.jsp"></jsp:include>

</body>
</html>