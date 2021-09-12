<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><decorator:title default="Trang chủ"></decorator:title></title>

<!-- Bootstrap core CSS -->
<link href="<c:url value='/template/web/css/bootstrap.css' />"
	rel="stylesheet" type="text/css">

<!-- Custom CSS for the '3 Col Portfolio' Template -->
<link href="<c:url value='/template/web/css/css/3-col-portfolio.css' />"
	rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="/common/web/header.jsp"%>

	<div class="container">
		<decorator:body></decorator:body>
	</div>

	<%@ include file="/common/web/footer.jsp"%>

	<!-- JavaScript -->
	<script type="text/javascript"
		src="<c:url value='/template/web/js/jquery-1.10.2.js' />"></script>
	<script type="text/javascript"
		src="<c:url value='/template/web/js/bootstrap.js' />"></script>
</body>
</html>