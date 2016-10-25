import taskItemTemplate from '../templates/task.html';

function TaskItemController($state, TaskService) {
    this.saveTask = function() {
        TaskService.saveTask(this.task).then((response) => {
            this.task = response.data;
            $state.go('task', {id: this.task.id});
        }, (response) => {
            console.log('saveTask error!');
            console.dir(response);
        });
    };

    this.createSurvey = function() {
        $state.go('createSurvey', {id: this.task.id});
    };

    this.addNewQuestion = function() {
        if (!this.task.questions) {
            this.task.questions = [];
        }

        const newQuestion = {
            task_id: this.task.id,
            text: 'New question'
        };

        this.task.questions.push(newQuestion);

        this.selectedQuestion = newQuestion;
    };

    this.editQuestion = function(question) {
        this.selectedQuestion = question;
    };

    this.removeSelectedQuestions = function() {
        for (let i = this.task.questions.length - 1; i >= 0; i--) {
            if (this.task.questions[i].selected) {
                this.task.questions.splice(i, 1);
            }
        }
    };

    this.markQuestion = function() {
        this.hasMarked = this.task.questions.find(q => q.selected);
    }
}

TaskItemController.$inject = ['$state', 'TaskService'];

export const TaskItem = {
    bindings: {
        task: '<',
    },
    template: taskItemTemplate,
    controller: TaskItemController,
};