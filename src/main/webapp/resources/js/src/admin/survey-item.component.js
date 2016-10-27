import surveyItemTemplate from '../templates/survey.html';

function SurveyController($rootScope, $state, SurveyService) {
    this.saveSurvey = function() {
        this.survey.sender =  $rootScope.email;

        SurveyService.saveSurvey(this.survey).then((response) => {
            this.survey = response.data;
            $state.go('surveyItem', {id: this.survey.id});
        });
    };

    this.resendSurvey = function(survey) {
        SurveyService.resendSurvey(survey);
    };
}
SurveyController.$inject = ['$rootScope', '$state', 'SurveyService'];

export const SurveyItem = {
    template: surveyItemTemplate,
    bindings: {
        survey: '<'
    },
    controller: SurveyController,
};