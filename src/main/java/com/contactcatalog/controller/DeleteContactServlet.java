package com.contactcatalog.controller;

import com.contactcatalog.dao.ContactDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/DeleteContactServlet")
public class DeleteContactServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/contact_catalog";
    private static final String DB_USER = "root";  // Update with your MySQL username
    private static final String DB_PASSWORD = "FAla12/?"; // Update with your MySQL password

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Missing 'id' parameter\"}");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Invalid ID format\"}");
            return;
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            ContactDAO contactDAO = new ContactDAO(conn);
            contactDAO.deleteContact(id);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\": \"Contact deleted successfully\"}");
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}