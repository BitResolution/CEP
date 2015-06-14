var eventTypes = angular.module('insights-event-types', [
    'ui.router',
    'ngResource',
])

    .factory('EventType', function ($resource) {
        return $resource(Insights.basePath + "api/1.0/event-type/:id", {id: '@id'}, {
            query: { method: "GET", params: {id: 'list'}, isArray: true},
            update: { method: 'PUT' }
        });
    })

    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.when("/event-type", "/event-type/list");

        $stateProvider
            .state("event.eventtype", {
                url: "^/event-type",
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
                },
            })
            .state("event.eventtype.create", {
                url: "/create",
                templateUrl: "view/event-types-edit",
                controller: "EditEventTypesController"
            })
            .state("event.eventtype.edit", {
                url: "/id/{id}",
                templateUrl: "view/event-types-edit",
                controller: "EditEventTypesController"
            })
            .state("event.eventtype.list", {
                url: "/list",
                templateUrl: "view/event-types-list",
                controller: "EventTypesListController"
            })
    }])

    .controller('EventTypesListController', ['$scope', 'EventType', function ($scope, EventType) {
        $scope.eventTypes = EventType.query();
    }])

    .controller('EditEventTypesController', ['$scope', '$state', '$stateParams', 'EventType', function ($scope, $state, $stateParams, EventType) {
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
                        $state.go("event.eventtype.list");
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
                        $state.go("event.eventtype.list");
                    },
                    function (error) {
                        console.log("Error saving event:" + error)
                    }
                );
            }
        };

        $scope.cancel = function () {
            $state.go("event.eventtype.list")
        }

    }])

    .controller('DeleteEventTypesController', ['$scope', 'EventType', function ($scope, EventType) {
        $scope.eventType = EventType.delete({ id: $stateParams.id });
    }]);

