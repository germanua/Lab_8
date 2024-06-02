<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Task</title>
</head>
<body>
<c:if test="${addTaskSuccess}">
    <div>Successfully added Task</div>
</c:if>

<form:form action="/teacher/${teacher_id}/course/${course.uid}/addTask" method="post" modelAttribute="task">
    <form:input style="display:none;" type="number" path="id"/>
    <form:label path="name">Name: </form:label> <form:input type="text" path="name"/>
    <form:label path="description">Description: </form:label> <form:input type="text" path="description"/>
    <input type="submit" value="submit"/>
</form:form>

<a href="/teacher/${teacher_id}/course/${course.uid}">Back to Course</a>
</body>
</html>