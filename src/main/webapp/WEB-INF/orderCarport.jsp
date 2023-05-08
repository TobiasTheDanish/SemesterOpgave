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
<body>
 <h1>ORDER CARPORT</h1>

<form method="post" action="ordercarportservlet">
    <label for="width">Width in cm</label> <br>
    <input list="widths" id="width" name="width">
    <datalist id="widths">
        <option value="400">
        <option value="450">
        <option value="500">
        <option value="550">
        <option value="600">
        <option value="650">
        <option value="700">
        <option value="750">
    </datalist> <br>
    <label for="height">Height in cm</label> <br>
    <input list="heights" id="height" name="height"> <br>
    <datalist id="heights">
        <option value="400">
        <option value="450">
        <option value="500">
        <option value="550">
        <option value="600">
        <option value="650">
        <option value="700">
        <option value="750">
    </datalist>
    <label for="length">Length in cm</label> <br>
    <input list="lengths" id="length" name="length">
    <datalist id="lengths">
        <option value="400">
        <option value="450">
        <option value="500">
        <option value="550">
        <option value="600">
        <option value="650">
        <option value="700">
        <option value="750">
    </datalist> <br> <br>

    <input type="submit">
</form>
</body>
</t:pagetemplate>
