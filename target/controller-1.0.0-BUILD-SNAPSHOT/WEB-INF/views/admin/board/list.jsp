<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                         <h1 class="m-0 text-dark">게시판 검색</h1><br><hr>
                     <div>
                     <select name='LY'>
  						<option value=''>----</option>
  					</select>
  					<input type="text"> <input type="submit" value="검색"> <input type="submit" value="새글쓰기">
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
                      <th>BNO</th>
                      <th>TITLE</th>
                      <th>WRITER</th>
                      <th>REGDATE</th>
                      <th>VIEWCNT</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>125</td>
                      <td>홈페이지 오픈 테스트[0]</td>
                      <td>LeeJy</td>
                      <td>2020-06-23 12:13</td>
                      <td class="right badge badge-danger">1</td>
                    </tr>
                    <tr>
                      <td>124</td>
                      <td>새로운 글을 넣습니다[0]</td>
                      <td>user01</td>
                      <td>2020-06-23 12:17</td>
                      <td class="right badge badge-danger">0</td>
                    </tr>
                     <tr>
                      <td>123</td>
                      <td>새로운 글을 넣습니다[0]</td>
                      <td>user02</td>
                      <td>2020-06-23 12:15</td>
                      <td class="right badge badge-danger">0</td>
                    </tr>
                     <tr>
                      <td>122</td>
                      <td>새로운 글을 넣습니다[0]</td>
                      <td>user03</td>
                      <td>2020-06-23 12:18</td>
                      <td class="right badge badge-danger">0</td>
                    </tr>
                     <tr>
                      <td>121</td>
                      <td>새로운 글을 넣습니다[0]</td>
                      <td>user04</td>
                      <td>2020-06-23 12:20</td>
                      <td class="right badge badge-danger">1</td>
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
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <div class="content">
    
    </div>
    <!-- /.content -->
  </div>
   <!-- Content Wrapper. Contains page content -->
    
    <%@include file="../include/footer.jsp" %>