<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h2>

    </c:if>

    <table class="table table-hover">
        <thead class="thead-dark">
        <tr>
            <th>Your tasks</th>
            <th><a href="${contextPath}/create" role="button" class="btn btn-block btn-secondary">Add new</a> </th>
        </tr>
            <th>Task</th>
            <th>Description</th>
            <th>Start</th>
            <th>Due</th>
            <th>Status</th>
            <th>#</th>
        </thead>
        <tbody><c:forEach var="task" items="${TASKS}" varStatus="status">
            <tr id="${task.id}" onclick="location.href='${contextPath}/change_status/${task.id}'"><td>${task.name}</td>
                <td>${task.description}</td>
                <td>${task.start.toString()}</td>
                <td>${task.due.toString()}</td>
                <td>${task.status}</td>
                <td><a href="${contextPath}/delete/${task.id}">delete</a></td>
            </tr>
        </c:forEach></tbody>
    </table>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
    /*$("tr").click(function(){
        $.get("${contextPath}/change_status/" + this.id, function(data, status){
            //alert("Data: " + data + "\nStatus: " + status);
        });
    });*/
    /*$.getJSON("/welcome", function(data) {
        var tbody = $('.table tbody');
        tbody.empty();
        $.each(data, function(key, task) {
            tbody.append("<tr><td>" + task.feedback + "</td></tr>");
        });
    });*/
</script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>