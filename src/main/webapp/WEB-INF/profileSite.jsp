<%--
  Created by IntelliJ IDEA.
  User: tobiasberg
  Date: 08/05/2023
  Time: 10.29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>
<head>
    <link href="/css/style.css">
</head>

<t:pagetemplate>

    <body class="bodyBackground">
        <div class="mainBox">
        <h4>${requestScope.errormessage}</h4>
        <h3>Du kan opdatere din profil her</h3>
        <form action="createprofileservlet" method="post">
            <label for="firstName">Fornavn: </label> <br>
            <input type="text" id="firstName" name="firstName" value="${sessionScope.user.firstName}" required/> <br>
            <label for="lastName">Efternavn: </label> <br>
            <input type="text" id="lastName" name="lastName" value="${sessionScope.user.lastName}" required/> <br>
            <label for="phoneNr">Telefonnummer: </label> <br>
            <input type="number" id="phoneNr" name="phoneNr" value="${sessionScope.user.phoneNr}" required/> <br>
            <label for="zipCode">Postnummer: </label> <br>
            <input type="number" id="zipCode" name="zipCode" value="${sessionScope.user.zipCode}" required/> <br>
            <input type="submit" value="Opdatér" class="buttonBlue" style="margin-top: 2rem;">
        </form>
        </div>
    </body>
</t:pagetemplate>
