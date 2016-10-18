export function SurveyServiceAdmin($http) {
    const endPoint = `${_contextPath}/rest/survey`;

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

SurveyServiceAdmin.$inject = ['$http'];

export function SurveyServiceUser($http) {
    const endPoint = `${_contextPath}/survey/`;

    function submitSurvey(survey) {
        return $http({method: 'post', url: `${endPoint}${survey.token}`, data: survey});
    }

    return {
        submitSurvey,
    }
}

SurveyServiceUser.$inject = ['$http'];