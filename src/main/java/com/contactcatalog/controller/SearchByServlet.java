package com.contactcatalog.controller;

import com.contactcatalog.dao.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/searchBy")
public class SearchByServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String phoneHash = request.getParameter("phoneHash");
        String maskedName = request.getParameter("maskedName");
        String maskedPhone = request.getParameter("maskedPhone");
        String organization = request.getParameter("organization");

        JSONArray resultArray = new JSONArray();

        try (Connection connection = DBConnection.getConnection()) {
            String sql = buildQuery(phoneHash, maskedName, maskedPhone, organization);
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                setQueryParameters(preparedStatement, phoneHash, maskedName, maskedPhone, organization);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        JSONObject contact = new JSONObject();
                        contact.put("fullName", resultSet.getString("full_name"));
                        contact.put("hashedPhone", resultSet.getString("hashed_phone"));
                        contact.put("maskedName", resultSet.getString("masked_name"));
                        contact.put("maskedPhone", resultSet.getString("masked_phone"));
                        contact.put("organization", resultSet.getString("organization"));
                        resultArray.put(contact);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred.");
            return;
        }

        response.getWriter().write(resultArray.toString());
    }

    private String buildQuery(String hashedPhone, String maskedName, String maskedPhone, String organization) {
        StringBuilder query = new StringBuilder("SELECT * FROM contacts WHERE 1=1");
        if (hashedPhone != null && !hashedPhone.isEmpty()) query.append(" AND hashed_phone = ?");
        if (maskedName != null && !maskedName.isEmpty()) query.append(" AND masked_name = ?");
        if (maskedPhone != null && !maskedPhone.isEmpty()) query.append(" AND masked_phone = ?");
        if (organization != null && !organization.isEmpty()) query.append(" AND organization = ?");
        return query.toString();
    }

    private void setQueryParameters(PreparedStatement ps, String hashedPhone, String maskedName, String maskedPhone, String organization) throws SQLException {
        int paramIndex = 1;
        if (hashedPhone != null && !hashedPhone.isEmpty()) ps.setString(paramIndex++, hashedPhone);
        if (maskedName != null && !maskedName.isEmpty()) ps.setString(paramIndex++, maskedName);
        if (maskedPhone != null && !maskedPhone.isEmpty()) ps.setString(paramIndex++, maskedPhone);
        if (organization != null && !organization.isEmpty()) ps.setString(paramIndex, organization);
    }
}
