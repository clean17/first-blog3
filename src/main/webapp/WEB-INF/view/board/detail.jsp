<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

    <div class="container my-3">
        <c:if test="${principal.id == dto.userId}" >
        <div class="mb-3">
            <a href="/board/${dto.id}/updateForm" class="btn btn-warning">수정</a>
            <button type="button" class="btn btn-danger" onclick="deleteBoardById(`${dto.id}`)">삭제</button>
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
            <form>
                <input type="hidden" name="boardId" value="${dto.id}">
                <div class="card-body">
                    <textarea id="reply-content" name="comment" placeholder="댓글을 입력하세요 " class="form-control" rows="1"></textarea>
                </div>
                <div class="card-footer">
                    <button type="button" id="btn-reply-save" class="btn btn-primary" onclick="test(`${dto.id}`,`${principal.username}`,`${principal.id}`)">등록</button>
                </div>
            </form>
        </div>
        <br />
        <div class="card mt-3">
            <div class="card-header">댓글 리스트</div>
            <ul id="reply-box" class="list-group">
                <c:forEach items="${replyList}" var="reply">
                
                <li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between ">
                    <div id="test">${reply.comment}</div>
                    
                    <div class="d-flex justify-content-left">
                        <div class="font-italic">작성자 : ${reply.username} &nbsp;</div>
                        <div>
                            <c:if test="${reply.userId == principal.id}">
                                <button class="badge bg-secondary" onclick="updateComment(`${reply.id}`)">수정</button>
                                <button class="badge bg-secondary" onclick="deleteComment(`${reply.id}`)">삭제</button>
                            </c:if>
                            <c:if test="${reply.userId != principal.id}">
                                <button class="badge bg-secondary" style="visibility: hidden;">수정</button>
                                <button class="badge bg-secondary" style="visibility: hidden;">삭제</button>
                            </c:if>
                        </div>
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
        // $('#test').click(()=>{
        // $('#test').focus();
        // console.log('dlrj')
        // $('#test').blur();
        
        // })
        // focus();
        // console.log('dlrj')
        // $('#test').blur();
        function test(id, username1, principalId){
            let comm = $('#reply-content').val();
            let username = username1;
            let pId = principalId;
            console.log(comm+username+pId);

            let data = { 
                comment: comm,
                boardId: id
             }

            let replyId ;
            $.ajax({
                type: "post",
                url: "/reply/",
                data: JSON.stringify(data),
                headers:{
                    "content-type":"application/json; charset=utf-8"
                },
                dataType:"json"
            }).done((res) => {
                replyId = res.data;
            }).fail((err) => {
            
            });

            $('#reply-content').val("");
            let str = "";
            str += `<li id="reply-`+replyId+`" class="list-group-item d-flex justify-content-between ">`;
            str += `<div id="test">`+comm+`</div>`;
            str += `<div class="d-flex justify-content-left">`;
            str += `<div class="font-italic">작성자 : `+username+` &nbsp;</div>`;
            str += `<div>`;
            // str += `<c:if test="${reply.userId == pId}">`;
            str += `<button class="badge bg-secondary" onclick="updateComment(`+replyId+`)">수정</button>`;
            str += `<button class="badge bg-secondary" onclick="deleteComment(`+replyId+`)">삭제</button>`;
            // str += `</c:if>`;
            // str += `<c:if test="${reply.userId != pId}">`;
            // str += `<button class="badge bg-secondary" style="visibility: hidden;">수정</button>`;
            // str += `<button class="badge bg-secondary" style="visibility: hidden;">삭제</button>`;
            // str += `</c:if>`;
            str += `</div>`;
            str += `</div>`;
            str += `</li>`;
            $('#reply-box').append(str);
            
        }

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
        function deleteComment(id){
            $.ajax({
                type: "delete",
                url: "/reply/"+id,
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