var mongoose = require("mongoose");

var IngredientSchema = new mongoose.Schema({
    name: String,
    amount: String
});

mongoose.model("Ingredient", IngredientSchema);