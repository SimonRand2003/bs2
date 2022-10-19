<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 07/03/2022
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Voeg toe</title>
    <link rel = "stylesheet" href="style/stijl.css">
</head>
<body>
<header>
    <h1> K3vin knows</h1>
    <%@include file="nav.jspf"%>
    <%@include file="zoek.jspf"%>
</header>
<main>
    <c:if test="${not empty errors}">
        <div id="error" class="alert alert-danger">
            <ul id="errorList">
                <c:forEach items="${errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>





    <form method="post" action="Controller?command=add"  novalidate>
        <p class="form-group">
            <label class="control-label" for="thuisploeg">Thuisploeg:</label><br>
            <input id ="thuisploeg" type="text" name="thuisploeg" value="${thuisploegPreviousValue}" class="${thuisploegHasErrors? 'error' : ''}" required><br>

        </p>

        <p class="form-group">
            <label class="control-label" for="uitploeg">Uitploeg:</label><br>
            <input id ="uitploeg" type="text" name="uitploeg" value="${uitploegPreviousValue}" class="${uitploegHasErrors? 'error' : ''}" required><br>

        </p>
        <p class="form-group">
            <label class="control-label" for="1">1:</label>
            <input id ="1" type="radio" name="voorspelling" value="1" class="${voorspellingHasErrors? 'error' : ''}" required>
            <label class="control-label" for="x">x:</label>
            <input id ="x" type="radio" name="voorspelling" value="x" class="${voorspellingHasErrors? 'error' : ''}">
            <label class="control-label" for="2">2:</label>
            <input id ="2" type="radio" name="voorspelling" value="2" class="${voorspellingHasErrors? 'error' : ''}">

        </p>


        <p class="form-group">
            <label for="zekerheid">Zekerheid:</label><br>
            <input
                    id="zekerheid" name="zekerheid" type="number"
                    max="10" min="0" value="${zekerheidPreviousValue}" class="${zekerheidHasErrors? 'error' : ''}"><br>
        </p>

        <p>
            <input id="submitForm" type="submit" value="Verstuur">
        </p>
    </form>
    <c:if test="${thuisploegPreviousValue ==null&&laatsteMatch!=null}">
        <form method=“POST" action="Controller?command=pasAan"><input class="LaatsteItemAanpassen" type="submit"  action=Controller?command=pasAan value="Wijzig reeds toegevoegde item(${laatsteMatch.thuisploeg}-${laatsteMatch.uitploeg})" onclick="return confirm('Ben je zeker dat je het laatste item wilt aanpassen?')"><input type="hidden" name="command" value="pasAan">
            <input  type="hidden" value="${laatsteMatch.thuisploeg}" name="thuisploeg"></form>
        </c:if>
</main>
</body>
</html>

