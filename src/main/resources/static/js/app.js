'use strict';

// Declare app level module which depends on views, and components
var elasticApp = angular.module('elasticApp', [
    'ngRoute',
    'elasticMainController'
]);
elasticApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider
            .when('/',{
                templateUrl: 'templates/index.html',
                controller: 'indexController'
            })
            .otherwise({redirectTo: '/'});
    }
]);

