package Greedy;

import java.util.Arrays;

public class PartialKnapsack {
    public static class Item implements Comparable<Item> {
        int price;
        int value;
        int valuePerUnitPrice;

        public Item(int price, int value) {
            this.price = price;
            this.value = value;
            this.valuePerUnitPrice = value/price;
        }

        public int compareTo(Item other) {
            return -Integer.compare(this.valuePerUnitPrice, other.valuePerUnitPrice);
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int P = 100;

        Item[] items = new Item[n];

        items[0] = new Item(20, 200);
        items[1] = new Item(30, 400);
        items[2] = new Item(70, 650);
        items[3] = new Item(55, 540);
        items[4] = new Item(80, 1000);

        System.out.println(CalculateMaxValue(items, P));
    }

    public static int CalculateMaxValue(Item[] items, int P) {
        Arrays.sort(items);

        int value_taken = 0;
        int P_left = P;

        for (int i=0; i<items.length; i++) {
            if (P_left >= items[i].price) {
                P_left -= items[i].price;
                value_taken += items[i].value;
            } else {
                value_taken += items[i].valuePerUnitPrice * P_left;
                break;
            }
        }

        return value_taken;
    }
}
