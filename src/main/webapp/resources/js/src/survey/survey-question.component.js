import surveyQuestionTemplate from '../templates/surveyQuestion.html';

function SurveyQuestionController() {
    // fired only for non-multiselect questions
    this.changeAnswer = function(answer) {
        this.question.answers.forEach((ans) => {
            if (ans != answer) {
                ans.checked = false;
            }
        });
    }
}

export const SurveyQuestion = {
    template: surveyQuestionTemplate,
    bindings: {
        question: '<',
    },
    controller: SurveyQuestionController,
};