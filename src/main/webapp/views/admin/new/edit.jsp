<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-new" />

<div class="main-content">
	<div class="form-body">
		<div class="row">
			<div class="form-holder">
				<div class="form-content">
					<div class="form-items">
						<h3>Thêm bài viết</h3>
						<form id="formSubmit" class="requires-validation"
							action="/admin-new?type=edit" method="post">

							<div class="col-md-12">
								<input class="form-control" type="text" name="title"
									placeholder="Tiêu đề" value="${model.title }">

							</div>

							<div class="col-md-12">
								<input class="form-control" type="text" name="thmbnail"
									placeholder="Hình ảnh" value="${model.thmbnail }">

							</div>
							<div class="col-md-12">
								<input class="form-control" type="text" name="shortDescription"
									placeholder="Mô tả" value="${model.shortDescription }" required>

							</div>
							<div class="col-md-12">
								<input class="form-control" type="text" name="content"
									placeholder="Nội dung" value="${model.content }">

							</div>

							<div class="col-md-12">
								<c:if test="${empty model.categoryCode }">
									<select class="form-select mt-3" id="categoryCode"
										name="categoryCode">
										<option selected disabled value="">Thể loại</option>
										<c:forEach var="item" items="${categories}">
											<option value="${item.code}">${item.name}</option>
										</c:forEach>
									</select>
								</c:if>

								<c:if test="${not empty model.categoryCode }">
									<select class="form-select mt-3" id="categoryCode">
										<option value="">Thể loại</option>
										<c:forEach var="item" items="${categories}">
											<option value="${item.code}"
												<c:if test="${item.code == model.categoryCode}">selected="selected"</c:if>>${item.name}</option>

										</c:forEach>
									</select>
								</c:if>
							</div>

							<div class="col-md-12 form-button mt-3" style="margin-top: 20px;">

								<button id="btnAddOrUpdate" type="submit"
									class="btn btn-primary" style="width: 100%; padding: 16px">
									<c:if test="${empty model.id }">Thêm bài viết</c:if>
									<c:if test="${not empty model.id }">Cập nhật bài viết</c:if>
								</button>
							</div>
							<input type="hidden" value="${model.id}" id="id" name="id">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$('#btnAddOrUpdate').click(function(event) {
		event.preventDefault();

		var data = {};

		var idNew = $('#id').val();

		console.log(idNew);

		var formData = $('#formSubmit').serializeArray();
		$.each(formData, function(index, v) {
			data["" + v.name + ""] = v.value
		});

		if (idNew == "") {
			addNew(data);
		} else {
			updateNew(data);
		}
	});
	function addNew(data) {
		console.log(data)
		$.ajax({
			url : '${APIurl}',
			type : 'POST',
			contentType : 'application/json',
			data : JSON.stringify(data),
			dataType : 'json',
			success : function(result) {
				console.log(result);
			},
			error : function(error) {
				console.log(error);
			}
		})
	};
	function updateNew(data) {
		console.log(data)
		$.ajax({
			url : '${APIurl}',
			type : 'PUT',
			contentType : 'application/json',
			data : JSON.stringify(data),
			dataType : 'json',
			success : function(result) {
				console.log(result);
			},
			error : function(error) {
				console.log(error);
			}
		})
	}
</script>