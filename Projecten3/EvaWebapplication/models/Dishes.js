var mongoose = require("mongoose");



var DishSchema = new mongoose.Schema({
    name : String,
    difficulty : String,
    preparation: String,
    dishType: {
        type: String,
        enum:[
            'APPETIZER',
            'MAINDISH',
            'DESSERT'],
    },
    cookingTime: {
        type: String,
        enum: ['SHORT', 'MEDIUM', 'LONG']},,
    imageId: {type: Number, default: 0},
    Ingredients: [{
        type: mongoose.Schema.types.ObjectId,
        ref: "Ingredient"
    }]
    
    
});

mongoose.model("Dish", DishSchema);