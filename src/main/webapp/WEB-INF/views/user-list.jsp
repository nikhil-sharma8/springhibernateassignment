<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>User List</title>
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

            .actions {
                display: flex;
                gap: 5px;
            }

            form {
                  display: flex;
                  flex-direction: column;
            }

            .form-group {
                   margin-bottom: 15px;
            }

            label {
                   display: block;
                   margin-bottom: 5px;
                   font-weight: bold;
                   color: #333;
            }

            input[type="text"], input[type="email"], input[type="password"] {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
                font-size: 16px;
            }

            input[type="text"]:focus, input[type="email"]:focus, input[type="password"]:focus {
                border-color: #4CAF50;
                outline: none;
                box-shadow: 0 0 5px rgba(76, 175, 80, 0.3);
            }

            button {
                background-color: #4CAF50;
                color: white;
                padding: 12px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
                margin-top: 10px;
                transition: background-color 0.3s ease;
            }

            button:hover {
                background-color: #45a049;
            }
        </style>
</head>
<body>
 <div class="container">
    <h2>User List</h2>
    <c:choose>
                <c:when test="${empty users}">
                    <p class="no-data">No users available.</p>
                </c:when>
                <c:otherwise>
                    <table>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Password</th>
                            <th>Email</th>
                            <th>Accounts</th>
                            <th>Actions</th>
                        </tr>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.username}</td>
                                <td>******</td>
                                <td>${user.email}</td>
                                <td>
                                    <c:forEach var="account" items="${user.accounts}">
                                        <div>Account Number: ${account.accountNumber}</div>
                                    </c:forEach>
                                </td>
                                <td class="actions">
                                    <a href="/user/edit/${user.id}">Edit</a> |
                                    <a href="/user/delete/${user.id}" onclick="return confirm('Are you sure?')">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                    <h3>Link Account to User</h3>
                        <form action="/user/linkAccount" method="POST">
                            <label for="userId">User ID:</label>
                            <input type="text" id="userId" name="userId" required>
                            <br>
                            <label for="accountId">Account ID:</label>
                            <input type="text" id="accountId" name="accountId" required>
                            <br>
                            <button type="submit">Link Account</button>
                        </form>
                </c:otherwise>
            </c:choose>
    <a href="/user/post">Add New User</a> |
    <a href="/account/post">Add New Account</a> |
    <a href="/transaction/post">Add New Transaction</a>
    <br/>
    <br/>
    <a href="/account/accounts">Show Accounts</a>   |
    <a href="/transaction/transactions">Show Transactions</a>
  </div>
</body>
</html>
