
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="comms/taglib.jsp"%>
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
<link rel="stylesheet" type="text/css" href="css/carousel.css">
</head>
<body class="mall-container">

	<jsp:include page="/jsps/comms/header.jsp"></jsp:include>
<div class="main-content">
		<div id="carousel-pic" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<c:forEach begin="0" end="4" varStatus="loop">
					<li data-target="#carousel-pic" data-slide-to="${loop.index}"
						<c:if test="${loop.index==0}">class="active"</c:if>></li>
				</c:forEach>
			</ol>
			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">
				<c:forEach begin="0" end="4" varStatus="loop">
					<div class="item <c:if test="${loop.index==0}">active</c:if>">
						<img src="images/main/main_banner${loop.count}.jpg"
							class="img-rounded">
						<div class="carousel-caption">
							<div class="carousel-caption-div">
								<div>BREAKING</div>
								<div>NEW</div>
								<div>
									GET AHEAD OF THE TRENDS WITH<br>ALL-NEW ARRIVALS
								</div>
								<div>
									<a>SHOP MEN</a> <a>SHOP WOMEN</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>

			</div>
			<!-- Controls -->
			<a class="left carousel-control" href="#carousel-pic" role="button"
				STYLE="background-image: url(images/prev.png)" data-slide="prev">
			</a> <a class="right carousel-control" href="#carousel-pic" role="button"
				STYLE="right: 10px; background-image: url(images/next.png)"
				data-slide="next"> </a>
		</div>

		<div class="row">
			<c:forEach items="${mainnail}" var="nail" varStatus="loop">
				<div class="col-xs-6 col-md-3 main-pic" style="position: static;">
					<a href="#" class="thumbnail"> <img
						src="images/main/main_show${loop.count}.jpg">
					</a>
					<div class="main-pic-content">
						<span>${nail}</span>
					</div>
				</div>

			</c:forEach>
		</div>
	</div>
	<jsp:include page="/jsps/comms/footer.jsp"></jsp:include>

</body>
</html>