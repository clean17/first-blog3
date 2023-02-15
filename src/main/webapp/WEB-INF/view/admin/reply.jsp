<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<div class="d-flex mx-auto">
    <div class="ms-4 mt-4">
        <ul class="nav flex-column nav-pills">
            <li class="nav-item">
                <a class="nav-link" aria-current="page" href="/admin/user">회원관리</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/board">게시글 관리</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="/admin/reply">댓글 관리</a>
            </li>
            <!-- <li class="nav-item">
                <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
            </li> -->
        </ul>
    </div>
    
    <div class="container mt-3 ">
        <h2>댓글 관리</h2>

                        <div class="d-flex justify-content-between">

                    <div>
                        <p>댓글을 삭제할 수 있습니다.</p>
                    </div>
           
                    <div>
                    <div class="input-group">
                        <div class="form-outline">
                            <input id="search-input" type="search" id="form1" class="form-control" placeholder="검색"/>
                        </div>
                        <button id="search-button" type="button" class="btn btn-primary">
                            <i class="fas fa-search"></i>
                        </button>
                    </div>
                    </div>
                </div>
        
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>댓글 번호</th>
                    <th>댓글 내용</th>
                    <th>댓글 작성자</th>
                    <th>게시글 번호</th>
                    <th>댓글 생성일</th>
                    <th>댓글 삭제</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${replyList}" var="reply">
                        <tr id="reply-${reply.id}">
                            <td>${reply.id}</td>
                            <td class="my-title-ellipsis">${reply.comment}</td>
                            <td>${reply.username}</td>
                            <td>${reply.boardId}</td>
                            <td>${reply.createdAtToString}</td>
                            <td><button class="btn btn-danger" onclick="deleteUser(`${reply.id}`)">삭제하기</button></td>
                        </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    </div>
<script>
    function deleteUser(id){
        $.ajax({
            type: "delete",
            url: "/admin/reply/"+id+"/delete",
            dataType:"json"
        }).done((res) => {
            alert(res.msg);
            $('#reply-'+id).remove();
        }).fail((err) => {
            alert(err.responseJSON.msg);
        });
    }
</script>
<%@ include file="../layout/footer.jsp" %>