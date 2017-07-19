package projecten3_h6.evaandroid.Domain;

import android.app.Application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import projecten3_h6.evaandroid.R;

/**
 * Created by Yoran on 16/07/2017.
 */

public class EvaApplication extends Application {

    private User user = fillInUser();

    public User getUser() {
        return user;
    }

    private User fillInUser() {
        User filledInUser;
        // if(user in local storage)
        //{
        // Fill user from Local Storage
        //}
        // else
        // {

        List<Achievement> achievements = new ArrayList<>();

        achievements.add(new Achievement(R.drawable.bronze_app_completed, R.drawable.bronze_app, "We're Just Getting Started", "Launch the app.",
                AchievementRanking.BRONZE, true));

        achievements.add(new Achievement(R.drawable.bronze_calendar_completed, R.drawable.bronze_calendar, "I’m On a Regime", "Open the ‘Progress’ tab.",
                AchievementRanking.BRONZE, true));

        achievements.add(new Achievement(R.drawable.bronze_cooking_completed, R.drawable.bronze_cooking, "What’s For Dinner?", "Open the ‘Today’ tab.",
                AchievementRanking.BRONZE, false));

        achievements.add(new Achievement(R.drawable.silver_checkbox_completed, R.drawable.silver_checkbox, "Making Progress", "Complete a ‘segment’ while having all days marked as complete.",
                AchievementRanking.SILVER, false));

        achievements.add(new Achievement(R.drawable.gold_streak_25_completed, R.drawable.gold_streak_25, "Vegan Master Streak", "Achieve a 25-day vegan streak.",
                AchievementRanking.GOLD, true));

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Carrots", "2kg"));
        ingredients.add(new Ingredient("Tomatoes", "500g"));
        ingredients.add(new Ingredient("Eggs", "12"));
        ingredients.add(new Ingredient("Vanilla", "200g"));
        ingredients.add(new Ingredient("Strawberries", "1kg"));

        ShoppingList shoppingList = new ShoppingList(ingredients);

        List<Day> days = new ArrayList<>();
        days.add(new Day(2017,7,18,4, new Dish(R.drawable.winterovenschotel, "Winterovenschotel met Le Puy-linzen (Tuesday)", CookingTime.MEDIUM, "Beginner", DishType.MAINDISH,
                ingredients,
                "1. Kook de linzen gaar in de groentenbouillon samen met een blaadje laurier," +
                        " een halve ui en 1/2 tl gedroogde tijm.\n" +
                        "\n" +
                        "2. Versnipper de rest van de uit en stoof ze aan in 3 el olijfolie." +
                        " Voeg de look, groenten, wat zout en de gemengde mediterraanse kruiden toe en laat alles zo goed als gaar stoven" +
                        " met het deksel op de pan. Voeg eventueel wat water toe als de groenten dreigen aan te branden.\n" +
                        "\n" +
                        "3. Warm de oven voor op 18°C en vet een ovenschaal in. Meng het broodkruim met de boter," +
                        " 2 teentjes fijngehakte knoflook, de verse kruiden, zout en peper.\n" +
                        "\n" +
                        "4. Meng de gekookte linzen (haal de halve ui en het laurierblad eruit)," +
                        " de groenten en de noten onder elkaar in de ovenschotel." +
                        " Leg bovenop een laagje van het broodkruim en bak de schotel zo'n 10 minuten in de oven," +
                        " tot het korstje licht verkleurt."), ""));

        days.add(new Day(2017,7,19,5, new Dish(R.drawable.winterovenschotel, "Winterovenschotel met Le Puy-linzen (Wednesday)", CookingTime.MEDIUM, "Beginner", DishType.MAINDISH,
                ingredients,
                "1. Kook de linzen gaar in de groentenbouillon samen met een blaadje laurier," +
                        " een halve ui en 1/2 tl gedroogde tijm.\n" +
                        "\n" +
                        "2. Versnipper de rest van de uit en stoof ze aan in 3 el olijfolie." +
                        " Voeg de look, groenten, wat zout en de gemengde mediterraanse kruiden toe en laat alles zo goed als gaar stoven" +
                        " met het deksel op de pan. Voeg eventueel wat water toe als de groenten dreigen aan te branden.\n" +
                        "\n" +
                        "3. Warm de oven voor op 18°C en vet een ovenschaal in. Meng het broodkruim met de boter," +
                        " 2 teentjes fijngehakte knoflook, de verse kruiden, zout en peper.\n" +
                        "\n" +
                        "4. Meng de gekookte linzen (haal de halve ui en het laurierblad eruit)," +
                        " de groenten en de noten onder elkaar in de ovenschotel." +
                        " Leg bovenop een laagje van het broodkruim en bak de schotel zo'n 10 minuten in de oven," +
                        " tot het korstje licht verkleurt."), ""));

        days.add(new Day(2017,7,20,6, new Dish(R.drawable.winterovenschotel, "Winterovenschotel met Le Puy-linzen (Thursday)", CookingTime.MEDIUM, "Beginner", DishType.MAINDISH,
                ingredients,
                "1. Kook de linzen gaar in de groentenbouillon samen met een blaadje laurier," +
                        " een halve ui en 1/2 tl gedroogde tijm.\n" +
                        "\n" +
                        "2. Versnipper de rest van de uit en stoof ze aan in 3 el olijfolie." +
                        " Voeg de look, groenten, wat zout en de gemengde mediterraanse kruiden toe en laat alles zo goed als gaar stoven" +
                        " met het deksel op de pan. Voeg eventueel wat water toe als de groenten dreigen aan te branden.\n" +
                        "\n" +
                        "3. Warm de oven voor op 18°C en vet een ovenschaal in. Meng het broodkruim met de boter," +
                        " 2 teentjes fijngehakte knoflook, de verse kruiden, zout en peper.\n" +
                        "\n" +
                        "4. Meng de gekookte linzen (haal de halve ui en het laurierblad eruit)," +
                        " de groenten en de noten onder elkaar in de ovenschotel." +
                        " Leg bovenop een laagje van het broodkruim en bak de schotel zo'n 10 minuten in de oven," +
                        " tot het korstje licht verkleurt."), ""));

        filledInUser = new User(achievements, days, shoppingList, 15, 34);
        // }
        return filledInUser;
    }
}
