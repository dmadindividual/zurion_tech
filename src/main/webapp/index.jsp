<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Contact List</title>
    <link rel="stylesheet" href="css/home.css">
    <script>
        function deleteContact(id) {
            if (confirm("Are you sure you want to delete this contact?")) {
                fetch("DeleteContactServlet?id=" + id, {
                    method: "DELETE"
                }).then(response => response.json())
                  .then(data => {
                      alert(data.message || data.error);
                      location.reload(); // Refresh page after delete
                  }).catch(error => console.error("Error:", error));
            }
        }
    </script>
    <style>
        .search-btn {
            display: inline-block;
            padding: 10px 20px;
            margin: 20px 0;
            font-size: 16px;
            font-weight: bold;
            text-decoration: none;
            color: white;
            background: linear-gradient(45deg, #007bff, #00c6ff);
            border: none;
            border-radius: 5px;
            transition: 0.3s;
            cursor: pointer;
        }

        .search-btn:hover {
            background: linear-gradient(45deg, #0056b3, #0080ff);
            transform: scale(1.05);
        }
    </style>
</head>
<body>

<h2>Contact List</h2>

<!-- Button to navigate to search.jsp -->
<a href="search.jsp" class="search-btn">🔍 Search Contacts</a>

<c:choose>
    <c:when test="${empty contactList}">
        <script>
            window.location.href = "add_contact.jsp";
        </script>
    </c:when>
    <c:otherwise>
        <table border="1">
            <tr>
                <th>Full Name</th>
                <th>Phone Number</th>
                <th>Email</th>
                <th>ID Number</th>
                <th>Date of Birth</th>
                <th>Gender</th>
                <th>Organization</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="contact" items="${contactList}">
                <tr>
                    <td>${contact.fullName}</td>
                    <td>${contact.phoneNumber}</td>
                    <td>${contact.email}</td>
                    <td>${contact.idNumber}</td>
                    <td>${contact.dob}</td>
                    <td>${contact.gender}</td>
                    <td>${contact.organization}</td>
                    <td>
                        <a href="edit_contact.jsp?id=${contact.id}">Edit</a>
                        <button onclick="deleteContact(${contact.id})">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>

</body>
</html>
