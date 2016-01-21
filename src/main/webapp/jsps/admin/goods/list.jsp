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
	<div class="m-query">
		<form class="form-inline"
			action="goods/goodsAction!preAdd.action" method="post"
			onsubmit="return checkChoose();">
			<!--  <input type="hidden" name="pageNo" value="${page.pageNo}" />  -->
			<c:if test="${not empty page.pageContent}">
				<input type="hidden" id="pageNo" name="pageNo"
					value="${page.pageNo}" />
				<input type="hidden" id="endNo" name="endNo"
					value="${page.totalPageNum}" />
			</c:if>
			<div class="form-group">
				<label for="firstCtg">First Category</label><select name="firstCtg"
					id="firstCtg" onchange="findChildrenCtg('firstCtg','1');">
					<option value="xx">--Select--</option>
					<c:forEach items="${firstCategories}" var="ctg">
						<option value="${ctg.ctgNo}">${ctg.ctgName}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="secondCtg">Second Category</label> <select
					onchange="findChildrenCtg('secondCtg','2');" id="secondCtg"
					name="secondCtg">
					<option value="xx">--Select--</option>
				</select>
			</div>

			<div class="form-group">
				<label for="thirdCtg">Third Category</label> <select
					onchange="findChildrenCtg('thirdCtg','3');" id="thirdCtg"
					name="thirdCtg">
					<option value="xx">--Select--</option>
				</select>
			</div>
			<div class="form-group">
				<label for="forthCtg">Forth Category</label> <select id="forthCtg"
					name="forthCtg">
					<option value="xx">--Select--</option>
				</select>
			</div>
			<div class="form-group">
				<label>&nbsp;</label>
				<button type="submit" class="btn btn-default">Add Goods</button>
				<button class="btn btn-default" type="submit" onclick="query()">Query</button>
			</div>
		</form>
	</div>

	<c:if test="${not empty page.pageContent}">
		<div class="m-content">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th class="scol">ID</th>
						<th class="scol">Category</th>
						<th class="scol">Short Name</th>
						<th class="scol">Name</th>
						<th class="scol">Price</th>
						<th class="scol">Product Number</th>
						<th class="scol">Color</th>
						<th class="scol">Status</th>
						<th class="scol">Detail</th>
						<th class="scol">Rating</th>
						<th class="scol">Picture</th>
						<th class="scol">Register time</th>
						<th class="scol">Register</th>
						<th class="scol">Operate</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.pageContent}" var="goods">
						<tr>
							<td><span>${goods.goodsNo}</span></td>
							<td><span>${goods.category.ctgNo}</span></td>
							<td><span>${goods.goodsShortName}</span></td>
							<td><span>${goods.goodsName}</span></td>
							<td><span>${goods.goodsPrice}</span></td>
							<td><span>${goods.goodsSize}</span></td>
							<td><span>${goods.goodsColor}</span></td>
							<td><span> <c:choose>
										<c:when test="${goods.goodsStatus=='I'}">on shelves</c:when>
										<c:otherwise>下架</c:otherwise>
									</c:choose>
							</span></td>
							<td><span>${goods.goodsDesc}</span></td>
							<td><span>${goods.goodsMark}</span></td>
							<td><c:forEach items="${fn:split(goods.goodsPics,'|')}"
									var="picName">
									<img src="<c:url value="pics/${picName}"/>" width="40"
										height="40" border=0 id="img1">
								</c:forEach></td>
							<td><span>${goods.goodsRegtime}</span></td>
							<td><span>${goods.employee.empNo}</span></td>
							<td><a
								href="<c:url value="/goods/goodsAction!preUpt.action?goods.goodsNo=${goods.goodsNo}"/>">Modify</a>
								<a
								href="<c:url value="/goods/goodsAction!delEntity.action?goods.goodsNo=${goods.goodsNo}&goods.goodsStatus=${goods.goodsStatus}"/>">
									<c:choose>
										<c:when test="${goods.goodsStatus=='I'}">off shelves</c:when>
										<c:otherwise>Upload</c:otherwise>
									</c:choose>
							</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div>
				<%@ include file="/jsps/comms/page.jsp"%>
			</div>
		</div>
	</c:if>
	<c:if test="${empty page.pageContent}">
		<center>
			<div id="no_rec">
				<span class="failMsg">No Record</span>
				<button onclick="history.go(-1);">Return</button>
			</div>
		</center>
	</c:if>
	<jsp:include page="/jsps/comms/admin_footer.jsp"></jsp:include>
	<script type="text/javascript" src="js/page.js"></script>
	<script type="text/javascript" src="js/category.js"></script>
	<script type="text/javascript">
		function query() {
			document.forms[0].action = "goods/goodsAction!getList.action?act=query";
			forms[0].submit();
		}

		function checkChoose() {
			var error = "";
			if ($('#firstCtg').val() == "xx")
				error += "Please choose a first category!";
			if ($('#secondCtg') != null) {
				if ($('#secondCtg').val() == "xx")
					error += "Please choose a second category!";
			}
			if ($('#thirdCtg') != null) {
				if ($('#thirdCtg').val() == "xx")
					error += "Please choose a third category!";
			}
			if ($('#forthCtg') != null) {
				if ($('#forthCtg').val() == "xx") {
					var ctgs = $('#thirdCtg').val().split('.');
					if (ctgs[0] === '3' || ctgs[0] === '4') {
						error += "Please choose a forth category!";
					}
				}
			}
			if (error == "")
				return true;
			else {
				alert(error);
				return false;
			}
		}
		function init() {
			RsizeAllImageById("img1", 40, 40);
		}

		function ResizeImage(imageDest, W, H) {
			//显示框宽度W,高度H 
			var image = new Image();
			image.src = imageDest.src;
			if (image.width > 0 && image.height > 0) {
				//比较纵横比
				if (image.width / image.height >= W / H)//相对显示框：宽>高
				{
					if (image.width > W) //宽度大于显示框宽度W，应压缩高度
					{
						imageDest.width = W;
						imageDest.height = (image.height * W) / image.width;
					} else //宽度少于或等于显示框宽度W，图片完全显示
					{
						imageDest.width = image.width;
						imageDest.height = image.height;
					}
				} else//同理
				{
					if (image.height > H) {
						imageDest.height = H;
						imageDest.width = (image.width * H) / image.height;
					} else {
						imageDest.width = image.width;
						imageDest.height = image.height;
					}
				}
			}
		}

		//将页面内所有指定id的图片按比例缩放
		function RsizeAllImageById(id, W, H) {
			var imgs = document.getElementsByTagName("img1");
			for (var i = 0; i < imgs.length; i++) {
				if (imgs[i].id == id) {
					ResizeImage(imgs[i], W, H);
				}
			}
		}
	</script>
</body>
</html>
