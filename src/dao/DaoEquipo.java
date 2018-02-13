/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import appfutbol.ConexionBD;
import appfutbol.Equipo;
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
public class DaoEquipo {
    ConexionBD conn;
    
    /**
     * Método para devolver true si se encuentra el Equipo mediante su identificador
     * 
     * @param id Identificador del equipo a buscar
     * @return si se encuentra el Equipo en la tabla
     */
    public boolean BuscarEquipo(int id){
        //Instrucción que devuelve la tupla del Equipo con identificador
        ResultSet rs= conn.getInstancia().ejecutarConsulta("SELECT * FROM test.EQUIPO WHERE id = "+ id +";");		
        try {
            //Devuelve true si existe es tupla
            return rs.first();
        } catch (SQLException e) { }
        //Por defecto devuelve false
        return false;
    }
    
    /**
     * Método para introducir Equipo en la base de datos
     * 
     * @param equipo Objeto Equipo del que obtendremos los datos que se almacenarán en la base de datos
     */
    public void CrearEquipo(Equipo equipo){
        //Si no existe ya el id del Equipo
        if(!BuscarEquipo(equipo.getId())){
            //Creo instancia del DAO del Estadio
            DaoEstadio daoest = new DaoEstadio();
            //Si existe el id del Estadio
            if(daoest.BuscarEstadio(equipo.getEsta())){
                //Lo introduzco en la tabla EQUIPO
                String cadena= "INSERT INTO test.EQUIPO (id,nombre,posicion,estadio) VALUES ("+equipo.getIdequipo()+",'"
                        +equipo.getNombre()+"',"+equipo.getPosicion()+","+equipo.getEsta()+") ;";
                conn.getInstancia().ejecutar(cadena);	
                
                JOptionPane.showMessageDialog(null,"Equipo aceptado", "Información", JOptionPane.INFORMATION_MESSAGE); 
            }else{
                JOptionPane.showMessageDialog(null,"No existe el estadio", "Error", JOptionPane.ERROR_MESSAGE); 
            }
        }else{
            JOptionPane.showMessageDialog(null,"Ya existe el equipo", "Información", JOptionPane.INFORMATION_MESSAGE); 
        }
    }
    
    /**
     * Método para elminar equipo de la base de datos
     * 
     * @param idequipo Identificador del Equipo a eliminar
     */
    public void BajaEquipo(int idequipo){
        //Si existe el Equipo
        if(BuscarEquipo(idequipo)){
            //Instrucción para borrar la tupla del identificador del Equipo
            conn.getInstancia().ejecutar("DELETE FROM test.EQUIPO WHERE id = "+ idequipo +";");
            
            JOptionPane.showMessageDialog(null,"Equipo borrado", "Información", JOptionPane.INFORMATION_MESSAGE); 
        }else{
            JOptionPane.showMessageDialog(null,"No existe el equipo", "Error", JOptionPane.ERROR_MESSAGE); 
        }
    }
    
    /**
     * Método que devuelve la cantidad de tuplas de la tabla EQUIPO
     * 
     * @return Número de tuplas de la tabla EQUIPO
     */
    public int nEquipos(){
        try {
            //Instrucción que devuelve la cuenta
            ResultSet rs= conn.getInstancia().ejecutarConsulta("SELECT count(*) FROM test.EQUIPO;");
            while (rs.next()) {
                //Devuelve el número de tuplas
                return rs.getInt(1);
            }
        } catch (Exception e) { }
        return 0;
    }
    
    /**
     * Método que devuelve una lista de Equipos de la base de datos
     * 
     * @return ArrayList de Equipos
     */
    public ArrayList<Equipo> listarEquipos(){
        //Creo lista de equipos
        ArrayList<Equipo> listaequipos = new ArrayList<Equipo>();
        
        //Si la tabla de equipos no está vacía
        if(nEquipos()!=0){
            //Instrucción que devuelve todas las tuplas de la tabla EQUIPO
            ResultSet rs= conn.getInstancia().ejecutarConsulta("SELECT id, nombre, posicion, puntos, estadio FROM test.EQUIPO ;");	
            try{
                //Cada tupla
                while (rs.next()) { 
                    //Inicio Equipo mediante constructor
                    Equipo equipo = new Equipo(rs.getInt (1),rs.getString (2),rs.getInt (3),rs.getInt (4),rs.getInt (5));
                    //Añado estadio a la lista
                    listaequipos.add(equipo);
                }
            }catch (SQLException e){ }
        }else{
            JOptionPane.showMessageDialog(null,"Introduzca algún equipo", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Devuelve la lista de Estadios
        return listaequipos;
    }
    
    /**
     * Método para establecer las posiciones en función de los puntos y mostrar los equipos
     * 
     */
    public void listarEquiposOrden(){
       int contador=1;
        //Si la tabla de equipos no está vacía
        if(nEquipos()!=0){
            //Instrucción que devuelve los equipos ordenados de más puntos a menos
            ResultSet rs= conn.getInstancia().ejecutarConsulta("SELECT id, nombre, posicion, puntos, estadio "
                    + "FROM test.EQUIPO ORDER BY puntos DESC;");	
            try{
                //En la siguiente tupla
                while (rs.next()) { 
                    //Establece las posiciones mediante el contador, actualizando las tuplas
                    String cadena= "UPDATE test.EQUIPO SET posicion ="+contador+" WHERE id = "+ rs.getInt (1) +"; ";
                    conn.getInstancia().ejecutar(cadena);
                    
                    //Muestra los datos del equipo de la tupla
                    JOptionPane.showMessageDialog(null,"Identificador = "+ rs.getInt (1)+ "\nNombre = " + rs.getString (2)
                            + "\nPosición = "+ contador+ "\nPuntos = "+ rs.getInt (4)
                            + "\nId Estadio = "+ rs.getInt (5),
                            "Información", JOptionPane.INFORMATION_MESSAGE);
                    //Aumento el contador para establecer la posición del siguiente equipo
                    contador++;
                }
            }catch (SQLException e){ }
        }else{
            JOptionPane.showMessageDialog(null,"Introduzca algún equipo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        
}
