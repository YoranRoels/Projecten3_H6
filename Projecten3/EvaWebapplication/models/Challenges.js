var mongoose = require('mongoose');

var ChallengeSchema = new mongoose.Schema({
    name: String,
    description: String,
    challengeType: {
        type: String,
        enum:[
            'EASY',
            'MEDIUM',
            'HARD'],
    },
});

mongoose.model('Challenge', ChallengeSchema);