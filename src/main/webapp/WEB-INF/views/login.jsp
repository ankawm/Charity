<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="pl">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>

<jsp:include page="header2.jsp"></jsp:include>

    <section class="login-page">
        <h2>Zaloguj się</h2>
    <form method="POST" action="${contextPath}/login" class="form-signin">
        <div class="form-group ${error != null ? 'has-error' : ''}">
        <div class="form-group">
            <span>${message}</span>
            <br>
            <input name="username" type="text" placeholder="Email"
                   autofocus="true"/>
        </div>
        <div class="form-group">
            <input type="password" name="password" placeholder="Hasło" />
            <a href="#" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
            <span>${error}</span><br>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </div>

        <div class="form-group form-group--buttons">
            <a href="${contextPath}/registration" class="btn btn--without-border">Załóż konto</a>
            <button class="btn" type="submit">Zaloguj się</button>
        </div>
        </div>
    </form>
    </section>

<jsp:include page="footer.jsp"></jsp:include>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
