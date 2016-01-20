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
<link rel="stylesheet" type="text/css" href="css/detail.css">
</head>
<body>
	<jsp:include page="/jsps/comms/admin_header.jsp"></jsp:include>
	<div class="breadcrumb">
		<form class="m-form"
			action="goods/goodsAction!addEntity.action"
			enctype="multipart/form-data" onsubmit="return checkForm();"
			method="post">
			<div class="page-header">
				<h3>
					<c:if test="${not empty  add}">
			Add Goods</c:if>
					<c:if test="${empty  add}">
			Modify Goods</c:if>
				</h3>
			</div>
			<input type="hidden" name="goods.category.ctgNo"
				value="${goods.category.ctgNo}" /> <input type="hidden"
				name="goods.goodsNo" value="${goods.goodsNo}" />

			<div class="form-group">
				<label class="control-label" for="goods.goodsShortName">Short
					Name</label> <input type="text" class="form-control"
					id="goods.goodsShortName" name="goods.goodsShortName"
					value="${goods.goodsShortName}">
			</div>
			<div class="form-group">
				<label class="control-label" for="goods.goodsName">Name</label> <input
					type="text" class="form-control" id="goods.goodsName"
					name="goods.goodsName" value="${goods.goodsName}">
			</div>
			<div class="form-group">
				<label class="control-label" for="goods.goodsPrice">Price</label> <input
					type="text" class="form-control" id="goods.goodsPrice"
					name="goods.goodsPrice" value="${goods.goodsPrice}">
			</div>
			<div class="form-group">
				<label class="control-label" for="goodsSize">Size</label>
				<div id="goodsSize">
					<c:forEach items="${goodsSize}" var="size">
					<input type="checkbox" name="goodsSize" value="${size}"  id="size${size}"
							<c:forEach items="${fn:split(goods.goodsSize,'|')}" var="oldsize">
					<c:if test="${size==oldsize}">checked="checked"</c:if>
					</c:forEach> />&nbsp;&nbsp;
					<label for="size${size}" >${size}</label>
					
					</c:forEach>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="goodsColor">Color</label>
				<div id="goodsColor">
					<c:forEach items="${goodsColor}" var="color">
					<input type="checkbox" name="goodsColor" value="${color}" id="g${color}" 
							<c:forEach items="${fn:split(goods.goodsColor,'|')}" var="oldcolor">
					<c:if test="${color==oldcolor}">checked="checked"</c:if>
					</c:forEach> />&nbsp;&nbsp;
					<label for="g${color}" class="g-${color}"></label>
			   </c:forEach>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label" for="goods.goodsStatus">Status</label>
				<div id="goods.goodsStatus">
					<input type="radio" name="goods.goodsStatus" value="I" checked id="g-status1"/>					
					<label for="g-status1" >ON</label>
					<input type="radio" name="goods.goodsStatus" value="O" id="g-status2"/>				
					<label for="g-status2" >OFF</label>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label" for="pictures">Pictures</label>
				<div id="pictures">
					<input type="file" name="pics" size="30" /> <input type="file"
						name="pics" size="30" /> <input type="file" name="pics" size="30" />
				</div>
			</div>
			<c:if test="${empty  add}">
				<div class="form-group">
					<label class="control-label" for="storedPic">Store Pictures</label>
					<div id="storedPic">
						<c:forEach items="${fn:split(goods.goodsPics,'|')}" var="picName">
							<input type="checkbox" name="oldPics" value="${picName}"
								checked="checked" />
							<img src="<c:url value="/pics/${picName}"/>" width="40"
								height="40" border=0 id="img1">
						</c:forEach>
					</div>
				</div>
			</c:if>
			<div class="form-group">
				<label class="control-label" for="goods.goodsDesc">Description</label>
				<textarea class="form-control" name="goods.goodsDesc" id="goodsDesc"
					rows="3" cols="40">${goods.goodsDesc}</textarea>
			</div>
			<div class="form-group">
				<c:if test="${not empty  add}">
					<input class="btn btn-primary" type="submit" id="submit"
						value="Add">
					<input class="btn btn-primary" type="reset" id="reset"
						value="Reset">
				</c:if>
				<c:if test="${empty  add}">
					<input type="submit" class="btn btn-primary" value="Modify"
						onclick="upt();" />
				</c:if>
			</div>
		</form>
	</div>
	<jsp:include page="/jsps/comms/admin_footer.jsp"></jsp:include>
	<script type="text/javascript">
		function Trim(sText) {
			return sText.replace(new RegExp("(^[\\s]*)|([\\s]*$)", "g"), "");
		}

		function upt() {
			document.forms[0].action = "goods/goodsAction!uptEntity.action";
			forms[0].submit();
		}

		function checkForm() {
			var error = "";
			var goodsPrice = Trim($('goodsPrice').value);
			if (Trim($('goodsShortName').value) == "")
				error += "商品简称不能为空！";
			if (Trim($('goodsName').value) == "")
				error += "商品全称不能为空！";
			if (goodsPrice == "")
				error += "商品价格不能为空！";
			else if (isNaN(goodsPrice))
				error += "商品价格为数字！";
			else if (parseFloat(goodsPrice) <= 0)
				error += "商品价格必须为大于0的数字！";
			if (Trim($('goodsDesc').value) == "")
				error += "商品描述不能为空！";

			if (error == "")
				return true;
			else {
				alert(error);
				return false;
			}
		}
	</script>
</body>
</html>
