/* globals _contextPath */

import angular from 'angular';
import uiRouter from 'angular-ui-router';

function SurveyService($http) {
    const endPoint = `${_contextPath}/survey/`;

    function submitSurvey(survey) {
        return $http({method: 'post', url: `${endPoint}${survey.token}`, data: survey});
    }

    return {
        submitSurvey,
    }
}

function SurveyController($rootScope, $scope, $location, SurveyService) {
    $scope.selectedQuestion = null;

    $scope.init = function(survey) {
        $scope.survey = survey;

        // select first question
        $scope.selectQuestion($scope.survey.task.questions[0]);
    };

    function injectQuestion(question) {
        angular.element(document.getElementById('question-content')).scope().question = question;
    }

    $scope.selectQuestion = function(question) {
        if ($scope.selectedQuestion === question) {
            return;
        }

        question.selected = true;
        injectQuestion(question);

        if ($scope.selectedQuestion) {
            $scope.selectedQuestion.selected = false;
        }

        $scope.selectedQuestion = question;
    };

    function getQuestion(question, offset) {
        const questions = $scope.survey.task.questions;
        const length = questions.length;
        const index = questions.indexOf(question);
        const newIndex = index + offset;

        if (newIndex >= length || index <= -1) {
            return question;
        }

        return questions[newIndex];
    }

    $scope.prevQuestion = function(question) {
        $scope.selectQuestion(getQuestion(question, -1));
    };

    $scope.nextQuestion = function(question) {
        $scope.selectQuestion(getQuestion(question, 1));
    };

    $scope.submitQuestion = function(question) {
        $scope.selectQuestion(getQuestion(question, 1));
    };

    $scope.submitSurvey = function() {
        SurveyService.submitSurvey($scope.survey).then(() => {
            location.href = `${_contextPath}/result/${$scope.survey.token}`;
        });
    };
}

function QuestionController($rootScope, $scope, $location, SurveyService) {
    $scope.changeAnswer = function(answer) {
        if (!$scope.question.multiSelect) {
            $scope.question.answers.forEach((ans) => {
                if (ans != answer) {
                    ans.checked = false;
                }
            });
        }
    }
}

var app = angular
    .module('survey', [uiRouter])
    .controller('SurveyController', SurveyController)
    .controller('QuestionController', QuestionController)
    .factory('SurveyService', SurveyService);