package catalogue;

public class Item {

    public String name;
    public int amount;

    public Item() {
        name = "";
        amount = 0;
    }

    // EFFECTS: returns name and current amount of item as a string
    public String toString(){
        return name+": "+amount;
    }

}
