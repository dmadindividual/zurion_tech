<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Contacts</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="css/search.css">
</head>
<body>

    <h2>Search Contacts</h2>

    <label for="searchType">Search by:</label>
    <select id="searchType">
        <option value="fullName">Full Name</option>
        <option value="maskedName">Masked Name</option>
        <option value="maskedPhone">Masked Phone</option>
        <option value="hashedPhone">Hashed Phone</option>
        <option value="organization" selected>Organization</option>
    </select>

    <input type="text" id="searchInput" placeholder="Enter search value">
    <button onclick="searchContacts()">Search</button>

    <div id="results" class="hidden">
        <h3>Search Results</h3>
        <table>
            <thead>
                <tr>
                    <th>Full Name</th>
                    <th>Hashed Phone</th>
                    <th>Masked Name</th>
                    <th>Masked Phone</th>
                    <th>Organization</th>
                </tr>
            </thead>
            <tbody id="resultTable">
                <tr>
                    <td colspan="5" class="no-results">No results found</td>
                </tr>
            </tbody>
        </table>
    </div>

    <script>
        function searchContacts() {
            var searchType = document.getElementById("searchType").value;  // Selected search type
            var searchValue = document.getElementById("searchInput").value.trim();

            if (!searchValue) {
                alert("Please enter a search value.");
                return;
            }

            var requestUrl = "http://localhost:8080/zurion_tech_task_one/searchBy?" + searchType + "=" + encodeURIComponent(searchValue);
            console.log("Final Request URL:", requestUrl);

            $.ajax({
                url: requestUrl,
                type: "GET",
                dataType: "json",
                success: function (data) {
                    console.log("Fetched JSON Data:", data);

                    var tableBody = document.getElementById("resultTable");
                    tableBody.innerHTML = ""; // Clear old data

                    if (!data || data.length === 0) {
                        tableBody.innerHTML = "<tr><td colspan='5' class='no-results'>No results found</td></tr>";
                    } else {
                        data.forEach(function (contact) {
                            console.log("Processed Contact Data:", contact);

                            var fullName = contact.fullName ? contact.fullName.trim() : "N/A";
                            var hashedPhone = contact.hashedPhone ? contact.hashedPhone.trim() : "N/A";
                            var maskedName = contact.maskedName ? contact.maskedName.trim() : "N/A";
                            var maskedPhone = contact.maskedPhone ? contact.maskedPhone.trim() : "N/A";
                            var organization = contact.organization ? contact.organization.trim() : "N/A";

                            var newRow = "<tr>" +
                                "<td>" + fullName + "</td>" +
                                "<td>" + hashedPhone + "</td>" +
                                "<td>" + maskedName + "</td>" +
                                "<td>" + maskedPhone + "</td>" +
                                "<td>" + organization + "</td>" +
                                "</tr>";

                            tableBody.innerHTML += newRow;
                        });
                    }

                    document.getElementById("results").classList.remove("hidden");
                },
                error: function () {
                    alert("Error fetching data. Please try again.");
                }
            });
        }
    </script>

</body>
</html>
