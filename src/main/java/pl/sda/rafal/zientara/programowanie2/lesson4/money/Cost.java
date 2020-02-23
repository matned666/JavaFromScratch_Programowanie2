package pl.sda.rafal.zientara.programowanie2.lesson4.money;

import java.time.LocalDate;

public class Cost {
    private final String shopName;
    private final double price;
    private final LocalDate date;

    public Cost(String shopName, double price, LocalDate date) {
        this.shopName = shopName;
        this.date = date;
        this.price = price;
    }

    @Override
    public String toString() {
        return "shop='" + shopName + '\'' +
                " price=" + price +
                " date=" + date;
    }
}
