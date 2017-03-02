<%-- 
    Document   : UserAdd
    Created on : 06/02/2017, 18:35:39
    Author     : marcella.pereira.a1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User - Add</title>
    </head>
    <body>
        <form action="/ScrumYourTeam/System?request=UserAdd" method="POST">
            Name: <br/><input type="text" name="nameUser"><br/>
            Login: <br/><input type="text" name="login"><br/>
            Password: <br/><input type="password" name="password"><br/>
            <br/><input type="submit" value="Save">

        </form>
    </body>
</html>
