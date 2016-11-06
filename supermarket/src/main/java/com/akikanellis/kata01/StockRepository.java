package com.akikanellis.kata01;

import java.util.HashMap;
import java.util.Map;

import static com.akikanellis.kata01.Preconditions.checkNotNegative;

public class StockRepository {
    private final Map<Item, Integer> stock;

    public StockRepository() { this.stock = new HashMap<>(); }

    public void create(Item item) {
        if (contains(item)) return;

        stock.put(item, 0);
    }

    public void increaseQuantity(Item item, int quantity) {
        checkNotNegative(quantity);

        if (!contains(item)) create(item);

        Integer currentQuantity = stock.get(item);
        stock.put(item, quantity + currentQuantity);
    }

    public void decreaseQuantity(Item item, int quantity) {
        checkNotNegative(quantity);

        if (!contains(item)) create(item);

        int newQuantity = calculateReducedQuantity(item, quantity);
        stock.put(item, newQuantity);
    }

    private int calculateReducedQuantity(Item item, int quantity) {
        int currentQuantity = getQuantity(item);
        int newQuantity = currentQuantity - quantity;
        if (newQuantity < 0) {
            return 0;
        } else {
            return newQuantity;
        }
    }

    /* Visible for testing */
    boolean contains(Item item) { return stock.containsKey(item); }

    /* Visible for testing */
    int getQuantity(Item item) { return stock.get(item); }
}
