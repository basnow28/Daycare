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
        if(ids.length > 1)
        for (int i = 0; i < ids.length; i++) {
            this.childrenIds.add(Integer.parseInt(ids[i]));
        }
    }

    public void addChild(int childId){
        childrenIds.add(childId);
    }

    public void deleteChild(int childId){
        childrenIds.remove(childId);
    }

    public ArrayList<Integer> getChildrenIds() {
        return childrenIds;
    }

    public void setChildrenIds(ArrayList<Integer> childrenIds) {
        this.childrenIds = childrenIds;
    }

    public String toString(){
        return this.id + " " + this.quarter + " " + year + childrenIds.toString() + '\n';
    }

    public String toStringConsole(){
        return String.join("\t", Integer.toString(this.id), this.quarter.toString(), this.year, Integer.toString(capacity), idsToConsole());
    }

    private String idsToConsole(){
        String result = "[";
        for(int id : this.childrenIds){
            result += id;
            result += ",";
        }
        int length = result.length();
        if(length>=2)
            result = result.substring(0, length-1);
        result += "]";
        return result;
    }

    public Quarter getQuarter() {
        return quarter;
    }

    public void setQuarter(Quarter quarter) {
        this.quarter = quarter;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getId(){
        return this.id;
    }
}