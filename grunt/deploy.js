'use strict';

// for more info on copying folders with grunt-contrib-copy see:
// http://stackoverflow.com/questions/18966485/copy-all-files-from-directory-to-another-with-grunt-js-copy
function deployTaskFactory(subdir, override = {}) {
    return Object.assign({
        cwd: `<%= staticRoot %>/${subdir}/`, // set working folder / root to copy
        dest: `<%= deployPath %>/${subdir}/`, // destination folder
        src: '**/*', // copy all files and subfolders
        expand: true, // required when using cwd
    }, override);
}

module.exports = (grunt) => {
    let config = {
        copy: {
            liveDeployStyles: {},
            liveDeployImages: {},
            liveDeployScripts: {},
        }
    };

    if (grunt.config.get('deployPath')) {
        config.copy = Object.assign(config.copy, {
            liveDeployStyles: deployTaskFactory('css'),

            liveDeployImages: deployTaskFactory('images'),

            liveDeployScripts: deployTaskFactory('js'),
        });
    } else {
        grunt.log.write('`deployPath` not specified, liveDeploy* are noop tasks`');
    }

    grunt.config.merge(config);
};