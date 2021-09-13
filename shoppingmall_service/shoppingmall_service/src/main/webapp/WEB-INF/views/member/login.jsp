<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="/assets/js/login.js"></script>
    <script>
        <c:if test="${member != null}">
            location.href = "/";
        </c:if>
    </script>
    <title>Document</title>
</head>
<body>
    <h1>로그인</h1>
    <div class="login_form">
        <input type="text" placeholder="아이디" id="user_id">
        <input type="password" placeholder="비밀번호" id="user_pwd">
        <button id="login_btn">로그인</button>
        <a href="/join">회원가입</a>
    </div>
</body>
</html>