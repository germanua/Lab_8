<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${course.name}</title>
</head>
<body>

<h1>${course.name}</h1>
<ul>
<c:forEach items="${course.students}" var="student">
    <li>${student}</li>
</c:forEach>
</ul>
<ul>
    <c:forEach items="${course.tasks}" var="task">
        <li>
            <h3>${task.name}</h3>
            <p>${task.description}</p>
            <p>Completed:</p>
            <c:forEach items="${task.completed}" var="id">
                <p>${id}</p>
            </c:forEach>
        </li>
    </c:forEach>
</ul>
<a href="<c:url value="/teacher/${teacher_id}/course/${course.uid}/addTask"/>">Add task</a>
<a href="<c:url value="/teacher/${teacher_id}/courses"/>">All courses</a>
</body>
</html>