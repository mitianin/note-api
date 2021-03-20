<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Note</title>
</head>
<body>

<h1>Note</h1>

<p><c:out value="${note.name}" /></p>
<p><c:out value="${note.txt}" /></p>

</body>
</html>
