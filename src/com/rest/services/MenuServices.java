
package com.rest.services;

import java.util.List;
import com.rest.Models.menu;
import com.rest.Repo.MenuRepo;

public class MenuServices {

    private final MenuRepo repo;

    public MenuServices(MenuRepo repo) {
        this.repo = repo;
    }

    public List<menu> getAll() {
        return repo.getmenus();
    }

    public String addMenu(menu menu) {

        if (menu == null) {
            return "Error: Menu item cannot be null.";
        }

        if (menu.getId() <= 0) {
            return "Error: Menu ID must be positive.";
        }

        if (menu.getName() == null || menu.getName().trim().isEmpty()) {
            return "Error: Menu name cannot be empty.";
        }

        if (menu.getPrice() <= 0) {
            return "Error: Price must be positive.";
        }

        if (repo.addmenu(menu)) {
            return "Menu item added successfully.";
        }

        return "Error: Menu ID already exists.";
    }

    public String deleteMenu(int id) {

        if (id <= 0) {
            return "Error: Invalid menu ID.";
        }

        if (repo.deletemenu(id)) {
            return "Menu item deleted successfully.";
        }

        return "Error: Menu item not found.";
    }
}
