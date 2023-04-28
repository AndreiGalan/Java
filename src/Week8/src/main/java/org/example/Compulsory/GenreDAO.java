package org.example.Compulsory;
import java.sql.*;

public class GenreDAO {
    public static void create(String name) throws SQLException {
        try (Connection con = Database.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement pstmt = con.prepareStatement(
                    "INSERT INTO genres (name) VALUES (?)")) {
                pstmt.setString(1, name);
                pstmt.executeUpdate();
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        }
    }

    public static Integer findByName(String name) throws SQLException {
        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(
                     "SELECT id FROM genres WHERE name = ?")) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getInt(1) : null;
            }
        }
    }

    public String findById(int id) throws SQLException {
        try (Connection con = Database.getConnection();
             PreparedStatement pstmt = con.prepareStatement(
                     "SELECT name FROM genres WHERE id = ?")) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getString(1) : null;
            }
        }
    }
}
