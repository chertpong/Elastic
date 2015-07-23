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
        //$scope.max_score = 0;
        $scope.blogs = [];
        //Request vars
        $scope.query = '';

        $scope.search = function () {
            console.log('search()');
            $scope.blogs = [];
            if ($scope.query == '') {
                $scope.blogs = [];
                $http.get('/search/all').success(
                    function (data) {
                        console.log('/search/all')
                        if (data.length != 0) {
                            $scope.result = true;
                            //$scope.max_score = data.hits.max_score;
                            $scope.blogs = data;
                            $scope.blogs.forEach(function(e,i,a){
                                var d = moment.unix(e.createDate/1000);
                                e.createDate = d.format();
                            });

                        }
                        else {
                            $scope.result = false;
                            setTimeout(function(){
                                $(".alert-danger").fadeOut(1000);
                            },4000);
                        }
                    }
                )
            }
            else {
                console.log('/search/keywords');
                $http.get('/search/keywords/'+$scope.query).success(
                    function (data) {
                        if (data.length != 0) {
                            $scope.result = true;
                            //$scope.max_score = data.hits.max_score;
                            $scope.blogs = data;
                            $scope.blogs.forEach(function(e,i,a){
                                var d = moment.unix(e.createDate/1000);
                                e.createDate = d.format();
                            });

                        }
                        else {
                            $scope.result = false;
                            setTimeout(function(){
                                $(".alert-danger").fadeOut(1000);
                            },4000);
                        }
                    }
                )
            }
        }

    }
]);