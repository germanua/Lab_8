<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${course.name}</title>
</head>
<body>

<h1>${course.name}</h1>
<ul>
    <c:forEach items="${course.tasks}" var="task">
        <li>
            <h3>${task.name}</h3>
            <p>${task.description}</p>
            <p>Completed:</p>
            <c:if test="${task.completed.contains(Integer.parseInt(student_id))}">
                <p>Done</p>
            </c:if>
            <c:if test="${!task.completed.contains(Integer.parseInt(student_id))}">
                <p><a href="/student/${student_id}/course/${course.uid}/tasks/${task.id}/complete">Complete</a></p>
            </c:if>
        </li>
    </c:forEach>
</ul>
<a href="<c:url value="/student/${student_id}/courses"/>">All courses</a>
</body>
</html>