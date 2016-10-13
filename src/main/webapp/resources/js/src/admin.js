/* globals _contextPath */

import angular from 'angular';
import uiRouter from 'angular-ui-router';

function TaskService($http) {
    const endPoint = `${_contextPath}/rest/task`;

    function getTasks() {
        return $http.get({method: 'get', url: endPoint}).then((response) => response.data);
    }

    function getTask(id) {
        return $http.get({method: 'get', url: `${endPoint}/${id}`}).then((response) => response.data);
    }

    function saveTask(task) {
        return $http({method: 'post', url: endPoint, data: task});
    }

    return {
        getTasks,
        getTask,
        saveTask,
    }
}

function SurveyService($http) {
    const endPoint = _contextPath + '/rest/survey';

    function getSurveys() {
        return $http({method: 'get', url: endPoint}).then((response) => response.data);
    }

    function getSurvey(surveyId) {
        return $http({method: 'get', url: `${endPoint}/${surveyId}`}).then((response) => response.data);
    }

    function saveSurvey(survey) {
        return $http({method: 'post', url: endPoint, data: survey});
    }

    return {
        getSurveys,
        saveSurvey,
        getSurvey,
    }
}

function IndexController($rootScope, $scope, $location, $http, TaskService) {
    $scope.init = function(email) {
        $rootScope.email = email;
    };

    $scope.createTask = function() {
        $location.path('/create-task');
    };

    $scope.getClass = function (path) {
        return ($location.path().substr(0, path.length) === path) ? 'active' : '';
    };
}

function TasksController($scope, $location, TaskService, tasks) {
    $scope.tasks = tasks;

    $scope.showTask = function(task) {
        $location.path('/task/' + task.id);
    }
}

function TaskController($scope, $location, TaskService, task) {
    $scope.task = task;

    $scope.selectedQuestion = null;
    angular.element(document.getElementById('question-content')).scope().question = null;

    $scope.saveTask = function() {
        TaskService.saveTask($scope.task).then((response) => {
            $scope.task = response.data;
            $location.path('/task/' + $scope.task.id);
        }, (response) => {
            console.log('saveTask error!');
            console.dir(response);
        });
    };

    $scope.createSurvey = function() {
        $location.path('/create-survey/' + $scope.task.id);
    };

    $scope.addNewQuestion = function() {
        if (!$scope.task.questions)
            $scope.task.questions = [];
        $scope.task.questions.push({ task_id: task.id, text: 'New question' });

        var lastQuestion = $scope.task.questions[$scope.task.questions.length - 1];
        $scope.selectedQuestion = lastQuestion;
        angular.element(document.getElementById('question-content')).scope().question = lastQuestion;
    };

    $scope.editQuestion = function(question) {
        angular.element(document.getElementById('question-content')).scope().question = question;
        $scope.selectedQuestion = question;
    };

    $scope.getClass = function(question) {
        return $scope.selectedQuestion == question ? 'active' : '';
    };

    $scope.removeSelectedQuestions = function() {
        for (var i = $scope.task.questions.length - 1; i >= 0; i--) {
            if ($scope.task.questions[i].selected) {
                $scope.task.questions.splice(i, 1);
            }
        }
    }
}

function QuestionController($scope) {
    $scope.addNewAnswer = function(question) {
        if (!question.answers) {
            question.answers = [];
        }

        question.answers.push({ question_id: question.id, text: 'New answer', selected: false });
    };

    function removeAnswer(index) {
        $scope.question.answers.splice(index, 1);
    };

    $scope.removeSelectedAnswers = function() {
        for (var i = $scope.question.answers.length - 1; i >= 0; i--) {
            if ($scope.question.answers[i].selected) {
                removeAnswer(i);
            }
        }
    };

    $scope.deleteAnswer = function(index) {
        if (confirm('Are you sure want to delete this answer?')) {
            removeAnswer(index);
        }
    }
}

function AnswerController($scope) {

}

function SurveysController($scope, surveys) {
    $scope.surveys = surveys;
    $scope.showSurvey = function() {

    }
}

function SurveyController($rootScope, $scope, $location, SurveyService, survey) {
    $scope.survey = survey;

    $scope.saveSurvey = function() {
        $scope.survey.sender =  $rootScope.email;

        SurveyService.saveSurvey($scope.survey).then((response) => {
            $scope.survey = response.data;
            $location.path('/survey/' + $scope.survey.id);
        });
    }
}

var app = angular
    .module('admin', [uiRouter])
    .controller('IndexController', IndexController)
    .controller('TasksController', TasksController)
    .controller('TaskController', TaskController)
    .controller('QuestionController', QuestionController)
    .controller('AnswerController', AnswerController)
    .controller('SurveysController', SurveysController)
    .controller('SurveyController', SurveyController)
    .factory('TaskService', TaskService)
    .factory('SurveyService', SurveyService);

app.config(function($stateProvider) {
    $stateProvider
        .state('tasks', {
            url: '/tasks',
            views: {
                content: {
                    templateUrl: `${_contextPath}/resources/templates/admin/tasks.html`,
                    controller: 'TasksController',
                    resolve: {
                        tasks (TaskService) {
                            return TaskService.getTasks();
                        }
                    }
                }
            }
        })
        .state('createTask', {
            url: '/create-task',
            views: {
                content: {
                    templateUrl: `${_contextPath}/resources/templates/admin/task.html`,
                    controller: 'TaskController',
                    resolve: {
                        task ($stateParams, TaskService) {
                            return {};
                        }
                    }
                }
            }
        })
        .state('task', {
            url: '/task/:id',
            views: {
                content: {
                    templateUrl: `${_contextPath}/resources/templates/admin/task.html`,
                    controller: 'TaskController',
                    resolve: {
                        task ($stateParams, TaskService) {
                            return TaskService.getTask($stateParams.id);
                        }
                    }
                }
            }
        })
        .state('createSurvey', {
            url: '/create-survey/:taskId',
            views: {
                content: {
                    templateUrl: `${_contextPath}/resources/templates/admin/survey.html`,
                    controller: 'SurveyController',
                    resolve: {
                        survey ($stateParams, TaskService) {
                            return TaskService.getTask($stateParams.taskId).then((task) => {
                                return {
                                    taskId: $stateParams.taskId,
                                    daysExpired: task.daysExpired,
                                    minutesForSolving: task.minutesForSolving
                                };
                            });
                        }
                    }
                }
            }
        })
        .state('surveys', {
            url: '/surveys',
            views: {
                content: {
                    templateUrl: `${_contextPath}/resources/templates/admin/surveys.html`,
                    controller: 'SurveysController',
                    resolve: {
                        surveys (SurveyService) {
                            return SurveyService.getSurveys();
                        }
                    }
                }
            }
        })
        .state('survey', {
            url: '/survey/:surveyId',
            views: {
                content: {
                    templateUrl: `${_contextPath}/resources/templates/admin/survey.html`,
                    controller: 'SurveysController',
                    resolve: {
                        survey ($stateParams, SurveyService) {
                            return SurveyService.getSurvey($stateParams.surveyId);
                        }
                    }
                }
            }
        })
});