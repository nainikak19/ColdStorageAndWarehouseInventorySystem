package com.first.project;

import java.sql.*;
import java.time.LocalDate;

public class ExpiryHandler {

    public void removeExpiredItems() {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "DELETE FROM inventory WHERE expiry_date < ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(LocalDate.now()));

            int deleted = ps.executeUpdate();
            System.out.println("Expired items removed: " + deleted);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
