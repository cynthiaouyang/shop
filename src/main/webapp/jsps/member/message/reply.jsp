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
<link rel="stylesheet" type="text/css" href="css/form.css">
<link rel="stylesheet" type="text/css" href="css/accountmenu.css">
<link rel="stylesheet" type="text/css" href="css/detail.css">
<link rel="stylesheet" type="text/css" href="css/display.css">
<style type="text/css">
#order-table th {
	text-align: center;
}

@media screen and (min-width: 992px) {
	.detail-comment {
		width:650px;
	}
}
</style>
</head>

<body class="mall-container">
	<jsp:include page="/jsps/comms/header.jsp"></jsp:include>
	<div class="account-main">
		<jsp:include page="accountmenu.jsp"></jsp:include>
		<div class="account-form">

			<form action="" method="post" name="form1" id="form1">

				<div class="detail-comment text-left">
					<div>
						<div>
							<div class="rating">
								<c:forEach begin="0" end="4" varStatus="loop">
									<c:choose>
										<c:when
											test="${goodsComment.goodsMark-loop.index<=0.5&&goodsComment.goodsMark-loop.index>0}">
											<span class="glyphicon glyphicon-star half"></span>
										</c:when>
										<c:when test="${goodsComment.goodsMark-loop.index>0}">
											<span class="glyphicon glyphicon-star"></span>
										</c:when>

										<c:otherwise>
											<span class="glyphicon glyphicon-star" empty="empty"></span>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</div>

							<div>
								By <span style="font-weight: bold;">${goodsComment.member.memberNickname}</span>
								on
								<fmt:formatDate value="${goodsComment.commentTime}" type="date" />
							</div>
						</div>
						<div>${goodsComment.commentContent}</div>
						<div>
							<div>${goodsComment.replyAmount}
								Replys&nbsp;&nbsp; <span class="btn btn-default" id="test11"
									onclick="toggleDetail(this,0)"> Detail</span>
							</div>
							<div>
								<span class="btn btn-default" onclick="toReply(0)">Reply</span>
							</div>
						</div>
						<div id="reply0" style="display: none;">
							<div>
								<textarea rows="3" name="estimate${goodsComment.commentNo}"
									id="estimate0"></textarea>
							</div>
							<div class="text-right">
							 <span class="btn btn-default" onclick="cancel(0)">
									Cancel</span>
								&nbsp;&nbsp;
								<button class="btn btn-default"
									onclick="comm('${goodsComment.commentNo}',${goodsComment.replyAmount})">
									Submit</button>
							</div>
						</div>
						<c:if test="${goodsComment.replyAmount>0}">
							<div id="hideId0" class="detail-replys">
								<c:forEach items="${goodsComment.replies}" var="reply">
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
				</div>

			</form>
		</div>
	</div>
	<jsp:include page="/jsps/comms/footer.jsp"></jsp:include>

	<script type="text/javascript" src="js/reply.js"></script>
</body>
</html>