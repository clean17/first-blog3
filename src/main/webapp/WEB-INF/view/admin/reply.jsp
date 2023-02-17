<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<div class="d-flex mx-auto">
    <div class="ms-4 mt-4">
        <ul class="nav flex-column nav-pills">
            <li class="nav-item">
                <a class="nav-link" aria-current="page" href="/admin/user">회원관리</a>
            </li>
            <li class="nav-item">
                <a class="nav-link " href="/admin/board">게시글 관리</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="/admin/reply">댓글 관리</a>
            </li>
        </ul>
    </div>
    
    <div class="container mt-3 ">
        <h2>댓글 관리</h2>
    
        <div class="d-flex justify-content-between">
    
            <div>
                <p>댓글을 삭제할 수 있습니다.</p>
            </div>
     <!-- action="/admin/board/search"  method="get" -->
     <div class="d-flex">
        <div class="me-2 " style="width:200px">
            <select class="form-select" aria-label="Default select example" onchange="changeDropdown(this)">
                <option value="comment">댓글 내용</option>
                <option value="username">댓글 작성자</option>
            </select>
        </div>

        <div class="input-group">
            <div class="form-outline">
                <input id="search-input" type="search" name="comment" class="form-control" placeholder="검색"
                    onkeypress="if(event.keyCode=='13'){event.preventDefault(); searchEvt();}" />
            </div>
            <button id="search-button" class="btn btn-primary" onclick="searchBoard()">
                <i class="fas fa-search"></i>
            </button>
        </div>
        <div>
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
            <tbody id="reply-table">
                <c:forEach items="${replyList}" var="reply">
                        <tr id="reply-${reply.id}" class="remove-table">
                            <td>${reply.id}</td>
                            <td class="my-title-ellipsis"><a href="/board/detail/${reply.boardId}">${reply.comment}</a></td>
                            <td>${reply.username}</td>
                            <td>${reply.boardId}</td>
                            <td>${reply.createdAtToString}</td>
                            <td><button class="btn btn-danger py-0" onclick="deleteUser(`${reply.id}`)">삭제하기</button></td>
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

    function searchEvt() {
        searchBoard();
    };

    function searchBoard() {
        $('.remove-table').remove();
        let keyword;
        if ($('#search-input').attr('name') === 'comment') {
            keyword = `comment`+`=`+$('#search-input').val()
        }
        if ($('#search-input').attr('name') === 'username') {
            keyword = `username`+`=`+$('#search-input').val()
        }
        // console.log(keyword);
        $.ajax({
            type: "get",
            url: "/admin/reply/search?"+keyword,
            dataType: "json"
        }).done((res) => {
            render(res.data);
            console.log(res.data);
        }).fail((err) => {
            alert(err.reponseJSON.msg);
        });
        $('#search-input').val("");
    }

    function render(replys) {
        // console.log('그리는중');
        // console.log(boards[1].id);
        replys.forEach(reply => {
            let id = reply.id;
            let title = reply.title;
            let comment = reply.comment;
            let boardId = reply.boardId;
            let username = reply.username;
            let createdAt = reply.createdAtToString;
            let el = `
                        <tr id="reply-`+id+`" class="remove-table">
                            <td>`+id+`</td>
                            <td class="my-title-ellipsis"><a href="/board/detail/`+boardId+`">`+comment+`</a></td>
                            <td>`+username+`</td>
                            <td>`+boardId+`</td>
                            <td>`+createdAt+`</td>
                            <td><button class="btn btn-danger py-0" onclick="deleteUser(`+id+`)">삭제하기</button></td>
                        </tr>
            `;
            $("#reply-table").append(el);
        });
    }

    function changeDropdown(obj) {
        let DrondwonValue = $(obj).val();
        console.log(DrondwonValue);
        $('#search-input').attr('name', DrondwonValue);
    }
</script>
<%@ include file="../layout/footer.jsp" %>