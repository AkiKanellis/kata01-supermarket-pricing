package com.akikanellis.kata01.stock;

import com.akikanellis.kata01.item.Item;
import com.akikanellis.kata01.item.ItemAlreadyExistsException;
import com.akikanellis.kata01.item.ItemNotFoundException;
import com.akikanellis.kata01.item.Items;

import java.util.HashMap;
import java.util.Map;

/**
 * An in-memory implementation of an {@link com.akikanellis.kata01.stock.StockRepository}. Each item is mapped into a
 * pair of itself and the current quantity.
 */
public class InMemoryStockRepository implements StockRepository {
    private static final int STARTING_QUANTITY = 0;

    private final Map<Item, Integer> stock;

    public InMemoryStockRepository() { this.stock = new HashMap<>(); }

    @Override public boolean contains(Item item) { return stock.containsKey(item); }

    @Override public void create(Item item) {
        if (contains(item)) throw new ItemAlreadyExistsException(item);

        stock.put(item, STARTING_QUANTITY);
    }

    @Override public void replaceQuantity(Item item, int quantity) {
        checkContainsItem(item);

        stock.put(item, quantity);
    }

    @Override public int getQuantity(Item item) {
        checkContainsItem(item);

        return stock.get(item);
    }

    private void checkContainsItem(Item item) { if (!contains(item)) throw new ItemNotFoundException(item); }

    @Override public Item getByBarcode(long barcode) {
        return stock.keySet().stream()
                .filter(item -> item.barcode() == barcode)
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException(barcode));
    }

    @Override public Items getAll() { return Items.fromEntries(stock.entrySet()); }
}
