<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="sidebar-wrapper">
    <ul class="sidebar-nav">
        <li class="sidebar-brand">
            <a href="#">
                ZOO
            </a>
        </li>
        <li>
            <a href="/staff">Staff</a>

        </li>
        <li>
            <a href="/staff/types">Staff Types</a>

        </li>
        <li>
            <a href="/staff/duties">Staff Duties</a>

        </li>
        <li>
            <a href="/animals">Animals</a>

        </li>
        <li>
            <a href="/animals/types">Animal Types</a>

        </li>
        <li>
            <a href="/animals/feeding">Animal Feeding</a>
        </li>
        <li>
            <a href="/cage">Cages</a>
        </li>
        <li>
            <a href="/cage/type">Cage Types</a>
        </li>
        <li>
            <a href="/cage/clean">Cages Cleaning</a>
        </li>
        <li>
            <a href="/cage/load">Cages Loading</a>
        </li>
    </ul>
</div>

<c:if test="${!empty error.getMessage()}">
    <div class="alert alert-danger">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong>Error!</strong> <c:out value="${error.getMessage()}"/>
    </div>
</c:if>