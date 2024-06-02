<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add Course</title>
    </head>
    <body>
        <c:if test="${addCourseSuccess}">
            <div>Successfully added Course with name: ${savedCourse.name}</div>
        </c:if>

        <form:form action="/teacher/${teacher_id}/addCourse" method="post" modelAttribute="course">
            <form:label path="name">Name: </form:label> <form:input type="text" path="name"/>
            <input type="submit" value="submit"/>
        </form:form>

        <a href="<c:url value="courses"/>">Back to Courses</a>
    </body>
</html>