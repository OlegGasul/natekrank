/* globals _contextPath */

import angular from 'angular';
import {SurveyServiceUser as SurveyService} from './services/SurveyService';
import './enable-lr'; // ignored in production build

import {SurveyIndex} from './survey/survey-index.component';
import {SurveyQuestion} from './survey/survey-question.component';

const surveyApp = angular.module('survey', []);

surveyApp
    .factory('SurveyService', SurveyService);

surveyApp
    .component('surveyIndex', SurveyIndex)
    .component('surveyQuestion', SurveyQuestion);

angular.bootstrap(document, [surveyApp.name], {strictDi: true});