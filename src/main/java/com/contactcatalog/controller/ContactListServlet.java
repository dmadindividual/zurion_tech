package com.contactcatalog.controller;


import com.contactcatalog.dao.ContactDAO;
import com.contactcatalog.dao.DBConnection;
import com.contactcatalog.model.Contact;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ContactListServlet")
public class ContactListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DBConnection.getConnection()) {
            System.out.println("✅ Connected to database.");

            ContactDAO contactDAO = new ContactDAO(connection);
            List<Contact> contactList = contactDAO.getContacts();

            System.out.println(" Contacts Retrieved: " + contactList.size());
            for (Contact c : contactList) {
                System.out.println(" - " + c.getFullName());
            }

            request.setAttribute("contactList", contactList);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();  // Print detailed error in logs
            throw new ServletException("Error retrieving contacts", e);
        }
    }
}
