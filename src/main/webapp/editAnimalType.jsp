<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <div class="col-lg-6">
                    <h1>Add Animal Type</h1>

                    <form:form method="post" action="/animal/type/edit" modelAttribute="animalType">
                        <form:hidden path="id"/>
                        <div class="form-group">
                            <label for="typeName">Animal Type Name</label>
                            <form:input id="input-typeName" path="typeName"  required="required" type="text" class="form-control" placeholder="Animal Type Name"/>
                        </div>
                        <div class="form-spec">
                            <label>Supervisor</label>
                            <form:select class="btn btn-default dropdown-toggle form-control"  path="baseTypeId">
                                <form:options items="${animalTypeList}" itemValue="Id" itemLabel="TypeName"/>
                            </form:select>
                        </div>
                        <button type="submit" class="btn btn-primary">Add</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
