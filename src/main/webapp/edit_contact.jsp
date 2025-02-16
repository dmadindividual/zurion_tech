<%@ page import="java.sql.*, com.contactcatalog.dao.DBConnection, com.contactcatalog.dao.ContactDAO, com.contactcatalog.model.Contact" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%
    String contactId = request.getParameter("id");
    Contact contact = new Contact(); // Default empty contact for new entries

    if (contactId != null && !contactId.trim().isEmpty()) {
        try {
            int id = Integer.parseInt(contactId);
            ContactDAO contactDAO = new ContactDAO(DBConnection.getConnection());
            contact = contactDAO.getContactById(id);

            if (contact == null) {
                contact = new Contact(); // If contact not found, show an empty form
            }
        } catch (NumberFormatException e) {
            contact = new Contact(); // If invalid ID, still allow creating a new contact
        }
    }
%>

<html>
<head>
    <title><%= (contact.getId() > 0) ? "Edit Contact" : "Add New Contact" %></title>
    <link rel="stylesheet" href="css/edit.css">
</head>
<body>
    <h2><%= (contact.getId() > 0) ? "Edit Contact" : "Add New Contact" %></h2>

    <form action="<%= (contact.getId() > 0) ? "UpdateContactServlet" : "AddContactServlet" %>" method="post">
        <% if (contact.getId() > 0) { %>
            <input type="hidden" name="id" value="<%= contact.getId() %>">
        <% } %>

        <label>Full Name:</label>
        <input type="text" name="fullName" value="<%= contact.getFullName() != null ? contact.getFullName() : "" %>" required>

        <label>Phone Number:</label>
        <input type="text" name="phoneNumber" value="<%= contact.getPhoneNumber() != null ? contact.getPhoneNumber() : "" %>" required>

        <label>Email:</label>
        <input type="email" name="email" value="<%= contact.getEmail() != null ? contact.getEmail() : "" %>">

        <label>ID Number:</label>
        <input type="text" name="idNumber" value="<%= contact.getIdNumber() != null ? contact.getIdNumber() : "" %>">

        <label>Date of Birth:</label>
        <input type="date" name="dob" value="<%= contact.getDob() != null ? contact.getDob() : "" %>">

        <label>Gender:</label>
        <select name="gender">
            <option value="Male" <%= "Male".equals(contact.getGender()) ? "selected" : "" %>>Male</option>
            <option value="Female" <%= "Female".equals(contact.getGender()) ? "selected" : "" %>>Female</option>
        </select>

        <label>Organization:</label>
        <input type="text" name="organization" value="<%= contact.getOrganization() != null ? contact.getOrganization() : "" %>">

        <button type="submit"><%= (contact.getId() > 0) ? "Update Contact" : "Create Contact" %></button>
    </form>
</body>
</html>
