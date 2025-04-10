package com.awesomeshot5051.resourceFarm;

import java.util.*;

public class ItemHashMap<T> extends HashMap<T, Integer> {
    private final int maxValue;
    private Map<T, Integer> map = new HashMap<>();

    // Constructor accepts the max value for each entry
    public ItemHashMap(int maxValue) {
        this.maxValue = maxValue;
    }

    public ItemHashMap(Map<T, Integer> map, int maxValue) {
        this.map = map;
        this.maxValue = maxValue;
    }

    @Override
    public Integer put(T key, Integer value) {
        if (value > maxValue) {
            System.out.println("Value exceeds the maximum allowed: " + maxValue);
            return null; // Return null because the value wasn't inserted
        }
        return super.put(key, value); // Use the superclass put method to insert into the map
    }

    // Get the value associated with the key
    @Override
    public Integer get(Object key) {
        return map.get(key);
    }

    // Check if the value exists in the map
    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    // Get the max value for this map
    public int getMaxValue() {
        return maxValue;
    }
}