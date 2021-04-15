<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/31/21
  Time: 10:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bochkov RIA</title>
    <link rel="stylesheet" href="styles/style.css">
</head>
<body>
<div id="container">
    <div id="header">Bochkov RIA</div>
    <div id="nav">
        <p><a href="stat.html">Статистика турнира</a></p>
        <p><a href="interview.html">Интервью с главным судьей</a></p>
        <p><a href="ask.html">Конкурсные вопросы</a></p>
    </div>
    <div id="aside">
        <h3>Статистика</h3>
        <p>одиннадцать человек дошли до финала;</p>
        <p>один человек правильно указал в каком стиле писал Диего Веласкес;</p>
        <p>только один человек знал, кто изобрел пароход;</p>
    </div>
    <div id="content">
        <form id="login-form" method="post" action="/Kursach2021/login">
            <input type="email" value="mail" name="mail"> <br>
            <input type="password" value="pass" name="pass">
            <input type="submit" value="qwerty">
        </form>
    </div>
    <div id="footer">&copy; Бочков Максим</div>
</div>
</body>
</html>
