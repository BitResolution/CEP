angular.module('insights-partitions', [
    'ui.router',
    'insights-events'
])
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.when("/partition", "/partition/list");

        $stateProvider
            .state("partition", {
                url: "/partition",
                template: '<ui-view/>',
            })
            .state("partition.create", {
                url: "/create",
                templateUrl: "view/partition-edit",
                controller: "EditPartitionController"
            })
            .state("partition.edit", {
                url: "/edit/id/{id}",
                templateUrl: "view/partition-edit",
                controller: "EditPartitionController"
            })
            .state("partition.list", {
                url: "/list",
                templateUrl: "view/partition-list",
                controller: "ListPartitionsController"
            })

    }
    ])

    .factory('Partition', ['$resource' , function ($resource) {
        return $resource(Insights.basePath + "api/1.0/partition/:id", {id: '@id'}, {
            query: { method: "GET", params: {id: 'list'}, isArray: true},
            update: { method: 'PUT' }
        });
    }])

    .controller('ListPartitionsController', ['$scope', 'Partition', function ($scope, Partition) {
        $scope.partitions = Partition.query();

        $scope.delete = function (partition) {
            partition.$delete(
                function (result) {
                    for(var i = 0; i < $scope.partitions.length; i++) {
                        if($scope.partitions[i].id == partition.id) {
                            $scope.partitions.splice(i, 1);
                        }
                    }
                },
                function (error) {
                    console.log("Error deleting Stream: " + partition.name + ", " + JSON.stringify(error))
                }
            );
        };
    }])

    .controller('EditPartitionController', ['$scope', '$state', '$stateParams', 'Partition', 'EventType', function ($scope, $state, $stateParams, Partition, EventType) {
        $scope.eventTypes = EventType.query();
        console.log("Loaded eventTypes: " + JSON.stringify($scope.eventTypes));

        if ($stateParams.id == undefined) {
            $scope.partition = new Partition();
            $scope.partition.id = "";
            $scope.partition.name = "";
            $scope.partition.definition = "";
        } else {
            $scope.partition = Partition.get({ id: $stateParams.id });
        }

        $scope.save = function () {
            if ($scope.partition.id == undefined || $scope.partition.id == "") {
                console.log("Saving Partition: " + JSON.stringify($scope.partition));
                $scope.partition.$save(
                    function (success) {
                        $state.go("partition.list");
                    },
                    function (error) {
                        console.log("Error saving partition:" + error)
                    }
                );
            }
            else {
                console.log("Updating Partition: " + JSON.stringify($scope.partition));
                $scope.partition.$update(
                    function (success) {
                        $state.go("partition.list");
                    },
                    function (error) {
                        console.log("Error saving partition:" + error)
                    }
                );
            }
        };

        $scope.cancel = function () {
            $state.go("partition.list")
        }

    }]);

;

