package model;

import java.util.ArrayList;
import java.util.Arrays;

public class WaitingList{
    private int id = 0;
    private Quarter quarter;
    private String year;
    private int capacity;
    private ArrayList<Integer> childrenIds;

    public WaitingList(int id, Quarter quarter, String year, int capacity) {
        this.id = id;
        childrenIds = new ArrayList<Integer>();
        this.quarter = quarter;
        this.capacity = capacity;
        this.year = year;
    }

    public WaitingList(int id, Quarter quarter, String year, int capacity, String childrenIds) {
        this(id, quarter, year, capacity);
        String[] ids = childrenIds.replaceAll("\\[|\\]| ", "").split(",");

        for (int i = 0; i < ids.length; i++) {
            this.childrenIds.add(Integer.parseInt(ids[i]));
        }
    }

    public void addChild(int childId){
        childrenIds.add(childId);
    }

    public ArrayList<Integer> getChildrenIds() {
        return childrenIds;
    }

    public void setChildrenIds(ArrayList<Integer> childrenIds) {
        this.childrenIds = childrenIds;
    }

    public String toString(){
        return this.quarter + " " + year + childrenIds.toString();
    }
}