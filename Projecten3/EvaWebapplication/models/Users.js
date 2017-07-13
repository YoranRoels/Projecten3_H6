var mongoose = require("mongoose");

var UserSchema = new mongoose.Schema({
    
    totalVeganDays : Number,
    longestStreak : Number,
    days : {
        type: mongoose.Schema.Types.ObjectId,
		ref: 'Day'
    },
    shoppingList : {
        type: mongoose.Schema.Types.ObjectId,
		ref: 'ShoppingList'
    },
    achievements : {
        type: mongoose.Schema.Types.ObjectId,
		ref: 'Achievement'
    }
    
});

mongoose.model("User",UserSchema);