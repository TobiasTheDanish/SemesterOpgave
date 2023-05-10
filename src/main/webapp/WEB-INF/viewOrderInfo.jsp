<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Ordre info
    </jsp:attribute>

    <jsp:attribute name="footer">
            Ordre info
    </jsp:attribute>

    <jsp:body>
        <h4>Ordre #${requestScope.order.id}</h4>
        <table>
            <tr>
                <th>Materiale</th>
                <th>Antal</th>
                <th>Beskrivelse</th>
                <th>Længde</th>
                <th>Bredde</th>
                <th>Højde</th>
            </tr>
            <c:forEach items="${requestScope.order.materials}" var="material">
                <tr>
                    <td>${material.value0.name}</td>
                    <td>${material.value1}</td>
                    <td>${material.value0.description}</td>
                    <td>${material.value0.length} cm</td>
                    <td>${material.value0.width} cm</td>
                    <td>${material.value0.height} cm</td>
                </tr>
            </c:forEach>
        </table>
    </jsp:body>

</t:pagetemplate>
