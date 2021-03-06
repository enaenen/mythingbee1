<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Welcome Admin</title>
    <script src="/resources/js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="/resources/css/adminlogin.css">
    <script src="/resources/js/adminlogin.js"></script>

</head>

<body>

<div class="wrapper fadeInDown">
    <div id="formContent">
        <!-- Tabs Titles -->
        <h2 class="active"> 로그인</h2>

        <!-- Icon -->
        <div class="fadeIn first">
            <img src="/resources/image/admin/fthinkbee.png" id="icon" alt="User Icon"/>
        </div>
        <div id="status"></div>
    <div>
        <!-- Login Form -->
        <form method="post" action='/admin/login-process' >
            <input type="text" id="id" class="fadeIn second" name="id" placeholder="ID">
            <input type="password" id="password" class="fadeIn third" name="password" placeholder="password">
            <input type="submit" id="btnSubmit" class="fadeIn fourth" value="로그인">
            <c:if test='${not empty error}'>
                <c:choose>
                    <c:when test="${error eq 'Bad credentials'}">
                        <div class="msg"><font color="red">로그인 정보를 확인하세요</font></div>
                    </c:when>
                    <c:otherwise> <div><font color="red">
                        중복접속은 허용되지 않습니다.</font><br></div>
                    </c:otherwise>
                </c:choose>
            </c:if>
            <div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </div>
        </form>
    </div>
</div>
</div>
</body>
</html>
