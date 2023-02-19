<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

    <div class="container my-3">
        <div class="container w-50">
            <form action="/join" method="post" onsubmit="return check()" class="was-validated">
                <div class="h-10">
                <div class="d-flex form-floating mb-2 h-10">
                    <input type="text" name="username" class="form-control" placeholder="Enter username" id="username" required>
                    <label for="username">Username</label>
                    <button type="button" class="badge bg-secondary ms-2" id="usernameCheck">중복확인</button>
                </div>
            </div>
                <div class="form-group mb-2">
                    <input type="password" name="password" class="form-control" placeholder="Enter password" id="password" required>
                    <div id="pw-check1">4자리 이상을 입력해야합니다.</div>
                </div>

                <div class="form-group mb-2">
                    <input type="password" class="form-control" placeholder="Enter passwordCheck" id="passwordCheck" required>
                    <div id="pw-check2">패스워드가 다릅니다</div>
                </div>

                <div class="form-group mb-2">
                    <input type="email" name="email" class="form-control" placeholder="Enter email" id="email" required>
                </div>

                <button type="submit" id="join-btn" class="btn btn-primary">회원가입</button>
            </form>
        </div>
    </div>
<script>
    let p1;
    let p2;
    function check(){
        p1 = $('#password').val();
        p2 = $('#passwordCheck').val();
        if ( p1 === p2 ){
            return true;
        }
        alert('비밀번호가 다릅니다.')
        return false;
    }
    $('#pw-check1').hide();
    $('#password').on('input', ()=>{
        p1 = $('#password').val();
        if ( $('#password').val() != '' && $('#password').val().length < 4 ){
            // console.log( $('#password').val().length);
            $('#pw-check1').show();
        }else{
            $('#pw-check1').hide();
        }
    })
    $('#passwordCheck').on('input',()=>{
        p2 = $('#passwordCheck').val();
        if ( p1 === p2 ){
            $('#pw-check2').hide();
        }else{
            $('#pw-check2').show();
        }

    })

</script>
    
<%@ include file="../layout/footer.jsp" %>