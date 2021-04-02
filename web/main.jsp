<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/1/21
  Time: 2:47 PM
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
        <form id="login-form">
            <select name="cars" id="typeOfService">
                <option value="rent">Арендувати квартиру</option>
                <option value="buy">Купити квартиру</option>
            </select>
            <br/>
            <select name="cars" id="city">
                <option value="kiev">Київ</option>
                <option value="vinnitsa">Вінниця</option>
                <option value="odessa">Одеса</option>
                <option value="dnipro">Дніпро</option>
                <option value="lviv">Львів</option>
                <option value="hmel">Хмельницький</option>
                <option value="ternopil">Тернопіль</option>
                <option value="kharkiv">Харків</option>
                <option value="mykolaiv">Миколаїв</option>
                <option value="zhitomir">Житомир</option>
                <option value="zapor">Запоріжжя</option>
                <option value="rivne">Рівне</option>
                <option value="uzhgorod">Ужгород</option>
                <option value="ifrank">Івано-Франківськ</option>
                <option value="cherk">Черкаси</option>
                <option value="chernivtsi">Чернівці</option>
                <option value="lutsk">Луцьк</option>
                <option value="kherson">Херсон</option>
                <option value="poltava">Полтава</option>
                <option value="chernihiv">Чернігів</option>
                <option value="summi">Суми</option>
                <option value="krop">Кропивницький</option>
            </select>
        </form>
    </div>
    <div id="footer">&copy; Бочков Максим</div>
</div>
</body>
</html>
