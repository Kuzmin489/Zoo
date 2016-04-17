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
                    <h1>Add Staff Type</h1>

                    <form:form method="post" action="/staff/duty/edit" modelAttribute="staffDuty">
                        <form:hidden id="id" path="id"/>
                        <div class="form-group">
                            <label for="description">Description</label>
                            <form:input id="input-description" required="required"  path="description" type="text" class="form-control" placeholder="description"/>
                        </div>
                        <div class="form-spec">
                            <label>Position</label>
                            <form:select class="btn btn-default dropdown-toggle form-control"  path="positionId">
                                <form:options items="${staffTypeList}" itemValue="Id" itemLabel="TypeName"/>
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
