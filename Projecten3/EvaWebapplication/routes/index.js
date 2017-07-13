var express = require('express');
var router = express.Router();

var mongoose = require('mongoose');
var Dish = mongoose.model('Dish');
var Ingredient = mongoose.model('Ingredient');

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.get('/dishes', function(req, res, next) {
    Dish.find(function(err, dishes){
        if(err){ return next(err); }

        res.json(dishes);
    });
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

router.get('/dishes/:dish', function(req, res) {
    res.json(req.dish);
});

module.exports = router;
