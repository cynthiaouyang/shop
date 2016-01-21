
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="taglib.jsp"%>

<!DOCTYPE html>
<html>
<div class="main-media">
	<ul>
		<c:forEach begin="0" end="5" varStatus="loop">
			<li><a> <img src="images/main/icon${loop.count}.png">
			</a></li>
		</c:forEach>
	</ul>
</div>
<div class="main-footer">
	<div class="row">
		<c:forEach items="${data}" var="footer">
			<div class="col-sm-12 col-md-4 col-lg-4">
				<ul>
					<li>${footer.key}</li>
					<c:forEach items="${footer.value}" var="val">
						<li><a>${val}</a></li>
					</c:forEach>
				</ul>
			</div>
		</c:forEach>
	</div>
</div>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/header.js"></script>
</html>