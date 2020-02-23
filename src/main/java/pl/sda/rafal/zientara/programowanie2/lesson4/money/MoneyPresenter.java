package pl.sda.rafal.zientara.programowanie2.lesson4.money;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoneyPresenter implements
        MoneyContract.Presenter {
    private final MoneyContract.View view;

    private List<Cost> costs = new ArrayList<>();

    public MoneyPresenter(MoneyContract.View view) {
        this.view = view;
    }

    @Override
    public void initData() {
        File file = new File("zakupy.csv");
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            boolean fistIgnored = false;
            String line = buffer.readLine();
            line = buffer.readLine();
            while (line != null) {
                if (!fistIgnored) {
                    fistIgnored = true;
                } else {
                    Cost cost = parseCost(line);
                    costs.add(cost);
                }
                line = buffer.readLine();
            }
            //todo czytamy linijka po linijce i tworzymy liste Cost
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        view.refreshList(costs);
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

    @Override
    public void onWordChange(String word) {
        //todo
    }
}
