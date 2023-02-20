<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <title>Blog</title>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
            <script src="https://kit.fontawesome.com/32aa2b8683.js" crossorigin="anonymous"></script>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
            <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
            <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
            <link rel="stylesheet" href="/css/style.css">
        </head>
        <style>
            .my-search {
                color: rgb(255, 255, 255);
            }

            #search-header {
                display: none;
            }
        </style>

        <body>

            <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
                <div class="container-fluid">
                    <a class="navbar-brand" href="/">Blog</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapsibleNavbar">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse justify-content-between" id="collapsibleNavbar">
                        <ul class="navbar-nav">
                            <c:choose>
                                <c:when test="${principal == null}">
                                    <li class="nav-item">
                                        <a class="nav-link" href="/login">로그인</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="/join">회원가입</a>
                                    </li>
                                </c:when>

                                <c:otherwise>
                                    <li class="nav-item">
                                        <a class="nav-link" href="/board/write">글쓰기</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="/update">회원수정</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="/logout">로그아웃</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </ul>

                        <div class="d-flex">
                            <div class="me-5">
                                <div class="input-group me-4">
                                    <div class="form-outline me-4">
                                        <input id="search-header" type="search" name="title" class="form-control"
                                            placeholder="검색" style="display: none;"
                                            onkeypress="if(event.keyCode=='13'){event.preventDefault(); searchEvt();}" />
                                    </div>
                                    <div class="my-auto">
                                        <i class="fa-solid fa-magnifying-glass btn btn-dark" onclick="searchBox()"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="mx-5 my-auto">
                                <i class="fa-sharp fa-solid fa-bell btn btn-dark"></i>
                            </div>
                            <div class="ms-5 my-auto">
                                <a href="/user/profileUpdateForm"><img
                                        src="${principal.profile == null ? '/images/default_profile.png' : principal.profile}"
                                        style="width: 35px;" class="rounded-circle" alt="Cinque Terre"></a>
                            </div>
                        </div>
                    </div>

                </div>
            </nav>
            <script>
                function searchBox() {
                    const searchHeader = document.getElementById("search-header");
                    if (searchHeader.style.display === "none") {
                        searchHeader.style.display = "block";
                        }
                        // console.log('dfadf');
                        // searchHeader.style.display = "none";
                    }

                function searchEvt(){
                    keyword = searchHeader.val()
                    location.href="/search?keyword="+keyword;
                }
                // document.addEventListener("click", function () {
                //     searchHeader.style.display.style.display = "none";
                // });

            </script>