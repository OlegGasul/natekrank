import taskItemTemplate from '../templates/task.html';

function TaskItemController($state, TaskService) {
    this.saveTask = function() {
        TaskService.saveTask(this.task).then((response) => {
            this.task = response.data;
            this.selectedQuestion = null;
            $state.go('task', {id: this.task.id});
        }, (response) => {
            console.log('saveTask error!');
            console.dir(response);
        });
    };

    this.createSurvey = function() {
        $state.go('createSurvey', {taskId: this.task.id});
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

    this.removeSelectedQuestion = function() {
        if (confirm('Are you sure you want to delete this question?')) {
            let index = this.task.questions.findIndex(q => q.selected);

            this.task.questions.splice(index, 1);

            if (this.task.questions.length !== 0) {
                index = Math.max(index--, 0);
                this.editQuestion(this.task.questions[index]);
            } else {
                this.selectedQuestion = null;
            }
        }
    };
}

TaskItemController.$inject = ['$state', 'TaskService'];

export const TaskItem = {
    bindings: {
        task: '<',
    },
    template: taskItemTemplate,
    controller: TaskItemController,
};