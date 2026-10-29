
package com.rest.services;

import com.rest.Models.Bill;
import com.rest.Models.menu;
import com.rest.Repo.BillRepo;
import com.rest.Repo.MenuRepo;

public class BillingService {
    private final BillRepo billRepo;
    private final MenuRepo menuRepo;
    private int nextBillId = 1;

    public BillingService(BillRepo billRepo, MenuRepo menuRepo) {
        this.billRepo = billRepo;
        this.menuRepo = menuRepo;
    }

    public Bill createBill() {
        Bill bill = new Bill(nextBillId++);
        billRepo.saveBill(bill);
        return bill;
    }

    public String addItemToBill(Bill bill, int menuId, int quantity) {
        if (bill == null) {
            return "Error: Bill not found.";
        }

        if (quantity <= 0) {
            return "Error: Quantity must be greater than zero.";
        }

        menu menu = menuRepo.getmenuById(menuId);

        if (menu == null) {
            return "Error: Menu item not found.";
        }

        bill.addItem(menu, quantity);
        return "Item added to bill successfully.";
    }

    public void printBill(Bill bill) {
        if (bill == null) {
            System.out.println("Bill not found.");
            return;
        }

        System.out.println("\n========== RESTAURANT BILL ==========");
        System.out.println("Bill ID: " + bill.getBillId());
        System.out.println("-------------------------------------");
        System.out.printf("%-18s %5s %10s%n",
                "Item", "Qty", "Subtotal");
        System.out.println("-------------------------------------");

        for (Bill.BillItem item : bill.getItems()) {
            System.out.printf("%-18s %5d %10.2f%n",
                    item.getMenu().getName(),
                    item.getQuantity(),
                    item.getSubtotal());
        }

        System.out.println("-------------------------------------");
        System.out.printf("TOTAL: Rs. %.2f%n", bill.getTotal());
        System.out.println("=====================================");
    }
}
