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

@WebServlet("/UpdateContactServlet")
public class UpdateContactServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid contact ID format", e);
        }


        Contact contact = new Contact();
        contact.setId(id);
        contact.setFullName(request.getParameter("fullName"));
        contact.setPhoneNumber(request.getParameter("phoneNumber"));
        contact.setEmail(request.getParameter("email"));
        contact.setIdNumber(request.getParameter("idNumber"));
        contact.setDob(request.getParameter("dob"));
        contact.setGender(request.getParameter("gender"));
        contact.setOrganization(request.getParameter("organization"));

        // Get database connection and perform update
        try (Connection connection = DBConnection.getConnection()) {
            ContactDAO contactDAO = new ContactDAO(connection);
            contactDAO.updateContact(contact);
        } catch (SQLException e) {
            throw new ServletException("Error updating contact in the database", e);
        }

        // Redirect to index page
        response.sendRedirect("ContactListServlet");
    }
}
