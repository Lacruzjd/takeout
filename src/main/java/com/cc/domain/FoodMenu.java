package com.cc.domain;

import java.util.ArrayList;
import java.util.List;

public class FoodMenu {
    private List<Food> menu = new ArrayList<>();

    public FoodMenu() {
        this.menu.add(new Food("Arepa", "Reina Pepiada", 5));
        this.menu.add(new Food("Arepa", "Pelua", 3));
        this.menu.add(new Food("Arepa", "Domino", 2));
    }

    public Food getFood(int index) {
        if (index <= 0 || index > menu.size()){
            return null;
        } else {
           return menu.get(index-1);
        }
    }
    
    public Food getLowestCostFood() {

        Food cheapestFood = menu.get(0);

        for (Food food : menu) {

            if(food.getPrice() < cheapestFood.getPrice())
            cheapestFood = food;
        }

        return cheapestFood;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < menu.size(); i++) {
            stringBuilder.append((i+1)+". ").append(menu.get(i)+"\n");
        }
        return stringBuilder.toString();
    }
  
}
