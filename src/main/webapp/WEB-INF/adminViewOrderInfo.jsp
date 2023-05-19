<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <fmt:setLocale value="da_DK"/>
    <body style="background: #003d76; color: black; text-align: center">
        <div style="display: flex; align-content: center; flex-direction: column; color: black; background: white; padding: 2rem; min-height: 30rem">
        <h4>Ordre #${requestScope.order.id}</h4>
        <table>
            <tr style="border-bottom: 2px solid black">
                <th>Bruger</th>
                <th>Status</th>
                <th>Højde</th>
                <th>Bredde</th>
                <th>Længde</th>
            </tr>
            <tr style="border-bottom: 1px solid black">
                <td>${requestScope.order.user.firstName} ${requestScope.order.user.lastName}</td>
                <td>${requestScope.order.status}</td>
                <td>${requestScope.order.height} cm</td>
                <td>${requestScope.order.width} cm</td>
                <td>${requestScope.order.length} cm</td>
            </tr>
        </table>
            <div class="spacer"></div>
        <table>
            <tr style="border-bottom: 2px solid black">
                <th>Materiale</th>
                <th>Antal</th>
                <th>Pris</th>
                <th>Beskrivelse</th>
                <th>Længde</th>
                <th>Bredde</th>
                <th>Højde</th>
            </tr>
            <c:forEach items="${requestScope.order.materials}" var="material">
                <tr style="border-bottom: 1px solid black">
                    <td>${material.value0.name}</td>
                    <td>${material.value1}</td>
                    <td><fmt:formatNumber type="currency" maxFractionDigits="2">${material.value0.pricePrMeter * material.value1 * (material.value0.length / 100)}</fmt:formatNumber></td>
                    <td>${material.value0.description}</td>
                    <td>${material.value0.length} cm</td>
                    <td>${material.value0.width} cm</td>
                    <td>${material.value0.height} cm</td>
                </tr>
            </c:forEach>
        </table>
        </div>
    </body>

</t:pagetemplate>
