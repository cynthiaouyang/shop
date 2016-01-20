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
				action="goodsOrder/orderAction!getList.action" method="post">
				<input type="hidden" id="pageNo" name="pageNo"
					value="${page.pageNo}" /> <input type="hidden" id="endNo"
					name="endNo" value="${page.totalPageNum}" />
				<div class="form-group">
					<label for="orderNo">OrderNo</label> <input type="text"
						class="form-control" id="orderNo" name="orderHelper.orderNo"
						value="${orderHelper.orderNo}" placeholder="1">
				</div>
				<div class="form-group">
					<label for="memberEmail">Member</label> <input type="text"
						class="form-control" id="memberEmail"
						name="orderHelper.member.memberEmail"
						value="${orderHelper.member.memberEmail}" />
				</div>
				<div class="form-group">
					<label for="empNo">Employee ID</label> <input type="text"
						class="form-control" id="empNo" name="orderHelper.employee.empNo"
						value="${orderHelper.employee.empNo }" />
				</div>
				<div class="form-group">
					<label for="orderStatus">Order Status</label> <select
						class="form-control" id="orderStatus"
						name="orderHelper.orderStatus">
						<option value="5">--Select--</option>
						<option value="0"
							<c:if test="${orderHelper.orderStatus==0}"> selected </c:if>>Order</option>
						<option value="1"
							<c:if test="${orderHelper.orderStatus==1}"> selected </c:if>>Shipped</option>
						<option value="2"
							<c:if test="${orderHelper.orderStatus==2}"> selected </c:if>>Uncomment</option>
						<option value="3"
							<c:if test="${orderHelper.orderStatus==3}"> selected </c:if>>Completed</option>
						<option value="4"
							<c:if test="${orderHelper.orderStatus==4}"> selected </c:if>>Return</option>
					</select>
				</div>
				<div class="form-group">
					<label>&nbsp;</label> <input type="submit" class="btn btn-default"
						value="Query" />
				</div>
			</form>
		</div>
		<div class="m-content">
			<table class="table table-hover table-striped">
				<tr>
					<th>ID</th>
					<th>Member</th>
					<th>Emp ID</th>
					<th>Pictures</th>
					<th>Short Name</th>
					<th>Amount</th>
					<th>Color/Size</th>
					<th>Order Date</th>
					<th>Remain Time</th>
					<th>Status</th>
					<th>Tracking No</th>
					<th>Operate</th>
				</tr>
				<c:forEach items="${page.pageContent}" var="goodsOrder">


					<c:forEach items="${goodsOrder.orderItems}" var="item"
						varStatus="abc">
						<tr>
							<c:if test="${abc.index==0}">
								<td rowspan="${fn:length(goodsOrder.orderItems)}">${goodsOrder.orderNo}</td>
								<td rowspan="${fn:length(goodsOrder.orderItems)}">${goodsOrder.member.memberEmail}</td>
								<td rowspan="${fn:length(goodsOrder.orderItems)}">
									${goodsOrder.employee.empNo} <c:if
										test="${empty goodsOrder.employee}">&nbsp;</c:if>
								</td>
							</c:if>
							<c:forEach items="${fn:split(item.goods.goodsPics,'|')}"
								var="picName" begin="0" end="0">
								<td rowspan="1"><img
									src="<c:url value="/pics/${picName}"/>" width="40" height="40"
									border=0 id="img1"></td>
							</c:forEach>
							<td rowspan="1">${item.goods.goodsName}</td>
							<td rowspan="1">${item.itemQuantity}</td>
							<td rowspan="1">${item.itemColor}/${item.itemSize}</td>
							<c:if test="${abc.index==0}">
								<td rowspan="${fn:length(goodsOrder.orderItems)}"><fmt:formatDate
										pattern="yyyy-MM-dd HH:mm:ss" value="${goodsOrder.orderTime}" />
								</td>
								<td rowspan="${fn:length(goodsOrder.orderItems)}"><fmt:formatDate
										pattern="dd HH:mm:ss" value="${goodsOrder.timeLeft}" /></td>
								<td rowspan="${fn:length(goodsOrder.orderItems)}"
									id="order${goodsOrder.orderNo}"><c:if
										test="${goodsOrder.orderStatus==0}">Ordered</c:if> <c:if
										test="${goodsOrder.orderStatus==1}">Shipped</c:if> <c:if
										test="${goodsOrder.orderStatus==2}">Uncomment</c:if> <c:if
										test="${goodsOrder.orderStatus==3}">Completed</c:if> <c:if
										test="${goodsOrder.orderStatus==4}">Returned</c:if></td>
								<td rowspan="${fn:length(goodsOrder.orderItems)}"
									id="logistics${goodsOrder.orderNo}">
									${goodsOrder.logistics} <c:if
										test="${empty goodsOrder.logistics}">&nbsp;</c:if> <span
									style="display: none;" id="logisticsSpan${goodsOrder.orderNo}">
										<input type="text" name="logisticsNo${goodsOrder.orderNo}"
										id="logisticsNo${goodsOrder.orderNo}" />
										<button class="btn btn-default"
											onclick="sendOrder(${goodsOrder.orderNo});">Confirm</button>
								</span>
								</td>
								<td rowspan="${fn:length(goodsOrder.orderItems)}"
									id="send${goodsOrder.orderNo}"><c:choose>
										<c:when
											test="${goodsOrder.orderStatus==0&&admin.empType=='C'}">
											<button class="btn btn-default"
												onclick="sendWrite(${goodsOrder.orderNo});">Ship</button>
										</c:when>
										<c:when
											test="${goodsOrder.orderStatus==0&&admin.empType=='D'}">
											<button class="btn btn-default"
												onclick="sendBack(${goodsOrder.orderNo});">Return</button>
										</c:when>
										<c:otherwise>&nbsp;</c:otherwise>
									</c:choose></td>
							</c:if>
						</tr>
					</c:forEach>
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
				<button class="btn btn-default" onclick="history.go(-1);">Return</button>
			</div>
		</center>
	</c:if>
	<jsp:include page="/jsps/comms/admin_footer.jsp"></jsp:include>
	<script type="text/javascript" src="js/page.js"></script>
	<script type="text/javascript" src="<c:url value="/js/prototype.js"/>"></script>

	<script type="text/javascript">
		function sendWrite(orderNo){
		      $("#logisticsSpan"+orderNo).css("display","block");
		}
       function sendOrder(orderNo){
       var logNo=$("#logisticsNo"+orderNo).val();
        r=confirm("Ready to ship?");
        if(r){ 
        	$.ajax({url:"goodsOrder/orderAction!sendOrder.action?orderNo="+orderNo+"&logisticsNo="+logNo,success:function(xhr){
        		 $("#order"+orderNo).text("Shipped");
                 $("#send"+orderNo).text("&nbsp;");
                 $("#logistics"+orderNo).text(logNo);
        	}});
         }
       }
       function sendBack(orderNo){
        r=confirm("Confirm return?");
        if(r){         	
        	$.ajax({url:"goodsOrder/orderAction!sendBack.action?orderNo="+orderNo,success:function(xhr){
        		$("order"+orderNo).innerHTML="Returned";
                $("send"+orderNo).innerHTML="&nbsp;";
        	}});
         }
       }

       </script>
</body>
</html>
