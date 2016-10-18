export function TaskService($http) {
    const endPoint = `${_contextPath}/rest/task`;

    function getTasks() {
        return $http({method: 'get', url: endPoint}).then((response) => response.data);
    }

    function getTask(id) {
        return $http({method: 'get', url: `${endPoint}/${id}`}).then((response) => response.data);
    }

    function saveTask(task) {
        return $http({method: 'post', url: endPoint, data: task});
    }

    return {
        getTasks,
        getTask,
        saveTask,
    }
}

TaskService.$inject = ['$http'];