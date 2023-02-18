<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<style>
    .my-xl {
        color: 000;
    }

    .my-cursor {
        cursor: pointer;
    }

    .my-cursor:hover {
        color: red;
    }

    .on-Clicked {
        color: red;
    }
</style>
<div class="container-xl my-3">
    <div class="my-grid">
        <c:forEach items="${dtos}" var="dto">
            <div class="card pt-3">
                <img class="card-img-top " src="${dto.thumbnail}" alt="Card image">
                <hr>
                <div class="card-body my-title-ellipsis">
                    <div class="d-flex">
                        <div class="my-title-ellipsis col-10">작성자 : ${dto.username}</div>
                        <div class="col-2">
                            <div class="d-flex" id="heart-${dto.id}-div">
                            <c:choose>
                                <c:when test="${dto.state == 1}">
                                    <i id="heart-${dto.id}"
                                        class="my-auto fa-regular fa-solid fa-heart my-xl my-cursor on-Clicked"
                                        onclick="heartclick(`${dto.id}`, `${principal.id}`)"></i>
                                </c:when>

                                <c:otherwise>
                                    <i id="heart-${dto.id}" class="my-auto fa-regular fa-heart my-xl my-cursor"
                                        onclick="heartclick(`${dto.id}`, `${principal.id}`)"></i>
                                </c:otherwise>
                            </c:choose>
                            &nbsp <div id="heart-${dto.id}-count">${dto.count}</div>
                        </div>
                    </div>
                </div>
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

<!-- Carousel -->
<div id="demo" class="carousel slide" data-bs-ride="carousel">

    <!-- Indicators/dots -->
    <div class="carousel-indicators">
        <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
        <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
        <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
    </div>

    <!-- The slideshow/carousel -->
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="/images/다운로드 (1).jpg" alt="Los Angeles" class="d-block" style="width:100%">
            <div class="carousel-caption">
                <h3>Los Angeles</h3>
                <p>We had such a great time in LA!</p>
            </div>
        </div>
        <div class="carousel-item">
            <img src="/images/다운로드 (2).jpg" alt="Chicago" class="d-block" style="width:100%">
            <div class="carousel-caption">
                <h3>Chicago</h3>
                <p>Thank you, Chicago!</p>
            </div>
        </div>
        <div class="carousel-item">
            <img src="/images/다운로드 (3).jpg" alt="New York" class="d-block" style="width:100%">
            <div class="carousel-caption">
                <h3>New York</h3>
                <p>We love the Big Apple!</p>
            </div>
        </div>
    </div>

    <!-- Left and right controls/icons -->
    <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
        <span class="carousel-control-prev-icon"></span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
        <span class="carousel-control-next-icon"></span>
    </button>
</div>

<div class="container-fluid mt-3">
    <h3>Carousel Example</h3>
    <p>The following example shows how to create a basic carousel with indicators and controls.</p>
</div>
<script>
    function heartclick(loveId, userId1) {
        console.log(userId1);
        if (userId1 !== null) {
            let data = {
                boardId: loveId,
                userId: userId1
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
                let count1 = res.data.count;
                heart(loveId, count1);
            }).fail((err) => {
                alert(err.responseJSON.msg);
            });
        }
    }

    function heart(id, count) {
        $('#heart-' + id).toggleClass("fa-solid");
        $('#heart-' + id).toggleClass("on-Clicked");
        $('#heart-' + id + '-count').remove();
        render(id, count);
    }

    function render(id, count1) {
        console.log(count1);
        let el = `<div id="heart-` + id + `-count">` + count1 + `</div>`;
        <div id="heart-${dto.id}-count">${dto.count}</div>
        $('#heart-' + id + '-div').append(el);
        
    }
</script>

<%@ include file="../layout/footer.jsp" %>  