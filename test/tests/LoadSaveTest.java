package tests;

import category_lists.AggregatesList;
import implementatons.InventoryCatalogue;
import model.Item;
import model.ListOfItems;
import org.junit.jupiter.api.Test;
import product_categories.Aggregate;

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
        ListOfItems aggregateList = new AggregatesList();
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
        ListOfItems aggregatesList = new AggregatesList();
        List<String> lines = Files.readAllLines(Paths.get("testOutput.txt"));
        for (String line : lines){
            if (!line.isEmpty()) {
                ArrayList<String> partsOfLine = InventoryCatalogue.splitOnSpace(line);
                Item l = new Aggregate();
                l.setName(partsOfLine.get(0));
                l.setAmount(partsOfLine.get(1));
                aggregatesList.insertItem(l);
            }
        }

        assertEquals(2, aggregatesList.getSize());
    }

}
