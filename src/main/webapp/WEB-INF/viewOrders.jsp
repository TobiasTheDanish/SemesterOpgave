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
                <th>Bredde (cm)</th>
                <th>Højde (cm)</th>
                <th>Længde (cm)</th>
            </tr>

            <c:forEach var="order" items="${sessionScope.orders}">
                <c:if test="${!order.inactive}">

                    <tr style="border-bottom: 1px solid black">
                        <td>${order.id}</td>
                        <td>${order.status}</td>
                        <td>${order.width}</td>
                        <td>${order.height}</td>
                        <td>${order.length}</td>
                        <td>
                            <form action="viewordersservlet" method="post">
                                <input type="hidden" name="vieworder" value="${order.id}">
                                <input type="hidden" name="action" value="Remove">
                                <input type="submit" value="Fjern">
                            </form>
                        </td>
                        <td>
                            <form action="viewordersservlet" method="post">
                                <input type="hidden" name="vieworder" value="${order.id}">
                                <input type="hidden" name="action" value="Edit">
                                <input type="submit" value="Rediger">
                            </form>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>


        </table>


    </div>
    </body>
</t:pagetemplate>
