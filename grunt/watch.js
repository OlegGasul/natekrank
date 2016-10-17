'use strict';

module.exports = (grunt) => {
    const watch = {
        compileStyles: {
            files: '<%= cssRoot %>/**/*.styl',
            tasks: ['styles:dev'],
        },

        deployStyles: {
            files: ['<%= cssOutAdmin %>', '<%= cssOutSurvey %>'],
            tasks: ['copy:liveDeployStyles'],
            options: {
                livereload: true,
            },
        },

        deployScripts: {
            files: '<%= staticRoot %>/js/*.js',
            tasks: ['copy:liveDeployScripts'],
            options: {
                livereload: true,
            },
        },

        images: {
            files: ['<%= staticRoot %>/images/**/*.*'],
            tasks: ['copy:liveDeployImages'],
            options: {
                livereload: true,
            },
        },
    };

    grunt.config.merge({watch});
};