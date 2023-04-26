package com.cc.domain;

import java.util.HashMap;
import java.util.Map;

public class ShoppingBag <T extends PricedItem<Integer>> {

    private Map<T, Integer> shoppingBag = new HashMap<>();

    public ShoppingBag() {
        this.shoppingBag =  new HashMap<>();
    }

    public void addItem(T item) {
        if(shoppingBag.containsKey(item)){
            int count = shoppingBag.get(item);
            count++;
            shoppingBag.put(item, count);
        } else {
            shoppingBag.put(item, 1);
        }
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (T item : shoppingBag.keySet()) {
            totalPrice += shoppingBag.get(item)*item.getPrice();
        }
        return totalPrice;
    }

}
