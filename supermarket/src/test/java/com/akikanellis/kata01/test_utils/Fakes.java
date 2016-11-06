package com.akikanellis.kata01.test_utils;

import com.akikanellis.kata01.Item;
import com.akikanellis.kata01.ItemWithQuantity;
import com.akikanellis.kata01.Items;
import com.akikanellis.kata01.Price;

import java.util.ArrayList;
import java.util.List;

public final class Fakes {

    private Fakes() { throw new AssertionError("No instances."); }

    public static Item createDefaultItem() {
        return Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ONE)
                .build();
    }

    public static Items defaultItems() {
        Item apple = Item.builder()
                .barcode(1)
                .name("Apple")
                .price(Price.ZERO)
                .build();
        Item orange = Item.builder()
                .barcode(2)
                .name("Orange")
                .price(Price.ZERO)
                .build();
        Item pear = Item.builder()
                .barcode(3)
                .name("Pear")
                .price(Price.ZERO)
                .build();
        ItemWithQuantity appleWithQuantity = ItemWithQuantity.create(apple, 20);
        ItemWithQuantity orangeWithQuantity = ItemWithQuantity.create(orange, 60);
        ItemWithQuantity pearWithQuantity = ItemWithQuantity.create(pear, 8);

        List<ItemWithQuantity> itemsWithQuantities = new ArrayList<>();
        itemsWithQuantities.add(appleWithQuantity);
        itemsWithQuantities.add(orangeWithQuantity);
        itemsWithQuantities.add(pearWithQuantity);

        return Items.fromList(itemsWithQuantities);
    }
}