<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>
<head>
    <link href="css/style.css">
</head>
<t:pagetemplate>
    <body class="bodyBackground">
        <div class="mainBox">
        <h3>Du kan lave en bruger her</h3>
        <h4>${requestScope.errormessage}</h4>
        <form action="createuserservlet" method="post">
            <label for="email">Email: </label> <br>
            <input type="email" id="email" name="email" value="${requestScope.email}" required/> <br> <br>
            <label for="password">Kodeord: </label> <br>
            <input type="password" id="password" name="password" required/> <br> <br>
            <label for="confirmPassword">Bekræft kodeord: </label> <br>
            <input type="password" id="confirmPassword" name="confirmPassword" required/> <br>
            <input type="submit" value="Opret" class="buttonBlue" style="margin-top: 2rem;">
        </form>
        </div>
    </body>
</t:pagetemplate>