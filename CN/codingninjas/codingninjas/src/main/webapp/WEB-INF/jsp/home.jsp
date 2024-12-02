<!DOCTYPE html>
<html>
<head>
    <title>Welcome Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
            background: linear-gradient(120deg, #84fab0, #8fd3f4);
            color: #333;
        }

        .container {
            text-align: center;
            background: #ffffffdd;
            padding: 30px 50px;
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            font-size: 2.5em;
            margin: 0 0 20px;
            color: #4a90e2;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            padding: 12px 24px;
            font-size: 1.1em;
            color: white;
            background-color: #4a90e2;
            border-radius: 6px;
            text-decoration: none;
            transition: background 0.3s;
        }

        a:hover {
            background-color: #357ABD;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome, Saumya!</h1>
        <a href="/signup">Register</a>
    </div>
</body>
</html>
