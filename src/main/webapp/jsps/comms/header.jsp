<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="taglib.jsp"%>
<!DOCTYPE html>
<html>
<div>
	<div class="logo-container">
		<a href="<c:url value="/jsps/main.jsp"/>">
			<div class="logo"></div>
		</a>
	</div>
	<ul class="top-btn">
		<li><a><span class="glyphicon glyphicon-menu-hamburger"
				style="font-size: 1.5em; top: 7px;" onclick="showMenu()"></span></a></li>
	</ul>
	<ul class="top-menu">

	</ul>
</div>
<ul class="mall-menu" style="margin-bottom: 0px;">
	<c:forEach items="${allCtg.categories}" var="ctg">
		<li><a id='${ctg.ctgName}'>${ctg.ctgName}</a>
			<div class="sub-menu" main-menu='${ctg.ctgName}' onclick="showMenu()">
				<c:choose>
					<c:when test="${ctg.ctgNo==1||ctg.ctgNo==2}">
						<c:forEach items="${ctg.categories}" var="ctgSecond">
							<ul>
								<li><a>${ctgSecond.ctgName}</a></li>
								<c:forEach items="${ctgSecond.categories}" var="ctgThird">
									<li><a
										href="<c:url value="/goods/goodsAction!getListGoods.action?act=query&thirdCtg=${ctgThird.ctgNo}"/>">${ctgThird.ctgName}</a></li>
								</c:forEach>
							</ul>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="row">
							<c:forEach items="${ctg.categories}" var="ctgSecondName">
								<div class="col-sm-12 col-lg-12">
									<span class="sub-menu-name">${ctgSecondName.ctgName}</span>
									<c:forEach items="${ctgSecondName.categories}" var="ctgSecond">
										<ul>
											<li><a>${ctgSecond.ctgName}</a></li>
											<c:forEach items="${ctgSecond.categories}" var="ctgThird">
												<li><a
													href="<c:url value="/goods/goodsAction!getListGoods.action?act=query&thirdCtg=${ctgThird.ctgNo}"/>">${ctgThird.ctgName}</a></li>
											</c:forEach>
										</ul>
									</c:forEach>
								</div>
							</c:forEach>
						</div>
					</c:otherwise>
				</c:choose>
			</div></li>
	</c:forEach>
	<li><a
		href="<c:url value="/orderItem/itemAction!showCar.action"/>"><span
			class="glyphicon glyphicon-shopping-cart"></span></a>
		<div class="sub-menu right-menu" id="shopcart">
			<ul>
				<c:if test="${not empty shopCar}">
					<li class="text-uppercase  text-right"><div
							style="float: left;">Shopping Bag</div>
						<div class="text-right">Subtotal:&nbsp;$${shopCar.orderAmount}</div></li>
					<li class="text-uppercase  text-right"><div
							style="float: left;">
							<button type="button" onclick="shopCart()">Bag Details</button>
						</div>
						<div class="text-right">
							<button onclick="checkOut()">Check out</button>
						</div></li>
					<c:forEach items="${shopCar.orderItems}" var="item"
						varStatus="loop">
						<c:choose>
							<c:when test="${loop.first}">
								<ul class="shopcart">
							</c:when>
							<c:when test="${loop.index%3==0}">
								<ul class="shopcart">
							</c:when>
						</c:choose>
						<li><c:forEach items="${fn:split(item.goods.goodsPics,'|')}"
								var="picName" begin="0" end="0">
								<div>
									<a
										href="<c:url value="/goods/goodsAction!getEntity.action?goods_no=${item.goods.goodsNo}"/>">
										<img src="<c:url value="/pics/${picName}"/>" width="120px"
										border=0 id="img${picName}">
									</a>
								</div>
							</c:forEach>
							<div>
								<div class="text-uppercase">
									<span onclick="removeItem(${loop.index})">Remove</span>
								</div>
								<div class="text-uppercase">${item.goods.goodsShortName}</div>
								<div class="text-uppercase">
									<div>
										<span style="margin-right: 70px;">${item.itemColor}</span><span>${item.itemSize}</span>
									</div>
									<div>
										<span style="margin-right: 25px;">$${item.goods.goodsPrice}X${item.itemQuantity}</span><span
											style="color: red">$${item.itemAmount}</span>
									</div>
								</div>
								<div class="empty-div">empty</div>
								<div class="empty-div">empty</div>
							</div></li>
						<c:choose>
							<c:when test="${loop.last}">
			</ul>
			</c:when>
			<c:when test="${(loop.index+1)%3==0&&!loop.first}">
</ul>
</c:when>
</c:choose>
</c:forEach>
</c:if>

<c:if test="${empty shopCar}">
	<li class="text-uppercase text-right"><div style="float: left;">Shopping
			Bag</div>
		<div class="text-right">Empty</div></li>
</c:if>
</ul>
</div>
</li>
<li><a id='user'><span class="glyphicon glyphicon-user"></span></a>
	<div class="sub-menu right-menu" main-menu='user' onclick="showMenu()">
		<ul>
			<c:if test="${empty loginMember}">
				<li><a href="<c:url value="/jsps/member/login.jsp"/>">SIGN
						IN</a></li>
				<li><a href="<c:url value="/jsps/member/register.jsp"/>">SIGN
						UP</a></li>
				<li><a href="<c:url value="/jsps/admin/admin_login.jsp"/>">ADMIN
						LOGIN</a></li>
			</c:if>
			<c:if test="${not empty loginMember}">
				<li><a
					href="<c:url value="/goodsOrder/orderAction!myMessage.action"/>">MY
						ACCOUNT</a></li>
				<li><a
					href="<c:url value="/member/memberAction!logout.action"/>">LOG
						OUT</a></li>
			</c:if>
		</ul>
	</div></li>
</ul>
<div class="main-head">
	FREE SHIPPING ON ORDERS OVER $75 <a>SEE DETAILS</a><span>|</span><a
		href="<c:url value="/jsps/member/register.jsp"/>">SIGN UP</a> TO OUR
	NEWSLETTER
</div>
</html>