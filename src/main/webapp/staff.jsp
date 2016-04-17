<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Zoo</title>
    <script src="${pageContext.request.contextPath}/resources/js/table-filter.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/sidebar-menu.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/search-field.css" rel="stylesheet">

    <script type='text/javascript' src="webjars/jquery/1.11.1/jquery.min.js"></script>
    <script type='text/javascript' src="webjars/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link rel='stylesheet' href='webjars/bootstrap/3.3.4/css/bootstrap.min.css'>

</head>
<body>

<div id="wrapper">
    <jsp:include page="/menu.jsp"/>

    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1>Staff</h1>

                    <c:if test="${!empty staff}">
                        <table class="table table-striped table-list-search">
                            <thead>
                            <tr>
                                <th>Firstname</th>
                                <th>Lastname</th>
                                <th>SSN</th>
                                <th>Position</th>
                                <th>Phone</th>
                                <th>Salary</th>
                                <th>Last Modified</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${staff}" var="worker">
                                <jsp:useBean id="worker"
                                             class="lv.autentica.domain.staff.Staff" scope="page"/>
                                <tr>
                                    <td>${worker.firstName}</td>
                                    <td>${worker.lastName}</td>
                                    <td>${worker.ssnNumber}</td>
                                    <td>${worker.position.typeName}</td>
                                    <td>${worker.phone}</td>
                                    <td>${worker.salary}</td>
                                    <td>${worker.user.login}</td>
                                    <td>
                                        <div class='pull-right'>
                                            <a class="btn btn-success" href="<c:url value='/staff/${worker.id}/edit' />" >Edit</a>
                                            <a class="btn btn-danger" href="<c:url value='/staff/${worker.id}/delete' />" >Delete</a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>

                    <button type="button" class="btn btn-primary" onclick="window.location.href='/staff/add'">Add Staff</button>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
