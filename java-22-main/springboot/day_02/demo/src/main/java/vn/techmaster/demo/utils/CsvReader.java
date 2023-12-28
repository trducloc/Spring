package vn.techmaster.demo.utils;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {

    public static void main(String[] args) {
        String csvFile = "src/main/resources/book.csv";

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] line;
            while ((line = reader.readNext()) != null) {

                for (String cell : line) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
