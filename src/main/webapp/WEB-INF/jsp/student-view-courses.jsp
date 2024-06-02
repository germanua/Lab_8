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
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${courses}" var="course">
        <tr>
            <td><a href="<c:url value="/student/${student_id}/course/${course.uid}"/>">${course.name}</a></td>
        </tr>
    </c:forEach>
    </tbody>
    <input type="text" placeholder="course code to enroll" id="course_code">
    <button onclick="enroll()">Enroll</button>
</table>
</body>
<script type="text/javascript">
    function enroll() {
        window.location.href = "/student/${student_id}/enroll/" + document.getElementById("course_code").value;
    }
</script>
</html>