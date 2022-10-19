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
    <p id ="errorZoek">${error}</p>
    <%@include file="zoek.jspf"%>

</header>
<main id="home">
    <article>
        <p>Dag allemaal</p>
        <p>Op deze website kun je al mijn voorspellingen zien van voetbalwedstrijden in de Champions League, Europa
            /Conference league, Premier League, JPL, La Liga, Bundesliga, …</p>
        <p>Ook zullen hier af en toe wat voorspellingen van dartmatchen komen zoals de Premier League of the World cup.
        </p>
    </article>
    <article>
        <c:if  test="${laatstVerwijderdeMatch!=null}">
            <p>Uw laatst verwijderde match was ${laatstVerwijderdeMatch.thuisploeg}-${laatstVerwijderdeMatch.uitploeg} met voorspelling ${laatstVerwijderdeMatch.voorspelling} en zekerheid ${laatstVerwijderdeMatch.zekerheid}</p>
        </c:if>
        <c:if  test="${laatstVerwijderdeMatch==null}">
            <p>U hebt nog geen match verwijderd</p>
        </c:if>
    </article>

</main>
</body>
</html>