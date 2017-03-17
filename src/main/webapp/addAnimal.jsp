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
    <br>
    <jsp:include page="/menu.jsp"/>
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-6">
                    <h1>Add Animal</h1>

                    <form:form method="post" action="/animal/add" modelAttribute="animal">
                        <div class="form-group">
                            <label for="name">Animal Name</label>
                            <form:input id="input-typeName" path="name" required="required" type="text" class="form-control" placeholder="Animal Name"/>
                        </div>
                        <div class="form-group">
                            <label for="sex">Animal Sex</label>
                            <form:input id="input-typeName" path="sex" required="required" type="text" class="form-control" placeholder="Animal Sex"/>
                        </div>
                        <div class="form-group">
                            <label for="weight">Animal Age</label>
                            <form:input id="input-typeName" path="age" required="required" type="number" step="0.01" class="form-control" placeholder="Animal Weight"/>
                        </div>
                        <div class="form-group">
                            <label for="weight">Animal Weight</label>
                            <form:input id="input-typeName" path="weight" required="required" type="number" step="0.01" class="form-control" placeholder="Animal Weight"/>
                        </div>
                        <div class="form-group">
                            <label for="name">Animal Features</label>
                            <form:input id="input-typeName" path="features" required="required" type="text" class="form-control" placeholder="Animal Features"/>
                        </div>
                        <div class="form-group">
                            <label for="name">Animal Comments</label>
                            <form:input id="input-typeName" path="comments" required="required" type="text" class="form-control" placeholder="Animal Comments"/>
                        </div>
                        <div class="form-spec">
                            <label>Animal Type</label>
                            <form:select class="btn btn-default dropdown-toggle form-control"  path="typeId">
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
