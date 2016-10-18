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
                        ['babelify'], ['html2js-browserify']
                    ]
                },
                files,
            },

            // TODO: add uglify
            prod: {
                options: {
                    configure (browserifyInstance) {
                        // browserify transforms all relative paths to absolute when using `ignore` options
                        // thus we need to manually put relative path to _ignore array
                        browserifyInstance._ignore.push('./enable-lr');
                    },
                    transform: [
                        ['babelify'], ['html2js-browserify']
                    ]
                },
                files,
            },
        },
    });
};