/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bycorp.dao;

import com.bycorp.models.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class ClientesDAO {
    Connection con = null;
    
    public Connection conectar(){
        String user = "postgres";
        String password = "admin";
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/clientes";

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);

            if (con == null) {
                JOptionPane.showMessageDialog(null,"conexion fallida");
            } else {
                System.out.println("conexion establecida");
            }
        } catch (Exception e) {
            System.out.println("exepcion");
        }
        
        return con;
    }

    public void insertarCliente(Cliente cliente) {

        try {
            conectar();
            String sql = "INSERT INTO clientes(nombre, apellido, email, telefono)	VALUES ('"+cliente.getNombre()+"', '"+cliente.getApellido()+"', '"+cliente.getEmail()+"', '"+cliente.getTelefono()+"');";
            Statement instruccion = con.createStatement();
            instruccion.execute(sql);
            JOptionPane.showMessageDialog(null,"cliente ingresado");

        } catch (SQLException e) {
            System.out.println("Error en el sql");
        }
    }
    
    public List<Cliente> listarClientes() {

        List<Cliente> listado = new ArrayList<>();
        try {
            conectar();
            String sql = "Select * from clientes;";
            Statement instruccion = con.createStatement();
            ResultSet datos = instruccion.executeQuery(sql);
                
            while(datos.next()){
                Cliente c = new Cliente();
                c.setId(datos.getString("id"));
                c.setNombre(datos.getString("nombre"));
                c.setApellido(datos.getString("apellido"));
                c.setEmail(datos.getString("email"));
                c.setTelefono(datos.getString("telefono"));
                listado.add(c);
            }    
        } catch(SQLException e){
            System.out.println("Error sql");
        }
        
        return listado;
    }
    public void eliminarCliente(String id) {

        try {
            conectar();
            String sql = "DELETE FROM clientes WHERE id = "+id+" ;";
            Statement instruccion = con.createStatement();
            instruccion.execute(sql);
            JOptionPane.showMessageDialog(null,"cliente eliminado");

        } catch (SQLException e) {
            System.out.println("Error en el sql");
        }
    }
}
