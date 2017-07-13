var mongoose = require("mongoose");

var AchievementSchema = new mongoose.Schema({
    completedImageId: Number,
    imageId : Number,
    title : String,
    description : String,
    completed : Boolean,
    achievementType : {
        type: mongoose.Schema.Types.ObjectId,
		ref: 'AchievementType'
    }
    
    
    
    
});

mongoose.model("Achievement",AchievementSchema);