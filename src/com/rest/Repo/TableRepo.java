package com.rest.Repo;

import java.util.ArrayList;
import java.util.List;
import com.rest.Models.Tables;

public class TableRepo{
    private final List<Tables> tables = new ArrayList<>();

    public TableRepo(){
        tables.add(new Tables(1,5));
        tables.add(new Tables(2,7));
        tables.add(new Tables(3,6));
    }

    public List<Tables> getTables(){
        return tables;
    }

    public boolean addTable(Tables table){
        return tables.add(table);
    }

    public boolean deleteTable (int id){
        return tables.removeIf (table -> table.getId() == id);
    }
}