<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Create user
    </jsp:attribute>

    <jsp:attribute name="footer">
            Create user
    </jsp:attribute>

    <jsp:body>

        <h3>You can create a new user here</h3>
        <h4>${requestScope.errormessage}</h4>
        <form action="createuserservlet" method="post">
            <label for="email">Email: </label>
            <input type="email" id="email" name="email" value="${requestScope.email}" required/>
            <label for="password">Password: </label>
            <input type="password" id="password" name="password" required/>
            <label for="confirmPassword">Confirm Password: </label>
            <input type="password" id="confirmPassword" name="confirmPassword" required/>
            <input type="submit" value="Create">
        </form>

    </jsp:body>
</t:pagetemplate>