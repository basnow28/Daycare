package model;

import java.util.ArrayList;

public class WaitingList{
    private ArrayList<Integer> childrenIds;
    private Quarter quarter;
    private int capacity;

    public WaitingList(Quarter quarter, int capacity) {
        childrenIds = new ArrayList<Integer>();
        this.quarter = quarter;
        this.capacity = capacity;
    }

    public void addChild(int childId){
        childrenIds.add(childId);
    }
}