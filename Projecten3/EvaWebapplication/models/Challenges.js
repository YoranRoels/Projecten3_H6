var mongoose = require('mongoose');

var ChallengeSchema = new mongoose.Schema({
    title: String,
    description: String,
    challengeType: {
        type: String,
        enum:[
            'Easy',
            'Medium',
            'Hard'],
    },
});

mongoose.model('Challenge', ChallengeSchema);