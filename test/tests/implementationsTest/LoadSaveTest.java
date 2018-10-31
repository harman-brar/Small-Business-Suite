package tests;

import model.ListOfItems;
import implementatons.LoadSave;
import model.Item;
import org.junit.jupiter.api.Test;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadSaveTest {

    @Test
    public void testSave() throws IOException {
        model.ListOfItems aggregateList = new ListOfItems();
        List<String> lines = Files.readAllLines(Paths.get("testInput.txt"));
        //PrintWriter writer = new PrintWriter("aggregateOutput.txt","UTF-8");
        PrintWriter writer = new PrintWriter(new FileOutputStream("testOutput.txt",false));
        lines.add(aggregateList.toString());
        for (String line : lines){
            writer.println(line);
        }
        writer.close();
        System.out.println("Aggregate entries saved.");
    }

    @Test
    public void testLoad() throws IOException {
        model.ListOfItems aggregatesList = new ListOfItems();
        List<String> lines = Files.readAllLines(Paths.get("testOutput.txt"));
        for (String line : lines){
            if (!line.isEmpty()) {
                ArrayList<String> partsOfLine = LoadSave.splitOnSpace(line);
                Item l = new Item();
                l.setName(partsOfLine.get(0));
                l.setAmount(partsOfLine.get(1));
                aggregatesList.insertItem(l);
            }
        }

        assertEquals(2, aggregatesList.getSize());
    }

}
