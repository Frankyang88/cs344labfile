package cosc344.utils;

import java.util.ArrayList;

/**
 * Provides the base print and group print methods.
 */
public class BaseClass {
    protected static void print(String text) {
        System.out.println(text);
    }

    /**
     * prints boolean as true or false
     * @param bol the boolean to print
     */
    protected static void print(boolean bol){
        String text = bol?"true":"false";
        print(text);
    }
    
    /**
     * prints the int number
     * @param number to println
     */
    protected static void print(int number) {
        System.out.println(number);
    }

    /**
     * This methods is used to create 2 blank lines
     * to the bash to provide more space in order to
     * get a better layout.
     */
    protected static void printBlankSpace() {
        print("");
        print("");
    }
    
    /**
     * Prints a string representation of the elements in the ArrayList
     * @param list to iterate through and toString on
     */
    protected static <T> void groupPrint( ArrayList<T> list ){
        list.forEach((x)->{System.out.println(x.toString());});
    }
}
