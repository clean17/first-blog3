<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

    <div class="container my-3">
        <c:if test="${principal.id == dto.userId}" >
        <div class="mb-3">
            <a href="/board/${dto.id}/updateForm" class="btn btn-warning">수정</a>
            <button type="button" class="btn btn-danger" onclick="deleteBoardById(${dto.id})">삭제</button>
        </div>
        </c:if>

        <div class="mb-2 d-flex justify-content-end">
            글 번호 : &nbsp<span id="id">${dto.id}&nbsp&nbsp<i>&nbsp&nbsp&nbsp&nbsp </i></span> 작성자 : &nbsp<span class="me-3"><i>${dto.username} </i></span> 
            <i id="heart" class="fa-regular fa-heart my-xl my-cursor"></i>
        </div>
        <div>
            <h1><b>${dto.title}</b></h1>
        </div>
        <hr />
        <div>
            <div>${dto.content}</div>
        </div>
        <hr />
        <div class="card">
            <form action="/reply" method="post">
                <input type="hidden" name="boardId" value="${dto.id}">
                <div class="card-body">
                    <textarea id="reply-content" name="comment" placeholder="댓글을 입력하세요 " class="form-control" rows="1"></textarea>
                </div>
                <div class="card-footer">
                    <button type="submit" id="btn-reply-save" class="btn btn-primary">등록</button>
                </div>
            </form>
        </div>
        <br />
        <div class="card mt-3">
            <div class="card-header">댓글 리스트</div>
            <ul id="reply-box" class="list-group">
                <c:forEach items="${replyList}" var="reply">
                
                <li id="reply-1" class="list-group-item d-flex justify-content-between">
                    <div>${reply.comment}</div>
                    <div class="d-flex">
                        <div class="font-italic">작성자 : ${reply.username} &nbsp;</div>
                        <button class="badge bg-secondary">삭제</button>
                    </div>
                </li>
                </c:forEach>
            </ul>
            
        </div>
    </div>
    <script>
        $('.summernote').summernote({
                tabsize: 2,
                height: 400
            });
        function deleteBoardById(id){
            $.ajax({
                type: "delete",
                url: "/board/"+id+"/delete",
                dataType:"json"
            }).done((res) => {
                alert(res.msg);
                location.href="/";
            }).fail((err) => {
                // console.dir(err);
                alert(err.responseJSON.msg);
                location.href="/";
            });
        
        }
    </script>
<%@ include file="../layout/footer.jsp" %>