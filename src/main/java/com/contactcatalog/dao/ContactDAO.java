package com.contactcatalog.dao;

import com.contactcatalog.model.Contact;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {
    private final Connection connection;

    public ContactDAO(Connection connection) {
        this.connection = connection;
    }

    public void addContact(Contact contact) throws SQLException {
        String sql = "INSERT INTO contacts (full_name, phone_number, email, id_number, date_of_birth, gender, organization, masked_name, masked_phone, hashed_phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, contact.getFullName());
            stmt.setString(2, contact.getPhoneNumber());
            stmt.setString(3, contact.getEmail());
            stmt.setString(4, contact.getIdNumber());
            stmt.setString(5, contact.getDob());
            stmt.setString(6, contact.getGender());
            stmt.setString(7, contact.getOrganization());
            stmt.setString(8, maskName(contact.getFullName()));
            stmt.setString(9, maskPhoneNumber(contact.getPhoneNumber()));
            stmt.setString(10, hashPhoneNumber(contact.getPhoneNumber()));
            stmt.executeUpdate();
        }
    }

    public List<Contact> getContacts() throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT id, full_name, phone_number, email, id_number, date_of_birth, gender, organization FROM contacts";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                contacts.add(mapResultSetToContact(rs));
            }
        }

        System.out.println(" Retrieved " + contacts.size() + " contacts.");
        return contacts;
    }


    public Contact getContactById(int id) throws SQLException {
        String sql = "SELECT * FROM contacts WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToContact(rs);
                }
            }
        }
        return null;
    }

    public void updateContact(Contact contact) throws SQLException {
        String sql = "UPDATE contacts SET full_name=?, phone_number=?, email=?, id_number=?, date_of_birth=?, gender=?, organization=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, contact.getFullName());
            stmt.setString(2, contact.getPhoneNumber());
            stmt.setString(3, contact.getEmail());
            stmt.setString(4, contact.getIdNumber());
            stmt.setString(5, contact.getDob());
            stmt.setString(6, contact.getGender());
            stmt.setString(7, contact.getOrganization());
            stmt.setInt(8, contact.getId());
            stmt.executeUpdate();
        }
    }

    private Contact mapResultSetToContact(ResultSet rs) throws SQLException {
        Contact contact = new Contact();
        contact.setId(rs.getInt("id"));
        contact.setFullName(rs.getString("full_name"));
        contact.setPhoneNumber(rs.getString("phone_number"));
        contact.setEmail(rs.getString("email"));
        contact.setIdNumber(rs.getString("id_number"));
        contact.setDob(rs.getString("date_of_birth"));
        contact.setGender(rs.getString("gender"));
        contact.setOrganization(rs.getString("organization"));
        return contact;
    }

    private String maskName(String name) {
        return name.split(" ")[0] + " ***";
    }

    private String maskPhoneNumber(String phone) {
        return phone.substring(0, 4) + "****" + phone.substring(phone.length() - 3);
    }

    private String hashPhoneNumber(String phone) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(phone.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    public void deleteContact(int id) throws SQLException {
        String sql = "DELETE FROM contacts WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println(" No contact found with ID: " + id);
            } else {
                System.out.println(" Contact deleted successfully (ID: " + id + ")");
            }
        }
    }

}
