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
            <!-- <li class="nav-item">
                <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
            </li> -->
        </ul>
    </div>

    <div class="container mt-3">
        <h2>게시글 관리</h2>

        <div class="d-flex justify-content-between">

            <div>
                <p>게시글을 삭제할 수 있습니다.</p>
            </div>

                <form action="/admin/board/search" method="post" class="d-flex">
                    <div class="me-2 " style="width:150px">
                        <select class="form-select" aria-label="Default select example" onchange="changeDropdown(this)">
                            <option id="title" value="title">글 제목</option>
                            <option id="content" value="content">글 내용</option>
                            <option id="writer" value="username">작성자</option>
                            <!-- onclick="changeDropdown(this) -->
                        </select>
                    </div>

                    <div class="input-group">
                        <div class="form-outline">
                            <input id="search-input" type="search" name="title" class="form-control"
                                placeholder="검색" />
                        </div>
                        <button id="search-button" type="submit" class="btn btn-primary" onclick="searchBoard()">
                            <i class="fas fa-search"></i>
                        </button>
                    </div>
                </form>
            
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
            <tbody>
                <c:forEach items="${boardList}" var="board">
                    <tr id="board-${board.id}">
                        <td>${board.id}</td>
                        <td>${board.title}</td>
                        <td class="my-title-ellipsis">${board.content}</td>
                        <td>${board.username}</td>
                        <td>${board.createdAtToString}</td>
                        <td><button class="btn btn-danger" onclick="deleteBoard(`${board.id}`)">삭제하기</button></td>
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
    // function searchBoard() {
    //     let data = {
    //         search: $('#form1').val()
    //     }
    //     $.ajax({
    //         type: "post",
    //         url: "/admin/board/search",
    //         data: JSON.stringify(data),
    //         headers: {
    //             "content-type": "application/json; charset=utf-8"
    //         },
    //         dataType: "json"
    //     }).done((res) => {

    //     }).fail((err) => {

    //     });

    // }

    function changeDropdown(obj) {
        let DrondwonValue = $(obj).val();
        console.log(DrondwonValue);
        $('#search-input').attr('name', DrondwonValue);
    }
</script>
<%@ include file="../layout/footer.jsp" %>