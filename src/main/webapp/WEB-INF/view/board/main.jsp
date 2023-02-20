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
<div class="col-8 mx-auto">
<%-- <!-- Carousel -->
<div id="demo" class="carousel slide w-25 mx-auto mt-3" data-bs-ride="carousel">

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
    
            </div>
        </div>
        <div class="carousel-item">
            <img src="/images/다운로드 (2).jpg" alt="Chicago" class="d-block" style="width:100%">
            <div class="carousel-caption">

            </div>
        </div>
        <div class="carousel-item">
            <img src="/images/다운로드 (3).jpg" alt="New York" class="d-block" style="width:100%">
            <div class="carousel-caption">
         
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
</div> --%>


<div class="row  mx-auto mt-3">
  <div class="col-md-4">
    <div id="carouselExampleSlidesOnly1" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img src="/images/다운로드 (1).jpg" class="d-block w-100" alt="Image 1">
        </div>
        <div class="carousel-item">
          <img src="/images/다운로드 (2).jpg" class="d-block w-100" alt="Image 2">
        </div>
        <div class="carousel-item">
          <img src="/images/다운로드 (3).jpg" class="d-block w-100" alt="Image 3">
        </div>
      </div>
    </div>
  </div>
  
  <div class="col-md-4">
    <div id="carouselExampleSlidesOnly2" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img src="/images/다운로드 (2).jpg" class="d-block w-100" alt="Image 4">
        </div>
        <div class="carousel-item">
          <img src="/images/다운로드 (3).jpg" class="d-block w-100" alt="Image 5">
        </div>
        <div class="carousel-item">
          <img src="/images/다운로드 (1).jpg" class="d-block w-100" alt="Image 6">
        </div>
      </div>
    </div>
  </div>
  
  <div class="col-md-4">
    <div id="carouselExampleSlidesOnly3" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img src="/images/다운로드 (3).jpg" class="d-block w-100" alt="Image 7">
        </div>
        <div class="carousel-item">
          <img src="/images/다운로드 (1).jpg" class="d-block w-100" alt="Image 8">
        </div>
        <div class="carousel-item">
          <img src="/images/다운로드 (2).jpg" class="d-block w-100" alt="Image 9">
        </div>
      </div>
    </div>
  </div>
</div>




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
                                &nbsp <div >${dto.count}</div>
                                </div>
                                
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


</div>
<script>
    let boardId;
    let count;
    let state;
    let loveId;
    let userId;

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
        render();
    }

    function render() {
        let el ;
        if ( state === 1 ){
            el = `
            <div id="heart-`+boardId+`-count" class="d-flex">
            <i id="heart- `+boardId+` " class="my-auto my-heart fa-regular fa-solid fa-heart my-xl my-cursor on-Clicked" onclick="heartclick(`+boardId+`,`+state+`,`+userId+`,`+loveId+`)" ></i> 
            &nbsp <div>`+count+`</div></div>
            </div>
            `;
        }
        if ( state === 0 ){
            el = `
            <div id="heart-`+boardId+`-count" class="d-flex">
            <i id="heart- `+boardId+` " class="my-auto fa-regular fa-heart my-xl my-cursor" onclick="heartclick(`+boardId+`,`+state+`,`+userId+`,`+loveId+`)" ></i>
            &nbsp <div>`+count+`</div></div>
            </div>
            `;
        }
        $('#heart-'+boardId+'-div').append(el);
    }
</script>

<%@ include file="../layout/footer.jsp" %>  