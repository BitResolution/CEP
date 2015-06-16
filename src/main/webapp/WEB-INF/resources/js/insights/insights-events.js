angular.module('insights-events', [
    'insights-event-types',
    'ui.router',
    'infinite-scroll'
])
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.when("/event", "/event-type");
            $stateProvider
                .state("event", {
                    url: "/event",
                    template: "<ul class='nav nav-tabs'><li ui-sref-active='active'><a ui-sref='event.eventtype.list'>Event Types</a></li><li ui-sref-active='active'><a ui-sref='event.eventlog'>Event Log</a></li></ul><div ui-view=''></div>",
                })
                .state("event.eventlog", {
                    url: "/event-log",
                    templateUrl: "view/events-log",
                    controller: 'EventLogController'
                })
                .state("event.create", {
                    url: "/create",
                    templateUrl: "view/event-edit",
                    controller: "EditEventController"
                })
        }
    ])


    .factory('Event', function ($resource) {
        return $resource(Insights.basePath + "api/1.0/event/:id", {id: '@id'}, {
            query: { method: "GET", params: {id: 'list'}, isArray: true},
            update: { method: 'PUT' }
        });
    })

    .controller('EventLogController', ['$scope', 'Event', function($scope, Event){
        $scope.events = Event.query();
//        $scope.busy = false;
//        $scope.page = 0;
//
//        $scope.nextPage = function() {
//            if ($scope.busy) return;
//            $scope.busy = true;
//
//            var url = Insights.basePath + "api/1.0/event/list/" + this.page;
//            $http.jsonp(url).success(function(data) {
//                var items = data.data.children;
//                for (var i = 0; i < items.length; i++) {
//                    $scope.events.push(items[i].data);
//                }
//                $scope.page += 1;
//                $scope.busy = false;
//            });
//        };

    }])

    .controller('EditEventController', ['$scope', '$state', '$stateParams', 'Event', 'EventType', function ($scope, $state, $stateParams, Event, EventType) {

        $scope.event = new Event();
        $scope.event.type = "";
        $scope.event.contents = "";
        $scope.event.receivedTimestamp = "";

        $scope.eventTypes = EventType.query();

        $scope.save = function () {
            if ($scope.event.id == undefined || $scope.event.id == "") {
                $scope.event.$save(
                    function (success) {
                        $state.go("event.eventlog");
                    },
                    function (error) {
                        console.log("Error saving event:" + error)
                    }
                );
            }
            else {
                $scope.event.$update(
                    function (success) {
                        $state.go("event.eventlog");
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
;

