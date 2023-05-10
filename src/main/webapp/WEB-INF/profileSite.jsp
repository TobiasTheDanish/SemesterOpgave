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

<t:pagetemplate>
    <jsp:attribute name="header">
             Profile information
    </jsp:attribute>

    <jsp:attribute name="footer">
            Profile information
    </jsp:attribute>

    <jsp:body>

        <h3>You can update your profile here</h3>
        <h4>${requestScope.errormessage}</h4>
        <form action="createprofileservlet" method="post">
            <label for="firstName">First name: </label>
            <input type="text" id="firstName" name="firstName" value="${sessionScope.user.firstName}" required/>
            <label for="lastName">Last name: </label>
            <input type="text" id="lastName" name="lastName" value="${sessionScope.user.lastName}" required/>
            <label for="phoneNr">Phone number: </label>
            <input type="number" id="phoneNr" name="phoneNr" value="${sessionScope.user.phoneNr}" required/>
            <label for="zipCode">Zip Code: </label>
            <input type="number" id="zipCode" name="zipCode" value="${sessionScope.user.zipCode}" required/>
            <input type="submit" value="Create">
        </form>

    </jsp:body>
</t:pagetemplate>
