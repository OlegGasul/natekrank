<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en" ng-app="app">
<head>
    <link rel="apple-touch-icon" href="${contextPath}/resources/images/favicon.ico">

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>NatekRank - Admin</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/main.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/lib/bootstrap.min.js"></script>

    <script src="${contextPath}/resources/js/lib/angular.min.js"></script>
    <script src="${contextPath}/resources/js/lib/angular-ui-router.js"></script>
    <script src="${contextPath}/resources/js/app.js"></script>
</head>

<body>
<div class="container" ng-controller="IndexController" ng-init="init('${pageContext.request.userPrincipal.name}')">
    <div class="container">
        <div id="logo">
            <img src="${contextPath}/resources/images/small-logo.png" border="0" />
            <h2>NatekRank</h2>
        </div>

        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>

            ${pageContext.request.userPrincipal.name} | <a href="#" onclick="document.forms['logoutForm'].submit()">Logout</a>
        </c:if>
    </div>

    <ul class="nav nav-pills" ng-controller="IndexController">
        <li role="presentation" ng-class="getClass('/tasks')"><a href="#tasks">Task defenitions</a></li>
        <li role="presentation" ng-class="getClass('/surveys')"><a href="#surveys">Tests taken</a></li>
    </ul>

    <div>
        <div ui-view="content"></div>
    </div>
</div>

</body>
</html>
