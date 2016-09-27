function TaskService($http) {
    function getTasks() {
        return $http.get('/task').then(function(response) {
            return response.data;
        });
    }

    function getTask(id) {
        return $http.get('/task/' + id).then(function(response) {
            return response.data;
        });
    }

    function saveTask(task) {
        return $http({ method: 'post', url: '/task', data: task });
    }

    return {
        getTasks: getTasks,
        getTask: getTask,
        saveTask: saveTask
    }
}

function SurveyService($http) {
    function getSurveys() {
        return $http.get('/survey').then(function(response) {
            return response.data;
        });
    }

    function createSurvey(survey) {
        return $http({ method: 'post', url: '/survey', data: survey });
    }

    return {
        getTestsTaken: getSurveys,
        createSurvey: createSurvey
    }
}

function IndexController($scope, $location, $http, TaskService) {
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
    angular.element(document.getElementById("question-content")).scope().question = null;

    function goTo(path) {
        $location.path(path);
    }

    $scope.saveTask = function() {
        TaskService.saveTask($scope.task).then(function successCallback(response) {
            $scope.task = response.data;
            goTo('/task/' + $scope.task.id);
        }, function errorCallback(response) {
            console.log('saveTask error!');
            console.dir(response);
        });
    }

    $scope.createSurvey = function() {
        goTo('/create-survey/' + $scope.task.id);
    }

    $scope.addNewQuestion = function() {
        if (!$scope.task.questions)
            $scope.task.questions = [];
        $scope.task.questions.push({ task_id: task.id, text: 'New question' });

        var lastQuestion = $scope.task.questions[$scope.task.questions.length - 1];
        $scope.selectedQuestion = lastQuestion;
        angular.element(document.getElementById("question-content")).scope().question = lastQuestion;
    }

    $scope.editQuestion = function(question) {
        angular.element(document.getElementById("question-content")).scope().question = question;
        $scope.selectedQuestion = question;
    }

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
        if (!question.answers)
            question.answers = [];
        question.answers.push({ question_id: question.id, text: 'New answer' });
    }

    $scope.removeSelectedAnswers = function() {
        for (var i = $scope.question.answers.length - 1; i >= 0; i--) {
            if ($scope.question.answers[i].selected) {
                $scope.question.answers.splice(i, 1);
            }
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

function SurveyController($scope, SurveyService, survey) {
    $scope.survey = survey;

    $scope.createSurvey = function() {
        SurveyService.createSurvey($scope.survey);
    }
}

var app = angular
    .module('app', ['ui.router'])
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
                'content': {
                    templateUrl: '/resources/templates/admin/tasks.html',
                    controller: 'TasksController',
                    resolve: {
                        tasks: function(TaskService) {
                            return TaskService.getTasks();
                        }
                    }
                }
            }
        })
        .state('createTask', {
            url: '/create-task',
            views: {
                'content': {
                    templateUrl: '/resources/templates/admin/task.html',
                    controller: 'TaskController',
                    resolve: {
                        task: function($stateParams, TaskService) {
                            return {};
                        }
                    }
                }
            }
        })
        .state('task', {
            url: '/task/:id',
            views: {
                'content': {
                    templateUrl: '/resources/templates/admin/task.html',
                    controller: 'TaskController',
                    resolve: {
                        task: function($stateParams, TaskService) {
                            return TaskService.getTask($stateParams.id);
                        }
                    }
                }
            }
        })
        .state('createSurvey', {
            url: '/create-survey/:taskId',
            views: {
                'content': {
                    templateUrl: '/resources/templates/admin/survey.html',
                    controller: 'SurveyController',
                    resolve: {
                        survey: function($stateParams, TaskService) {
                            return TaskService.getTask($stateParams.taskId).then(function(task) {
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
                'content': {
                    templateUrl: '/resources/templates/admin/surveys.html',
                    controller: 'SurveyController',
                    resolve: {
                        surveys: function(SurveyService) {
                            return SurveyService.getTestsTaken();
                        }
                    }
                }
            }
        })
});