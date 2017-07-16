var mongoose = require('mongoose');

var IngredientSchema = new mongoose.Schema({
    name: String,
    amount: String,
    //dish: { type: mongoose.Schema.Types.ObjectId, ref: 'Dish' }
});

mongoose.model('Ingredient', IngredientSchema);