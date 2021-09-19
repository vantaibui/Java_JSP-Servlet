<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-new" />
<c:url var="NEWurl" value="/admin-new" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách bài viết</title>

</head>
<body>

	<div class="main-content">
		<form action='<c:url value='/admin-new' />' method="get"
			id="formSubmit">
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang
								chủ</a></li>
					</ul>
					<!-- /.breadcrumb -->
				</div>

				<div
					style="display: flex; justify-content: flex-end; padding: 8px 20px 10px;"
					class="dt-buttons btn-overlap btn-group">
					<a flag="info"
						class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
						data-toggle="tooltip" title='Thêm bài viết'
						href='<c:url value="/admin-new?type=edit"/>'> <span> <i
							class="fa fa-plus-circle bigger-110 purple"></i>
					</span>
					</a>
					<button id="btnDelete" type="button"
						class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
						data-toggle="tooltip" title='Xóa bài viết'>
						<span> <i class="fa fa-trash-o bigger-110 pink"></i>
						</span>
					</button>
				</div>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<div class="row">
								<div class="col-xs-12">
									<div class="table-responsive"
										style="display: flex; flex-direction: column; align-items: center;">
										<table class="table table-bordered">
											<thead>
												<tr>
													<th><input type="checkbox" id="checkAll"></th>
													<th>Tên bài viết</th>
													<th>Mô tả ngắn</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${model.listResult }">
													<tr>
														<td><input type="checkbox" id="checkbox_${item.id}"
															value="${item.id}"></td>
														<td><c:out value="${item.title }"></c:out></td>
														<td>${item.content}</td>
														<td><c:url var="editURL" value="/admin-new">
																<c:param name="type" value="edit" />
																<c:param name="id" value="${item.id }" />
															</c:url> <a class="btn btn-sm btn-primary btn-edit"
															data-toggle="tooltip" title="Cập nhật bài viết"
															href=${editURL}><i class="fa fa-pencil-square-o"
																aria-hidden="true"></i> </a>
																
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<ul class="pagination" id="pagination"></ul>
										<input type="hidden" value="" id="page" name="page"> <input
											type="hidden" value="" id="maxPageItem" name="maxPageItem">

										<input type="hidden" value="" id="sortName" name="sortName">
										<input type="hidden" value="" id="sortBy" name="sortBy">
										<input type="hidden" value="" id="type" name="type">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>

	<script type="text/javascript">
		var totalPages = ${model.totalPage};
		var currentPage = ${model.page};
		var limit = 2;
		$(function() {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages : totalPages,
				visiblePages : 10,
				startPage : currentPage,
				onPageClick : function(event, page) {
					if (currentPage != page) {
						console.info(page + ' (from options)');
						$('#maxPageItem').val(limit);
						$('#page').val(page);
						$('#sortName').val('title');
						$('#sortBy').val('asc');
						$('#type').val('list');
						$('#formSubmit').submit();

					}
				}
			})
		});
		
		$('#btnDelete').click(function(){
			var data = {};
			var ids = $('tbody input[type=checkbox]:checked').map(function () {
				return $(this).val();
			}).get();
			data['ids'] = ids;
			deleteNew(data);
		})
		
		function deleteNew(data) {
		$.ajax({
			url : '${APIurl}',
			type : 'DELETE',
			contentType : 'application/json',
			data : JSON.stringify(data),
			success : function(result) {
				window.location.href = "${NEWurl}?type=list&page=1&maxPageItem=2";
			},
			error : function(error) {
				console.log(error);
			}
		})
	};
		
	</script>
</body>
</html>