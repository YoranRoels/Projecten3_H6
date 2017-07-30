var express = require('express');
var router = express.Router();

var mongoose = require('mongoose');
var underscore = require('underscore');
var Dish = mongoose.model('Dish');
var Ingredient = mongoose.model('Ingredient');
var Achievement = mongoose.model('Achievement');
var Challenge = mongoose.model('Challenge');


/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.get('/dishes', function(req, res, next) {
    Dish.find(function(err, dishes){
        if(err){ return next(err); }

        res.json(dishes);
    }).populate('ingredients');
});

router.get('/dishes/three-random',function(req , res, next){
    Dish.find(function(err,dishes){
        if(err){ return next(err); }
        var arr = underscore.sample(dishes, 3);
        res.json(arr);
    }).populate('ingredients');
});

router.get('/dishes/:dish', function(req, res) {
    req.dish.populate('ingredients', function(err, dish) {
        if (err) { return next(err); }

        res.json(dish);
    });

    //res.json(req.dish);
});

router.post('/dishes', function(req, res, next) {
    var dish = new Dish(req.body);

    dish.save(function(err, dish){
        if(err){ return next(err); }

        res.json(dish);
    });
});

// One thing you might notice about the remaining routes we need to implement is that they all require us to load a post object by ID.
// Rather than replicating the same code across several different request handler functions,
// we can use Express's param() function to automatically load an object.
router.param('dish', function(req, res, next, name) {
    var query = Dish.findOne({'name' : name});

    query.exec(function (err, dish){
        if (err) { return next(err); }
        if (!dish) { return next(new Error('can\'t find dish')); }

        req.dish = dish;
        return next();
    });
});

router.post('/dishes/:dish/ingredients', function(req, res, next) {
    var ingredient = new Ingredient(req.body);
    ingredient.dish = req.dish;

    ingredient.save(function(err, ingredient){
        if(err){ return next(err); }

        req.dish.ingredients.push(ingredient);
        req.dish.save(function(err, ingredient) {
            if(err){ return next(err); }

            res.json(ingredient);
        });
    });
});

router.get('/achievements', function(req, res, next) {
    Achievement.find(function(err, achievements){
        if(err){ return next(err); }

        res.json(achievements);
    });
});

router.get('/achievements/:achievement', function(req, res) {
    res.json(req.achievement);
});

router.post('/achievements', function(req, res, next) {
    var achievement = new Achievement(req.body);

    achievement.save(function(err, achievement){
        if(err){ return next(err); }

        res.json(achievement);
    });
});

router.param('achievement', function(req, res, next, id) {
    var query = Achievement.findById(id);

    query.exec(function (err, achievement){
        if (err) { return next(err); }
        if (!achievement) { return next(new Error('can\'t find achievement')); }

        req.achievement = achievement;
        return next();
    });
});

router.get('/ingredients', function(req, res, next) {
    Ingredient.find(function(err, ingredients){
        if(err){ return next(err); }

        res.json(ingredients);
    });
});

router.get('/ingredients/:ingredient', function(req, res) {
    res.json(req.ingredient);
});

router.post('/ingredients', function(req, res, next) {
    var ingredient = new Ingredient(req.body);

    ingredient.save(function(err, ingredient){
        if(err){ return next(err); }

        res.json(ingredient);
    });
});

// One thing you might notice about the remaining routes we need to implement is that they all require us to load a post object by ID.
// Rather than replicating the same code across several different request handler functions,
// we can use Express's param() function to automatically load an object.
router.param('ingredient', function(req, res, next, name) {
    var query = Ingredient.findOne({'name' : name});

    query.exec(function (err, ingredient){
        if (err) { return next(err); }
        if (!ingredient) { return next(new Error('can\'t find ingredient')); }

        req.ingredient = ingredient;
        return next();
    });
});

router.get('/challenges', function(req, res, next) {
    Challenge.find(function(err, challenges){
        if(err){ return next(err); }

        res.json(challenges);
    });
});

router.get('/challenges/:challenge', function(req, res) {
    res.json(req.challenge);
});

router.post('/challenges', function(req, res, next) {
    var challenge = new Challenge(req.body);

    challenge.save(function(err, challenge){
        if(err){ return next(err); }

        res.json(challenge);
    });
});

router.param('challenge', function(req, res, next, id) {
    var query = Challenge.findById(id);

    query.exec(function (err, challenge){
        if (err) { return next(err); }
        if (!challenge) { return next(new Error('can\'t find challenge')); }

        req.challenge = challenge;
        return next();
    });
});

module.exports = router;