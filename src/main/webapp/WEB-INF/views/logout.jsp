<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<section>
    <div class="container">
            <br>
            <h3 class="form-heading">Are you sure you want to log out? Confirm and log out</h3>
            <br>
                <sec:authorize access="isAuthenticated()">
                    <form action="<c:url value="/logout"/>" method="POST" class="form-signin">
                        <input class="btn btn-lg btn-primary btn-block w-25 p-2" type="submit" value="Log out">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </sec:authorize>
    </div>
</section>
</body>
</html>