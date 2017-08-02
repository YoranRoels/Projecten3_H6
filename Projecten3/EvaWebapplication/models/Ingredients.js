var mongoose = require('mongoose');

var IngredientSchema = new mongoose.Schema({
    amount: String,
    name: String,
    
    //dish: { type: mongoose.Schema.Types.ObjectId, ref: 'Dish' }
});

mongoose.model('Ingredient', IngredientSchema);