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
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/display.css">
</head>

<body class="mall-container">
	<jsp:include page="/jsps/comms/header.jsp"></jsp:include>
	<div class="main-content">
		<div class="goods-query">
			<form class="form-inline"
				action="goods/goodsAction!getListGoods.action"
				method="post" id="form1">
				<c:if test="${not empty page.pageContent}">
					<input type="hidden" id="pageNo" name="pageNo"
						value="${page.pageNo}" />
					<input type="hidden" id="endNo" name="endNo"
						value="${page.totalPageNum}" />
				</c:if>
				<div class="input-group">
					<input type="text" placeholder="Search for..."
						name="goodsHelper.goodsName" class="form-control"
						value="${goodsHelper.goodsName}" /> <span class="input-group-btn">
						<button class="btn btn-default" type="submit">
							<span class="glyphicon glyphicon-search"></span>
						</button>
					</span>
				</div>
				<div class="g-sort">
					<span onclick="price('${goodsHelper.goodsName}');">Price<span>↓</span></span>
					<span onclick="sales('${goodsHelper.goodsName}');">Sold<span>↓</span></span>
					<span onclick="grade('${goodsHelper.goodsName}');">Rating<span>↓</span></span>
					<span onclick="time('${goodsHelper.goodsName}');">Date<span>↓</span></span>
				</div>
			</form>
		</div>
		<c:if test="${not empty page.pageContent}">
			<div>
				<%@ include file="/jsps/comms/page.jsp"%>
				<div class="row" style="clear: both;">
					<c:forEach items="${page.pageContent}" var="goods"
						varStatus="currCount">

						<div class="col-sm-6 col-md-3">
							<div class="thumbnail">
								<c:forEach items="${fn:split(goods.goodsPics,'|')}"
									var="picName" begin="0" end="0">
									<a
										href="<c:url value="/goods/goodsAction!getEntity.action?goods_no=${goods.goodsNo}"/>">
										<img src="<c:url value="pics/${picName}"/>">
									</a>
								</c:forEach>
								<div class="caption rating">
									<p>
										<a
											href="<c:url value="/goods/goodsAction!getEntity.action?goods_no=${goods.goodsNo}"/>">${goods.goodsShortName}</a>
									</p>
									<p>
										<span>$${goods.goodsPrice} </span><span>${goods.soldAmount}&nbsp;&nbsp;Sold</span>
									</p>
									<p class="rating">
										<c:forEach begin="0" end="4" varStatus="loop">
											<c:choose>
												<c:when
													test="${goods.goodsMark-loop.index<=0.5&&goods.goodsMark-loop.index>0}">
													<span class="glyphicon glyphicon-star half"></span>
												</c:when>
												<c:when test="${goods.goodsMark-loop.index>0}">
													<span class="glyphicon glyphicon-star"></span>
												</c:when>

												<c:otherwise>
													<span class="glyphicon glyphicon-star" empty="empty"></span>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</p>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>

				<%@ include file="/jsps/comms/page.jsp"%>
			</div>
		</c:if>
	</div>
	<div style="clear: both;"></div>
	<jsp:include page="/jsps/comms/footer.jsp"></jsp:include>

</body>
</html>
