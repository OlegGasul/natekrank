'use strict';

const cssnano = require('cssnano');
const autoprefixer = require('autoprefixer');

module.exports = (grunt) => {
    const files = {
        '<%= cssOutSurvey %>': '<%= cssRoot %>/survey.styl',
        '<%= cssOutAdmin %>': '<%= cssRoot %>/admin.styl',
    };

    grunt.config.merge({
        stylus: {
            options: {
                'include css': true,
                'resolve url': true,
                compress: false, // don't spend time on built-in compression
                paths: [
                    './node_modules/',
                ]
            },

            dev: {
                options: {
                    sourcemap: {
                        inline: true,
                    }
                },
                files,
            },

            prod: {
                options: {
                },
                files,
            },
        },
    });

    grunt.config.merge({
        postcss: {
            dev: {
                options: {
                    map: true,

                    processors: [
                        autoprefixer({browsers: grunt.config.get('autoprefixer.dev')}),
                    ],
                },

                files: {
                    '<%= cssOutAdmin %>': '<%= cssOutAdmin %>',
                    '<%= cssOutSurvey %>': '<%= cssOutSurvey %>',
                },
            },

            prod: {
                options: {
                    map: false,

                    processors: [
                        autoprefixer({browsers: grunt.config.get('autoprefixer.prod')}),
                        cssnano(),
                    ],
                },

                files: {
                    '<%= cssOutAdmin %>': '<%= cssOutAdmin %>',
                    '<%= cssOutSurvey %>': '<%= cssOutSurvey %>',
                },
            },
        },
    });

    grunt.registerTask('styles:dev', ['stylus:dev', 'postcss:dev']);
    grunt.registerTask('styles:prod', ['stylus:prod', 'postcss:prod']);
};

