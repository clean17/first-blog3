<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<style>
    .my-xl {
        color: 000;
    }

    .my-cursor {
        cursor: pointer;
    }
    .blue{
        color: blue;
    }

    .my-cursor:hover {
        color: red;
    }

    .on-Clicked {
        color: red;
    }
</style>
<div class="container my-3">
    <c:if test="${principal.id == dto.userId}">
        <div class="mb-3">
            <a href="/board/${dto.id}/updateForm" class="btn btn-warning">수정</a>
            <button type="button" class="btn btn-danger" onclick="deleteBoardById(`${dto.id}`)">삭제</button>
        </div>
    </c:if>

    <div class="mb-2 d-flex justify-content-end">
        
        글 번호 : &nbsp<span id="id">${dto.id}&nbsp&nbsp<i>&nbsp&nbsp&nbsp&nbsp </i></span> 작성자 : &nbsp<span
            class="me-3"><i>${dto.username} </i></span>&nbsp&nbsp&nbsp 좋아요 &nbsp&nbsp
            <div id="heart-${dto.id}-div">
                <div id="heart-${dto.id}-count" class="d-flex">

                    <c:choose>
                        <c:when test="${dto.state == 1}">
                            <i id="heart-${dto.id}"
                                class="my-auto my-heart fa-regular fa-solid fa-heart my-xl my-cursor on-Clicked"
                                onclick="heartclick(`${dto.id}`,`${dto.state}`,`${principal.id}`,`${dto.loveId}`)"></i>
                        </c:when>
                        <c:otherwise>
                            <i id="heart-${dto.id}" class="my-auto fa-regular fa-heart my-xl my-cursor"
                                onclick="heartclick(`${dto.id}`,`${dto.state}`,`${principal.id}`,`${dto.loveId}`)"></i>
                        </c:otherwise>
                    </c:choose>
                    &nbsp <div>${dto.count}</div>
                </div>

            </div>
    </div>
    <div class="d-flex">
        <h1 class="d-inline col-9"><b>${dto.title}</b></h1>
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
                <textarea id="reply-content" name="comment" placeholder="댓글을 입력하세요 " class="form-control"
                    rows="1"></textarea>
            </div>

            <div class="card-footer">
                <button type="button" id="btn-reply-save" class="btn btn-primary"
                    onclick="saveReply(`${dto.id}`,`${principal.username}`,`${principal.id}`)">등록</button>
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

    let boardId;
    let count;
    let state;
    let loveId;
    let userId;
    let comm;
    let username;
    let replyId;

    function saveReply(id, username1, principalId) {
        comm = $('#reply-content').val();
        username = username1;
        let data = {
            comment: $('#reply-content').val(),
            boardId: id,
            userId: principalId
        }
        $.ajax({
            type: "post",
            url: "/reply/save",
            data: JSON.stringify(data),
            headers: {
                "content-type": "application/json; charset=utf-8"
            },
            dataType: "json"
        }).done((res) => {
            replyId = res.data;
            renderReply();
        }).fail((err) => {
            alert(err.responseJSON.msg);
        });
        $('#reply-content').val("");
    }
    function renderReply (){
        let str = `     
             <li id="reply-`+ replyId + `" class="list-group-item d-flex justify-content-between ">
             <div id="test">`+ comm + `</div>
             <div class="d-flex justify-content-left">
             <div class="font-italic">작성자 : `+ username + ` &nbsp;</div>
             <div>
             <button class="badge bg-secondary" onclick="updateComment(`+ replyId + `)">수정</button>
             <button class="badge bg-secondary" onclick="deleteComment(`+ replyId + `)">삭제</button>
             </div>
             </div>
             </li>`;
        $('#reply-box').append(str);
    }

    function deleteBoardById(id) {
        $.ajax({
            type: "delete",
            url: "/board/" + id + "/delete",
            dataType: "json"
        }).done((res) => {
            alert(res.msg);
            location.href = "/";
        }).fail((err) => {
            alert(err.responseJSON.msg);
            location.href = "/";
        });
    }
    function deleteComment(id) {
        $.ajax({
            type: "delete",
            url: "/reply/" + id,
            dataType: "json"
        }).done((res) => {
            alert(res.msg);
            $('#reply-' + id).remove();
        }).fail((err) => {
            alert(err.responseJSON.msg);
        });
    }

    function heartclick(boardId1, state1, userId1, loveId1) {
        boardId = boardId1;
        userId = userId1;
        // console.log(userId1 > 0);
        // el 표현식 여러개를 파라미터로 넣을 경우 파라미터마다 백틱으로 끊어서 입력해야 정상적인 값이 들어간다
        if (userId1 > 0) {
            let data = {
                boardId: boardId1,
                userId: userId1,
                state: state1,
                id: loveId1
            }
            $.ajax({
                type: "post",
                url: "/love/click",
                data: JSON.stringify(data),
                headers: {
                    "content-type": "application/json; charset=utf-8"
                },
                dataType: "json"
            }).done((res) => {
                // console.dir(res);
                count = res.data.count;
                state = res.data.state;
                loveId = res.data.id;
                // console.log(count + ' 카운트 '+ state + '상태');
                heart();
            }).fail((err) => {
                // console.dir(err);
                alert(err.responseJSON.msg);
            });
        }
    }

    function heart() {
        $('#heart-' + boardId).toggleClass("fa-solid");
        $('#heart-' + boardId).toggleClass("on-Clicked");
        $('#heart-' + boardId + '-count').remove();
        renderHeart();
    }

    function renderHeart() {
        let el;
        if (state === 1) {
            el = `
            <div id="heart-`+ boardId + `-count" class="d-flex">
            <i id="heart- `+ boardId + ` " class="my-auto my-heart fa-regular fa-solid fa-heart my-xl my-cursor on-Clicked" onclick="heartclick(` + boardId + `,` + state + `,` + userId + `,` + loveId + `)" ></i> 
            &nbsp <div>`+ count + `</div></div>
            </div>
            `;
        }
        if (state === 0) {
            el = `
            <div id="heart-`+ boardId + `-count" class="d-flex">
            <i id="heart- `+ boardId + ` " class="my-auto fa-regular fa-heart my-xl my-cursor" onclick="heartclick(` + boardId + `,` + state + `,` + userId + `,` + loveId + `)" ></i>
            &nbsp <div>`+ count + `</div></div>
            </div>
            `;
        }
        $('#heart-' + boardId + '-div').append(el);
    }
</script>
<%@ include file="../layout/footer.jsp" %>