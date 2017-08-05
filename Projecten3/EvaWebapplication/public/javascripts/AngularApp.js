var app = angular.module('eva-webapplication', ['ui.router']);

app.config([
    '$stateProvider',
    '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
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
                
            });


        $urlRouterProvider.otherwise('dishes');
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
    };
     o.createAchievement = function(achievement){
        return $http.post("/achievements",achievement).success(function(data){
            o.achievements.push(data);
        })
    };
     o.createChallenge = function(challenge){
        return $http.post("/challenges",challenge).success(function(data){
            o.challenges.push(data);
        })
    };
    o.getDish = function(id){
        return $http.get('/dishes'+ id).then(function(res) {
			return res.data;
		});
    };
    o.deleteAchievement = function(achievement){
        return $http.delete('/achievements/' + achievement._id).success(function(data) {
            _.remove(o.achievements, {_id : data._id});
		});
    };
    o.deleteDish = function(dish){
        return $http.delete('/dishes/' + dish._id).success(function(data) {
            _.remove(o.dishes, {_id : data._id});
        });
    };
    o.deleteChallenge = function(challenge){
        return $http.delete('/challenges/' + challenge._id).success(function(data) {
            _.remove(o.challenges, {_id : data._id});
        });
    };
    o.getAllAchievements = function() {
        return $http.get('/achievements').success(function(data){
            angular.copy(data, o.achievements);
        });
    };
    o.editChallenge = function(challenge) {
        console.log(challenge);
        return $http.put('/challenges/' + challenge._id, challenge).success(function(data) {
            o.challenges[_.findIndex(o.challenges, {_id : data._id})] = challenge;
        });
    };
    o.editAchievement = function(achievement) {
        return $http.put('/achievements/' + achievement._id, achievement).success(function(data) {
            o.achievements[_.findIndex(o.achievements, {_id : data._id})] = achievement;
        });;
    };
    o.editDish = function(dish) {
        return $http.put('/dishes/' + dish._id, dish);
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

        $scope.deleteDish = function(dish) {
            dishes.deleteDish(dish);
        };
    }]);

app.controller('AchievementsCtrl', [
    '$scope','data',
    function($scope,achievements){
        $scope.achievements = achievements.achievements;
        $scope.editMode = false;
        $scope.showTableHideForm = false;
        $scope.showedAchievement = {};
        
        $scope.createAchievement=function(){
            achievements.createAchievement({
                title: $scope.title,
                description : $scope.description,
                achievementType : $scope.achievementType,
            });
            $scope.cancelForm();
        };

        $scope.deleteAchievement = function(achievement) {
            achievements.deleteAchievement(achievement);
        };

        $scope.showAchievement = function(achievement) {
            $scope.showTableHideForm = true;
            $scope.title = achievement.title;
            $scope.description = achievement.description;
            $scope.achievementType = achievement.achievementType;
            $scope.editMode = true;
            $scope.showedAchievement = achievement;
        };

        $scope.editAchievement = function() {
            achievements.editAchievement({
                _id: $scope.showedAchievement._id,
                title: $scope.title,
                description : $scope.description,
                achievementType : $scope.achievementType,
            });
            $scope.cancelForm();
        };

        $scope.cancelForm = function() {
            $scope.showTableHideForm = false;
            $scope.title = "";
            $scope.description = "";
            $scope.achievementType = "";
            $scope.editMode = false;
        };
    }]);

app.controller('challengesCtrl', [
    '$scope','data',
    function($scope,challenges){
        $scope.challenges = challenges.challenges;
        $scope.editMode = false;
        $scope.showTableHideForm = false;
        $scope.showedChallenge = {};
        
        $scope.createChallenge=function(){
            challenges.createChallenge({
                title: $scope.title,
                description : $scope.description,
                challengeType : $scope.challengeType,
            });
            $scope.cancelForm();
        };

        $scope.deleteChallenge = function(challenge) {
            challenges.deleteChallenge(challenge);
        };
        $scope.showChallenge = function(challenge) {
            console.log(challenge);
            $scope.showTableHideForm = true;
            $scope.title = challenge.title;
            $scope.description = challenge.description;
            $scope.challengeType = challenge.challengeType;
            $scope.editMode = true;
            $scope.showedChallenge = challenge;
        };
        $scope.editChallenge = function() {
            challenges.editChallenge({
                _id: $scope.showedChallenge._id,
                title: $scope.title,
                description : $scope.description,
                challengeType : $scope.challengeType,
            });
            $scope.cancelForm();
        };
        $scope.cancelForm = function() {
            $scope.showTableHideForm = false;
            $scope.title = "";
            $scope.description = "";
            $scope.challengeType = "";
            $scope.editMode = false;
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