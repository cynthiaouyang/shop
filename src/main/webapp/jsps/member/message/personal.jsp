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
<style type="text/css">
#order-table th {
	text-align: center;
}
</style>
</head>

<body class="mall-container">
	<jsp:include page="/jsps/comms/header.jsp"></jsp:include>
	<div class="account-main">

		<jsp:include page="accountmenu.jsp"></jsp:include>
		<div class="account-form">
			<c:if test="${empty page.pageContent}">
				<center>
					<div style="width:650px;margin-top:30px;">
						<span class="failMsg">No Record</span>
						<button onclick="history.go(-1);" class="btn btn-default">Return</button>
					</div>
				</center>
			</c:if>
			<c:if test="${not empty page.pageContent}">

				<form action="" method="post" name="form1" id="form1">
					<input type="hidden" name="pageNo" id="pageNo" value="1" />

					<table class="table table-striped table-hover accounttable"
						id="order-table">
						<tr>

							<th>Picture</th>
							<th>Details</th>
							<th>SubTotal</th>
							<th>Status</th>
							<th>Time Remaining</th>
							<th>Tracking No.</th>

						</tr>

						<c:forEach items="${page.pageContent}" var="order">
							<c:forEach items="${order.orderItems}" var="item" varStatus="abc">
								<tr style="font-size: 0.8em;">
									<c:forEach items="${fn:split(item.goods.goodsPics,'|')}"
										var="picName" begin="0" end="0">

										<td><img src="<c:url value="/pics/${picName}"/>"
											width="60" border=0 id="img1"></td>
									</c:forEach>
									<td><div>${item.goods.goodsName}</div>
										<div>${item.itemAmount}*${item.itemQuantity}&nbsp;&nbsp;&nbsp;
											${item.itemColor}/${item.itemSize}</div></td>
									<c:if test="${abc.index==0}">
										<td rowspan="${fn:length(order.orderItems)}">
											<div>${order.orderAmount }</div>
											<div>
												<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
													value="${order.orderTime}" />
											</div>
										</td>
										<td rowspan="${fn:length(order.orderItems)}"><c:if
												test="${order.orderStatus==0}">Processing</c:if> <c:if
												test="${order.orderStatus==1}">
												<button class="btn btn-default"
													onclick="makeOrder(${order.orderNo});">Receive</button>
											</c:if> <c:if test="${order.orderStatus==2}">
												<button class="btn btn-default"
													onclick="estimate(${order.orderNo});">Comment</button>
											</c:if> <c:if test="${order.orderStatus==3}">Completed</c:if> <c:if
												test="${order.orderStatus==4}">Returned</c:if></td>


										<c:choose>
											<c:when test="${order.orderStatus==3}">
											</c:when>
											<c:when test="${order.orderStatus==4}">
												<td rowspan="${fn:length(order.orderItems)}">&nbsp;</td>

											</c:when>
											<c:otherwise>
												<td rowspan="${fn:length(order.orderItems)}"><fmt:formatDate
														pattern="dd-HH:mm:ss" value="${order.timeLeft}" /></td>
											</c:otherwise>
										</c:choose>


									</c:if>
									<c:if test="${order.orderStatus==3}">
										<c:forEach items="${item.goods.goodsComments}" var="comment"
											begin="0" end="0">
											<td rowspan="1">Reply:${comment.replyAmount}<br> <a class="btn btn-default"
												href="goodsComment/commentAction!getReply.action?commentNo=${comment.commentNo}">View</a>
											</td>
										</c:forEach>

									</c:if>
									<c:if test="${abc.index==0}">
										<td rowspan="${fn:length(order.orderItems)}"><c:if
												test="${empty order.logistics}">&nbsp;</c:if>${order.logistics}
										</td>
									</c:if>
								</tr>

							</c:forEach>

						</c:forEach>

					</table>

				</form>

				<td><%@ include file="/jsps/comms/page.jsp"%>
			</c:if>
		</div>
	</div>
	<jsp:include page="/jsps/comms/footer.jsp"></jsp:include>
	<script type="text/javascript">

	function estimate(orderNo){	
	    $('#form1').attr("action","goodsOrder/orderAction!estimate.action?orderNo="+orderNo);
		$('#form1').submit();
	}
	function makeOrder(orderNo){
	
	   var r=confirm("Confirm?")   
       if (r==true)   
       {   
            $('#form1').attr("action","goodsOrder/orderAction!makeOrder.action?orderNo="+orderNo);
		    $('#form1').submit();
       }  
	
	}
	</script>
</body>
</html>