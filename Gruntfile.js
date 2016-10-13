'use strict';

const loadGruntTasks = require('load-grunt-tasks');

let config = {};

try {
    // create grunt.config.js file to locally override settings
    config = Object.assign(
        config,
        require('./grunt.config')
    );
    console.log('Custom devserver config loaded.');
} catch (e) {
    if (!e.message.startsWith('Cannot find module')) {
        console.error('Error loading custom config: ', e);
    }
}

module.exports = (grunt) => {
    loadGruntTasks(grunt);

    config = Object.assign(
        {
            pkg: grunt.file.readJSON('package.json'),
            staticRoot: 'src/main/webapp/resources',
            jsRoot: 'src/main/webapp/resources/js/src',

            cssRoot: 'src/main/webapp/resources/css/src',
            cssOutAdmin: '<%= staticRoot %>/css/admin.css',
            cssOutSurvey: '<%= staticRoot %>/css/survey.css',
            autoprefixer: {
                dev: 'last 1 version',
                prod: 'last 2 versions'
            },
        },
        config
    );

    grunt.initConfig(config);

    grunt.loadTasks('grunt/');


    grunt.registerTask('default', [
        'browserify:dev',
        'styles:dev',
        'copy:liveDeployImages',
        'watch',
    ]);

    grunt.registerTask('build', [
        'browserify:prod',
    ]);
};
