package model.foodrelated;

public interface DeclareConstants {

    /**
     * This is just a temporary interface used for testing, in the future
     * I will implement this in a file reading system.
     */


    public static final String[] ThemesDefault = {
            "ITALIAN",
            "SPANISH",
            "FRENCH",
            "CHINESE",
            "JAPANESE",
            "KOREAN",
            "INDIAN",
            "VIETNAMESE",
            "THAI",
            "AMERICAN",
            "MEXICAN"
    };

    public static final Recipe[] RecipesDefault = {
            new Recipe("Spaghetti", "ITALIAN"),
            new Recipe("Ramen", "JAPANESE"),
            new Recipe("Meat Dumplings", "CHINESE"),
            new Recipe("Salad Roll", "VIETNAMESE"),
            new Recipe("Burger with Fries", "AMERICAN"),
            new Recipe("Pad Thai", "THAI"),
            new Recipe("Chicken Samosa", "INDIAN"),
            new Recipe("Paella Valenciana", "SPANISH"),
            new Recipe("Beef Pho", "VIETNAMESE"),
            new Recipe("Tacos", "MEXICAN"),
            new Recipe("Seafood Pancake", "KOREAN"),
    };

    public static final String[] ingredientSpaghetti = {
            "TOMATO SAUCE",
            "SPAGHETTI NOODLES",
            "FRESH BASIL",
            "PARMESAN CHEESE"
    };

    public static final String[] ingredientRamen = {
            "RAMEN NOODLES",
            "CHASHU PORK",
            "EGGS",
            "BEAN SPROUTS",
            "NORI"
    };

    public static final String[] ingredientDumplings = {
            "GROUND PORK",
            "DUMPLING SKIN",
            "SCALLIONS",
            "NAPPA",
            "GINGER"
    };

    public static final String[] ingredientSaladRoll = {
            "PORK PATTY",
            "VERMICELLI",
            "CLEAR SKIN SALAD WRAP",
            "BEAN SPROUTS",
            "SHRIMP"
    };

    public static final FoodItem[] ingredientBurgerWithFries = {
            new FoodItem("Buns", 10, "Loaves")
    };


}
