package com.akikanellis.kata01;

import static com.akikanellis.kata01.Preconditions.checkNotNegative;

public class ReduceStockUseCase {
    private final StockRepository stock;
    private final AddNewItemIfNotExistsUseCase addNewItemIfNotExists;

    public ReduceStockUseCase(StockRepository stock, AddNewItemIfNotExistsUseCase addNewItemIfNotExists) {
        this.addNewItemIfNotExists = addNewItemIfNotExists;
        this.stock = stock;
    }

    public void execute(Item item, int quantity) {
        checkNotNegative(quantity);
        addNewItemIfNotExists.execute(item);

        int newQuantity = calculateNewQuantity(item, quantity);
        stock.replaceQuantity(item, newQuantity);
    }

    private int calculateNewQuantity(Item item, int quantity) {
        int currentQuantity = stock.getQuantity(item);
        if (currentQuantity > quantity) {
            return currentQuantity - quantity;
        } else {
            return 0;
        }
    }
}
