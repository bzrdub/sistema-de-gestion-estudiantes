<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.miempresa.entidades.Estudiante" %>
<!DOCTYPE html>
<html>
<head>
    <title>Estudiantes</title>
</head>
<body>
    <h1>Estudiantes</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
        </tr>
        <% 
            List<Estudiante> estudiantes = (List<Estudiante>) request.getAttribute("estudiantes");
            for (Estudiante estudiante : estudiantes) { 
        %>
            <tr>
                <td><%= estudiante.getId() %></td>
                <td><%= estudiante.getNombre() %></td>
                <td><%= estudiante.getApellido() %></td>
            </tr>
        <% } %>
    </table>
    <h2>Crear Estudiante</h2>
    <form action="estudiantes" method="post">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required>
        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellido" required>
        <button type="submit">Crear</button>
    </form>
</body>
</html>
