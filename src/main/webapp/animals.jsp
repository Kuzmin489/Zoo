<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Zoo</title>
    <script src="${pageContext.request.contextPath}/resources/js/table-filter.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/sidebar-menu.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/search-field.css" rel="stylesheet">

    <script type='text/javascript' src="${pageContext.request.contextPath}/webjars/jquery/1.11.1/jquery.min.js"></script>
    <script type='text/javascript' src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link rel='stylesheet' href='${pageContext.request.contextPath}/webjars/bootstrap/3.3.4/css/bootstrap.min.css'>

</head>
<body>

<div id="wrapper">
    <jsp:include page="/menu.jsp"/>

    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1>Animals</h1>
                    <button type="button" class="btn btn-primary pull-right" onclick="window.location.href='/animal/add'">Add animal</button>

                    <c:if test="${!empty animals}">
                        <table class="table table-striped table-list-search">
                            <thead>
                            <tr>
                                <th>Animal Type</th>
                                <th>Animal Name</th>
                                <th>Sex</th>
                                <th>Age</th>
                                <th>Weight</th>
                                <th>Features</th>
                                <th>Las Modified</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${animals}" var="animal">
                                <jsp:useBean id="animal"
                                             class="lv.autentica.domain.animal.Animal" scope="page"/>
                                <tr>
                                    <td>${animal.animalType.typeName}</td>
                                    <td>${animal.name}</td>
                                    <td>${animal.sex}</td>
                                    <td>${animal.age}</td>
                                    <td>${animal.weight}</td>
                                    <td>${animal.features}</td>
                                    <td>${animal.user.login}</td>
                                    <td>
                                        <div class='pull-right'>
                                            <a class="btn btn-success" href="<c:url value='/animal/${animal.id}/edit' />" >Edit</a>
                                            <a class="btn btn-danger" href="<c:url value='/animal/${animal.id}/delete' />" >Delete</a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>

                    <button type="button" class="btn btn-primary" onclick="window.location.href='/animal/add'">Add animal</button>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
