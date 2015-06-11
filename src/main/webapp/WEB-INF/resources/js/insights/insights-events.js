angular.module('insights.events', [
    'ui.router',
    'infinite-scroll'
])
    .config(
    [ '$stateProvider', '$urlRouterProvider',
        function ($stateProvider, $urlRouterProvider) {
            $stateProvider

                .state("events", {
                    url: "/",
                    templateUrl: "view/events-list"
                })
        }
    ])
    .controller('ListEventsController', function($scope){
        var events = function() {
            this.events = [];
            this.busy = false;
            this.page = 0;
        }

        $scope.nextPage = function() {
            if (this.busy) return;
            this.busy = true;

            var url = Insights.basePath + "api/1.0/event/list/" + this.page;
            $http.jsonp(url).success(function(data) {
                var items = data.data.children;
                for (var i = 0; i < items.length; i++) {
                    this.events.push(items[i].data);
                }
                this.page += 1;
                this.busy = false;
            }.bind(this));
        };

    })
;

