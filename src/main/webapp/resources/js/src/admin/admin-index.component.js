import adminIndexTemplate from '../templates/adminIndex.html';

function AdminIndexController($rootScope) {
    $rootScope.email = this.email;
}
AdminIndexController.$inject = ['$rootScope'];

export const AdminIndex = {
    template: adminIndexTemplate,
    bindings: {
        email: '<'
    },
    controller: AdminIndexController,
};