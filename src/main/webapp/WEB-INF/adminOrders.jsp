<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Ordrer
    </jsp:attribute>

    <jsp:attribute name="footer">
            Ordrer
    </jsp:attribute>

    <jsp:body>
        <table>
            <tr>
                <th></th>
                <th>Ordre ID</th>
                <th>Kunde</th>
                <th>Status</th>
                <th>Bredde</th>
                <th>Højde</th>
                <th>Længde</th>
                <th></th>
            </tr>
            <c:forEach var="order" items="${requestScope.orders}">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.user.username}</td>
                    <td>${order.status}</td>
                    <td>${order.width}</td>
                    <td>${order.height}</td>
                    <td>${order.length}</td>
                    <td>
                        <form action="adminvieworderservlet">
                            <input type="hidden" value="${order.id}" name="orderId">
                            <input type="submit" value="Se info">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </jsp:body>

</t:pagetemplate>
