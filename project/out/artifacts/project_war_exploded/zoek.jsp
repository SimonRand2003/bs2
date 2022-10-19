<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 12/05/2022
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link rel = "stylesheet" href="style/stijl.css">
</head>
<body>
<header>
    <h1> K3vin knows</h1>
    <%@include file="nav.jspf"%>
</header>
<main>
<table>

    <p>${error}</p>
<c:choose>
    <%--@elvariable id="alleDieren" type="be.ucll.domain.model.Dier"--%>
    <c:when test="${match != null}">
    <thead>
    <tr>
        <th>Match</th>
        <th>Voorspelling</th>
        <th>Zekerheid</th>
    </thead>
    <tbody>
    <tr>
        <td>${match.thuisploeg} - ${match.uitploeg}
        </td>
        <td>${match.voorspelling}
        </td>
        <td>${match.zekerheid}
        </td>
    </tr>
    </tbody>
</table>
    </c:when>
    <c:otherwise>
        <p> De ploeg die je zoekt is niet in onze database</p>
    </c:otherwise>
    </c:choose>
</main>
</body>
</html>
