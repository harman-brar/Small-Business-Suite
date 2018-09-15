package ui;

public class Item {

    public String name;
    public int amount;
    public String SKU;

    public Item() {
        name = "";
        amount = 0;
    }

    public String toString(){
        return name+" "+amount;
    }

}
