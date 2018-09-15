package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {

    Scanner scanner = new Scanner(System.in);
    ArrayList<Item> itemsList = new ArrayList<>();

    public Inventory() {
        String name;
        Item i;
        String action;
        while (true) {
            System.out.println("Please select action (add, remove, delete, done).");
            action = scanner.nextLine();
            System.out.println("You entered action: " + action);

            if (action.equals("add")) {
                System.out.println("Enter the name of the item that you would like to apply this action to.");
                name = scanner.nextLine();
                if (itemsList.size() == 0) {
                    i = createItem(name);
                    itemsList.add(i);
                    System.out.println("Added item: " + name + " to inventory catalogue");
                    performAdd(i);
                } else if (itemsList.size() > 0) {
                    for (int c = 0; c < itemsList.size(); c++) {
                        i = itemsList.get(c);
                        if (i.name.equals(name)) { // this breaks if 'else' (below) is run between
                            performAdd(i);
                            break;
                        }
                        else {
                            i = createItem(name);
                            itemsList.add(i);
                            System.out.println("Added item: " + name + " to inventory catalogue");
                            performAdd(i);
                            break;
                        }
                    }
                }
            }
            else if (action.equals("remove")) {
                System.out.println("Enter the name of the item that you would like to apply this action to.");
                name = scanner.nextLine();
                if (itemsList.size() == 0) {
                    System.out.println("There is currently no stock of this item.");
                } else if (itemsList.size() > 0) {
                    for (int c = 0; c < itemsList.size(); c++) {
                        i = itemsList.get(c);
                        if (i.name.equals(name)) {
                            performRemoval(i);
                            break;
                        }
                        else {
                            System.out.println("There is currently no stock of this item.");
                            break;
                        }
                    }
                }
            }
            else if (action.equals("delete")) {
                System.out.println("Enter the name of the item that you would like to apply this action to.");
                name = scanner.nextLine();
                if (itemsList.size() == 0) {
                    System.out.println("This item cannot be deleted as it does not exist.");
                } else if (itemsList.size() > 0) {
                    for (int c = 0; c < itemsList.size(); c++) {
                        i = itemsList.get(c);
                        if (i.name.equals(name)) {
                            itemsList.remove(i);
                            System.out.println("Item: " + name + " removed from catalogue");
                            break;
                        }
                        else {
                            System.out.println("This item cannot be deleted as it does not exist.");
                            break;
                        }
                    }
                }
            }
            else if (action.equals("done")) {
                break;
            }
        }
        System.out.println("Inventory: " + itemsList.toString());
    }

    private void performRemoval(Item item) {
        System.out.println("How many units would you like to remove?");
        String stringAmount = scanner.nextLine();
        int localAmount = Integer.parseInt(stringAmount);
        item.amount -= localAmount;
        System.out.println(localAmount + " units removed from stock of " + item.name);
    }

    private void performAdd(Item item) {
        System.out.println("How many units would you like to add?");
        String stringAmount = scanner.nextLine();
        int localAmount = Integer.parseInt(stringAmount);
        item.amount += localAmount;
        System.out.println(localAmount + " units added to stock of " + item.name);
    }

    private Item createItem(String nameOfItem) {
        Item itemToAdd = new Item();
        itemToAdd.name = nameOfItem;
        System.out.println("Item: " + nameOfItem + " created in inventory");
        return itemToAdd;
    }

    public static void main(String args[]) {
        new Inventory();
    }

}
