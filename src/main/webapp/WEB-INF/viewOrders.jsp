<%--
  Created by IntelliJ IDEA.
  User: sanderroust
  Date: 09/05/2023
  Time: 11.13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>
<t:pagetemplate>
<head>
    <title>Title</title>
    <h1>Mine ordrer</h1>
</head>
<body>
    <c:forEach var="order" items="${requestScope.orders}">
        <h5>Ordre: ${order.id}</h5>
        <p class="card-subtitle text-secondary">Status: ${order.status}</p>

    </c:forEach>
</body>
</t:pagetemplate>
