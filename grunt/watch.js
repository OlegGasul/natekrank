'use strict';

module.exports = (grunt) => {
    const watch = {
        compileStyles: {
            files: '<%= cssRoot%>/**/*.css',
            tasks: ['styles:dev'],
        },

        deployStyles: {
            files: ['<%= cssOutAdmin %>', '<%= cssOutSurvey %>'],
            tasks: ['copy:liveDeployStyles'],
        },

        deployScripts: {
            files: '<%= staticRoot %>/js/*.js',
            tasks: ['copy:liveDeployScripts'],
        },

        images: {
            files: ['<%= staticRoot %>/images/**/*.*'],
            tasks: ['copy:liveDeployImages'],
        },
    };

    grunt.config.merge({watch});
};