<%--
  Created by IntelliJ IDEA.
  User: sanderroust
  Date: 08/05/2023
  Time: 09.49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>
<t:pagetemplate>
    <head>
        <title>Order Carport</title>
    </head>
    <body class="bodyBackground">
    <div class="container p-5 mainBox">
        <div style="">
            <h1><span>Rediger carport</span></h1>

        </div>

        <div style="display: flex; justify-content: center">
            <form method="post" action="editorderservlet">
                <div style="margin: 5px 5px"> <br> <br>
                    <label for="width" style="font-size: 24px">Bredde (cm)</label> <br>
                    <select id="width" name="width"  style="padding: 2px 40px">
                        <option value="${requestScope.width}">${requestScope.width} cm</option>
                        <option value="250">250 cm</option>
                        <option value="300">300 cm</option>
                        <option value="350">350 cm</option>
                        <option value="400">400 cm</option>
                        <option value="450">450 cm</option>
                        <option value="500">500 cm</option>
                        <option value="550">550 cm</option>
                        <option value="600">600 cm</option>
                    </select> <br>
                </div>
                <div style="margin: 10px 5px">
                    <label for="height" style="font-size: 24px">Højde (cm)</label> <br>
                    <select id="height" name="height" required="required" style="padding: 2px 40px">
                        <option value="${requestScope.height}">${requestScope.height} cm</option>
                        <option value="200">200 cm</option>
                        <option value="230">230 cm</option>
                        <option value="260">260 cm</option>
                        <option value="290">290 cm</option>
                        <option value="310">310 cm</option>
                        <option value="340">340 cm</option>
                        <option value="360">360 cm</option>
                    </select> <br>
                </div>
                <div style="margin: 10px 5px">
                    <label for="length" style="font-size: 24px">Længde (cm)</label> <br>
                    <select id="length" name="length" required="required" style="padding: 2px 40px">
                        <option value="${requestScope.length}">${requestScope.length} cm</option>
                        <option value="300">300 cm</option>
                        <option value="400">400 cm</option>
                        <option value="450">450 cm</option>
                        <option value="500">500 cm</option>
                        <option value="550">550 cm</option>
                        <option value="600">600 cm</option>
                    </select> <br>
                </div>
                <div style="display: flex; justify-content: center; margin: 2rem">
                    <input type="hidden" value="${requestScope.orderId}" name="orderId">
                    <input type="submit" value="Gem ændring" style="right: 100%; border-radius: 25px; padding: 8px 25px; background: #003d76; color: white; border: 0">
                </div>
                <div style="display: flex; justify-content: center; margin: 2rem">
                    <input type="button" value="Tilbage" onclick="window.history.back();" style="right: 100%; border-radius: 25px; padding: 8px 25px; background: #003d76; color: white; border: 0">
                </div>
            </form>
        </div>
    </div>
    </body>
</t:pagetemplate>
