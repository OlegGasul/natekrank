function TaskService($http) {
    function getTasks() {
        return $http.get('/task/list').then(function(response) {
            return response.data;
        });
    }

    function getTask(id) {
        return $http.get('/task/' + id).then(function(response) {
            return response.data;
        });
    }

    function saveTask(task) {
        return $http.post('/task', $scope.formData).success(function(response) {

        });
    }

    return {
        getTasks: getTasks,
        getTask: getTask,
        saveTask: saveTask
    }
}

function AdminController($scope, $location, $http, TaskService) {
    $scope.task = {};
    $scope.tasks = [];

    function goTo(path) {
        $location.path(path);
    }

    $scope.showTask = function(task) {
        TaskService.getTask(task.id).then(function(response) {
            $location.path('/task/' + task.id);
            $scope.task = response;
        });
    };

    $scope.saveTask = function() {
        $http({
            method: 'post',
            url: '/task',
            data: $scope.task
        }).then(function successCallback(response) {
            $scope.task = response;
            goTo('/task/' + $scope.task.id);
        }, function errorCallback(response) {
            console.dir(response)
        });
    };

    $scope.getClass = function (path) {
        return ($location.path().substr(0, path.length) === path) ? 'active' : '';
    };

    TaskService.getTasks().then(function (response) {
        $scope.tasks = response;
    });
}

var app = angular
    .module('app', ['ui.router'])
    .controller('AdminController', AdminController)
    .factory('TaskService', TaskService);

app.config(function($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('tasks', {
            url: '/tasks',
            views: {
                'content': {
                    templateUrl: '/resources/templates/admin/tasks.html',
                    controller: 'AdminController'
                }
            }
        })
        .state('task', {
            url: '/task/:id',
            views: {
                'content': {
                    templateUrl: '/resources/templates/admin/task.html',
                    controller: 'AdminController'
                }
            }
        })
        .state('taskForm', {
            url: '/create-task',
            views: {
                'content': {
                    templateUrl: '/resources/templates/admin/task-form.html',
                    controller: 'AdminController'
                }
            }
        })
        .state('tickets', {
            url: '/tickets',
            views: {
                'content': {
                    templateUrl: '/resources/templates/admin/tickets.html',
                    controller: 'AdminController'
                }
            }
        })
})