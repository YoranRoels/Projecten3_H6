var express = require('express');
var router = express.Router();

var mongoose = require('mongoose');
var underscore = require('underscore');
var Dish = mongoose.model('Dish');

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
    res.json(req.dish);
});

router.delete('/dishes/:dish', function(req, res) {
    Dish.findOneAndRemove({'name': req.dish.name}, function(err, dish) {
        if(err){
            return err;
        }
        res.json(dish);
    });
});

router.put('/dishes/:dish', function(req, res) {
    Dish.findOneAndUpdate({_id: req.body._id}, {
        $set: {
            name: req.body.name,
            imageId : req.body.imageId,
            cookingTime : req.body.cookingTime,
            difficultyType : req.body.difficultyType,
            dishType : req.body.dishType,
            preparation : req.body.preparation,
            ingredients : req.body.ingredients,
        }
    }, function(err, result) {
        if (err) { return res.send(err) }

        res.send(result);
    });
});

router.post('/dishes', function(req, res, next) {
    var dish = new Dish(req.body);

    dish.save(function(err, dish){
        if(err){ return next(err); }

        res.json(dish);
    });
});

router.param('dish', function(req, res, next, id) {
    var query = Dish.findById(id);

    query.exec(function (err, dish){
        if (err) { return next(err); }
        if (!dish) { return next(new Error('can\'t find dish')); }

        req.dish = dish;
        return next();
    });
});

module.exports = router;