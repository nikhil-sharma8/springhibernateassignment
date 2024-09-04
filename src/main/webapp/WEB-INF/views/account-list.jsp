<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Account List</title>
    <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .container {
                background: #fff;
                padding: 20px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
                max-width: 600px;
                width: 100%;
            }

            h2 {
                color: #333;
                margin-bottom: 20px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }

            table, th, td {
                border: 1px solid #ddd;
            }

            th, td {
                padding: 10px;
                text-align: left;
            }

            th {
                background-color: #f2f2f2;
            }

            a {
                text-decoration: none;
                color: #4CAF50;
            }

            a:hover {
                color: #45a049;
            }
        </style>
</head>
<body>
<div class="container">
    <h2>Account List</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Account Number</th>
            <th>Balance</th>
        </tr>
        <c:forEach var="account" items="${accounts}">
            <tr>
                <td>${account.id}</td>
                <td>${account.accountNumber}</td>
                <td>${account.balance}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="/user/users">Return to User List</a>
    </div>
</body>
</html>
