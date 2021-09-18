<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
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
													<th>Tên bài viết</th>
													<th>Mô tả ngắn</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${model.listResult }">
													<tr>
														<td><c:out value="${item.title }"></c:out></td>
														<td>${item.content}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<ul class="pagination" id="pagination"></ul>
										<input type="hidden" value="" id="page" name="page"> <input
											type="hidden" value="" id="maxPageItem" name="maxPageItem">

										<input type="hidden" value="" id="sortName" name="sortName">
										<input type="hidden" value="" id="sortBy" name="sortBy">
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
		var totalPages = $
		{
			model.totalPage
		};
		var currentPage = $
		{
			model.page
		};
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
						$('#sortBy').val('desc');
						$('#formSubmit').submit();

					}
				}
			})
		});
	</script>
</body>
</html>