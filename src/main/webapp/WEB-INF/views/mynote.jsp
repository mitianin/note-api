<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Note</title>
</head>
<body>

<h1>Note</h1>
<%@page import="org.example.servlet.service.DataBaseNoteService" %>
<%@ page import="org.example.servlet.dto.NotesResponse" %>
<%@ page import="org.example.servlet.dto.Note" %>

<%
    String uri = request.getRequestURI();
    int id = Integer.parseInt(uri.substring(uri.lastIndexOf("/") + 1).trim());
%>

<p><%="Info about NOTE with id " + "[" + id + "]"%>
</p>

<p><%
    NotesResponse nr = new DataBaseNoteService().getById(id);
    Note n = new Note();
    if (!nr.getNoteList().isEmpty()) {
        n = nr.getNoteList().get(0);
    }
%></p>

<p><%=n.getName()%>
</p>
<p><%=n.getTxt()%>
</p>
<p><%=nr.getStatus()%>
</p>


</body>
</html>
