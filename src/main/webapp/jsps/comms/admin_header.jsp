<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/comms/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value="/jsps/admin/admin_main.jsp"/>"><img src="images/logo.png"
				width="100px"></a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a>WELCOME! ${admin.empName}</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${admin.empType=='A'}">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Employee manage<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a 
								href="<c:url value="/employee/empAction!preAdd.action"/>">Add
									Employee</a></li>
							<li><a 
								href="<c:url value="/employee/empAction!getList.action"/>">List
									Employees</a></li>
						</ul></li>

					<li><a 
						href="<c:url value="/member/memberAction!getList.action"/>">List
							Members</a></li>

					<li><a 
						href="<c:url value="/goods/goodsAction!getList.action"/>">List
							Goods</a></li>


					<li><a 
						href="<c:url value="/category/ctgAction!getList.action"/>">List
							Category</a></li>

					<li><a 
						href="<c:url value="/goodsOrder/orderAction!getList.action"/>">List
							Order</a></li>

				</c:if>
				<c:if test="${admin.empType=='B'}">
					<li><a 
						href="<c:url value="/goods/goodsAction!getList.action"/>">List
							Goods</a></li>


					<li><a 
						href="<c:url value="/category/ctgAction!getList.action"/>">List
							Category</a></li>
				</c:if>
				<c:if test="${admin.empType=='C'||admin.empType=='D'}">
					<li><a 
						href="<c:url value="/goodsOrder/orderAction!getList.action"/>">List
							Order</a></li>
				</c:if>
				<li><span><a href="#" class="btn btn-default active" role="button" 
						style="margin: 9px 9px 0px 15px;" onclick="logout()">Log
						Out</a></span></li>

			</ul>

		</div>
	</div>
	</nav>
</html>
