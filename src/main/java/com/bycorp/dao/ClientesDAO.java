/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bycorp.dao;

import com.bycorp.models.Cliente;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class ClientesDAO {

    Connection con = null;

    public void insertarCliente(Cliente cliente) {
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
                String sql = "INSERT INTO clientes(nombre, apellido, email, telefono)	VALUES ('"+cliente.getNombre()+"', '"+cliente.getApellido()+"', '"+cliente.getEmail()+"', '"+cliente.getTelefono()+"');";
                Statement instruccion = con.createStatement();
                instruccion.execute(sql);
                JOptionPane.showMessageDialog(null,"cliente ingresado");
            }
        } catch (Exception e) {
            System.out.println("exepcion");
        }
    }
}
