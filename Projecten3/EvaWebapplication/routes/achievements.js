var express = require('express');
var router = express.Router();

var mongoose = require('mongoose');
var underscore = require('underscore');
var Achievement = mongoose.model('Achievement');

router.get('/achievements', function(req, res, next) {
    Achievement.find(function(err, achievements){
        if(err){ return next(err); }

        res.json(achievements);
    });
});

router.delete('/achievements/:achievement', function(req, res) {
    Achievement.findByIdAndRemove(req.achievement._id, function(err, achievement) {
        if(err){
            return err;
        }
        res.json(achievement);
    });
});

router.put('/achievements/:achievement', function(req, res) {
    Achievement.findOneAndUpdate({_id: req.body._id}, {
        $set: {
            title: req.body.title,
            achievementType: req.body.achievementType,
            description: req.body.description,
        }
    }, function(err, result) {
        if (err) return res.send(err)
        res.send(result)
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

module.exports = router;