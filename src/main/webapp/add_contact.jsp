<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Form</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>

<form action="${pageContext.request.contextPath}/ContactServlet" method="post">
        <label>Full Name:
            <input type="text" name="fullName" placeholder="Full Name" required>
        </label>

        <label>Phone Number:
            <input type="text" name="phoneNumber" placeholder="Phone Number" required>
        </label>

        <label>Email:
            <input type="email" name="email" placeholder="Email" required>
        </label>

        <label>ID Number:
            <input type="text" name="idNumber" placeholder="ID Number" required>
        </label>

        <label>Date of Birth:
            <input type="date" name="dob" required>
        </label>

        <label>Gender:
            <select name="gender" required>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
            </select>
        </label>

        <label>Organization:
            <input type="text" name="organization" placeholder="Organization">
        </label>

        <button type="submit">Submit</button>
    </form>

</body>
</html>
