
package com.emergentes.dao;

import com.emergentes.modelo.Productos;
import com.emergentes.utiles.Conexion_bd;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Productos_dao_implementacion  extends Conexion_bd implements Productos_dao{

    @Override
    public void insert(Productos productos) throws Exception {
         try {
            
        this.conectar();
        String sql = "insert into productos(descripcion,cantidad,precio,categoria) values (?,?,?,?)";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setString(1, productos.getDescripcion());
        ps.setInt(2, productos.getCantidad());
        ps.setFloat(3, productos.getPrecio());
        ps.setString(4, productos.getCategoria());
        
        // ejecutar la sentencia
        ps.executeUpdate();
        
        } catch (SQLException e) {
            throw e;
        }finally{
        this.desconectar();
        }
        
    }

    @Override
    public void update(Productos productos) throws Exception {
         try {
        this.conectar();
        String sql = "UPDATE productos SET descripcion = ?, cantidad =?, precio = ?, categoria=? WHERE id = ? ";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setString(1, productos.getDescripcion());
        ps.setInt(2, productos.getCantidad());
        ps.setFloat(3, productos.getPrecio());
        ps.setString(4, productos.getCategoria());
        ps.setInt(5, productos.getId());
        
        ps.executeUpdate();
        
        } catch (SQLException e) {
            throw e;
        }finally{
        this.desconectar();
        }
        
    }

    @Override
    public void delete(int id) throws Exception {
         try {
        this.conectar();
        String sql = "DELETE FROM productos WHERE id = ?";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setInt(1, id);
        
        //ejecutar query
        ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }finally{
        this.desconectar();
        }
        
    }

    @Override
    public Productos getByid(int id) throws Exception {
        
         Productos p = new Productos();
        try {
       this.conectar();
       String sql = "select * from productos where id = ?";
       PreparedStatement ps = this.conn.prepareStatement(sql);
       ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
       
        
        if(rs.next()){
          p.setId(rs.getInt("id"));
          p.setDescripcion(rs.getString("descripcion"));
          p.setCantidad(rs.getInt("cantidad"));
          p.setPrecio(rs.getFloat("precio"));
          p.setCategoria(rs.getString("categoria"));
          
        }
        
        } catch (SQLException e) {
            throw e;
        }finally{
        this.desconectar();
        }
    
       
       return p;
        
    }

    @Override
    public List<Productos> getAll() throws Exception {
        
         ArrayList<Productos> lista = new ArrayList<Productos>();
         try {
        this.conectar();
        String sql = "select * from productos";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
           Productos p = new Productos();
           p.setId(rs.getInt("id"));
           p.setDescripcion(rs.getString("descripcion"));
           p.setCantidad(rs.getInt("cantidad"));
           p.setPrecio(rs.getFloat("precio"));
           p.setCategoria(rs.getString("categoria"));
           
           
           lista.add(p);
        }
        rs.close();
        ps.close();
        
        } catch (SQLException e) {
            throw e;
        }finally{
        this.desconectar();
        }
    
        return lista;
    }
    
        
    
    
}
