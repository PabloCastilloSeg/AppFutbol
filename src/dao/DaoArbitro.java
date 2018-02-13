/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import appfutbol.ConexionBD;
import appfutbol.Personas;
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
public class DaoArbitro {
    ConexionBD conn;
    
    /**
     * Método para devolver true si se encuentra el Árbitro mediante su identificador
     * 
     * @param id Identificador del árbitro a buscar
     * @return si se encuentra el árbitro en la tabla
     */
    public boolean BuscarArbitro(int id){
        //Instrucción que devuelve la tupla del árbitro que contiene el id
        ResultSet rs= conn.getInstancia().ejecutarConsulta("SELECT * FROM test.ARBITRO WHERE id = "+ id +";");		
        try {
            //Devuelve true si existe
            return rs.first();
        } catch (SQLException e) { }
        //Devuelve false por defecto
        return false;
    }
    
    /**
     * Método que introduce el árbitro en la base de datos
     * 
     * @param arbitro Objeto Árbitro del que obtendremos los datos que se almacenarán en la base de datos
     */
    public void CrearArbitro(Personas.Arbitro arbitro){
        //Si el árbitro existe en la base de datos
        if(BuscarArbitro(arbitro.getId())){
            JOptionPane.showMessageDialog(null,"Ya existe el árbitro", "Información", JOptionPane.INFORMATION_MESSAGE); 
        }else{
            //Instrucción que introduce el árbitro en la tabla ARBITRO
            String cadena= "INSERT INTO test.ARBITRO (id,nombre,email,tlf,tipo) VALUES ("+arbitro.getId()+",'"
                    +arbitro.getNombre()+"','"+arbitro.getEmail()+"','"+arbitro.getTlf()+"','"+arbitro.getTipo()+"') ;";
            conn.getInstancia().ejecutar(cadena);
            
            JOptionPane.showMessageDialog(null,"Árbitro aceptado", "Información", JOptionPane.INFORMATION_MESSAGE); 
        }
    }
    
    /**
     * Método que borra un árbitro de la base de datos
     * 
     * @param idarb Identificador del árbitro a borrar
     */
    public void BajaArbitro(int idarb){
        //Si el árbitro existe en la base de datos
        if(BuscarArbitro(idarb)){
            //Instrucción que borra todas las tuplas de la tabla ARBITROS_PARTIDO con identificador indicado
            conn.getInstancia().ejecutar("DELETE FROM test.ARBITROS_PARTIDO WHERE idar = "+ idarb +";");
            //Instrucción que borra la tupla con identificador indicado de la tabla ARBITRO
            conn.getInstancia().ejecutar("DELETE FROM test.ARBITRO WHERE id = "+ idarb +";");	
            
            JOptionPane.showMessageDialog(null,"Árbitro borrado", "Información", JOptionPane.INFORMATION_MESSAGE); 
        }else{
            JOptionPane.showMessageDialog(null,"No existe el árbitro", "Error", JOptionPane.ERROR_MESSAGE); 
        }
    }
    
    /**
     * Método que devuelve el número de árbitros que se encuentran en la base de datos
     * 
     * @return número de árbitros
     */
    public int nArbitros(){
        try {
            //Instrucción que devuelve el número de tuplas en la tabla ARBITRO
            ResultSet rs= conn.getInstancia().ejecutarConsulta("SELECT count(*) FROM test.ARBITRO;");		
            
            while (rs.next()) {
                //Devuelve el número de tuplas
                return rs.getInt(1);
            }
        } catch (Exception e) { }
        //0 por defecto
        return 0;
    }
    
    /**
     * Método que devuelve una ArrayList de Árbitros
     * 
     * @return ArrayList de Árbitros
     */
    public ArrayList<Personas.Arbitro> listarArbitros(){
        //Creo la lista de árbitros
        ArrayList<Personas.Arbitro> listaarbitros = new ArrayList<Personas.Arbitro>();
        
        //Si la tabla de árbitros no está vacía
        if(nArbitros()!=0){
            //Instrucción que devuelve las tuplas de la tabla ARBITRO
            ResultSet rs= conn.getInstancia().ejecutarConsulta("SELECT id, nombre, email, tlf, tipo FROM test.ARBITRO ;");	
            try{
                //Siguiente tupla
                while (rs.next()) { 
                    //Creo Árbitro
                    Personas personas = new Personas(rs.getInt (1), rs.getString (2), rs.getString (3),rs.getString (4));
                    Personas.Arbitro arbitro = personas.new Arbitro(rs.getInt (1), rs.getString (2), rs.getString (3)
                            ,rs.getString (4),rs.getString (5));
                    //Lo añado a la lista
                    listaarbitros.add(arbitro);
                }
            }catch (SQLException e){ }
        }else{
            JOptionPane.showMessageDialog(null,"Introduzca algún árbitro", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Devuelve la lista de árbitros
        return listaarbitros;
    }
    
}
