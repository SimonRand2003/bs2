<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.Match" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 07/03/2022
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Overzicht</title>
    <link rel = "stylesheet" href="style/stijl.css">
</head>
<body>
<header>
    <h1> K3vin knows</h1>
    <%@include file="nav.jspf"%>
    <%@include file="zoek.jspf"%>
</header>
<main>
    <c:choose>
        <%--@elvariable id="alleDieren" type="be.ucll.domain.model.Dier"--%>
        <c:when test="${not empty matches}">
            <h2>Bekijk alle matchen</h2>

            <table>
                <thead>
                <tr>
                    <th>Match</th>
                    <th>Voorspelling</th>
                    <th>Zekerheid</th>

                </thead>
                <tbody>
                <c:if test="$not empty matches"></c:if>
                <c:forEach var="match" items="${matches}">
                    <tr>
                        <td class ="match">${match.thuisploeg} - ${match.uitploeg}
                        </td>
                        <td>${match.voorspelling}
                        </td>
                        <td>${match.zekerheid}
                        </td>
                        <td><form method=“POST" action="Controller?command=verwijder"><input class="verwijder" onclick="return confirm('Ben je zeker dat je deze match wilt verwijderen?')"type="submit"  action=Controller?command=verwijder value="Verwijder match"><input type="hidden" name="command" value="verwijder">
                        <input  type="hidden" value="${match.thuisploeg}" name="thuisploeg"></form></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table
            <p>De meest zekere match is ${meestZekere}</p>
        </c:when>
        <c:otherwise>
            <p>Er zijn geen matchen </p>
        </c:otherwise>
    </c:choose>






</main>
</body>
</html>

