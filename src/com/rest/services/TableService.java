package com.rest.services;

import java.util.List;

import com.rest.Models.Tables;
import com.rest.Repo.TableRepo;

public class TableService {

    private final TableRepo repo;

    public TableService(TableRepo repo) {
        this.repo = repo;
    }

    // View all tables
    public List<Tables> getALL() {
        return repo.getTables();
    }

    // Add a new table
    public String addTable(Tables table) {

        if (table == null) {
            return "Error: Invalid table.";
        }

        if (table.getId() <= 0) {
            return "Error: Invalid table ID.";
        }

        if (table.getCap() <= 0) {
            return "Error: Table capacity must be positive.";
        }

        // Check for duplicate table ID
        for (Tables existing : repo.getTables()) {
            if (existing.getId() == table.getId()) {
                return "Error: Table ID already exists.";
            }
        }

        repo.addTable(table);

        return "Table added successfully.";
    }

    // Delete a table
    public String deleteTable(int id) {

        if (id <= 0) {
            return "Error: Invalid table ID.";
        }

        boolean deleted = repo.deleteTable(id);

        if (deleted) {
            return "Table deleted successfully.";
        }

        return "Error: Table not found.";
    }
}