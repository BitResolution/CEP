angular.module('insights-event-handlers', [
    'ui.router',
    'insights-streams'
])
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.when("/event-handler", "/event-handler/list");

        $stateProvider
            .state("eventhandler", {
                url: "/event-handler",
                template: '<ui-view/>',
            })
            .state("eventhandler.create", {
                url: "/create",
                templateUrl: "view/event-handler-edit",
                controller: "EditEventHandlerController"
            })
            .state("eventhandler.edit", {
                url: "/edit/id/{id}",
                templateUrl: "view/event-handler-edit",
                controller: "EditEventHandlerController"
            })
            .state("eventhandler.list", {
                url: "/list",
                templateUrl: "view/event-handler-list",
                controller: "ListEventHandlersController"
            })

    }
    ])

    .factory('EventHandler', ['$resource' , function ($resource) {
        return $resource(Insights.basePath + "api/1.0/event-handler/:id", {id: '@id'}, {
            query: { method: "GET", params: {id: 'list'}, isArray: true},
            update: { method: 'PUT' }
        });
    }])

    .factory('StreamConsumer', ['$resource' , function ($resource) {
            return $resource(Insights.basePath + "api/1.0/stream-consumer/:id", {id: '@id'}, {
                query: { method: "GET", params: {id: 'list'}, isArray: true},
                update: { method: 'PUT' }
            });
    }])

    .controller('ListEventHandlersController', ['$scope', 'EventHandler', function ($scope, EventHandler) {
        $scope.eventHandlers = EventHandler.query();

        $scope.delete = function (eventHandler) {
            eventHandler.$delete(
                function (result) {
                    for (var i = 0; i < $scope.eventHandlers.length; i++) {
                        if ($scope.eventHandlers[i].id == eventHandler.id) {
                            $scope.eventHandlers.splice(i, 1);
                        }
                    }
                },
                function (error) {
                    console.log("Error deleting Stream: " + eventHandler.name + ", " + JSON.stringify(error))
                }
            );
        };
    }])

    .controller('EditEventHandlerController', ['$scope', '$state', '$stateParams', 'EventHandler', 'Stream', 'StreamConsumer', function ($scope, $state, $stateParams, EventHandler, Stream, StreamConsumer) {
        $scope.eventHandler = EventHandler.query();
        $scope.streams = Stream.query();
        $scope.streamConsumers = StreamConsumer.query();

        if ($stateParams.id == undefined) {
            $scope.eventHandler = new EventHandler();
            $scope.eventHandler.id = "";
            $scope.eventHandler.name = "";
            $scope.eventHandler.definition = "";
            $scope.eventHandler.streamName = "";
            $scope.eventHandler.streamConsumer = "";
        } else {
            $scope.eventHandler = EventHandler.get({ id: $stateParams.id });
        }

        $scope.save = function () {
            if ($scope.eventHandler.id == undefined || $scope.eventHandler.id == "") {
                $scope.eventHandler.$save(
                    function (success) {
                        $state.go("eventhandler.list");
                    },
                    function (error) {
                        console.log("Error saving eventHandler:" + error)
                    }
                );
            }
            else {
                $scope.eventHandler.$update(
                    function (success) {
                        $state.go("eventhandler.list");
                    },
                    function (error) {
                        console.log("Error saving eventHandler:" + error)
                    }
                );
            }
        };

        $scope.cancel = function () {
            $state.go("eventhandler.list")
        }

    }]);

;

