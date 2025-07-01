package com.first.project;

import java.sql.*;
import java.util.Scanner;

public class InventoryService {

	public void addItem(Scanner sc) {
	    try (Connection con = DBConnection.getConnection()) {
	        System.out.print("Item Name: ");
	        String name = sc.nextLine();
	        System.out.print("Quantity: ");
	        int qty = Integer.parseInt(sc.nextLine());
	        System.out.print("Expiry Date (YYYY-MM-DD): ");
	        String date = sc.nextLine().trim(); 

	        try {
	            Date expiry = Date.valueOf(date); 
	            PreparedStatement ps = con.prepareStatement(
	                "INSERT INTO inventory(item_name, quantity, expiry_date) VALUES (?, ?, ?)");
	            ps.setString(1, name);
	            ps.setInt(2, qty);
	            ps.setDate(3, expiry);

	            int rows = ps.executeUpdate();
	            System.out.println(rows > 0 ? "Item added!" : "Failed to add item.");

	        } catch (IllegalArgumentException e) {
	            System.out.println("❌ Invalid date format! Please use YYYY-MM-DD (e.g., 2025-07-30).");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


    public void viewInventory() {
        try  {
        	Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM inventory");

            while (rs.next()) {
                System.out.printf("ID: %d | Item: %s | Qty: %d | Expiry: %s | Added: %s%n",
                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getInt("quantity"),
                        rs.getDate("expiry_date"),
                        rs.getTimestamp("added_on"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
