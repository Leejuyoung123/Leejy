<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="include/header.jsp"%>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<div class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1 class="m-0 text-dark">Error Page</h1>
				</div>
				<!-- /.col -->
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">에러명</li>
						<li class="breadcrumb-item active">${exception.getMessage()}</li>
					</ol>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container-fluid -->
	</div>
	<!-- /.content-header -->
<div class="content">
<ul>
<c:forEach items="${exception.getStackTrace()}" var="stack">
	<li class="clear">${stack.toString()}</li>
</c:forEach>
</ul>
</div>
</div>
<!-- /.content-wrapper -->


<!-- /.control-sidebar -->

<%@include file="include/footer.jsp"%>
