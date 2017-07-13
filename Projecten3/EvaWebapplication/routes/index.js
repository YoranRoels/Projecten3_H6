var express = require('express');
var router = express.Router();

var mongoose = require('mongoose');
var Achievement = mongoose.model('Achievement');
var AchievementType = mongoose.model('AchievementType');
var Day= mongoose.model('Day');
var Dish = mongoose.model('Dish');
var Ingredient = mongoose.model('Ingredient');
var ShoppingList = mongoose.model('ShoppingList');
var User = mongoose.model('User');


var jwt = require('express-jwt');
var auth = jwt({
    secret: 'SECRET',
    userProperty: 'payload'
});
// ------------------------------------------- GET ------------------------------------------------
/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

/* get all ingredients */
router.get('/ingredients',function(req, res, next)){
           Ingredient.find(function(err, ingredients) {
            if (err) {
                return next(err);
            }
            res.json(ingredients);
     });
});



module.exports = router;
