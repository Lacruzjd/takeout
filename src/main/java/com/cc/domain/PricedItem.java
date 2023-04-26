package com.cc.domain;

public interface PricedItem<T extends Number> {
    T getPrice();
    void setPrice(T price);
}
