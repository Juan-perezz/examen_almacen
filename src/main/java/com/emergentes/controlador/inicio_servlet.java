
package com.emergentes.controlador;

import com.emergentes.dao.Productos_dao;
import com.emergentes.dao.Productos_dao_implementacion;
import com.emergentes.modelo.Productos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author USER
 */
@WebServlet(name = "inicio_servlet", urlPatterns = {"/inicio_servlet"})
public class inicio_servlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            int id;
            Productos p = new Productos();
            Productos_dao dao = new Productos_dao_implementacion();

            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    request.setAttribute("productos", p);
                    request.getRequestDispatcher("frmproductos.jsp").forward(request, response);
                    break;

                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    p = dao.getByid(id);
                    request.setAttribute("productos", p);
                    request.getRequestDispatcher("frmproductos.jsp").forward(request, response);

                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("inicio_servlet");

                    break;
                case "view":
                    List<Productos> lista = dao.getAll();
                    request.setAttribute("productos", lista);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            System.out.println("error :" + e.getMessage());
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         Productos_dao dao = new Productos_dao_implementacion();
        
        //variables 
        int id = Integer.parseInt(request.getParameter("id"));
        String descripcion =request.getParameter("descripcion");
        int cantidad = Integer.parseInt( request.getParameter("cantidad"));
        float precio = Float.parseFloat(request.getParameter("precio"));
        String categoria = request.getParameter("categoria");
        
        
        //incalpular en un objeto
        Productos p =new Productos();
        
        p.setId(id);
        p.setDescripcion(descripcion);
        p.setCantidad(cantidad);
        p.setPrecio(precio);
        p.setCategoria(categoria);
        
        
        try {
        if(id == 0){
            
                //insertar registro
                dao.insert(p);
            
        
        }else{
            // actualizar registro
            dao.update(p);
        
        }
       } catch (Exception ex) {
               System.out.println("error al guardar datos" + ex.getMessage());
       } 
        response.sendRedirect("inicio_servlet");
    }

    }


