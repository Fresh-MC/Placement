
package com.rest.Models;

import java.util.ArrayList;
import java.util.List;

public class Bill {
    private int billId;
    private List<BillItem> items = new ArrayList<>();

    public Bill(int billId) {
        this.billId = billId;
    }

    public int getBillId() {
        return billId;
    }

    public List<BillItem> getItems() {
        return items;
    }

    public void addItem(menu menu, int quantity) {
        if (menu == null || quantity <= 0) {
            throw new IllegalArgumentException(
                "Menu item and positive quantity are required."
            );
        }

        items.add(new BillItem(menu, quantity));
    }

    public double getTotal() {
        double total = 0;

        for (BillItem item : items) {
            total += item.getSubtotal();
        }

        return total;
    }

    public static class BillItem {
        private menu menu;
        private int quantity;

        public BillItem(menu menu, int quantity) {
            this.menu = menu;
            this.quantity = quantity;
        }

        public menu getMenu() {
            return menu;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getSubtotal() {
            return menu.getPrice() * quantity;
        }
    }
}
