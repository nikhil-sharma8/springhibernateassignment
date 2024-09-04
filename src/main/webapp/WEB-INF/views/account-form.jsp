<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="form"%>
<html>
<head>
    <title>Account Form</title>
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

            a {
                text-decoration: none;
                color: #4CAF50;
                font-size: 16px;
                margin-top: 10px;
                display: inline-block;
            }

            a:hover {
                color: #45a049;
            }

            .back-link {
                        margin-top: 20px;
                        display: flex;
                        justify-content: center;
            }
        </style>
</head>
<body>
<div class="container">
    <h2>Account Form</h2>
    <form action="addAccount" method="post">
        <label for="balance">Balance:</label>
        <input type="number" id="balance" name="balance" value="${account.balance}" required /><br/>
        <button type="submit">Save</button>
    </form>
    <a href="/account/accounts">Back to Account List</a>
    </div>
</body>
</html>
