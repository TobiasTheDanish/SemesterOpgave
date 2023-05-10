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

    <body class="bodyBackground">
    <div class="mainBox" style="display: flex; align-content: center; flex-direction: column;">
        <h1>Mine ordrer</h1>

        <table>
            <tr style="border-bottom: 2px solid black">
                <th>Ordre-ID</th>
                <th>Status</th>
                <th>Bredde</th>
                <th>Højde</th>
                <th>Længde</th>
            </tr>

            <c:forEach var="order" items="${requestScope.orders}">
                <tr style="border-bottom: 1px solid black">
                    <td>${order.id}</td>
                    <td>${order.status}</td>
                    <td>${order.width}</td>
                    <td>${order.height}</td>
                    <td>${order.length}</td>
                </tr>
            </c:forEach>


        </table>

    </div>
    </body>
</t:pagetemplate>
