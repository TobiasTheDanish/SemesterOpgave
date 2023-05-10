<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <body style="background: #003d76; color: white; text-align: center;">
        <c:if test="${sessionScope.user == null}">
            <p>Du er ikke logget ind. Log ind <a href="../login.jsp">her</a>.</p>
        </c:if>


    </body>

</t:pagetemplate>