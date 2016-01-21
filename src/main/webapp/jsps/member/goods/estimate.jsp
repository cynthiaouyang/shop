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
<link rel="stylesheet" type="text/css" href="css/reply.css">
</head>

<body class="mall-container">
	<jsp:include page="/jsps/comms/header.jsp"></jsp:include>


	<form action="goodsComment/commentAction!addEntity.action"
		method="post">
		<input type="hidden" name="orderNo" value="${goodsOrder.orderNo}">
		<table id="replay-table">

			<c:forEach items="${goodsOrder.orderItems}" var="item"
				varStatus="abc">
				<tr align="center">
					<c:forEach items="${fn:split(item.goods.goodsPics,'|')}"
						var="picName" begin="0" end="0">

						<td><img src="<c:url value="/pics/${picName}"/>" width="150"
							border=0 id="img1"></td>
					</c:forEach>
					<td align="right">
						<table>
							<tr>
								<td>Rating：</td>
								<td><fieldset class="rating">
										<input type="radio" id="star5" name="mark${abc.index}"
											value="5" /><label class="full" for="star5"
											title="Awesome - 5 stars"></label>
										<c:forEach items="${rate}" var="rate" varStatus="loop">
											<input type="radio" id="star${5-loop.count}.5"
												name="mark${abc.index}" value="${5-loop.count}.5" />
											<label class="half" for="star${5-loop.count}.5"
												title="${rate} - ${5-loop.count}.5 stars"></label>
											<input type="radio" id="star${5-loop.count}"
												name="mark${abc.index}" value="${5-loop.count}" />
											<label class="full" for="star${5-loop.count}"
												title="${rate} - ${5-loop.count} stars"></label>

										</c:forEach>
										<input name="mark${abc.index}" type="radio" id="star0.5"
											name="rating" value="0.5" /><label class="half"
											for="star0.5" title="Sucks big time - 0.5 stars"></label>
									</fieldset></td>
							</tr>
							<tr>
								<td class="comment-td">Comment:</td>
								<td><textarea name="eatimate${abc.index}" rows="3"
										cols="30" placeHolder="This is"></textarea></td>
							</tr>
						</table>
					</td>

				</tr>

			</c:forEach>
			<tr>
				<td align="right" colspan="2"><input type="submit"
					class="btn btn-default" value="Submit" /></td>
			</tr>

		</table>

	</form>

</body>
<jsp:include page="/jsps/comms/footer.jsp"></jsp:include>
</html>