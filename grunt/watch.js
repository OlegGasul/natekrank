'use strict';

module.exports = (grunt) => {
    const config = {
        watch: {
            compileStyles: {
                files: '<%= cssRoot%>/**/*.css',
                tasks: ['styles:dev'],
            },
            deployStyles: {
                files: ['<%= cssOutAdmin %>', '<%= cssOutSurvey %>'],
                tasks: ['copy:liveDeployStyles'],
            },

            compileScripts: {
                files: '<%= jsRoot %>/**/*.js',
                tasks: ['browserify:dev'],
            },
            deployScripts: {
                files: '<%= staticRoot %>/js/*.js',
                tasks: ['copy:liveDeployScripts'],
            },

            images: {
                files: ['<%= staticRoot %>/images/**/*.*'],
                tasks: ['copy:liveDeployImages'],
            }
        }
    };

    grunt.config.merge(config);
};