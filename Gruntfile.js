'use strict';

const loadGruntTasks = require('load-grunt-tasks');

let config = {};

try {
    // create grunt.config.js file to locally override settings
    config = Object.assign(
        config,
        require('./grunt.config')
    );
    console.log('Grunt config override loaded.');
} catch (e) {
    if (!e.message.startsWith('Cannot find module')) {
        console.error('Error loading custom config: ', e);
    }
}

module.exports = (grunt) => {
    loadGruntTasks(grunt);

    const pkg = grunt.file.readJSON('package.json');

    config = Object.assign(
        {
            pkg,
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

    // automatically create deployPath value
    // please note that *.war filename is taken from package.json, so keep this up to date
    if (config.tomcatDir && !config.deployPath) {
        config.deployPath = `${config.tomcatDir}\\${pkg.name}\\resources`;
    }

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
