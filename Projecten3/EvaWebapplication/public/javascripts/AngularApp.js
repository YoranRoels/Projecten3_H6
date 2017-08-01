var app = angular.module('eva-webapplication', ['ui.router']);

app.config([
    '$stateProvider',
    '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/home',
                templateUrl: 'partials/home',
                controller: 'MainCtrl',
                resolve: {
					postPromise: ['data', function(data) {
						return data.getAllDishes();
					}]
				}
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
            }).state('challenges', {
                url: '/challenges',
                templateUrl: 'partials/challenges',
                controller: 'challengesCtrl',
                resolve: {
					postPromise: ['data', function(data) {
						return data.getAllChallanges();
					}]
				}
                
            });;


        $urlRouterProvider.otherwise('home');
    }]);

app.factory('data', ['$http', function($http){
    var o = {
        dishes: [],
        achievements: [],
        challenges:[]
    };
    o.getAllDishes = function() {
        return $http.get('/dishes').success(function(data){
            angular.copy(data, o.dishes);
            
        });
    };
     o.getAllChallanges = function() {
        return $http.get('/challenges').success(function(data){
            angular.copy(data, o.challenges);
            
        });
    };
    o.createDish = function(dish){
        return $http.post("/dishes",dish).success(function(data){
            o.dishes.push(data);
        })
    }
     o.createAchievement = function(achievement){
        return $http.post("/achievements",achievement).success(function(data){
            o.achievements.push(data);
        })
    }
     o.createChallenge = function(challenge){
        return $http.post("/challenges",challenge).success(function(data){
            o.challenges.push(data);
        })
    }
    o.getDish = function(id){
        return $http.get('/dishes'+ id).then(function(res) {
			return res.data;
		});
    }
    
    
    
    o.getAllAchievements = function() {
        return $http.get('/achievements').success(function(data){
            angular.copy(data, o.achievements);
        });
    };
    return o;
}]);

app.controller('MainCtrl', [
    '$scope','data',
    function($scope,dishes){
        $scope.dishes = dishes.dishes;
        
    }]);

app.controller('DishesCtrl', [
    '$scope','data',
    function($scope, dishes){
        $scope.dishes = dishes.dishes;
         $scope.items = [];

  $scope.itemsToAdd = [{
    name: '',
    lastName: ''
  }];

  $scope.add = function(itemToAdd) {

    var index = $scope.itemsToAdd.indexOf(itemToAdd);

    $scope.itemsToAdd.splice(index, 1);

    $scope.items.push(angular.copy(itemToAdd))
  }

  $scope.addNew = function() {

    $scope.itemsToAdd.push({
      firstName: '',
      lastName: ''
    })
  }
        
        
          $scope.createDish = function(){
            dishes.createDish({
                name:$scope.name,
                cookingTime : $scope.cookingTime,
                difficulty : $scope.difficulty,
                dishType : $scope.dishType,
                preparation : $scope.preparation,
                ingredients : $scope.ingredientList
                  
            })
        
        }
       
        
    }]);

app.controller('AchievementsCtrl', [
    '$scope','data',
    function($scope,achievements){
        $scope.achievements= achievements.achievements;
        
        $scope.createAchievement=function(){
            achievements.createAchievement({
                title: $scope.title,
                description : $scope.description,
                achievementType : $scope.achievementType
                    
            })
        }
        
    }]);
app.controller('challengesCtrl', [
    '$scope','data',
    function($scope,challenges){
        $scope.challenges= challenges.challenges;
        
        $scope.createChallenge=function(){
            challenges.createChallenge({
                name: $scope.name,
                description : $scope.description,
                challengeType : $scope.challengeType
                    
            })
        }
        
    }]);

app.controller('NavCtrl', [
    '$scope',
    function($scope){

    }]);

app.controller('dishCtrl', [
    '$scope',
    function($scope){
        $scope.test = 'Hello DISH';
        
    }]);