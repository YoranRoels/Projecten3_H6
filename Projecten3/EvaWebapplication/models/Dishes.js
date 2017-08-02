var mongoose = require('mongoose');

var DishSchema = new mongoose.Schema({
    name: String,
    cookingTime: {
        type: String,
        enum: ['SHORT', 'MEDIUM', 'LONG']
    },
    difficulty: {
        type: String,
        enum: ['STARTERS', 'ADVANCED', 'MASTER']
    },
    dishType: {
        type: String,
        enum:[
            'APPETIZER',
            'MAINDISH',
            'DESSERT'],
    },
    ingredients: [{ name: String , amount : String }],
    preparation: String,
    imageId: {type: Number, default: 0},
});

mongoose.model('Dish', DishSchema);
