import taskQuestionTemplate from '../templates/question.html';

function TaskQuestionController() {
    var self = this;

    this.addNewAnswer = function() {
        if (!this.question.answers) {
            this.question.answers = [];
        }

        this.question.answers.push({
            question_id: this.question.id,
            text: 'New answer',
            selected: false
        });
    };

    function removeAnswer(index) {
        self.question.answers.splice(index, 1);
    };

    this.deleteAnswer = function(index) {
        if (confirm('Are you sure want to delete this answer?')) {
            removeAnswer(index);
        }
    }
}

TaskQuestionController.$inject = ['$scope'];

export const TaskQuestion = {
    bindings: {
        question: '<',
    },
    template: taskQuestionTemplate,
    controller: TaskQuestionController,
};