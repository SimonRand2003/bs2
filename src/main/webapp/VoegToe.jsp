<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 26/02/2022
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Voeg toe</title>
    <link rel = "stylesheet" href="style/stijl.css">
</head>
<body>
<header>
<h1> K3vin knows</h1>
<nav>
    <ul>
        <li><a href = "index.jsp">Home</a></li>
        <li id="hier"><a href = "VoegToe.jsp">Voeg toe</a></li>
        <li><a href = "overzicht.jsp">Overzicht</a></li>
    </ul>
</nav>
</header>
<main>
<form>
    <label >Match:</label><br>
    <input type="text" id="Match"><br>
    <label>Voorspelling:</label><br>
    <input type="text" id="voorspelling" ><br>
    <label >Zekerheid (1-10):</label><br>
    <input type="number" id="Zekerheid"><br>
    <input id = "submitbutton" type="submit" value="Voeg toe"><br>
</form>
</main>
</body>
</html>
