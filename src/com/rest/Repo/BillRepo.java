
package com.rest.Repo;

import java.util.ArrayList;
import java.util.List;
import com.rest.Models.Bill;

public class BillRepo {
    private final List<Bill> bills = new ArrayList<>();

    public void saveBill(Bill bill) {
        bills.add(bill);
    }

    public List<Bill> getAllBills() {
        return new ArrayList<>(bills);
    }

    public Bill getBillById(int billId) {
        for (Bill bill : bills) {
            if (bill.getBillId() == billId) {
                return bill;
            }
        }
        return null;
    }
}
