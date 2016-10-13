<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en" ng-app="app">
<head>
    <link rel="apple-touch-icon" href="${contextPath}/resources/images/favicon.ico"/>

    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content=""/>
    <meta name="author" content=""/>

    <title>NatekRank - Welcome</title>


    <link href="${contextPath}/resources/css/survey.css" rel="stylesheet"/>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${contextPath}/resources/js/lib/html5shiv.min.js"></script>
    <script src="${contextPath}/resources/js/lib/respond.min.js"></script>
    <![endif]-->

    <script src="${contextPath}/resources/js/lib/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/lib/bootstrap.min.js"></script>


    <script src="${contextPath}/resources/js/survey.js"></script>
</head>

<body>

<%@ include file="../includes/header.jsp" %>

<div class="container" ng-controller="SurveyController">

    <h2>Welcome!</h2>

    <p>
        We offer you to pass this test. You will have only ${survey.minutesForSolving} minutes. It's not possible to
        pause test, so make sure you have distraction-free period.
        <br/>
        Click "Start test" when ready.
    </p>

    <a href="?start=1">Start test</a>
</div>
<!-- /container -->

</body>
</html>
