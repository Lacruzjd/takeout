package com.cc.domain;

public interface IntUserInputRetriever<T> {
    T produceOutputOnIntUserInput(int selection) throws IllegalArgumentException;
}
