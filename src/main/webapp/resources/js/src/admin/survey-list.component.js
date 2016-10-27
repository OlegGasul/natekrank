import surveysListTemplate from '../templates/surveys.html';

function SurveyListController(SurveyService) {
    this.resendSurvey = function(survey) {
        SurveyService.resendSurvey(survey);
    };
}
SurveyListController.$inject = ['SurveyService'];

export const SurveyList = {
    bindings: {
        surveys: '<'
    },
    controller: SurveyListController,
    template: surveysListTemplate,
};