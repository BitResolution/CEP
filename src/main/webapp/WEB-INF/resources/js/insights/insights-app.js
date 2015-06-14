// Make sure to include the `ui.router` module as a dependency
angular.module('insights', [
    'ui.router',
    'ngAnimate',
    'insights-events',
    'insights-streams',
    'insights-partitions',
])

    .run(
    [          '$rootScope', '$state', '$stateParams',
        function ($rootScope,   $state,   $stateParams) {
            // It's very handy to add references to $state and $stateParams to the $rootScope
            // so that you can access them from any scope within your applications.For example,
            // <li ng-class="{ active: $state.includes('contacts.list') }"> will set the <li>
            // to active whenever 'contacts.list' or one of its decendents is active.
            $rootScope.$state = $state;
            $rootScope.$stateParams = $stateParams;
        }
    ]
)

    .config(
    [          '$stateProvider', '$urlRouterProvider',
        function ($stateProvider,   $urlRouterProvider) {
            $urlRouterProvider
                .when('', '/')
                .otherwise('404');


            $stateProvider
                .state("dashboard", {
                    url: "/",
                    templateUrl: "view/dashboard"
                })
                .state("scenarios", {
                    url: "/",
                    template: '<p class="lead">Scenarios</p>'
                })
                .state("analytics", {
                    url: "/",
                    template: '<p class="lead">Analytics</p>'
                })
                .state("help", {
                    url: "/",
                    template: '<p class="lead">Analytics</p>'
                })
                .state("404", {
                    url: "/",
                    template: '<p class="lead">Requested URL not found :(</p>'
                })
        }
    ]
);
