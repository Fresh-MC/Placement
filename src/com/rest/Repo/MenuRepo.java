
package com.rest.Repo;

import java.util.ArrayList;
import java.util.List;
import com.rest.Models.menu;

public class MenuRepo {

    private final List<menu> menus = new ArrayList<>();

    public List<menu> getmenus() {
        return new ArrayList<>(menus);
    }

    public menu getmenuById(int id) {
        for (menu menu : menus) {
            if (menu.getId() == id) {
                return menu;
            }
        }
        return null;
    }

    public boolean addmenu(menu menu) {
        if (menu == null || getmenuById(menu.getId()) != null) {
            return false;
        }

        menus.add(menu);
        return true;
    }

    public boolean deletemenu(int id) {
        return menus.removeIf(menu -> menu.getId() == id);
    }
}
