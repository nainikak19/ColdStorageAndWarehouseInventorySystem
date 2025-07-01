package com.first.project;

import java.sql.*;
import java.util.List;

public class BatchUploader {

    public void batchInsert(List<String[]> items) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO inventory(item_name, quantity, expiry_date) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            for (String[] item : items) {
                ps.setString(1, item[0]);
                ps.setInt(2, Integer.parseInt(item[1]));
                ps.setDate(3, Date.valueOf(item[2]));
                ps.addBatch();
            }

            int[] results = ps.executeBatch();
            System.out.println("Items uploaded: " + results.length);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
