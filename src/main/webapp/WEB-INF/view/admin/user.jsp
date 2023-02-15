<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>
        <div class="d-flex mx-auto">
            <div class="ms-4 mt-4">
                <ul class="nav flex-column nav-pills">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/admin/user">회원관리</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/board">게시글 관리</a>
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




                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>회원 번호</th>
                            <th>회원 아이디</th>
                            <th>회원 E-mail</th>
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
                                    <td>${user.role}</td>
                                    <td>${user.createdAtToString}</td>
                                    <td><button class="btn btn-danger" onclick="deleteUser(`${user.id}`)">삭제하기</button>
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