<%@ page isELIgnored="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Notes</title>
</head>
<body>
<h1>My note list</h1>

<c:forEach var="note" items="${notes}">

    <table border="1">
        <tr>
            <th>
                <b>${note.id}</b>
            </th>
            <th>
                <a href="<c:url value="/note/${note.id}"/>">${note.name}</a>
            </th>

            <th>
                <form action="/delete" method="post">
                    <input type="hidden" value="${note.id}" name="id">
                    <input type="submit" value="delete">
                </form>
            </th>
        </tr>
    </table>
</c:forEach>
<br/>
<h3>Create note:</h3>
<form action="<c:url value="/" />" method="post">
    note name: <input type="text" name="noteName"> <br/>
    note text: <input type="text" name="noteText"> <br/>
    <input type="submit" value="create note">
    <form/>


</body>
</html>
