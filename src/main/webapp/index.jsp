<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Productos"%>
<%
List<Productos> productos = (List<Productos>)request.getAttribute("productos");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <Style>
            #contenedor{
                text-align: center;
                
            }
            table{
                margin: 0 auto;
            }
        </style>>
        
    </head>
    <body>
        <div id="contenedor">
    
            <fieldset>
                <h3>SEGUNDO PARCIAL TEM-742 <br>
                NOMBRE:JHONNY MAMANI QUISPE <br>
                CARNET: 10067846 L-p
                </h3>  
            </fieldset>
      
        <h1>GESTION DE PRODUCTOS</h1>
        
        <a href="inicio_servlet?action=add">NUEVO PRODUCTO</a>
         <br><br><br>
         <table border="1">
            <tr>
                <th>ID</th>
                <th>DESCRIPCION</th>
                <th>CANTIDAD</th>
                <th>PRECIO</th>
                <th>CATEGORIA</th>
                <th></th>
                <th></th>
            </tr>
           
            <c:forEach var="item" items="${productos}">
            
            <tr>
                <td>${item.id}</td>
                <td>${item.descripcion}</td>
                <td>${item.cantidad}</td>
                <td>${item.precio}</td>
                <td>${item.categoria}</td>
                <td><a href="inicio_servlet?action=edit&id=${item.id}">EDITAR</a></td>
                <td><a href="inicio_servlet?action=delete&id=${item.id}" onclick="return(confirm('esta deguro de eliminar '))">ELIMINAR</a></td>
            </tr>
          </c:forEach>
        </table>
        </div>
    </body>
</html>
