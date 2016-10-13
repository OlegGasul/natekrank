<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en" ng-app="survey">
<head>
    <link rel="apple-touch-icon" href="${contextPath}/resources/images/favicon.ico">

    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content=""/>
    <meta name="author" content=""/>

    <title>NatekRank - Survey</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${contextPath}/resources/css/main.css" rel="stylesheet"/>

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

<div class="container" ng-controller="SurveyController" ng-init='init(${survey})'>
    <div class="container">
        <div class="list-group" id="questions-list">
            <div ng-repeat="question in survey.task.questions track by $index">
                <div class="cell-question">
                    <li ng-click="selectQuestion(question)" ng-class="{'active': question.selected}" class="list-group-item">
                        {{$index + 1}}
                    </li>
                </div>
            </div>
        </div>

        <div id="question-content" ng-show="selectedQuestion" ng-controller="QuestionController">
            <div>
                <form>
                    <div>
                        {{question.text}}
                    </div>
                    <div ng-repeat="answer in question.answers track by $index" ng-show="question.multiSelect">
                        <input id="checkbox{{$index}}" type="checkbox" ng-model="answer.checked" ng-change="changeAnswer(answer)"/>
                        <label for="checkbox{{$index}}">{{answer.text}}</label>
                    </div>
                    <div ng-repeat="answer in question.answers track by $index" ng-hide="question.multiSelect">
                        <input id="radio{{$index}}" name="answer" type="radio" ng-value="true" ng-model="answer.checked" ng-change="changeAnswer(answer)"/>
                        <label for="radio{{$index}}">{{answer.text}}</label>
                    </div>

                    <div class="control-buttons">
                        <button class="btn btn-default" ng-click="prevQuestion(question)" type="button">Prev</button>
                        <button class="btn btn-primary" ng-click="submitQuestion(question)" type="button">Submit</button>
                        <button class="btn btn-default" ng-click="nextQuestion(question)" type="button">Next</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <input class="btn btn-primary" ng-click="submitSurvey()" type="button" value="Submit test">
</div>
<!-- /container -->

<%@ include file="../includes/footer.jsp" %>

<script>
    var _contextPath = "${contextPath}";
</script>

</body>
</html>
