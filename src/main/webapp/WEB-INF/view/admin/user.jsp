<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
    <div class="container my-3">
        <div class="my-grid">
        <c:forEach items="${dtos}" var="dto">
            <div class="card pt-3">
                <img class="card-img-top " src="${dto.thumbnail}" alt="Card image">
                <hr>
                <div class="card-body my-title-ellipsis">
                    <div class="my-title-ellipsis">작성자 : ${dto.username}</div>
                    <h4 class="card-title my-title-ellipsis">${dto.title}</h4>
                    <a href="/board/detail/${dto.id}" class="btn btn-primary">상세보기</a>
                </div>
            </div>
        </c:forEach>
        </div>
        <ul class="pagination mt-3 d-flex justify-content-center">
            <li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
            <li class="page-item"><a class="page-link" href="#">Next</a></li>
        </ul>
    </div>

<%@ include file="../layout/footer.jsp" %>