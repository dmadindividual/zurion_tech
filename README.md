# JSP Contact Registry and Phone Directory

## Overview
This project is a simple Contact Registry and Phone Directory web application built using Java Servlets and Java Server Pages (JSP). It allows users to add, view, edit, search, and delete contacts in a structured and user-friendly interface.

## Technologies Used
- **Java Servlet and JSP** (No frameworks required)
- **Maven** (For dependency management)
- **HTML, CSS, JavaScript** (For frontend design and interactivity)
- **MySQL** (For database management)
- **Apache Tomcat** (For running the application locally)

## Getting Started

### Prerequisites
Ensure you have the following installed on your system:
- **JDK (Java Development Kit) 8 or later**
- **Apache Tomcat (Version 9 or later recommended)**
- **Maven**
- **MySQL Server and MySQL Workbench (or any other MySQL client)**
- **Git** (For cloning the repository)

### Installation Steps

1. **Clone the Repository**
   ```sh
   git clone https://github.com/dmadindividual/zurion_tech
   cd zurion_tech
   ```

2. **Set Up Database Credentials**
   - Navigate to `DBConnection.java` and update your database credentials:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/contact_catalog";
   private static final String USER = "your_database_username";
   private static final String PASSWORD = "your_database_password";
   ```

3. **Create the Required Database Table**
   Open your MySQL Workbench or MySQL CLI and execute the following SQL command to create the necessary table:
   ```sql
   CREATE TABLE contacts (
       id INT AUTO_INCREMENT PRIMARY KEY,
       full_name VARCHAR(255) NOT NULL,
       phone_number VARCHAR(20) NOT NULL,
       email VARCHAR(255),
       id_number VARCHAR(50),
       date_of_birth DATE,
       gender ENUM('Male', 'Female', 'Other'),
       organization VARCHAR(255),
       masked_name VARCHAR(255),
       masked_phone VARCHAR(20),
       hashed_phone VARCHAR(255)
   );
   ```

4. **Build the Project Using Maven**
   Run the following command in the project directory to download dependencies and build the project:
   ```sh
   mvn clean install
   ```

5. **Deploy on Tomcat**
   - Copy the generated WAR file from `target/` and place it inside `webapps/` directory of your Tomcat installation.
   - Start Tomcat and access the application.

## Usage

### Adding a Contact
- Open your browser and navigate to:
  ```
  http://localhost:8080/zurion_tech_task_one/add_contact.jsp
  ```
- Fill in the contact details and submit.

### Viewing Contacts
- Once a contact is added, you will be routed to the contact list page:
  ```
  http://localhost:8080/zurion_tech_task_one/ContactListServlet
  ```
- Here, you can view all contacts with a clean and structured UI.

### Editing and Deleting Contacts
- Click the "Edit" button next to a contact to update details.
- Click the "Delete" button to remove a contact permanently.

### Searching for Contacts
- To search for contacts, navigate to:
  ```
  http://localhost:8080/zurion_tech_task_one/search.jsp
  ```
- You can search by **masked name, organization, phone numbers, or full names**.

## Notes
- This project does **not** use Spring Boot, meaning database tables **must** be created manually.
- Ensure Tomcat is running on port **8080**.
- The database connection must be correctly configured before running the application.

## Happy Testing!
Enjoy using the JSP Contact Registry and Phone Directory application!

