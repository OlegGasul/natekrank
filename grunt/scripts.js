'use strict';

module.exports = (grunt) => {
    const files = {
        '<%= staticRoot %>/js/admin.js': '<%= jsRoot %>/admin.js',
        '<%= staticRoot %>/js/survey.js': '<%= jsRoot %>/survey.js',
    };

    grunt.config.merge({
        browserify: {
            dev: {
                options: {
                    watch: true,
                    browserifyOptions: {
                        debug: true, // adds sourcemaps
                    },
                    transform: [
                        ['babelify']
                    ]
                },
                files,
            },


            // TODO: add uglify
            prod: {
                options: {
                    transform: [
                        ['babelify']
                    ]
                },
                files,
            },
        },
    });
};