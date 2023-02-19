<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>
<div class="d-flex mx-auto">
    <div class="ms-4 mt-4">
        <ul class="nav flex-column nav-pills">
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="/admin/user">회원관리</a>
            </li>
            <li class="nav-item">
                <a class="nav-link " href="/admin/board">게시글 관리</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/reply">댓글 관리</a>
            </li>
        </ul>
    </div>

            <div class="container mt-3">
                <h2>회원 관리</h2>

                <div class="d-flex justify-content-between">

                    <div>
                        <p>회원의 권한을 변경하거나 계정을 삭제할 수 있습니다.</p>
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



    <!-- 버튼 클릭시 모달 생김 -->
    <div class="modal" id="myModal">
        <div class="modal-dialog">
            <!-- modal-sm modal-lg modal-xl 모달 사이즈 -->
            <!-- modal-dialog-centered 화면 가운데 -->
            <!-- modal-dialog-scrollable 스크롤 기능 -->
            <div class="modal-content">

                <div class="modal-header">
                    <h4 class="modal-title">Modal Heading</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <h1>메일 발송</h1>
                    <form action="/admin/mail" method="post">
                        <input name="address" placeholder="이메일 주소" class="form-control" value=""> <br>
                        <input name="title" placeholder="제목" class="form-control"> <br>
                        <textarea name="message" placeholder="메일 내용을 입력해주세요." cols="60" rows="20" class="form-control"></textarea>
                        <button type="submit" class="btn btn-primary mt-2">발송</button>
                    </form>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>회원 번호</th>
                            <th>회원 아이디</th>
                            <th>회원 E-mail</th>
                            <th>
                                메일 전송
                            </th>
                            <th>회원 권한</th>
                            <th>계정 생성일</th>
                            <th>회원 계정 삭제</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${userList}" var="user">
                            <c:if test="${user.role == 'USER'}">
                                <tr id="user-${user.id}">
                                    <td>${user.id}</td>
                                    <td>${user.username}</td>
                                    <td>${user.email}</td>
                                    <td><button type="button" class="py-0 btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">
                                    메일 전송
                                    </button></td>
                                    <td>${user.role}</td>
                                    <td>${user.createdAtToString}</td>
                                    <td><button class="btn btn-danger py-0" onclick="deleteUser(`${user.id}`)">삭제하기</button>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <script>
            function deleteUser(id) {
                $.ajax({
                    type: "delete",
                    url: "/admin/user/" + id + "/delete",
                    dataType: "json"
                }).done((res) => {
                    alert(res.msg);
                    $('#user-' + id).remove();
                }).fail((err) => {
                    alert(err.responseJSON.msg);
                });
            }
            const searchButton = document.getElementById('search-button');
            const searchInput = document.getElementById('search-input');
            searchButton.addEventListener('click', () => {
                const inputValue = searchInput.value;
                alert(inputValue);
            });
        </script>
<%@ include file="../layout/footer.jsp" %>