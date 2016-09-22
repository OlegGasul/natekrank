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

    function createTicket(ticket) {
        return $http({ method: 'post', url: '/ticket', data: ticket });
    }

    return {
        getTasks: getTasks,
        getTask: getTask,
        saveTask: saveTask,
        createTicket: createTicket
    }
}

function TicketsService($http) {
    function getTickets() {
        return $http.get('/ticket').then(function(response) {
            return response.data;
        });
    }

    return {
        getTickets: getTickets
    }
}

function IndexController($scope, $location, $http, TaskService) {
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

function TaskController($scope, $location, TicketsService, TaskService, task) {
    $scope.task = task;
    $scope.currentQuestion = {};

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

    $scope.addNewQuestion = function(task) {
        $scope.task.questions.push({ task_id: task.id, text: 'New question' });
    }

    $scope.editQuestion = function(question) {
        console.dir(question);
        angular.element(document.getElementById("question-content")).scope().question = question;
    }

    $scope.createTicket = function(ticket) {
        TicketsService.createTicket();
    }
}

function QuestionController($scope) {
    $scope.saveQuestion = function(item) {
        alert('aveQuestion');
    }
}

function TicketsController($scope, $location, TicketsService, tickets) {
    $scope.tickets = tickets;

    $scope.showTicket = function(ticket) {
        $location.path('/ticket/' + ticket.id);
    }
}

function TicketController($scope, $location, TicketsService, ticket) {
    $scope.ticket = ticket;

    $scope.saveTicket = function() {
        TaskService.saveTicket($scope.ticket).then(function successCallback(response) {
            $scope.ticket = response.data;
            goTo('/ticket/' + $scope.ticket.id);
        }, function errorCallback(response) {
            console.log('saveTicket error!');
            console.dir(response);
        });
    }
}

var app = angular
    .module('app', ['ui.router'])
    .controller('IndexController', IndexController)
    .controller('TasksController', TasksController)
    .controller('TaskController', TaskController)
    .controller('TicketsController', TicketsController)
    .controller('TicketController', TicketController)
    .controller('QuestionController', QuestionController)
    .factory('TaskService', TaskService)
    .factory('TicketsService', TicketsService);

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
        .state('tickets', {
            url: '/tickets',
            views: {
                'content': {
                    templateUrl: '/resources/templates/admin/tickets.html',
                    controller: 'TicketsController',
                    resolve: {
                        tickets: function(TicketsService) {
                            return TicketsService.getTickets();
                        }
                    }
                }
            }
        })
});