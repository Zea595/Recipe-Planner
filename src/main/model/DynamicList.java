package model;

import model.foodrelated.FoodItem;

import java.util.Arrays;
import java.util.Comparator;

/**
 * The "DynamicList" class I chose to implement for the core features of my program, since we cannot use arraylist.
 * Essentially like an inefficient arraylist, except implemented by myself using copying methods of arrays.
 * Has many mutator methods such as adding to the list and growing it at the same time.
 */
public class DynamicList {

    private Object[] list;

    public DynamicList() {
        list = new Object[0];
    }

    //creates a new array and copies everything from previous(MANUAL COPY)
    // MODIFIES: list
    public void addToList(Object entry) {
        int newSize = list.length + 1;
        Object[] temp;
        temp = new Object[newSize];

        for (int i = 0; i < newSize - 1; i++) {
            temp[i] = list[i];
        }
        temp[list.length] = entry;

        list = temp;
    }

    //REQUIRES list.length to be > 0;
    // MODIFIES list
    public void removeFromList(Object entry) {
        int newSize = list.length;
        Object[] temp;

        //searches for entry to delete
        for (int i = 0; i < list.length; i++) {
            if (list[i] == entry) {
                list[i] = null;
                newSize--;
            }
        }

        // copies new array if entry was deleted
        if (newSize < list.length) {
            temp = new Object[newSize];
            int tempIndex = 0;

            for (int i = 0; i < list.length; i++) {
                if (list[i] != null) {
                    temp[tempIndex] = list[i];
                    tempIndex++;
                }
            }
            list = temp;
        }
    }

    //REQUIRES array size of > 1
    // MODIFIES: list
    public void removeIndex(int x) {
        int newSize = list.length - 1;
        Object[] temp;
        temp = new Object[newSize];
        int tempIndex = 0;

        list[x] = null;

        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                temp[tempIndex] = list[i];
                tempIndex++;
            }
            list = temp;
        }
    }

    // prints array in string format
    public void printList() {
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i] + "\n");
        }
    }

    // instance fields
    public int getListSize() {
        return list.length;
    }

    public Object getListAtIndex(int x) {
        return list[x];
    }

    public Object[] getList() {
        return list;
    }

}
