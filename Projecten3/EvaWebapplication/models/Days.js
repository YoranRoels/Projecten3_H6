var mongoose = require("mongoose");

var DaySchema = new mongoose.Schema({
    dateOfTheWeek : String,
    date :String,
    tip : String,
    completed : Boolean,
    Dish : {
        type: mongoose.Schema.types.ObjectId,
        ref : 'Dish'
    }
    
});

mongoose.model("Day",DaySchema);