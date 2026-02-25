<!--Importar la directiva para que funcione el código java-->
<%@page contentType="UTF-8" import="java.util.*, org.serogr.apiservlet.webapp.cookies.models.*"%>
<!--Obtener el username (Optional) del login, y el producto obtenerlo desde el Request-->
<%
List<Producto> productos = (List<Producto>) request.getAttribute("productos");
Optional<String> username = (Optional<String>) request.getAttribute("username");
String mensajeRequest = (String) request.getAttribute("mensaje");
String mensajeGlobal = (String) getServletContext().getAttribute("mensaje");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Listado de productos</title>
</head>
<body>
    <h1>Listado de productos</h1>
    <% if(username.isPresent()){ %>
    <div>Hola, <%=username.get()%> bienvenido! </div>
    <% } %>
    <table>
        <tr>
            <th>id</th>
            <th>nombre</th>
            <th>tipo</th>
            <% if(username.isPresent()){ %>
            <th>precio</th>
            <th>agregar</th>
            <% } %>
        </tr>
        <% for (Producto p: productos) {%>
        <tr>
            <th><%=p.getId()%></th>
            <th><%=p.getNombre()%></th>
            <th><%=p.getTipo()%></th>
            <% if(username.isPresent()){ %>
            <th><%=p.getPrecio()%></th>
            <th><a href="<%=request.getContextPath()%>/carro/agregar?id=<%=p.getId()%>">Agregar al carro</a></th>
            <% } %>
        </tr>
        <% } %>
    </table>
    <p><%=mensajeGlobal%></p>
    <p><%=mensajeRequest%></p>
</body>
</html>