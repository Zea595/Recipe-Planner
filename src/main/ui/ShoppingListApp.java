package ui;

import model.ShoppingList;
import model.Week;
import model.exceptions.IntegerException;

import java.util.Scanner;
/**
 * The shoppinglist interface, user has many options to mutate the shoppinglist similar to the main menu
 * 1: Displays the current list of items
 * 2: User can specify an item to check off.
 * 3: Sorting is still work in progress
 * 4: User can return to main menu
 */

public class ShoppingListApp {
    private final Scanner scanner = new Scanner(System.in);
    private ShoppingList shopList;

    public ShoppingListApp(Week w) {
        shopList = new ShoppingList(w);
        runApp();
    }

    private void instructions() {
        System.out.println("Shopping list functions: " + "\n"
                + "==========================================" + "\n"
                + "Press 1 to see the current list." + "\n"
                + "Press 2 to check off an item." + "\n"
                + "Press 3 to sort the list by name." + "\n"
                + "Press 4 to return to main menu." + "\n"
        );
    }

    private void runApp() {
        int keyBoardResponse = 0;

        while (keyBoardResponse != 4) {
            instructions();
            keyBoardResponse = Integer.parseInt(scanner.next());
            switch (keyBoardResponse) {
                case 1:
                    showList();
                    break;
                case 2:
                    checkOffItem();
                    break;
                case 3:
                    sortList();
                    break;
                case 4:
                    break;
            }
        }

    }

    private void showList() {
        System.out.println(shopList.toString());
    }

    //EFFECTS: calls checkoff method of shoppinglist and checks off the specified items
    // states "item not found" if not found
    private void checkOffItem() {
        System.out.println("Provide the name of the item.");
        String name = scanner.nextLine();
        scanner.nextLine();

        System.out.println("Provide the quanity in integer format.");
        int quantity = verifyInteger();
        boolean x = shopList.checkOff(name, quantity);

        if (!x) {
            System.out.println("Item not found!");
        } else {
            System.out.println("Item was checked off!");
        }
        showList();
    }

    private void sortList() {
        shopList.sortCheckList();
        System.out.println("List is now sorted by alphabetical order.");
    }

    //EFFECTS: Verifies that the next user input is an int and returns that integer.
    private int verifyInteger() {
        Scanner input = new Scanner(System.in);
        int n;
        while (true) {

            try {
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