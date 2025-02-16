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

@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        response.getWriter().write("ContactServlet is running...");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullName");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String idNumber = request.getParameter("idNumber");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String organization = request.getParameter("organization");

        Contact contact = new Contact();
        contact.setFullName(fullName);
        contact.setPhoneNumber(phoneNumber);
        contact.setEmail(email);
        contact.setIdNumber(idNumber);
        contact.setDob(dob);
        contact.setGender(gender);
        contact.setOrganization(organization);

        try (Connection connection = DBConnection.getConnection()) {
            ContactDAO contactDAO = new ContactDAO(connection);
            contactDAO.addContact(contact);

            // 🔥 Redirect to ContactListServlet after adding a contact
            response.sendRedirect("ContactListServlet");

        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding contact to database");
        }
    }
}
