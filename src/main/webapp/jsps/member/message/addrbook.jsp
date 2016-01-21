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
<link rel="stylesheet" type="text/css" href="css/accountmenu.css">
<link rel="stylesheet" type="text/css" href="css/accounttable.css">

</head>

<body class="mall-container">
	<jsp:include page="/jsps/comms/header.jsp"></jsp:include>
	<div class="account-main">

		<jsp:include page="accountmenu.jsp"></jsp:include>
		<div class="account-form">
			<form action="addrBook/addrAction!addEntity.action" method="post"
				id="form1">
				<table class="table table-striped table-hover accounttable"
					id="addrTable">

					<tr>
						<th>Name</th>
						<th>Address</th>
						<th>Tel</th>
						<th>Operate</th>
					</tr>
					<c:forEach items="${addrs}" var="addr">
						<tr>
							<td>${addr.rev}</td>
							<td>${addr.revAddr}</td>
							<td>${addr.revPhone}</td>
							<td>
								<button type='button' class="btn btn-default btn-sm"
									onclick="preUpt(${addr.bookNo},'${addr.rev}','${addr.revAddr}','${addr.revPhone}');">Edit</button>
								&nbsp; <input type='button' onclick="delAddr(${addr.bookNo});"
								value="Delete" class="btn btn-default btn-sm" />
							</td>
						</tr>
					</c:forEach>
				</table>
				<div class="text-center">
					<button onclick="addAddr();" type='button' class="btn btn-default">ADD
						Address</button>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="/jsps/comms/footer.jsp"></jsp:include>
	<script>
        function addAddr(){

			$('#hideDiv').remove();
        	var uptDiv='<tr id="hideDiv">'+
			'<td><input name="addrBook.rev" type="text"'+
				' /></td>'+
			'<td><input name="addrBook.revAddr" type="text"'+
				'></td>'+
			'<td><input name="addrBook.revPhone"'+
				'type="text" /></td>'+
			'<td><input type="submit" class="btn btn-default btn-sm" value="ADD" /></td></tr>'
			    $("#addrTable").append($(uptDiv));
        }
        
        function delAddr(bookNo)
		{ 
        	$('#form1').attr("action","addrBook/addrAction!delEntity.action?bookNo="+bookNo);
        	$('#form1').submit();
		}
		
		function preUpt(bookNo,rev,revAddr,revPhone){
			$('#uptDiv').remove();
		var uptDiv='<tr id="uptDiv">'+
		'<td><input id="uptAddrrev" name="uptAddr.rev"'+
			'type="text" /></td>'+
		'<td><input id="uptAddrrevAddr"'+
			'name="uptAddr.revAddr" type="text" /></td>'+
		'<td><input id="uptAddrrevPhone"'+
			'name="uptAddr.revPhone" type="text" /></td>'+
		'<td><input type="submit" value="Edit" class="btn btn-default btn-sm"'+
			'onclick="uptAddr();" /> <input id="uptAddrbookNo" type="hidden"'+
			'name="uptAddr.bookNo" /></td></tr>'
			$("#addrTable").append($(uptDiv));
		$("#uptAddrbookNo").val(bookNo);
		$("#uptAddrrev").val(rev);
		$("#uptAddrrevAddr").val(revAddr);
		$("#uptAddrrevPhone").val(revPhone);
		}
		
		function uptAddr(bookNo){
			 $('#form1').attr("action","addrBook/addrAction!uptEntity.action?bookNo="+bookNo);
			 $('#form1').submit();
		}
     </script>
</body>
</html>