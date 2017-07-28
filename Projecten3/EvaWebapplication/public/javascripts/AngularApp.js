var app = angular.module('eva-webapplication', ['ui.router']);

app.config([
    '$stateProvider',
    '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/home',
                templateUrl: 'partials/home',
                controller: 'MainCtrl'
            })
            .state('dishes', {
                url: '/dishes',
                templateUrl: 'partials/dishes',
                controller: 'DishesCtrl',
                resolve: {
					postPromise: ['data', function(data) {
						return data.getAllDishes();
					}]
				}
            })
            .state('achievements', {
                url: '/achievements',
                templateUrl: 'partials/achievements',
                controller: 'AchievementsCtrl',
                resolve: {
					postPromise: ['data', function(data) {
						return data.getAllAchievements();
					}]
				}
            });


        $urlRouterProvider.otherwise('home');
    }]);

app.factory('data', ['$http', function($http){
    var o = {
        dishes: [],
        achievements: []
    };
    o.getAllDishes = function() {
        return $http.get('/dishes').success(function(data){
            angular.copy(data, o.dishes);
            
        });
    };
    o.createDish = function(dish){
        return $http.post("/dishes").success(function(data){
            o.dishes.push(data);
        })
    }
    
    
    
    o.getAllAchievements = function() {
        return $http.get('/achievements').success(function(data){
            angular.copy(data, o.achievements);
        });
    };
    return o;
}]);

app.controller('MainCtrl', [
    '$scope',
    function($scope){
        $scope.test = 'Hello world!';
        
    }]);

app.controller('DishesCtrl', [
    '$scope','data',
    function($scope, dishes){
        $scope.dishes = dishes.dishes;
        
        $scope.createDish = function(){
            //todo
        }
       
        
    }]);

app.controller('AchievementsCtrl', [
    '$scope',,'data'
    function($scope,achievements){
       
        $scope.achievements = achievements.achievements;
    }]);

app.controller('NavCtrl', [
    '$scope',
    function($scope){

    }]);