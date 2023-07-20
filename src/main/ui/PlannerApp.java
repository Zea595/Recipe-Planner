package ui;

import model.Week;
import model.exceptions.IntegerException;
import model.foodrelated.Recipe;
import model.persistance.JsonReader;
import model.persistance.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class PlannerApp {
    private final int terminate = 7;
    private static final String JSON_PATH = "./data/week.json";
    private final Scanner scanner = new Scanner(System.in);
    private Week week;
    private boolean hasGeneratedWeek = false;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private boolean isSaved;

    public PlannerApp() {
        jsonWriter = new JsonWriter(JSON_PATH);
        jsonReader = new JsonReader(JSON_PATH);
        week = new Week();
        runApp();
    }

    public void instructions() {
        System.out.println("Welcome to the weekly cooking planner. "
                + "Either create a randomized week of cuisine plans, or customize it to your own liking.");
        System.out.println("Choose one of the following options:" + "\n"
                + "Press 1 to generate a new random week." + "\n"
                + "Press 2 to display current week." + "\n"
                + "Press 3 to change a day's plan from 1-7." + "\n"
                + "Press 4 to access shopping list." + "\n"
                + "Press 5 to save the week." + "\n"
                + "Press 7 to exit the program."
        );
    }

    private void runApp() {
        int keyBoardResponse = 0;
        while (true) {
            instructions();
            keyBoardResponse = Integer.parseInt(scanner.next());
            if (keyBoardResponse == 1) {
                generateWeek();
            } else if (keyBoardResponse == 2) {
                displayWeek();
            } else if (keyBoardResponse == 3) {
                editDay();
            } else if (keyBoardResponse == 4) {
                showShoppingList();
            } else if (keyBoardResponse == 5) {
                saveWeekSchedule();
            } else if (keyBoardResponse == 6) {
                loadWeekSchedule();
            } else {
                System.out.println("Program has exited successfully.");
                scanner.close();
                break;
            }
        }
    }

    // EFFECTS: changes hasGeneratedWeek to true and generates a week with random schedule.
    private void generateWeek() {
        week.generateRandomWeekSchedule();
        hasGeneratedWeek = true;
        System.out.println("Successfully generated week.");
    }

    // EFFECTS: Prints out current week schedule, kicks user back if
    // hasGeneratedWeek is false
    private void displayWeek() {
        if (!hasGeneratedWeek) {
            System.out.println("Please generate a week!");
        } else {
            System.out.println(week.toString());
        }
    }

    // Allows user to edit the recipe for the day and customize it to their own liking.
    // MODIFIES: week.schedule
    // EFFECTS: kicks user back to menu if hasGeneratedWeek is false.
    private void editDay() {
        if (!hasGeneratedWeek) {
            System.out.println("Please generate a week!");
        } else {
            int d = 0;
            System.out.println("Enter 1-7 for which day you would like to change.");
            while (d > 7 || d < 1) {
                d = scanner.nextInt();
                scanner.nextLine();
                
                if (d > 7 || d < 1) {
                    System.out.println("Invalid input, please enter a day 1-7 from the respective week.");
                }
            }
            Recipe r = editDayRecipe();
            System.out.println(r.toString());
            week.changeDayRecipe(d, r);
            System.out.println("Successfully changed.");
        }
    }

    //HELPER FUNCTION FOR editDay
    //EFFECTS: Creates a new recipe object with new info
    // and returns it to editDay method
    public Recipe editDayRecipe() {
        String name;
        String type;

        System.out.println("Please enter the new recipe name: ");
        name = scanner.nextLine();
        System.out.println("Please enter the new recipe theme: ");
        type = scanner.nextLine();
        Recipe r = new Recipe(name, type);

        inputIngredientList(r);
        return r;
    }

    //HELPER function for editDayRecipe
    public void inputIngredientList(Recipe r) {
        System.out.println("Please enter the ingredients. Enter 'exit' when complete.");
        String i;
        String u;

        while (true) {
            System.out.println("Enter the name of the ingredient: ");
            i = scanner.nextLine();

            if (i.equals("exit")) {
                break;
            }

            System.out.println("Enter the name of the ingredient's units.");
            u = scanner.nextLine();

            int n = verifyInteger("ingredient");
            r.addIngredient(i, n, u);
        }
        System.out.println("Exited.");
    }

    //EFFECTS: Generates and prints out a list of all the ingredients needed for each day of the
    // current week. Kicks user back if hasGeneratedWeek is false
    public void showShoppingList() {

        if (!hasGeneratedWeek) {
            System.out.println("Please generate a week!");
        } else {
            new ShoppingListApp(week);
        }
    }

    //EFFECTS: Saves current week and recipe to JSON file in the specified directory
    public void saveWeekSchedule() {
        try {
            jsonWriter.open();
            jsonWriter.write(week);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_PATH);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_PATH);
        }
    }

    //EFFECTS: Loads in a week from the JSON directory
    public void loadWeekSchedule() {
        try {
            week = jsonReader.read();
            System.out.println("Loaded from " + JSON_PATH);
            hasGeneratedWeek = true;
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_PATH);
        }
    }


    //EFFECTS: Verifies that the next user input is an int and returns that integer.
    private int verifyInteger(String item) {
        Scanner input = new Scanner(System.in);
        int n;
        while (true) {

            try {
                System.out.println("Enter the integer amount of this " + item + ": ");
                n = input.nextInt();
                if (n < 1) {
                    throw (new IntegerException("Has to be greater than 1!"));
                }
                return n;
            } catch (java.util.InputMismatchException e) {
                System.out.println("That is not a valid integer! Try again.");
                input.nextLine();
                continue;
            } catch (IntegerException e) {
                System.out.println(e);
                input.nextLine();
                continue;
            }
        }

    }


}
