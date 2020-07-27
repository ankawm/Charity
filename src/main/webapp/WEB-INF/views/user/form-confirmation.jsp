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
    <title>Confirmation</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <li class="logged-user">
                Witaj <c:out value = "${currentUser.firstName}"/>
                <ul class="dropdown">
                    <li><a href="#">Profil</a></li>
                    <li><a href="#">Moje zbiórki</a></li>
                    <li><a href="/logout">Wyloguj</a></li>
                </ul>
            </li>
        </ul>

        <ul>
            <li><a href="/" class="btn btn--without-border active">Start</a></li>
            <li><a href="/#o_co_chodzi" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="/#o_nas" class="btn btn--without-border">O nas</a></li>
            <li><a href="/#fundacje" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="/#kontakt" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>


    <div class="slogan container container--90">
        <h2>
            Dziękujemy za przesłanie formularza <br> Na maila prześlemy wszelkie
            informacje o odbiorze.
        </h2>
    </div>
</header>

<jsp:include page="../footer.jsp"></jsp:include>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
