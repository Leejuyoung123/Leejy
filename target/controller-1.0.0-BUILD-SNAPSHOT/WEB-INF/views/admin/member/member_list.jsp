<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- ../ 밖경로 list.jsp= member 폴더에 위치해 있기떄문에 
							include 경로 밖 			-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/header.jsp"%>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<div class="box3">
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
				<!-- /.row mb-2 -->
			</div>
			<!-- container -->
		</div>
		<!-- content-header -->
		<div class="col-12">
			<div class="card card-secondary">
				<div class="card-header" style="padding-top: 0px;">
					<h3 class="card-title"></h3>
				</div>
				<!-- /.card-header -->
				<div class="card">
					<div class="card-body">
						<form role="form" method="post">
							<div class="row">
								<div class="col-sm-12">
									<!-- text input -->
									<div class="form-group">
										<h1 class="m-0 text-dark">멤버검색</h1>
										<br>
										<hr>
										<div>
											<select name='LY'>
												<option value=''>----</option>
											</select> <input type="text"> <input type="submit" value="검색">
											<input type="submit" value="새 사용자 등록">
										</div>
										<!-- select  -->
									</div>
									<!-- form-group -->
								</div>
								<!-- col -->
							</div>
							<!-- row -->
						</form>
						<!-- form -->
					</div>
					<!-- card body -->
				</div>
				<!-- card -->
			</div>
			<!-- card card-secondary -->
		</div>
		<!-- col-12 -->

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
										<h1 class="m-0 text-dark">LIST ALL PAGE</h1>
										<br>
										<table class="table table-hover text-nowrap">
											<thead>
												<tr>
													<th>ID</th>
													<th>User</th>
													<th>email</th>
													<th>Use</th>
													<th>REGDATE</th>
													<th>level</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${memberList}" var="memberVO"
													varStatus="status">
													<tr>
														<td>${memberVO.user_id}</td>
														<td><a
															href="/admin/member/view?user_id=${memberVO.user_id}">${memberVO.user_name}</a></td>
														<td>${memberVO.email}</td>
														<td>${memberVO.enabled}</td>
														<td>${memberVO.reg_date}</td>
														<td class="right badge badge-danger">${memberVO.levels}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
									<!-- form group  -->
								</div>
								<!-- col sm- 12 -->
							</div>
						</form>
						<!-- form -->
					</div>
					<!-- card body -->
				</div>
				<!-- card -->
			</div>
			<!-- card card-secondary -->

			<div>
				<a href="/admin/member/write" class="btn btn-primary">CREATE</a>
				<div class="btn">
					<button type="button" class="btn btn-primary">1</button>
				</div>
			</div>

		</div>
		<!-- col-12 -->
	</div>
	<!-- box -->
</div>
<!-- /.content-header -->

<!-- Main content -->
<div class="content"></div>
<!-- /.content -->
<%@include file="../include/footer.jsp"%>