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
                    <h1>Cage Cleaning</h1>

                    <c:if test="${!empty cages}">
                        <table class="table table-striped table-list-search">
                            <thead>
                            <tr>
                                <th>Cage Type</th>
                                <th>Cage</th>
                                <th>Worker Position</th>
                                <th>Worker Name</th>
                                <th>Cleaning Time</th>
                                <th>Last Modified</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${cages}" var="cage">
                                <jsp:useBean id="cage"
                                             class="lv.autentica.domain.cages.CageClean" scope="page"/>
                                <tr>
                                    <td>${cage.cage.cageType.cageType}</td>
                                    <td>${cage.cage.name}</td>
                                    <td>${cage.worker.position.typeName}</td>
                                    <td>${cage.worker.firstName} ${cage.worker.lastName}</td>
                                    <td>${cage.cleanTime}</td>
                                    <td>${cage.user.login}</td>
                                    <td>
                                        <div class='pull-right'>
                                            <a class="btn btn-success" href="<c:url value='/cage/clean/${cage.id}/edit' />" >Edit</a>
                                            <a class="btn btn-danger" href="<c:url value='/cage/clean/${cage.id}/delete' />" >Delete</a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>

                    <button type="button" class="btn btn-primary" onclick="window.location.href='/cage/clean/add'">Add Cage Cleaning</button>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
