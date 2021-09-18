<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container h-100">

	<div class="d-flex justify-content-center h-100">
		<div class="user_card">

			<div class="d-flex justify-content-center">
				<div class="brand_logo_container">
					<img
						src="https://cdn.freebiesupply.com/logos/large/2x/pinterest-circle-logo-png-transparent.png"
						class="brand_logo" alt="Logo">
				</div>
			</div>
			<div class="d-flex justify-content-center form_container">
				<form action="<c:url value='/login' />" method="post">
					<c:if test="not empty message">
						<div class="alert alert-${alert}" role="alert">${message }</div>
					</c:if>




					<div class="input-group mb-3">
						<div class="input-group-append">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
						</div>
						<input type="text" name="userName" class="form-control input_user"
							value="" placeholder="Tên đăng nhập...">
					</div>
					<div class="input-group mb-2">
						<div class="input-group-append">
							<span class="input-group-text"><i class="fas fa-key"></i></span>
						</div>
						<input type="password" name="password"
							class="form-control input_pass" value=""
							placeholder="Mật khẩu...">
					</div>
					<div class="form-group">
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input"
								id="customControlInline"> <label
								class="custom-control-label" for="customControlInline">Remember
								me</label>
						</div>
					</div>
					<input type="hidden" value="login" name="action" />
					<div class="d-flex justify-content-center mt-3 login_container">
						<button type="submit" name="button" class="btn login_btn">Đăng
							nhập</button>
					</div>
				</form>
			</div>

			<div class="mt-4">
				<div class="d-flex justify-content-center links">
					Don't have an account? <a href="#" class="ml-2">Sign Up</a>
				</div>
				<div class="d-flex justify-content-center links">
					<a href="#">Forgot your password?</a>
				</div>
			</div>
		</div>

	</div>

</div>
