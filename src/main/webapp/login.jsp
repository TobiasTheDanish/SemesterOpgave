<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <body style="background: #003d76; color: black; text-align: center">

        <div class="container" style="background: white; min-height: 30rem; margin-top: 5rem; padding: 2rem">
        <h3>Log ind her</h3>

        <form action="login" method="post">
            <label for="email">Email: </label> <br>
            <input type="email" id="email" name="email"/> <br> <br>
            <label for="password">Password: </label> <br>
            <input type="password" id="password" name="password"/> <br>
            <input type="submit"  value="Log in" style="margin: 2rem; border-radius: 25px; padding: 8px 25px; background: #003d76; color: white; border: 0;"/>
        </form>
        <p style="margin-top: 6rem;">Har du <span style="text-decoration: 1px underline">IKKE</span> en bruger? Klik <a href="createUser.jsp" style="background: #003d76; color: white; border-radius: 25px; padding: 5px 12px; text-decoration: none">her</a> for at oprette dig.</p>
        </div>
    </body>
</t:pagetemplate>