<%@page contentType="text/html" pageEncoding="UTF-8" import="org.serogr.apiservlet.webapp.cookies.models.*"%>
<%
Carro carro = (Carro) session.getAttribute("carro");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Carro de compras</title>
</head>
<body>
    <h1>Carro de compras</h1>

    <%if (carro == null || carro.getItems().isEmpty()){%>
    <p> No tienes productos agregados al carro de compra! </p>
    <% } else { %>
<form name="formcarro" action="<%=request.getContextPath()%>/actualizar-carro" method="POST">
    <table>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Precio</th>
            <th>Cantidad</th>
            <th>Total</th>
        </tr>

    <%for(ItemCarro item: carro.getItems()){%>
        <tr>
            <th><%=item.getProducto().getId()%></th>
            <th><%=item.getProducto().getNombre()%></th>
            <th><%=item.getProducto().getPrecio()%></th>
            <th><input type="text" size="4" name="cant_<%=item.getProducto().getId()%>" value="<%=item.getCantidad()%>"></input></th>
            <th><%=item.getImporte()%></th>
            <th><input type="checkbox" value="<%=item.getProducto().getId()%>" name="deleteProductos"></input></th>
        </tr>
    <%}%>

        <tr>
            <td colspan="4" style="text-align: right">Total: </td>
            <td><%=carro.getTotal()%></td>
        </tr>
    </table>
    <%}%>
    <a href="javascript:document.formcarro.submit();">Actualizar</a>
</form>
    <p><a href="<%=request.getContextPath()%>/productos.html">Seguir comprando</a></p>
    <p><a href="<%=request.getContextPath()%>/index.html">Volver a inicio</a></p>
</body>
</html>