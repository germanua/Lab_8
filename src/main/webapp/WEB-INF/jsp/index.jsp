<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>Home</h1>
<div>
    Student?
    <label>
        <input id="student-id" type="number" placeholder="student id">
    </label>
    <button onclick="studentLogin()">Log in</button>
</div>
<div>
    Teacher?
    <label>
        <input id="teacher-id" type="number" placeholder="teacher id">
    </label>
    <button onclick="teacherLogin()">Log in</button>
</div>
<script type="text/javascript">
    function studentLogin() {
        window.location.href = "/student/" + document.getElementById("student-id").value + "/courses";
    }

    function teacherLogin() {
        window.location.href = "/teacher/" + document.getElementById("teacher-id").value + "/courses";
    }
</script>
</body>
</html>