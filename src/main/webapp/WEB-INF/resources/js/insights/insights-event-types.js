var eventTypes = angular.module('insights.eventtypes', [
    'ui.router',
    'ngResource',
]);

eventTypes.factory('EventType', function ($resource) {
    return $resource(Insights.basePath + "api/1.0/event-type/:id", {id: '@id'}, {
        query: { method: "GET", params: {id: 'list'}, isArray: true} ,
        update: { method: 'PUT' }
    });
});

eventTypes.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state("events.eventtype", {
            abstract: true,
            url: "/event-type",
            template: '<ui-view/>',
            data: {
                attributeTypes: [
                    {value: "BOOLEAN", name: "boolean"},
                    {value: "INT", name: "int"},
                    {value: "LONG", name: "long"},
                    {value: "FLOAT", name: "float"},
                    {value: "DOUBLE", name: "double"},
                    {value: "STRING", name: "string"},
                ]
            }
        })
        .state("events.eventtype.create", {
            url: "/create",
            templateUrl: "view/event-types-edit",
            controller: "EditEventTypesController"
        })
        .state("events.eventtype.edit", {
            url: "/edit/id/{id}",
            templateUrl: "view/event-types-edit",
            controller: "EditEventTypesController"
        })
        .state("events.eventtype.list", {
            url: "/list",
            templateUrl: "view/event-types-list",
            controller: "EventTypesListController"
        })
}]);

eventTypes.controller('EventTypesListController', ['$scope', 'EventType', function ($scope, EventType) {
    $scope.eventTypes = EventType.query();
}]);

eventTypes.controller('EditEventTypesController', ['$scope', '$state', '$stateParams', 'EventType', function ($scope, $state, $stateParams, EventType) {
    if ($stateParams.id == undefined) {
        $scope.eventType = new EventType();
        $scope.eventType.id = "";
        $scope.eventType.name = "";
        $scope.eventType.attributes = [];
    } else {
        $scope.eventType = EventType.get({ id: $stateParams.id });
    }

    $scope.addAttribute = function () {
        if ($scope.eventType.attributes == undefined) {
            $scope.eventType.attributes = []
        }
        $scope.eventType.attributes.push({ id: "", name: "", type: null });
    };

    $scope.removeAttribute = function (id, name, type) {
        for (var i = 0; i < $scope.eventType.attributes.length; i++) {
            var eventType = $scope.eventType.attributes[i];
            if (eventType.id == id && eventType.name == name && eventType.type == type) {
                $scope.eventType.attributes.splice(i, 1);
                return;
            }
        }
    };

    $scope.save = function () {
        if ($scope.eventType.id == undefined || $scope.eventType.id == "") {
            console.log("Saving EventType: " + JSON.stringify($scope.eventType));
            $scope.eventType.$save(
                function (success) {
                    console.log("Succesfully saved EventType");
                    $state.go("events.eventtype.list");
                },
                function (error) {
                    console.log("Error saving event:" + error)
                }
            );
        }
        else {
            console.log("Updating EventType: " + JSON.stringify($scope.eventType));
            $scope.eventType.$update(
                function (success) {
                    console.log("Succesfully saved EventType");
                    $state.go("events.eventtype.list");
                },
                function (error) {
                    console.log("Error saving event:" + error)
                }
            );
        }
    };

    $scope.cancel = function () {
        $state.go("events.eventtype.list")
    }

}]);

eventTypes.controller('DeleteEventTypesController', ['$scope', 'EventType', function ($scope, EventType) {
    $scope.eventType = EventType.delete({ id: $stateParams.id });
}]);

