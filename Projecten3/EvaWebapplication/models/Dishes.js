var mongoose = require('mongoose');

var DishSchema = new mongoose.Schema({
    name: String,
    cookingTime: {
        type: String,
        enum: ['Short', 'Medium', 'Long']
    },
    difficultyType: {
        type: String,
        enum: ['Starters', 'Advanced', 'Master']
    },
    dishType: {
        type: String,
        enum:[
            'Appetizer',
            'Maindish',
            'Dessert'],
    },
    ingredients: [{ name: String , amount : String }],
    preparation: String,
    imageId: {type: Number, default: 0},
});

mongoose.model('Dish', DishSchema);