<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Log in with your account</title>

    <link href="${contextPath}/resources/css/admin.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${contextPath}/resources/js/lib/html5shiv.min.js"></script>
    <script src="${contextPath}/resources/js/lib/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">

    <form method="post" action="${contextPath}/login" class="form-signin col-md-6 col-md-offset-3">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <h2 class="form-heading">Log in</h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span class="help-block">${message}</span>
            <input name="email" type="text" class="form-control" placeholder="Username" autofocus="true"/>

            <span class="help-block">${error}</span>
        </div>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <input name="password" type="password" class="form-control" placeholder="Password"/>
        </div>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
    </form>

</div>
<!-- /container -->
<script src="${contextPath}/resources/js/lib/jquery.min.js"></script>
<script src="${contextPath}/resources/js/lib/bootstrap.min.js"></script>
<script>
    var _contextPath = "${contextPath}";
</script>
</body>
</html>
