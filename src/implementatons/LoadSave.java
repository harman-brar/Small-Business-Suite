package implementatons;

import model.ListOfItems;
import model.Item;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadSave {

    static void save(String listToString, String file, Boolean append) throws IOException {
        List<String> lines = new ArrayList<>();
        PrintWriter writer = new PrintWriter(new FileWriter(file, append));
        lines.add(listToString);
        for (String line : lines){
            writer.println(line);
        }
        writer.close();
        System.out.println("All entries saved.");
    }

    static void load(ListOfItems list, String file) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(file));
        for (String line : lines) {
            if (!line.isEmpty()) {
                ArrayList<String> partsOfLine = splitOnSpace(line);
                Item l = new Item();
                l.setName(partsOfLine.get(0));
                l.setAmount(partsOfLine.get(1));
                list.insertItem(l);
                System.out.print("Name: " + partsOfLine.get(0) + " ");
                System.out.println("Amount: " + partsOfLine.get(1));
            }
        }
    }

    public static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }


}
