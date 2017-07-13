var mongoose = require("mongoose");

var AchievementTypeSchema = new mongoose.Schema({
    achievementRanking : String,
    value : Number
    
    
});

mongoose.model("AchievementType",AchievementTypeSchema);