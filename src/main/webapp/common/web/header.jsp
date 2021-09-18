<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="http://startbootstrap.com">JSP
				Servlet</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav">
				<li><a href="#about">Trang chủ</a></li>

				<c:if test="${not empty USERMODEL }">
					<li><a href="<c:url value='#' />">Xin chào,
							${USERMODEL.fullName}</a></li>

					<li><a href="<c:url value='/logout?action=logout' />">Đăng
							xuất</a></li>
				</c:if>

				<c:if test="${empty USERMODEL }">
					<li><a href="<c:url value='/login?action=login' />">Đăng
							nhập</a></li>
				</c:if>
				<li><a href="#contact">Contact</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>