/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import appfutbol.ConexionBD;
import appfutbol.Estadio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Asignatura: Bases de datos
 * Grado en Ingeniería Telemática
 * Universidad de Jaén - Escuela Politécnica Superior de Linares
 * 
 * @version 01/12/2016
 * 
 * @author Pablo Castillo Segura
 */
public class DaoEstadio {
    ConexionBD conn;
    
    /**
     * Método para devolver true si se encuentra el Estadio mediante su identificador
     * 
     * @param id Identificador del estadio a buscar
     * @return si se encuentra el Estadio en la tabla
     */
    public boolean BuscarEstadio(int id){
        //Instrucción que devuelve la tupla del Estadio con identificador
        ResultSet rs= conn.getInstancia().ejecutarConsulta("SELECT * FROM test.ESTADIO WHERE id = "+ id +";");		
        try {
            //Devuelve true si existe es tupla
            return rs.first();
        } catch (SQLException e) { }
        //Por defecto devuelve false
        return false;
    }
    
    /**
     * Método para introducir Estadio en la base de datos
     * 
     * @param estadio Objeto Estadio del que obtendremos los datos que se almacenarán en la base de datos
     */
    public void CrearEstadio(Estadio estadio){
        //Si existe el id del Estadio
        if(!BuscarEstadio(estadio.getIdestadio())){
            //Lo introduzco en la tabla ESTADIO
            String cadena= "INSERT INTO test.ESTADIO (id,direccion,ciudad,capacidad) VALUES ("
                    +estadio.getIdestadio()+",'"+estadio.getDireccion()+"','"+estadio.getCiudad()+"',"
                    +estadio.getCapacidad()+") ;";
            conn.getInstancia().ejecutar(cadena);
            
            JOptionPane.showMessageDialog(null,"Estadio aceptado", "Información", JOptionPane.INFORMATION_MESSAGE); 
        }else{
            JOptionPane.showMessageDialog(null,"Ya existe el estadio", "Información", JOptionPane.INFORMATION_MESSAGE); 
        }
    }
    
    
    /**
     * Método que devuelve la cantidad de tuplas de la tabla ESTADIO
     * 
     * @return Número de tuplas de la tabla ESTADIO
     */
    public int nEstadios(){
        try {
            //Instrucción que devuelve la cuenta
            ResultSet rs= conn.getInstancia().ejecutarConsulta("SELECT count(*) FROM test.ESTADIO;");	
            while (rs.next()) {
                //Devuelve el número de tuplas
                return rs.getInt(1);
            }
        } catch (Exception e) { }
        return 0;
    }
    
    /**
     * Método que devuelve una lista de Estadios de la base de datos
     * 
     * @return ArrayList de Estadios 
     */
    public ArrayList<Estadio> listarEstadios(){
        //Creo lista de estadios
        ArrayList<Estadio> listaestadios = new ArrayList<Estadio>();
        
        //Si la tabla de ESTADIOS no está vacía
        if(nEstadios()!=0){
            //Instrucción que devuelve todas las tuplas de la tabla ESTADIO
            ResultSet rs= conn.getInstancia().ejecutarConsulta("SELECT id, direccion, ciudad, capacidad FROM test.ESTADIO ;");	
            try{
                //Cada tupla
                while (rs.next()) { 
                    //Inicio Estadio mediante constructor
                    Estadio estadio = new Estadio(rs.getInt (1),rs.getString (2),rs.getString (3),rs.getInt (4));
                    //Añado estadio a la lista
                    listaestadios.add(estadio);
                }
            }catch (SQLException e){ }
        }else{
            JOptionPane.showMessageDialog(null,"Introduzca algún estadio", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Devuelve la lista de Estadios
        return listaestadios;
    }
    
}
