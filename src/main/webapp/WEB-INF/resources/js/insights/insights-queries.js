angular.module('insights-queries', [
    'ui.router',
    'insights-events'
])
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.when("/query", "/query/list");

        $stateProvider
            .state("query", {
                url: "/query",
                template: '<ui-view/>',
            })
            .state("query.create", {
                url: "/create",
                templateUrl: "view/query-edit",
                controller: "EditQueryController"
            })
            .state("query.edit", {
                url: "/edit/id/{id}",
                templateUrl: "view/query-edit",
                controller: "EditQueryController"
            })
            .state("query.list", {
                url: "/list",
                templateUrl: "view/query-list",
                controller: "ListQueriesController"
            })

    }
    ])

    .factory('Query', ['$resource' , function ($resource) {
        return $resource(Insights.basePath + "api/1.0/query/:id", {id: '@id'}, {
            query: { method: "GET", params: {id: 'list'}, isArray: true},
            update: { method: 'PUT' }
        });
    }])

    .controller('ListQueriesController', ['$scope', 'Query', function ($scope, Query) {
        $scope.queries = Query.query();

        $scope.delete = function (query) {
            query.$delete(
                function (result) {
                    for(var i = 0; i < $scope.queries.length; i++) {
                        if($scope.queries[i].id == query.id) {
                            $scope.queries.splice(i, 1);
                        }
                    }
                },
                function (error) {
                    console.log("Error deleting Stream: " + query.name + ", " + JSON.stringify(error))
                }
            );
        };
    }])

    .controller('EditQueryController', ['$scope', '$state', '$stateParams', 'Query', 'EventType', function ($scope, $state, $stateParams, Query, EventType) {
        $scope.eventTypes = EventType.query();

        if ($stateParams.id == undefined) {
            $scope.query = new Query();
            $scope.query.id = "";
            $scope.query.name = "";
            $scope.query.definition = "";
        } else {
            $scope.query = Query.get({ id: $stateParams.id });
        }

        $scope.save = function () {
            if ($scope.query.id == undefined || $scope.query.id == "") {
                $scope.query.$save(
                    function (success) {
                        $state.go("query.list");
                    },
                    function (error) {
                        console.log("Error saving query:" + error)
                    }
                );
            }
            else {
                $scope.query.$update(
                    function (success) {
                        $state.go("query.list");
                    },
                    function (error) {
                        console.log("Error saving query:" + error)
                    }
                );
            }
        };

        $scope.cancel = function () {
            $state.go("query.list")
        }

    }]);

;

