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
                    <h1>Add Staff</h1>

                    <form:form method="post" action="/staff/add" modelAttribute="worker">
                        <div class="form-group">
                            <label for="input-name">Name</label>
                            <form:input id="input-name" path="name" required="required" type="text" class="form-control" placeholder="Name"/>
                        </div>

                        <div class="form-group">
                            <label for="input-surname">Surname</label>
                            <form:input id="input-surname" path="lastName" required="required" type="text" class="form-control" placeholder="Surname"/>
                        </div>
                        <div class="form-group">
                            <label for="input-ssn">SSN</label>
                            <form:input id="input-ssn" path="ssn" type="number" required="required"  class="form-control" placeholder="SSN"/>
                        </div>
                        <div class="form-group">
                            <label for="input-phone">Phone</label>
                            <form:input id="input-phone" path="phone" required="required" type="number" class="form-control" placeholder="Phone"/>
                        </div>
                        <div class="form-group">
                            <label for="input-salary">Salary</label>
                            <form:input id="input-salary" path="salary"  required="required" type="number" step="0.01" class="form-control" placeholder="Salary"/>
                        </div>
                        <div class="form-group">
                            <label for="input-address">Address</label>
                            <form:input id="input-address" path="address"  required="required" type="text" class="form-control" placeholder="Address"/>
                        </div>
                        <div class="form-spec">
                            <label>Position</label>
                            <form:select class="btn btn-default dropdown-toggle form-control"  path="staffTypeId">
                                <form:options items="${staffTypes}" itemValue="Id" itemLabel="TypeName"/>
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
