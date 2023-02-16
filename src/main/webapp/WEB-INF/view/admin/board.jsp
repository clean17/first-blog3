<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<div class="d-flex mx-auto">
    <div class="ms-4 mt-4">
        <ul class="nav flex-column nav-pills">
            <li class="nav-item">
                <a class="nav-link" aria-current="page" href="/admin/user">회원관리</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="/admin/board">게시글 관리</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/reply">댓글 관리</a>
            </li>
        </ul>
    </div>

    <div class="container mt-3">
        <h2>게시글 관리</h2>

        <div class="d-flex justify-content-between">

            <div>
                <p>게시글을 삭제할 수 있습니다.</p>
            </div>
            <!-- action="/admin/board/search"  method="get" -->
            <div class="d-flex">
                <div class="me-2 " style="width:150px">
                    <select class="form-select" aria-label="Default select example" onchange="changeDropdown(this)">
                        <option id="title" value="title">글 제목</option>
                        <option id="content" value="content">글 내용</option>
                        <option id="writer" value="username">작성자</option>
                    </select>
                </div>

                <div class="input-group">
                    <div class="form-outline">
                        <input id="search-input" type="search" name="title" class="form-control" placeholder="검색"
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
                    <th>게시글 번호</th>
                    <th>게시글 제목</th>
                    <th>게시글 내용</th>
                    <th>게시글 작성자</th>
                    <th>게시글 작성일</th>
                    <th>게시글 삭제</th>
                </tr>
            </thead>
            <tbody id="board-table">
                <c:forEach items="${boardList}" var="board">
                    <tr id="board-${board.id}" class="remove-table">
                        <td>${board.id}</td>
                        <td><a href="/board/detail/${board.id}">${board.title}</a>  </td>
                        <td class="my-title-ellipsis">${board.content}</td>
                        <td>${board.username}</td>
                        <td>${board.createdAtToString}</td>
                        <td><button class="btn btn-danger py-0 " onclick="deleteBoard(`${board.id}`)">삭제하기</button></td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
    </div>
</div>
<script>
    function deleteBoard(id) {
        $.ajax({
            type: "delete",
            url: "/admin/board/" + id + "/delete",
            dataType: "json"
        }).done((res) => {
            alert(res.msg);
            $('#board-' + id).remove();
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
        if ($('#search-input').attr('name') === 'title') {
            keyword = `title`+`=`+$('#search-input').val()
        }
        if ($('#search-input').attr('name') === 'content') {
            keyword = `content`+`=`+$('#search-input').val()
        }
        if ($('#search-input').attr('name') === 'username') {
            keyword = `username`+`=`+$('#search-input').val()
        }
        console.log(keyword);
        $.ajax({
            type: "get",
            url: "/admin/board/search?"+keyword,
            dataType: "json"
        }).done((res) => {
            render(res.data);
            // console.log(res.data);
        }).fail((err) => {
            alert(err.reponseJSON.msg);
        });
        $('#search-input').val("");
    }

    function render(boards) {
        // console.log('그리는중');
        // console.log(boards[1].id);
        boards.forEach(board => {
            let id = board.id;
            let title = board.title;
            let content = board.content;
            let username = board.username;
            let createdAt = board.createdAtToString;
            let el = `
                    <tr id="board-`+ id + `" class="remove-table">
                        <td>`+ id + `</td>
                        <td>`+ title + `</td>
                        <td class="my-title-ellipsis">`+ content + `</td>
                        <td>`+ username + `</td>
                        <td>`+ createdAt + `</td>
                        <td><button class="btn btn-danger" onclick="deleteBoard(`+ id + `)">삭제하기</button></td>
                    </tr>
            `;
            $("#board-table").append(el);
        });
    }

    function changeDropdown(obj) {
        let DrondwonValue = $(obj).val();
        console.log(DrondwonValue);
        $('#search-input').attr('name', DrondwonValue);
    }
</script>
<%@ include file="../layout/footer.jsp" %>