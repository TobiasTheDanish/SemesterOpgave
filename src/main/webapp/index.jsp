<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>

    <body style="background: #003d76; color: white; text-align: center">
        <div style="min-height: 45rem">
        <c:if test="${sessionScope.user != null}">
            <h1>Velkommen til Johannes Fog</h1>
            <p>Velkommen til vores hjemmeside! Vi er specialiseret i at levere skræddersyede carporte i præcis de mål,
                du ønsker. Vores holdbare og vejrbestandige carporte er bygget til at modstå de hårde danske vejrforhold
                og er tilgængelige i forskellige stilarter og farver, så du kan finde en, der passer perfekt til dit hjem.
                Bestil din specialtilpassede carport i dag og få den perfekte løsning til at beskytte dine køretøjer eller opbevare dine ting.</p>

        </c:if>

        <c:if test="${sessionScope.user == null}">
            <h1>Velkommen til Johannes Fog</h1>

        </c:if>
        </div>
    </body>

</t:pagetemplate>