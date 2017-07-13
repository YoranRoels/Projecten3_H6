var mongoose = require('mongoose');

var AchievementSchema = new mongoose.Schema({
    title: String,
    description: String,
    achievementType: {
        type: String,
        enum:[
            'BRONZE',
            'SILVER',
            'GOLD'],
    },
});

mongoose.model('Achievement', AchievementSchema);


