package com.rest.Models;
 public class Tables{
    public enum Avail{
        occupy,free
    }

    private int id;
    private int cap;
    private Avail status;

    public Tables(int id, int cap){
        this.id = id;
        this.cap = cap;
        this.status = Avail.free;
    }

    public int getId(){
        return id;
    } 

    public int getCap(){
        return cap;
    } 

    public Avail getStatus(){
        return status;
    } 

    public void booking(){
        status = Avail.occupy;
    }

    public void free(){
        status = Avail.free;
    }

    


}