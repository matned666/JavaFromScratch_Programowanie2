package pl.sda.rafal.zientara.programowanie2.lesson4.money.model;

import pl.sda.rafal.zientara.programowanie2.lesson4.money.Cost;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataCostsProvider {
    private final String filename;

    public DataCostsProvider(String filename) {
        this.filename = filename;
    }

    public DataCostsProvider() {
        filename = "zakupy.csv";
    }

    public List<Cost> readCosts() {
        List<Cost> costs = new ArrayList<>();
        File file = new File(filename);
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            boolean fistIgnored = false;
            String line = buffer.readLine();
            while (line != null) {
                if (!fistIgnored) {
                    fistIgnored = true;
                } else {
                    Cost cost = parseCost(line);
                    costs.add(cost);
                }
                line = buffer.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return costs;
    }

    private Cost parseCost(String line) {
        String[] split = line.split(";");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]//"345.50"
                .replace(",", ".")
                .replace("\"", ""));
        String input = split[2];//2020-01-02
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(input, formatter);
        return new Cost(shopName, price, date);
    }
}
