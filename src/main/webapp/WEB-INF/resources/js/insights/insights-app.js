// Make sure to include the `ui.router` module as a dependency
angular.module('insights-dashboard', [
    'ui.router',
    'ngAnimate',
    'insights.events',
    'insights.utils.service',
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

            /////////////////////////////
            // Redirects and Otherwise //
            /////////////////////////////
            $urlRouterProvider
            // when there is an empty route, redirect to /index
                .when('', '/')
                // If the url is ever invalid, e.g. '/asdf', then redirect to '/' aka the home state
                .otherwise('/404');


            //////////////////////////
            // State Configurations //
            //////////////////////////
            $stateProvider
                .state("dashboard", {
                    url: "/",
                    templateUrl: "view/mock-content"
                })
                .state("streams", {
                    url: "/",
                    template: '<p class="lead">Streams</p>'
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
