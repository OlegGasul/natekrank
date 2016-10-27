import surveyIndexTemplate from '../templates/surveyIndex.html';

function SurveyIndexController($timeout, SurveyService) {
    this.selectQuestion = function(question) {
        if (this.selectedQuestion === question) {
            return;
        }

        this.selectedQuestion = question;
    };

    this.getQuestion = function (offset) {
        const questions = this.survey.task.questions;
        const length = questions.length;
        const index = questions.indexOf(this.selectedQuestion);
        const newIndex = index + offset;

        if (newIndex >= length || index <= -1) {
            return this.selectedQuestion;
        }

        return questions[newIndex];
    };

    this.prevQuestion = function() {
        this.selectQuestion(this.getQuestion(-1));
    };

    this.nextQuestion = function() {
        this.selectQuestion(this.getQuestion(1));
    };

    this.submitQuestion = function() {
        this.selectQuestion(this.getQuestion(1));
    };

    this.submitSurvey = function() {
        SurveyService.submitSurvey(this.survey).then(() => {
            location.href = `${_contextPath}/result/${this.survey.token}`;
        });
    };

    $timeout(() => {
        // do you still think angular is good framework?
        this.selectQuestion(this.survey.task.questions[0]);
    });

    this.startedAt = Date.now();

    this._contextPath = _contextPath;
}

SurveyIndexController.$inject = ['$timeout', 'SurveyService'];

export const SurveyIndex = {
    template: surveyIndexTemplate,
    bindings: {
        survey: '<',
    },
    controller: SurveyIndexController,
};