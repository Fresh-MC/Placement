
package com.rest.Models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public enum Status {
        PENDING, CONFIRMED, PREPARING, READY, SERVED, CANCELLED
    }

    private int orderId;
    private int tableId;
    private List<OrderItem> items = new ArrayList<>();
    private Status status = Status.PENDING;

    public Order(int orderId, int tableId) {
        this.orderId = orderId;
        this.tableId = tableId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getTableId() {
        return tableId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void addItem(menu menu, int quantity) {
        if (menu == null || quantity <= 0) {
            throw new IllegalArgumentException(
                "Menu item and positive quantity are required."
            );
        }

        for (OrderItem item : items) {
            if (item.getMenu().getId() == menu.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        items.add(new OrderItem(menu, quantity));
    }

    public double getTotal() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getSubtotal();
        }

        return total;
    }

    public static class OrderItem {
        private menu menu;
        private int quantity;

        public OrderItem(menu menu, int quantity) {
            this.menu = menu;
            this.quantity = quantity;
        }

        public menu getMenu() {
            return menu;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getSubtotal() {
            return menu.getPrice() * quantity;
        }
    }
}
