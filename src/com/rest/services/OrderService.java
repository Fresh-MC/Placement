
package com.rest.services;

import java.util.List;
import com.rest.Models.menu;
import com.rest.Models.Order;
import com.rest.Models.Tables;
import com.rest.Repo.MenuRepo;
import com.rest.Repo.OrderRepo;
import com.rest.Repo.TableRepo;

public class OrderService {
    private final OrderRepo orderRepo;
    private final MenuRepo menuRepo;
    private final TableRepo tableRepo;
    private int nextOrderId = 1;

    public OrderService(
            OrderRepo orderRepo,
            MenuRepo menuRepo,
            TableRepo tableRepo) {
        this.orderRepo = orderRepo;
        this.menuRepo = menuRepo;
        this.tableRepo = tableRepo;
    }

    public String createOrder(int tableId) {
        if (tableId <= 0) {
            return "Error: Invalid table ID.";
        }

        List<Tables> tables = tableRepo.getTables();
        boolean tableExists = false;

        for (Tables table : tables) {
            if (table.getId() == tableId) {
                tableExists = true;
                break;
            }
        }

        if (!tableExists) {
            return "Error: Table not found.";
        }

        Order order = new Order(nextOrderId++, tableId);
        orderRepo.saveOrder(order);

        return "Order created successfully. Order ID: "
                + order.getOrderId();
    }

    public String addItem(int orderId, int menuId, int quantity) {
        Order order = orderRepo.getOrderById(orderId);

        if (order == null) {
            return "Error: Order not found.";
        }

        if (order.getStatus() != Order.Status.PENDING) {
            return "Error: Items can only be added to pending orders.";
        }

        if (quantity <= 0) {
            return "Error: Quantity must be greater than zero.";
        }

        menu menu = menuRepo.getmenuById(menuId);

        if (menu == null) {
            return "Error: Menu item not found.";
        }

        order.addItem(menu, quantity);
        return "Item added to order successfully.";
    }

    public String confirmOrder(int orderId) {
        Order order = orderRepo.getOrderById(orderId);

        if (order == null) {
            return "Error: Order not found.";
        }

        if (order.getStatus() != Order.Status.PENDING) {
            return "Error: Order is not pending.";
        }

        if (order.getItems().isEmpty()) {
            return "Error: Cannot confirm an empty order.";
        }

        order.setStatus(Order.Status.CONFIRMED);
        return "Order confirmed successfully.";
    }

    public List<Order> getAllOrders() {
        return orderRepo.getAllOrders();
    }

    public String updateStatus(int orderId, Order.Status status) {
        Order order = orderRepo.getOrderById(orderId);

        if (order == null) {
            return "Error: Order not found.";
        }

        if (status == null || status == Order.Status.PENDING
                || status == Order.Status.CANCELLED) {
            return "Error: Invalid status update.";
        }

        if (order.getStatus() == Order.Status.CANCELLED
                || order.getStatus() == Order.Status.SERVED) {
            return "Error: This order is already closed.";
        }

        order.setStatus(status);
        return "Order status updated to " + status + ".";
    }
}
