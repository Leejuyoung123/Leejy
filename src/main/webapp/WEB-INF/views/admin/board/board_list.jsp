<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/header.jsp"%>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<div class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1 class="m-0 text-dark">
						DashBoard Management <small>priview</small>
					</h1>
				</div>
				<!-- /.col -->
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="#">Home</a></li>
						<li class="breadcrumb-item active">Starter Page</li>
					</ol>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->

			<div class="col-12">
				<div class="card card-secondary">
					<div class="card-header" style="padding-top: 0px;">
						<h3 class="card-title"></h3>
					</div>
					<!-- /.card-header -->
					<div class="card">
						<div class="card-body">
							<form role="form">
								<div class="row">
									<div class="col-sm-12">
										<!-- text input -->
										<div class="form-group">
											<h1 class="m-0 text-dark">게시판 검색</h1>
											<br>
											<hr>
											<div>
												<select name='LY'>
													<option value=''>----</option>
												</select> <input type="text"> <input type="submit" value="검색">
												<input type="submit" value="새글쓰기">
											</div>
										</div>
										<!-- form-group -->
									</div>
									<!-- col sm-12  -->
								</div>
								<!-- row -->
							</form>
							<!-- form -->
						</div>
						<!-- card-body -->
					</div>
					<!-- card -->
				</div>
				<!-- card card-secondary -->
			</div>
			<!-- col-12 -->

			<div class="col-12">
				<div class="card card-secondary">
					<div class="card-header">
						<h3 class="card-title"></h3>
					</div>
					<div class="card">
						<!-- /.card-header -->
						<div class="card-body">
							<form role="form">
								<div class="row">
									<div class="col-sm-12">
										<!-- text input -->
										<div class="form-group">
											<h1 class="m-0 text-dark">LIST ALL PAGE</h1>
											<br>
											<table class="table table-hover text-nowrap">
												<thead>
													<tr>
														<th>BNO</th>
														<th>TITLE</th>
														<th>WRITER</th>
														<th>REGDATE</th>
														<th>VIEWCNT</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${boardList}" var="boardVO"
														varStatus="status">
														<tr>
															<th>${boardVO.bno}</th>
															<th><a href="/admin/board/view?bno=${boardVO.bno}">${boardVO.title}</a></th>
															<th>${boardVO.writer}</th>
															<th>${boardVO.regdate}</th>
															<td class="right badge badge-danger">${boardVO.view_count }</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<!-- form-group -->
									</div>
									<!-- col sm-12 -->
								</div>
								<!-- row -->
							</form>
							<!-- form -->
						</div>
						<!-- card body -->
					</div>
					<!-- card -->

					<div>
						<a href="/admin/board/write" class="btn btn-primary">Create</a>
						<nav aria-label="Page navigation example">
							<ul class="pagination">
								<li class="page-item"><a class="page-link" href="#"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								</a></li>
								<li class="page-item"><a class="page-link" href="#">1</a></li>
								<li class="page-item"><a class="page-link" href="#">2</a></li>
								<li class="page-item"><a class="page-link" href="#">3</a></li>
								<li class="page-item"><a class="page-link" href="#"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a></li>
							</ul>
						</nav>
						<!-- btn -->
					</div>

				</div>
				<!-- card-secondry  -->
			</div>
			<!-- col -->
		</div>
		<!-- /.container-fluid -->
	</div>
	<!-- /.content-header -->

	<!-- Main content -->
	<div class="content"></div>
	<!-- /.content -->

</div>
<!-- Content Wrapper. Contains page content -->


<%@include file="../include/footer.jsp"%>