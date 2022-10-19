<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Bekijk alle kunstwerken</title>
</head>
<body>
<div id="container">

    <%@include file="nav.jspf"%>

    <p>Er zijn geen kunstwerken</p>

        <c:if test="${hoogstebod.bod>1000000}"><p>Er zijn ook heel dure kunstwerken</p></c:if>

        <p>Het kunstwerk met het hoogste bod is ${hoogstebod.naam} van ${hoogstebod.artiest} met een bedrag van ${hoogstebod.bod}euro.</p>

    <main>
        <h2>Bekijk alle kunstwerken</h2>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Naam kunstwerk</th>
                <th>Artiest</th>
                <th>Jaar</th>
                <th>Hoogste bod</th>
            </thead>
            <tbody>
            <c:if test="$not empty kunstwerken"></c:if>
            <c:forEach var="kunstwerk" items="${kunstwerken}">
                <tr>
                    <td> ${kunstwerk.id}
                    </td>
                    <td>${kunstwerk.naam}
                    </td>
                    <td>${kunstwerk.artiest}
                    </td>
                    <td>${kunstwerk.jaar}
                    </td>
                    <td>${kunstwerk.bod}
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
</body>
</html>
