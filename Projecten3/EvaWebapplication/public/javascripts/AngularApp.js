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
						return data.getAllChallenges();
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
     o.getAllChallenges = function() {
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
    o.deleteAch = function(achievement){
        return $http.delete('/achievement/' + achievement).success(function(data) {
			angular.copy(data, o.achievements);
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
        $scope.showTableHideForm = false;
        $scope.showPreparation = false;
        $scope.ingredients=[];
        $scope.iName="";
        $scope.iAmount="";

        $scope.add = function(){
            this.ingredients.push({name:$scope.iName, amount: $scope.iAmount});
            $scope.iName="";
            $scope.iAmount="";
        };
       
        $scope.createDish = function(){              
            dishes.createDish({
                imageId : $scope.imageId,
                name: $scope.name,
                cookingTime : $scope.cookingTime,
                difficultyType : $scope.difficultyType,
                dishType : $scope.dishType,
                preparation : $scope.preparation,
                ingredients : $scope.ingredients
            });
            $scope.showTableHideForm = false;
            $scope.ingredients =[];
            $scope.iName = "";
            $scope.iAmount = "";
            $scope.name = "";
            $scope.cookingTime = "";
            $scope.difficultyType = "";
            $scope.dishType = "";
            $scope.preparation = "";
            $scope.imageId = "";
        };
    }]);

app.controller('AchievementsCtrl', [
    '$scope','data',
    function($scope,achievements){
        $scope.achievements= achievements.achievements;
         $scope.showTableHideForm = false;
        
        $scope.createAchievement=function(){
            achievements.createAchievement({
                title: $scope.title,
                description : $scope.description,
                achievementType : $scope.achievementType,
            });
            $scope.showTableHideForm = false;
            $scope.title = "";
            $scope.description = "";
            $scope.achievementType = "";
        };
        
        $scope.deleteAch = function(achievement){
            achievements.deleteAch(achievement);
        };
        $scope.deleteAchievement = function(achievement) {
            console.log(achievement);

        };
    }]);

app.controller('challengesCtrl', [
    '$scope','data',
    function($scope,challenges){
        $scope.challenges= challenges.challenges;
        $scope.showTableHideForm = false;
        
        $scope.createChallenge=function(){
            challenges.createChallenge({
                title: $scope.title,
                description : $scope.description,
                challengeType : $scope.challengeType,
            });
            $scope.showTableHideForm = false;
            $scope.title = "";
            $scope.description = "";
            $scope.challengeType = "";
        };
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