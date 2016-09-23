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

    function createTestTaken(testTaken) {
        return $http({ method: 'post', url: '/test-taken', data: ticket });
    }

    return {
        getTasks: getTasks,
        getTask: getTask,
        saveTask: saveTask,
        createTestTaken: createTestTaken
    }
}

function TestTakenService($http) {
    function getTestsTaken() {
        return $http.get('/tests').then(function(response) {
            return response.data;
        });
    }

    return {
        getTestsTaken: getTestsTaken
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

function TaskController($scope, $location, TestTakenService, TaskService, task) {
    $scope.task = task;

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

    $scope.sendTest = function() {
        goTo('/send-test/' + $scope.task.id);
    }

    $scope.addNewQuestion = function(task) {
        if (!$scope.task.questions)
            $scope.task.questions = [];
        $scope.task.questions.push({ task_id: task.id, text: 'New question' });
    }

    $scope.editQuestion = function(question) {
        console.dir(question);
        angular.element(document.getElementById("question-content")).scope().question = question;
    }

    $scope.createTestTaken = function(testTaken) {
        TestTakenService.createTestTaken();
    }
}

function QuestionController($scope) {
    $scope.addNewAnswer = function(question) {
        if (!question.answers)
            question.answers = [];
        question.answers.push({ question_id: question.id, text: 'New answer' });
    }
}

function AnswerController($scope) {

}

function TestTakenController($scope, testsTaken) {
    $scope.testsTaken = testsTaken;
    $scope.showTest = function() {

    }
}

var app = angular
    .module('app', ['ui.router'])
    .controller('IndexController', IndexController)
    .controller('TasksController', TasksController)
    .controller('TaskController', TaskController)
    .controller('QuestionController', QuestionController)
    .controller('AnswerController', AnswerController)
    .factory('TaskService', TaskService)
    .factory('TestTakenService', TestTakenService);

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
        .state('test', {
            url: '/send-test/:taskId',
            views: {
                'content': {
                    templateUrl: '/resources/templates/admin/testsTaken.html',
                    controller: 'TestTakenController'
                }
            }
        })
        .state('tests', {
            url: '/tests',
            views: {
                'content': {
                    templateUrl: '/resources/templates/admin/testsTaken.html',
                    controller: 'TestTakenController',
                    resolve: {
                        testsTaken: function(TestTakenService) {
                            return TestTakenService.getTestsTaken();
                        }
                    }
                }
            }
        })
});