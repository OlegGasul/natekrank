function SurveyService($http) {
    function submitSurvey(survey) {
        return $http({ method: 'post', url: '/survey/' + survey.surveyKey, data: survey });
    }

    return {
        submitSurvey: submitSurvey
    }
}

function SurveyController($rootScope, $scope, $location, SurveyService) {
    $scope.selectedQuestion = null;

    $scope.init = function(survey) {
        $scope.survey = survey;

        var firstQuestion = $scope.survey.task.questions[0];
        $scope.selectQuestion(firstQuestion);
    }

    $scope.start = function() {

    };

    $scope.getClass = function(question) {
        return question.selected ? "active" : "";
    };

    function injectQuestion(question) {
        angular.element(document.getElementById("question-content")).scope().question = question;
    }

    $scope.selectQuestion = function(question) {
        if ($scope.selectedQuestion == question)
            return;

        question.selected = true;
        injectQuestion(question);

        if ($scope.selectedQuestion)
            $scope.selectedQuestion.selected = false;
        $scope.selectedQuestion = question;
    }

    function getQuestion(question, incr) {
        var questions = $scope.survey.task.questions;
        var length = questions.length;
        var index = questions.indexOf(question);
        var newIndex = index + incr;
        if (newIndex >= length || index <= -1)
            return question;

        return questions[newIndex];
    }

    $scope.prevQuestion = function(question) {
        $scope.selectQuestion(getQuestion(question, -1));
    }

    $scope.nextQuestion = function(question) {
        $scope.selectQuestion(getQuestion(question, 1));
    }

    $scope.submitQuestion = function(question) {
        $scope.selectQuestion(getQuestion(question, 1));
    }

    $scope.submitSurvey = function() {
        SurveyService.submitSurvey($scope.survey);
        location.href = "/result/";
    };
}

function QuestionController($rootScope, $scope, $location, SurveyService) {

}

var app = angular
    .module('app', ['ui.router'])
    .controller('SurveyController', SurveyController)
    .controller('QuestionController', QuestionController)
    .factory('SurveyService', SurveyService);