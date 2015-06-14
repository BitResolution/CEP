angular.module('insights-streams', [
    'ui.router',
    'insights-events'
])
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.when("/streams", "/streams/list");

        $stateProvider
            .state("stream", {
                url: "/streams",
                template: '<ui-view/>',
            })
            .state("stream.create", {
                url: "/create",
                templateUrl: "view/streams-edit",
                controller: "EditStreamController"
            })
            .state("stream.edit", {
                url: "/edit/id/{id}",
                templateUrl: "view/streams-edit",
                controller: "EditStreamController"
            })
            .state("stream.list", {
                url: "/list",
                templateUrl: "view/streams-list",
                controller: "ListStreamsController"
            })

    }
    ])

    .factory('Stream', ['$resource' , function ($resource) {
        return $resource(Insights.basePath + "api/1.0/stream/:id", {id: '@id'}, {
            query: { method: "GET", params: {id: 'list'}, isArray: true},
            update: { method: 'PUT' }
        });
    }])

    .controller('ListStreamsController', ['$scope', 'Stream', function ($scope, Stream) {
        $scope.streams = Stream.query();
    }])

    .controller('EditStreamController', ['$scope', '$state', '$stateParams', 'Stream', 'EventType', function ($scope, $state, $stateParams, Stream, EventType) {
        $scope.eventTypes = EventType.query();
        console.log("Loaded eventTypes: " + JSON.stringify($scope.eventTypes));

        if ($stateParams.id == undefined) {
            $scope.stream = new Stream();
            $scope.stream.id = "";
            $scope.stream.name = "";
            $scope.stream.eventType = null;
        } else {
            $scope.stream = Stream.get({ id: $stateParams.id });
        }

        $scope.save = function () {
            if ($scope.stream.id == undefined || $scope.stream.id == "") {
                console.log("Saving Stream: " + JSON.stringify($scope.stream));
                $scope.stream.$save(
                    function (success) {
                        $state.go("stream.list");
                    },
                    function (error) {
                        console.log("Error saving stream:" + error)
                    }
                );
            }
            else {
                console.log("Updating Stream: " + JSON.stringify($scope.stream));
                $scope.stream.$update(
                    function (success) {
                        $state.go("stream.list");
                    },
                    function (error) {
                        console.log("Error saving stream:" + error)
                    }
                );
            }
        };

        $scope.cancel = function () {
            $state.go("stream.list")
        }

    }]);

;

