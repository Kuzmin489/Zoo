<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>The Phones</title>
    <link href="${pageContext.request.contextPath}/resources/css/sidebar-menu.css" rel="stylesheet">
    <script type='text/javascript' src="webjars/jquery/1.11.1/jquery.min.js"></script>
    <script type='text/javascript' src="webjars/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link rel='stylesheet' href='webjars/bootstrap/3.3.4/css/bootstrap.min.css'>
</head>
<c:if test="${!empty worker}">
    <div class="alert alert-danger">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong>Error!</strong> <c:out value="${worker}"/>
    </div>
</c:if>
<body>

    <div id="wrapper">
        <jsp:include page="/menu.jsp"/>

        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6">
                        <h1>MAIN PAGE</h1>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>

</html>
