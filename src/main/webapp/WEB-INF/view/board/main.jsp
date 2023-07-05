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
    #card-frame {
      height: 23em;
    }
    #card-image {
      height: 50%;
      margin: 5px;
    }
    #card-image img {
      width: 100%; /* 이미지가 박스에 가득 차도록 설정 */
      height: 100%; /* 이미지가 박스에 가득 차도록 설정 */
      object-fit: cover; /* 이미지 비율을 유지하면서 가득 채우기 */
    }
</style>
<div class="col-8 mx-auto">
<div class="container-xl my-3">
    <div class="my-grid">
        <c:forEach items="${dtos}" var="dto">
            <div class="card pt-3" id="card-frame">
              <div id="card-image">
                <img class="card-img-top " src="${dto.thumbnail}" alt="Card image">
              </div>
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
                    <h5 class="card-title my-title-ellipsis">${dto.title}</h5>
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