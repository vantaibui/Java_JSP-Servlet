<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<div class="main-content">
	<div class="form-body">
		<div class="row">
			<div class="form-holder">
				<div class="form-content">
					<div class="form-items">
						<h3>Thêm bài viết</h3>
						<form class="requires-validation" action="/admin-new?type=edit"
							method="post">

							<div class="col-md-12">
								<input class="form-control" type="text" name="title"
									placeholder="Tiêu đề" value="${model.title }" required>

							</div>

							<div class="col-md-12">
								<input class="form-control" type="text" name="thumbnail"
									placeholder="Hình ảnh" value="${model.thmbnail }" required>

							</div>
							<div class="col-md-12">
								<input class="form-control" type="text"
									" name="shortdescription" placeholder="Mô tả"
									value="${model.shortDescription }" required>

							</div>
							<div class="col-md-12">
								<input class="form-control" type="text" name="content"
									placeholder="Nội dung" value="${model.content }" required>

							</div>

							<div class="col-md-12">
								<c:if test="${empty model.categoryCode }">
									<select class="form-select mt-3" required>
										<option selected disabled value="">Thể loại</option>
										<c:forEach var="item" items="${categories}">
											<option value="${item.code}">${item.name}</option>
										</c:forEach>
									</select>
								</c:if>

								<c:if test="${not empty model.categoryCode }">
									<select class="form-select mt-3" required>
										<option selected disabled value="">Thể loại</option>
										<c:forEach var="item" items="${categories}">
											<c:if test="${item.code == model.categoryCode}">
												<option value="${item.code}" selected="selected">${item.name}</option>
											</c:if>
											<c:if test="${item.code != model.categoryCode}">
												<option value="${item.code}">${item.name}</option>
											</c:if>

										</c:forEach>
									</select>
								</c:if>
							</div>

							<div class="form-button mt-3">
								<button id="submit" type="submit" class="btn btn-primary">Register</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>