var express = require('express');
var router = express.Router();

var mongoose = require('mongoose');
var underscore = require('underscore');
var Challenge = mongoose.model('Challenge');

router.get('/challenges', function(req, res, next) {
    Challenge.find(function(err, challenges){
        if(err){ return next(err); }

        res.json(challenges);
    });
});

router.get('/challenges/three-random',function(req , res, next){
    Challenge.find(function(err,challenges){
        if(err){ return next(err); }
        var arr = underscore.sample(challenges, 3);
        res.json(arr);
    });
});

router.get('/challenges/:challenge', function(req, res) {
    res.json(req.challenge);
});

router.delete('/challenges/:challenge', function(req, res) {
    Challenge.findByIdAndRemove(req.challenge._id, function(err, challenge) {
        if(err){
            return err;
        }
        res.json(challenge);
    });
});

router.post('/challenges', function(req, res, next) {
    var challenge = new Challenge(req.body);

    challenge.save(function(err, challenge){
        if(err){ return next(err); }

        res.json(challenge);
    });
});

router.put('/challenges/:challenge', function(req, res) {
    Challenge.findOneAndUpdate({_id: req.body._id}, {
        $set: {
            title: req.body.title,
            challengeType: req.body.challengeType,
            description: req.body.description,
        }
    }, function(err, result) {
        if (err) {return res.send(err)}
        res.send(result);
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