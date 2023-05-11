<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>

    <body style="background: #003d76; color: black; text-align: center">
    <div style="display: flex; align-content: center; flex-direction: column; color: black; background: white; padding: 2rem; min-height: 30rem">

    <h1>Alle ordrer</h1>
        <p>${requestScope.message}</p>
        <table>
            <tr style="border-bottom: 2px solid black">
                <th>Fjern</th>
                <th>Ordre-ID</th>
                <th>Kunde</th>
                <th>Status</th>
                <th>Bredde</th>
                <th>Højde</th>
                <th>Længde</th>
                <th></th>
            </tr>
            <c:forEach var="order" items="${sessionScope.orders}">
                <c:if test="${!order.inactive}">
                <tr style="border-bottom: 1px solid black">
                    <td>
                        <form action="admindeleteorderservlet" method="post" id="removeOrder${order.id}">
                            <input type="hidden" value="${order.id}" name="orderId">
                            <input type="button" value="&#10060;" style="border: none; background: none" onclick="confirmPopUp(${order.id})">
                        </form>
                    </td>
                    <td>${order.id}</td>
                    <td>${order.user.username}</td>
                    <td>${order.status}</td>
                    <td>${order.width}</td>
                    <td>${order.height}</td>
                    <td>${order.length}</td>
                    <td>
                        <form action="adminvieworderservlet">
                            <input type="hidden" value="${order.id}" name="orderId">
                            <input type="submit" value="Se info" style="background: #003d76; border: none; color: white; border-radius: 25px; padding: 5px 12px;">
                        </form>
                    </td>
                </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
    </body>

</t:pagetemplate>
