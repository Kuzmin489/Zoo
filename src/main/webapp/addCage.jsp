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
                    <h1>Add Cage</h1>

                    <form:form method="post" action="/cage/add" modelAttribute="cage">
                        <div class="form-group">
                            <label for="input-name">Cage Name</label>
                            <form:input id="input-name"  path="name" type="text" required="required" class="form-control" placeholder="Name"/>
                        </div>

                        <div class="form-group">
                            <label for="input-capacity">Capacity</label>
                            <form:input id="input-capacity" path="capacity" type="number" required="required" class="form-control" placeholder="Capacity"/>
                        </div>
                        <div class="form-spec">
                            <label>Cage Type</label>
                            <form:select class="btn btn-default dropdown-toggle form-control"  path="cageTypeId">
                                <form:options items="${cageTypeList}" itemValue="Id" itemLabel="CageType"/>
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
