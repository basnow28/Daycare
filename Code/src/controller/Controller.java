package controller;
import model.Database;


public class Controller{
    private static Database database;

    public Controller(){
        database = new Database();
    }
}