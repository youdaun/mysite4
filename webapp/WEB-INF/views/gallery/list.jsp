<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/galleryAside.jsp"></c:import>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>갤러리</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">갤러리</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->


			<div id="gallery">
				<div id="list">

					<c:if test="${authUser != null}">
						<button id="btnImgUpload" data-uno="${authUser.no}">이미지올리기</button>
						<div class="clear"></div>
					</c:if>


					<ul id="viewArea">

						<!-- 이미지반복영역 -->
						<c:forEach items="${galleryList}" var="gvo">
							<li id="listImg${gvo.no}">
								<div class="view">
									<img class="imgItem" data-no="${gvo.no}" src="${pageContext.request.contextPath }/upload/${gvo.saveName}">
									<div class="imgWriter">
										작성자: <strong>${gvo.userName}</strong>
									</div>
								</div>
							</li>
						</c:forEach>
						<!-- 이미지반복영역 -->


					</ul>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->



	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지등록</h4>
				</div>

				<form method="post" action="${pageContext.request.contextPath}/gallery/upload" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label> <input id="addModalContent" type="text" name="content" value="">
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label> <input id="file" type="file" name="file" value="">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
					<input type="hidden" name="userNo" value="${authUser.no}"> <input type="hidden" name="userName" value="${authUser.name}">
				</form>


			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">

					<div class="formgroup">
						<img id="viewModelImg" src="">
						<!-- ajax로 처리 : 이미지출력 위치-->
					</div>

					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>

				</div>
				<form method="" action="">
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
						<input type="hidden" id="listNo" val="">
					</div>


				</form>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


</body>

<script type="text/javascript">
	//이미지등록버튼 눌렀을때
	$("#btnImgUpload").on("click", function() {
		$('#addModal').modal('show');
	});

	//이미지 눌렀을때(이미지보기)
	$("#viewArea").on("click", ".imgItem", function() {
		var $this = $(this);
		var no = $this.data("no");
		getImg(no);
	});
	
	//삭제버튼 눌렀을때(이미지보기)
	$("#delBtn").on("click", function() {
		var no = $("#listNo").val();
		deleteImg(no);
	});

	//이미지 불러오기(이미지보기)
	function getImg(no) {

		var authUserNo = $("#btnImgUpload").data("uno");

		$.ajax({

			url : "${pageContext.request.contextPath }/gallery/viewImg",
			type : "post",
			//contentType : "application/json",
			data : {
				no : no
			},

			dataType : "json",
			success : function(gvo) {

				if (gvo.userNo == authUserNo) {
					$("#btnDel").show();
				} else {
					$("#btnDel").hide();
				}

				$("#viewModal").modal('show');
				$("#viewModelImg").attr("src", '${pageContext.request.contextPath}/upload/' + gvo.saveName);
				$("#viewModelContent").html(gvo.content);
				$("#listNo").val(gvo.no);
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}

	//이미지 삭제
	function deleteImg(no) {
		
		$.ajax({
			url : "${pageContext.request.contextPath}/gallery/delete",
			type : "post",
			//contentType: "application/json",
			data : {
				no : no
			},

			dataType : "json",
			success : function(result) {
				if (result == "s") {
					
					$("#listImg" + no).remove();
					$("#viewModal").modal('hide');
				} else {
					
					$("#viewModal").modal('hide');
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	};
</script>




</html>

