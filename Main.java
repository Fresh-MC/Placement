import java.util.List;
import java.util.Scanner;

import com.rest.Models.menu;
import com.rest.Models.Tables;
import com.rest.Models.Bill;

import com.rest.Repo.MenuRepo;
import com.rest.Repo.TableRepo;
import com.rest.Repo.BillRepo;

import com.rest.services.MenuServices;
import com.rest.services.TableService;
import com.rest.services.BillingService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Repository objects
        TableRepo tableRepo = new TableRepo();
        MenuRepo menuRepo = new MenuRepo();
        BillRepo billRepo = new BillRepo();

        // Service objects
        TableService tableService = new TableService(tableRepo);
        MenuServices menuService = new MenuServices(menuRepo);
        BillingService billingService =
                new BillingService(billRepo, menuRepo);

        while (true) {

            System.out.println("\n=== Restaurant Management ===");
            System.out.println("1. View Tables");
            System.out.println("2. Add Table");
            System.out.println("3. Delete Table");
            System.out.println("4. View Menu");
            System.out.println("5. Add Menu Item");
            System.out.println("6. Delete Menu Item");
            System.out.println("7. Generate Bill");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
                continue;
            }

            switch (choice) {

                // View Tables
                case 1: {
                    List<Tables> tables = tableService.getALL();

                    if (tables.isEmpty()) {
                        System.out.println("No tables available.");
                    } else {
                        for (Tables table : tables) {
                            System.out.println(
                                    "Table ID: " + table.getId()
                                    + " | Capacity: " + table.getCap()
                            );
                        }
                    }
                    break;
                }

                // Add Table
                case 2: {
                    try {
                        System.out.print("Enter table ID: ");
                        int tableId = Integer.parseInt(
                                sc.nextLine().trim()
                        );

                        System.out.print("Enter table capacity: ");
                        int capacity = Integer.parseInt(
                                sc.nextLine().trim()
                        );

                        Tables table = new Tables(tableId, capacity);

                        System.out.println(
                                tableService.addTable(table)
                        );

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid numeric input.");
                    }
                    break;
                }

                // Delete Table
                case 3: {
                    try {
                        System.out.print("Enter table ID to delete: ");
                        int tableId = Integer.parseInt(
                                sc.nextLine().trim()
                        );

                        System.out.println(
                                tableService.deleteTable(tableId)
                        );

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid table ID.");
                    }
                    break;
                }

                // View Menu
                case 4: {
                    List<menu> menus = menuService.getAll();

                    if (menus.isEmpty()) {
                        System.out.println("No menu items available.");
                    } else {
                        for (menu item : menus) {
                            System.out.println(
                                    "ID: " + item.getId()
                                    + " | Name: " + item.getName()
                                    + " | Price: " + item.getPrice()
                            );
                        }
                    }
                    break;
                }

                // Add Menu Item
                case 5: {
                    try {
                        System.out.print("Enter menu ID: ");
                        int menuId = Integer.parseInt(
                                sc.nextLine().trim()
                        );

                        System.out.print("Enter menu item name: ");
                        String name = sc.nextLine().trim();

                        System.out.print("Enter price: ");
                        double price = Double.parseDouble(
                                sc.nextLine().trim()
                        );

                        menu.Type type = menu.Type.FOOD;

                        menu newMenu = new menu(
                                menuId, name, price, type
                        );

                        System.out.println(
                                menuService.addMenu(newMenu)
                        );

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid numeric input.");
                    }
                    break;
                }

                // Delete Menu Item
                case 6: {
                    try {
                        System.out.print("Enter menu ID to delete: ");
                        int menuId = Integer.parseInt(
                                sc.nextLine().trim()
                        );

                        System.out.println(
                                menuService.deleteMenu(menuId)
                        );

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid menu ID.");
                    }
                    break;
                }

                // Generate Bill
                case 7: {
                    Bill bill = billingService.createBill();

                    while (true) {
                        try {
                            System.out.print(
                                    "Enter menu item ID (0 to finish): "
                            );

                            int menuId = Integer.parseInt(
                                    sc.nextLine().trim()
                            );

                            if (menuId == 0) {
                                break;
                            }

                            System.out.print("Enter quantity: ");
                            int quantity = Integer.parseInt(
                                    sc.nextLine().trim()
                            );

                            System.out.println(
                                    billingService.addItemToBill(
                                            bill, menuId, quantity
                                    )
                            );

                        } catch (NumberFormatException e) {
                            System.out.println(
                                    "Invalid numeric input. Try again."
                            );
                        }
                    }

                    if (bill.getItems().isEmpty()) {
                        System.out.println(
                                "No items added. Bill is empty."
                        );
                    } else {
                        billingService.printBill(bill);
                    }
                    break;
                }

                // Exit
                case 0: {
                    System.out.println("Exiting application.");
                    sc.close();
                    return;
                }

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}