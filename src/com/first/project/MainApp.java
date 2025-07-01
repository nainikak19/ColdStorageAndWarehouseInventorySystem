package com.first.project;

import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventoryService service = new InventoryService();
        ExpiryHandler expiryHandler = new ExpiryHandler();
        BatchUploader batchUploader = new BatchUploader();

        System.out.println("Login as: 1. Warehouse Staff  2. Vendor");
        int role = Integer.parseInt(sc.nextLine());

        while (true) {
            System.out.println("\n1. View Inventory\n2. Add Item\n3. Remove Expired Items\n4. Batch Upload\n5. Exit");
            System.out.print("Choose option: ");
            int ch = Integer.parseInt(sc.nextLine());

            switch (ch) {
                case 1 -> service.viewInventory();
                case 2 -> {
                    if (role == 1) service.addItem(sc);
                    else System.out.println("Access denied.");
                }
                case 3 -> {
                    if (role == 1) expiryHandler.removeExpiredItems();
                    else System.out.println("Access denied.");
                }
                case 4 -> {
                    if (role == 1) {
                        List<String[]> items = new ArrayList<>();
                        System.out.print("Enter number of items: ");
                        int count = Integer.parseInt(sc.nextLine());
                        for (int i = 0; i < count; i++) {
                            System.out.println("Item " + (i + 1));
                            System.out.print("Name: ");
                            String name = sc.nextLine();
                            System.out.print("Qty: ");
                            String qty = sc.nextLine();
                            System.out.print("Expiry (YYYY-MM-DD): ");
                            String exp = sc.nextLine();
                            items.add(new String[]{name, qty, exp});
                        }
                        batchUploader.batchInsert(items);
                    } else System.out.println("Access denied.");
                }
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}

