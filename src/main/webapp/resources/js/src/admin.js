/* globals _contextPath */

import angular from 'angular';
import uiRouter from 'angular-ui-router';
import './enable-lr'; // ignored in production build

import {TaskService} from './services/TaskService';
import {SurveyServiceAdmin as SurveyService} from './services/SurveyService'

import {AdminIndex} from './admin/admin-index.component';
import {TaskList} from './admin/task-list.component';
import {TaskItem} from './admin/task-item.component';
import {TaskQuestion} from './admin/task-question.component';
import {SurveyList} from './admin/survey-list.component';
import {SurveyItem} from './admin/survey-item.component';

import {tasks, taskItem, taskCreate, surveys, surveyItem, surveyCreate} from './admin/states';

function config($stateProvider, $urlRouterProvider) {
    $stateProvider.state(tasks);
    $stateProvider.state(taskItem);
    $stateProvider.state(taskCreate);

    $stateProvider.state(surveys);
    $stateProvider.state(surveyItem);
    $stateProvider.state(surveyCreate);

    $urlRouterProvider.otherwise('/tasks');
}

config.$inject = ['$stateProvider', '$urlRouterProvider'];

const adminApp = angular.module('admin', [uiRouter]);

adminApp
    .factory('TaskService', TaskService)
    .factory('SurveyService', SurveyService);

adminApp
    .component('adminIndex', AdminIndex)
    .component('taskList', TaskList)
    .component('taskItem', TaskItem)
    .component('taskQuestion', TaskQuestion)
    .component('surveyList', SurveyList)
    .component('surveyItem', SurveyItem);

adminApp.config(config);

angular.bootstrap(document, [adminApp.name], {strictDi: true});