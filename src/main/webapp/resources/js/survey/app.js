function SurveyService($http) {
    function getSurveys() {
        return $http.get('/rest/survey').then(function(response) {
            return response.data;
        });
    }

    function getSurvey(surveyId) {
        return $http.get('/rest/survey/' + surveyId).then(function(response) {
            return response.data;
        });
    }

    function saveSurvey(survey) {
        return $http({ method: 'post', url: '/rest/survey', data: survey });
    }

    return {
        getSurveys: getSurveys,
        saveSurvey: saveSurvey,
        getSurvey: getSurvey
    }
}

function SurveyController($rootScope, $scope, $location, SurveyService) {
    $scope.init = function(email) {
        $rootScope.email = email;
    }

    $scope.start = function() {
        $location.url("./start");
    };
}

var app = angular
    .module('app', ['ui.router'])
    .controller('SurveyController', SurveyController)
    .factory('SurveyService', SurveyService);

app.config(function($stateProvider) {
    $stateProvider
        .state('survey', {
            url: '/survey/:surveyId',
            views: {
                'content': {
                    templateUrl: '/resources/templates/admin/survey.html',
                    controller: 'SurveysController',
                    resolve: {
                        survey: function($stateParams, SurveyService) {
                            return SurveyService.getSurvey($stateParams.surveyId);
                        }
                    }
                }
            }
        })
});