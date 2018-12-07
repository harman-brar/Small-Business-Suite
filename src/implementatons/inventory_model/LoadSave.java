package implementatons.inventory_model;

import billing.Account;
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
            System.out.println("Saved inventory.");
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    static HashMap<String, ListOfItems> load() throws IOException {
        HashMap<String, ListOfItems> map = null;
        try {
            File file = new File("inventory.ser");
            if (file.length() == 0) {
                map = new HashMap<String, ListOfItems>();
            } else {
                FileInputStream fis = new FileInputStream("inventory.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
                map = (HashMap) ois.readObject();
                ois.close();
                fis.close();
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        catch(ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }

        return map;
    }

    public static void saveAccounts(List<Account> accounts) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream("accounts.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(accounts);
            oos.close();
            fos.close();
            System.out.println("Saved account.");
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static List<Account> loadAccounts() throws IOException {
        List<Account> accounts = null;
        try {
            File file = new File("accounts.ser");
            if (file.length() == 0) {
                accounts = new ArrayList<>();
            } else {
                FileInputStream fis = new FileInputStream("accounts.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
                accounts = (List<Account>) ois.readObject();
                ois.close();
                fis.close();
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        catch(ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
        return accounts;
    }

    public static ArrayList<String> splitOnPipe(String line){
        String[] splits = line.split(" " + "\\|" + " ");;
        return new ArrayList<>(Arrays.asList(splits));
    }

}
