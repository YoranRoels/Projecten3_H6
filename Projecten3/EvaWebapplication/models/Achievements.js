var mongoose = require('mongoose');

var AchievementSchema = new mongoose.Schema({
    title: String,
    description: String,
    achievementType: {
        type: String,
        enum:[
            'Bronze',
            'Silver',
            'Gold'],
    },
});

mongoose.model('Achievement', AchievementSchema);