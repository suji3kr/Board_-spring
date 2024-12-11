<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Tables</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				Board List Page
				<button id='regBtn' type="button" class="btn btn-xs pull-right btn-info">Register New Board</button>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<table width="100%"
					class="table table-striped table-bordered table-hover"
					id="dataTables-example">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>수정일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="board">
							<tr>
								<td><c:out value="${board.bno}" /></td>
								<td><a href='/board/get?bno=<c:out value= "${board.bno}"/>'>
								<c:out value="${board.title}"/></a></td>
								<td><c:out value="${board.writer}" /></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value='${board.regDate}' /></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value='${board.updateDate}' /></td>
							</tr>

						</c:forEach>
					</tbody>

				</table>
				<!-- https://www.w3schools.com/bootstrap4/bootstrap_modal.asp  -->
				<!-- The Modal -->
				<div class="modal" id="myModal">
					<div class="modal-dialog">
						<div class="modal-content">

							<!-- Modal Header -->
							<div class="modal-header">
								<h4 class="modal-title">Modal Heading</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>

							<!-- Modal body -->
							<div class="modal-body">처리가 완료되었습니다.</div>

							<!-- Modal footer -->
							<div class="modal-footer">
								<button type="button" class="btn btn-danger"
									data-dismiss="modal">Close</button>
							</div>

						</div>
					</div>
					<!-- End Modal -->

				</div>
				<!-- /.table-responsive -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-6 -->
</div>
<!-- /.row -->
<%@include file="../includes/footer.jsp"%>

<script>
$(document).ready(function() {
    var result = '${result}';  // 서버에서 전달된 result 값을 받아옵니다.

    checkModal(result);

    // 뒤로가기 버튼을 클릭해도 모달이 다시 표시되지 않도록 히스토리 상태를 변경합니다.
    history.replaceState({}, null, null); 

    function checkModal(result) {
        // result 값이 비어있거나 이미 히스토리 상태가 변경된 경우 모달을 표시하지 않음
        if (result === '' || history.state) {
            return;
        } else {
            // result 값을 숫자로 변환하고, 그 값이 0보다 큰 경우에만 모달을 표시
        	if(result === '' || history.state){
    			return ;
    		}else{
    			if(parseInt(result)>0){
    				$(".modal-body").html("게시글 " + parseInt(result) + "번이 등록되었습니다.");
    			}
    			
    			$("#myModal").modal("show");
    		}
    	}

    // 등록 버튼 클릭 시, 등록 페이지로 이동
    $("#regBtn").on("click", function() {
        self.location = "/board/register";
    });
});

</script>