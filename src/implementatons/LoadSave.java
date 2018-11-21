package implementatons;

import model.ListOfItems;
import model.Item;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LoadSave {

    static void save(HashMap<String, ListOfItems> hmap) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream("inventory.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(hmap);
            oos.close();
            fos.close();
            System.out.printf("Saved inventory.");
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    static HashMap<String, ListOfItems> load() throws IOException {
        HashMap<String, ListOfItems> map = null;
        try {
            FileInputStream fis = new FileInputStream("inventory.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        catch(ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
        System.out.println("Loaded inventory.");

        return map;
    }

    public static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }


}
