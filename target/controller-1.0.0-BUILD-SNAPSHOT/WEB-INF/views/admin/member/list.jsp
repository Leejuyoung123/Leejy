<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ../ 밖경로 list.jsp= member 폴더에 위치해 있기떄문에 
							include 경로 밖 -->
<%@ include file="../include/header.jsp" %>
<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
  <div class="box3" >
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
			<h1 class="m-0 text-dark">DashBoard Management <small>priview</small></h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Starter Page</li>
            </ol>          
          </div><!-- /.col -->
        </div><!-- /.row -->
        </div>
        </div>
         <div class="col-12">
          <div class="card card-secondary">
              <div class="card-header" style="padding-top:0px;">
                <h3 class="card-title"></h3>
              </div>
            <div class="card">
              <!-- /.card-header -->
              <div class="card-body">
                <form role="form" method="post">
                  <div class="row">
                    <div class="col-sm-12">
                      <!-- text input -->
                     <div class="form-group">
                         <h1 class="m-0 text-dark">멤버검색</h1><br><hr>
                    <div>
                    <select name='LY'>
  						<option value=''>----</option>
  					</select>
  					<input type="text"> <input type="submit" value="검색"> <input type="submit" value="새 사용자 등록">
                     </div>
        		</div>
              </div>
             </div>
            </form>
            </div>
            </div>
            </div>
			</div>
			        
        <div class="col-12">
          <div class="card card-secondary">
             <div class="card-header" style="padding-top:0px;">
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
                         <h1 class="m-0 text-dark">LIST ALL PAGE</h1><br>
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
                    <tr>
                      <td>user02</td>
                      <td>user02[0]</td>
                      <td>user02@edu1.com</td>
                      <td>true</td>
                      <td>2020-06-23</td>
                      <td class="right badge badge-danger">ROLE_ADMIN</td>
                    </tr>
                </tbody>
               </table>
              </div>
              </div>
              </div>
              </form>
              </div>
              </div>
              </div>
              <div>
              <button type="button" class="btn btn-primary">List All</button>
              <div class ="btn">
              <button type="button" class="btn btn-primary" >1</button>
              </div>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
          </div>
      </div><!-- /.container-fluid -->
    <!-- /.content-header -->
    <!-- Main content -->
    <div class="content">
    </div>
    <!-- /.content -->
  <!-- /.content-wrapper -->
<!-- Content Header (Page header) -->

<%@include file="../include/footer.jsp" %>