/**
 * Created by Chertpong on 7/19/2015.
 */
'use strict';
var elasticMainController = angular.module('elasticMainController',[]);

elasticMainController.controller('indexController',['$scope','$http','$location','$rootScope',
    function($scope,$http,$location,$rootScope){
        //Page type
        $scope.result = true;
        $scope.searchPage = true;

        //Response vars
        $scope.max_score = 0;
        $scope.blogs = [];
        //Request vars
        $scope.query = "";

        $scope.search = function () {
            if ($scope.query != "") {
                $http.get('http://localhost:9200/jobs/it/_search', {params: {q: $scope.query}}).success(
                    function (data) {
                        if (data.hits.hits.length != 0) {
                            $scope.result = true;
                            $scope.max_score = data.hits.max_score;
                            $scope.jobs = data.hits.hits;
                            Console.log($scope.max_score);
                        }
                        else {
                            $scope.result = false;
                        }
                    }
                )
            }
            else {
                $http.get('http://localhost:9200/jobs/it/_search', {params: {size: 1000}}).success(
                    function (data) {
                        $scope.max_score = data.hits.max_score;
                        $scope.jobs = data.hits.hits;
                        Console.log($scope.max_score);
                        $scope.result = true;
                    }
                )
            }
        }

    }
]);