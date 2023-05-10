<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html lang="da">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><jsp:invoke fragment="header"/></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light" style="background: #003d76; border-bottom: 2px solid white">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">
                <img src="${pageContext.request.contextPath}/images/fogImage.jpg" width="200px;" class="img-fluid"/>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                    aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup">
                <div class="navbar-nav">

                    <a class="nav-item nav-link" style="color: white" href="${pageContext.request.contextPath}/">${sessionScope.user.username}</a>
                    <c:if test="${sessionScope.user != null && sessionScope.user.role == 0}">
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/adminordersservlet">Ordrer</a>
                    </c:if>
                    <a class="nav-item nav-link" style="color: white" href="${pageContext.request.contextPath}/ordercarportservlet">Bestil Carport</a>
                    <a class="nav-item nav-link" style="color: white" href="${pageContext.request.contextPath}/viewordersservlet">Mine ordrer</a>
                    <a class="nav-item nav-link" style="color: white" href="${pageContext.request.contextPath}/">Page 3</a>
                    <c:if test="${sessionScope.user == null }">
                        <a class="nav-item nav-link" style="background: white; color: #003d76; border-radius: 25px; padding: 8px 15px; margin-left: 3rem" href="${pageContext.request.contextPath}/login.jsp">Log ind</a>
                    </c:if>
                    <c:if test="${sessionScope.user != null }">
                        <a class="nav-item nav-link" style="background: white; color: #003d76; border-radius: 25px; padding: 8px 15px; margin-left: 3rem" href="${pageContext.request.contextPath}/logout">Log ud</a>
                    </c:if>
                </div>
            </div>
        </div>
    </nav>
</header>

<div id="body" class="container mt-4" style="min-height: 400px;">
    <h1><jsp:invoke fragment="header"/></h1>
    <jsp:doBody/>
</div>

<!-- Footer -->
<div class="container mt-3" style="color: white; text-align: center">
    <hr/>
    <div class="row mt-4">
        <div class="col">
            Firskovvej 20 <br/>
            2800 Lyngby
        </div>
        <div class="col">
            <jsp:invoke fragment="footer"/><br/>
            <p>CVR-nr. 16314439</p>
        </div>
        <div class="col">
            Johannes Fog &copy;<br/>
            Byggematerialer siden 1920
        </div>
    </div>

</div>

</div>

<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

</body>
</html>