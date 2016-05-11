<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="scripts/jsbn.js"></script>
        <script src="scripts/random.js"></script>
        <script src="scripts/hash.js"></script>
        <script src="scripts/rsa.js"></script>
        <script src="scripts/aes.js"></script>
        <script src="scripts/api.js"></script>
        <script src="scripts/cryptico.js"></script>
        <script src="scripts/script.js"></script>
        <script src="scripts/jsencrypt/bin/jsencrypt.min.js"></script>
    </head>

    <body>
        HI !!!
        <form action="controller" id="loginForm" method="post">
            <input name="login" id="login" placeholder="Login" />
            </br>
            <input name="password" id="password" type="password" placeholder="Password" />
            </br>
            <input type="submit" id="submitButton" value="Submit" />
        </form>

    </body>

    </html>