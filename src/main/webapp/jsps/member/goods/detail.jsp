
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
<link rel="stylesheet" type="text/css" href="css/detail.css">
<link rel="stylesheet" type="text/css" href="css/carousel.css">
<link rel="stylesheet" type="text/css" href="css/display.css">
</head>

<body class="mall-container">
	<jsp:include page="/jsps/comms/header.jsp"></jsp:include>
	<form action="orderItem/itemAction!showCar.action" method="post"
		name="form1" id="form1">
		<div class="row detail-main">
			<div class="col-xs-12 col-sm-6 col-md-6 detail-pics">
				<div style="display: inline-block">
					<div id="carousel-pic" class="carousel slide" data-ride="carousel">
						<!-- Wrapper for slides -->
						<div class="carousel-inner" role="listbox">
							<c:forEach items="${fn:split(goods.goodsPics,'|')}" var="picName"
								varStatus="loop" begin="0" end="2">
								<div class="item <c:if test="${loop.index==0}">active</c:if>">
									<img src="<c:url value="pics/${picName}"/>" id="img1">
								</div>
							</c:forEach>
						</div>
						<a class="left carousel-control" href="#carousel-pic"
							role="button" STYLE="background-image: url(images/prev.png)"
							data-slide="prev"> </a> <a class="right carousel-control"
							href="#carousel-pic" role="button"
							STYLE="right: 10px; background-image: url(images/next.png)"
							data-slide="next"> </a>
					</div>
				</div>
				<input type="hidden" name="goods.goodsNo " value="${goods.goodsNo}" />
			</div>
			<div class="col-xs-12 col-sm-6 col-md-6">
				<div>
					<h3>${goods.goodsName}</h3>
				</div>
				<div>$${goods.goodsPrice}</div>
				<div class="rating">
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
					<span class="d-sold">${goods.soldAmount}&nbsp;&nbsp;Sold</span>
				</div>
				<div class="mar-left">
					<c:forEach items="${fn:split(goods.goodsColor,'|')}"
						varStatus="loop" var="goodsColor">
						<input type="radio" name="goods.goodsColor"
							<c:if test="${loop.count==1}">checked="checked"</c:if>
							style="top: -20px; position: relative;" id="g${goodsColor}"
							value="${goodsColor}">
						<label for="g${goodsColor}" class="g-${goodsColor}"></label>

					</c:forEach>
				</div>
				<div class="mar-left">
					<c:forEach items="${fn:split(goods.goodsSize,'|')}" var="goodsSize"
						varStatus="loop">
						<input type="radio" id="size${goodsSize}" name="goods.goodsSize"
							<c:if test="${loop.count==1}">checked="checked"</c:if>
							value="${goodsSize}" id="size${goodsSize}">
						<label for="size${goodsSize}" class="size${goodsSize}">${goodsSize}</label>
					</c:forEach>
				</div>
				<div>
					<select class="form-control" id="num" name="num"
						style="width: 190px">
						<c:forEach begin="1" end="5" varStatus="loop">
							<option value="${loop.index}">${loop.index}</option>
						</c:forEach>
					</select>
				</div>
				<div>
					<input type="button" onclick="addCart();" value="ADD"
						class="btn btn-default" />
				</div>
				<div class="d-detail">${goods.goodsDesc}</div>
			</div>
		</div>
		<c:if test="${not empty page.pageContent}">
			<div class="detail-comment">
				<c:forEach items="${page.pageContent}" var="comment" varStatus="abc">
					<div>
						<div>
							<div class="rating">
								<c:forEach begin="0" end="4" varStatus="loop">
									<c:choose>
										<c:when
											test="${comment.goodsMark-loop.index<=0.5&&comment.goodsMark-loop.index>0}">
											<span class="glyphicon glyphicon-star half"></span>
										</c:when>
										<c:when test="${comment.goodsMark-loop.index>0}">
											<span class="glyphicon glyphicon-star"></span>
										</c:when>

										<c:otherwise>
											<span class="glyphicon glyphicon-star" empty="empty"></span>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</div>

							<div>
								By <span style="font-weight: bold;">${comment.member.memberNickname}</span>
								on
								<fmt:formatDate value="${comment.commentTime}" type="date" />
							</div>
						</div>
						<div>${comment.commentContent}</div>
						<div>
							<div>${comment.replyAmount}
								Replys&nbsp;&nbsp; <span class="btn btn-default" id="test11"
									onclick="toggleDetail(this,${abc.index})"> Detail</span>
							</div>
							<div>
								<span class="btn btn-default" onclick="toReply('${abc.index}')">Reply</span>
							</div>
						</div>
						<div id="reply${abc.index}" style="display: none;">
							<div>
								<textarea rows="3" name="estimate${comment.commentNo}"
									id="estimate${abc.index}"></textarea>
							</div>
							<div class="text-right">
								<span class="btn btn-default" onclick="cancel(${abc.index})">
									Cancel</span> &nbsp;&nbsp;
								<button class="btn btn-default"
									onclick="comm('${comment.commentNo}',${comment.replyAmount})">
									Submit</button>
							</div>
						</div>
						<c:if test="${comment.replyAmount>0}">
							<div id="hideId${abc.index}" class="detail-replys">
								<c:forEach items="${comment.replies}" var="reply">
									<div>
										<div>${reply.replyTxt}</div>
										<div class="text-right" style="font-size: 0.9em">
											By <span style="font-weight: bold;">${reply.member.memberNickname}</span>
											on
											<fmt:formatDate value="${reply.replyTime}" type="date" />
										</div>
									</div>
								</c:forEach>
							</div>
						</c:if>
					</div>
				</c:forEach>
				<%@ include file="/jsps/comms/page.jsp"%>
			</div>
		</c:if>
		<div style="clear: both;"></div>
	</form>
	<jsp:include page="/jsps/comms/footer.jsp"></jsp:include>
	<script type="text/javascript" src="js/reply.js"></script>
	<script type="text/javascript">
 function addCart() {
   var number=$('#num').val();
   var color=$('input[name="goods.goodsColor"]:checked').val();
   var size=$('input[name="goods.goodsSize"]:checked').val();
$.ajax({url:"orderItem/itemAction!addCar.action?goodsNo="+${goods.goodsNo}+"&num="+number+"&color="+encodeURI(encodeURI(color))+"&size="+size,success:function(xhr){
	showCartDetail(xhr);
}});
 }
</script>

</body>
</html>
