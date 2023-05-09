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
<body style="background: #003d76; text-align: center">
<div class="container p-5" style="min-height: 50rem; background: white">
    <div style="">
    <h1><span>Bestil Carport</span></h1>
    <p><span>Velkommen til vores side for specialbestilling af carporte hos Johannes Fog.
        Her kan du få en carport i præcis de mål, du ønsker. Vi ved, hvor vigtigt det er at have den rigtige størrelse,
        når du skal beskytte dine køretøjer eller opbevare dine ting, og derfor tilbyder vi en unik løsning til dig.
        Vi har gjort det nemt for dig at bestille din carport ved at give dig mulighed for at specificere alle de mål,
        du ønsker, herunder højde, bredde og længde. Uanset om du har brug for en carport til en enkelt bil,
        en større vognpark eller som et ekstra opbevaringsrum, kan vi hjælpe dig med at designe og levere den perfekte løsning.
        </span></p>
    </div>

    <div style="display: flex; justify-content: center">
<form method="post" action="ordercarportservlet">
    <div style="margin: 5px 5px"> <br> <br>
    <label for="width" style="font-size: 24px">Bredde (cm)</label> <br>
    <select id="width" name="width" required="required" style="padding: 2px 40px">
        <option value="400">400</option>
        <option value="450">450</option>
        <option value="500">500</option>
        <option value="550">550</option>
        <option value="600">600</option>
        <option value="650">650</option>
        <option value="700">700</option>
        <option value="750">750</option>
    </select> <br>
    </div>
    <div style="margin: 10px 5px">
    <label for="height" style="font-size: 24px">Højde (cm)</label> <br>
    <select id="height" name="height" required="required" style="padding: 2px 40px">
        <option value="400">400</option>
        <option value="450">450</option>
        <option value="500">500</option>
        <option value="550">550</option>
        <option value="600">600</option>
        <option value="650">650</option>
        <option value="700">700</option>
        <option value="750">750</option>
    </select> <br>
    </div>
    <div style="margin: 10px 5px">
    <label for="length" style="font-size: 24px">Længde (cm)</label> <br>
    <select id="length" name="length" required="required" style="padding: 2px 40px">
        <option value="400">400</option>
        <option value="450">450</option>
        <option value="500">500</option>
        <option value="550">550</option>
        <option value="600">600</option>
        <option value="650">650</option>
        <option value="700">700</option>
        <option value="750">750</option>
    </select> <br>
    </div>
    <div style="display: flex; justify-content: center; margin: 2rem">
    <input type="submit" value="Bestil" style="right: 100%; border-radius: 25px; padding: 8px 25px; background: #003d76; color: white; border: 0">
    </div>

</form>
    </div>
</div>
</body>
</t:pagetemplate>
