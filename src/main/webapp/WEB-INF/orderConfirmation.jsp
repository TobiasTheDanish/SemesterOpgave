<%--
  Created by IntelliJ IDEA.
  User: basti
  Date: 08-05-2023
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>
<t:pagetemplate>
<head>
    <title>Title</title>
</head>
<body>
<h1>Kvitteringsside</h1>
<h3>Du har bestilt en carport med følgende mål:</h3>
<p>
    Bredde: ${requestScope.order.width}<br>
    Højde: ${requestScope.order.height}<br>
    Længde: ${requestScope.order.length}<br>
</p>
<h3>For yderligere spørgsmål kontakt:</h3>
<p> Tobias Horne Christiansen <br>
    info@fog.dk <br>
    +45 47 16 08 00</p>

</body>
</t:pagetemplate>
