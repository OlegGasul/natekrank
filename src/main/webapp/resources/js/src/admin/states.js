export const tasks = {
    name: 'tasks',
    url: '/tasks',
    component: 'taskList',
    resolve: {
        tasks: ['TaskService', (TaskService) => TaskService.getTasks()]
    }
};

export const taskItem = {
    name: 'tasksItem',
    url: '/task/:id',
    component: 'taskItem',
    resolve: {
        task: ['$stateParams', 'TaskService', ($stateParams, TaskService) => TaskService.getTask($stateParams.id)],
    },
};

export const taskCreate = {
    name: 'createTask',
    url: '/create-task',
    component: 'taskItem',
    resolve: {
        task: ['$stateParams', 'TaskService', ($stateParams, TaskService) => {
            return Promise.resolve({});
        }],
    }
};

export const surveys = {
    name: 'surveys',
    url: '/surveys',
    component: 'surveyList',
    resolve: {
        surveys: ['SurveyService', (SurveyService) => SurveyService.getSurveys()]
    }
};

export const surveyItem = {
    name: 'surveyItem',
    url: '/survey/:id',
    component: 'surveyItem',
    resolve: {
        survey: ['$stateParams', 'SurveyService', ($stateParams, SurveyService) => {
            return SurveyService.getSurvey($stateParams.id);
        }]
    }
};

export const surveyCreate = {
    name: 'createSurvey',
    url: '/create-survey/:taskId',
    component: 'surveyItem',
    resolve: {
        survey: ['$stateParams', 'TaskService', ($stateParams, TaskService) => {
            return TaskService.getTask($stateParams.taskId).then((task) => {
                return {
                    taskId: $stateParams.taskId,
                    daysExpired: task.daysExpired,
                    minutesForSolving: task.minutesForSolving,
                };
            });
        }]
    }
};