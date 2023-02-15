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
        <p>게시글을 삭제할 수 있습니다.</p>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>게시글 번호</th>
                    <th>게시글 제목</th>
                    <th>게시글 내용</th>
                    <th>게시글 작성자</th>
                    <th>게시글 작성일</th>
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
    function deleteBoard(id){
        $.ajax({
            type: "delete",
            url: "/admin/board/"+id+"/delete",
            dataType:"json"
        }).done((res) => {
            alert(res.msg);
            $('#board-'+id).remove();
        }).fail((err) => {
            alert(err.responseJSON.msg);
        });
    }
</script>
<%@ include file="../layout/footer.jsp" %>