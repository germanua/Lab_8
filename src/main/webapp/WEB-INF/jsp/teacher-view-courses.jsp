<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>View Courses</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Course name</th>
        <th>Course code</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${courses}" var="course">
        <tr>
            <td><a href="<c:url value="/teacher/${teacher_id}/course/${course.uid}"/>">${course.name}</a></td>
            <td>${course.uid}</td>
        </tr>
    </c:forEach>
    </tbody>
    <a href="<c:url value="addCourse"/>">Add new</a>
</table>
</body>
</html>