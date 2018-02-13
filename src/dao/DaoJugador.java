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
public class DaoJugador {
    ConexionBD conn;
    
    /**
     * Método para devolver true si se encuentra el Jugador mediante su identificador
     * 
     * @param id Identificador del jugador a buscar
     * @return si se encuentra el Jugador en la tabla
     */
    public boolean BuscarJugador(int id){
        //Instrucción para obtener la tupla del jugador mediante su identificador
        ResultSet rs= conn.getInstancia().ejecutarConsulta("SELECT * FROM test.JUGADOR WHERE id = "+ id +";");		
        try {
            //Devuelve true si existe es tupla
            return rs.first();
        } catch (SQLException e) { }
        //Por defecto devuelve false
        return false;
    }
    
    
    /**
     * Método para introducir Jugador en la base de datos
     * 
     * @param jugador Objeto Jugador del que obtendremos los datos que se almacenarán en la base de datos
     */
    public void CrearJugador(Personas.Jugador jugador){
        //Si el jugador ya está en el sistema
        if(BuscarJugador(jugador.getId())){
            JOptionPane.showMessageDialog(null,"El jugador ya está en el sistema", "Información", JOptionPane.INFORMATION_MESSAGE); 
        }else{
            //Creamos una instancia del DAO del equipo
            DaoEquipo daoeq = new DaoEquipo();
            //Si existe el equipo donde jugadrá el jugador
            if(daoeq.BuscarEquipo(jugador.getIdestadio())){
                //Instrucción que introduce el Jugador en la base de datos
                String cadena= "INSERT INTO test.JUGADOR (id,nombre,email,tlf,salario,posicion,titular,numero,equipo) VALUES ("
                        +jugador.getId()+",'"+jugador.getNombre()+"','"+jugador.getEmail()+"','"+jugador.getTlf()+"',"
                        +jugador.getSalario()+",'"+jugador.getPosicion()+"',"+jugador.getTitular()+","
                        +jugador.getNum()+","+jugador.getIdestadio()+") ;";
		conn.getInstancia().ejecutar(cadena);
                
                JOptionPane.showMessageDialog(null,"Jugador aceptado", "Información", JOptionPane.INFORMATION_MESSAGE); 
            }else{
                JOptionPane.showMessageDialog(null,"No existe el equipo", "Error", JOptionPane.ERROR_MESSAGE); 
            }
        }
    }
    
    
    /**
     * Método para eliminar jugador de la base de datos
     * 
     * @param idjugador Identificador del Jugador a eliminar
     */
    public void BajaJugador(int idjugador){
        //Si existe el Jugador
        if(BuscarJugador(idjugador)){
            //Instrucción para borrar la tupla donde se repite el id del Jugador en la tabla JUGADORES_PARTIDO
            conn.getInstancia().ejecutar("DELETE FROM test.JUGADORES_PARTIDO WHERE idjugador = "+ idjugador +";");
            //Instrucción para borrar la tupla del Jugador en la tabla JUGADOR
            conn.getInstancia().ejecutar("DELETE FROM test.JUGADOR WHERE id = "+ idjugador +";");	
                
            JOptionPane.showMessageDialog(null,"Jugador borrado", "Información", JOptionPane.INFORMATION_MESSAGE); 
        }else{
            JOptionPane.showMessageDialog(null,"No existe el jugador", "Error", JOptionPane.ERROR_MESSAGE); 
        }
    }
    
    /**
     * Método que devuelve la cantidad de tuplas de la tabla JUGADOR
     * 
     * @return Número de tuplas de la tabla JUGADOR
     */
    public int nJugadores(){
        try {
            //Instrucción que devuelve la cuenta
            ResultSet rs= conn.getInstancia().ejecutarConsulta("SELECT count(*) FROM test.JUGADOR;");
            while (rs.next()) {
                //Devuelve el número de tuplas
                return rs.getInt(1);
            }
        } catch (Exception e) { }
        return 0;
    }
    
    /**
     * Método que devuelve una lista de Jugadores de la base de datos
     * 
     * @return ArrayList de Jugadores
     */
    public ArrayList<Personas.Jugador> listarJugadores(){
        //Creo la lista de jugadores
        ArrayList<Personas.Jugador> listajugadores = new ArrayList<Personas.Jugador>();
        
        //Si la tabla de jugadores no está vacía
        if(nJugadores()!=0){
            //Instrucción para devolver todos los datos de cada tupla
            ResultSet rs= conn.getInstancia().ejecutarConsulta("SELECT id, nombre, email, tlf, salario, posicion, "
                    + "titular, numero, equipo FROM test.JUGADOR ;");	
            try{
                //Siguiente tupla
                while (rs.next()) {
                    //Creo el Jugador mediante su constructor
                    Personas personas = new Personas(rs.getInt (1), rs.getString (2), rs.getString (3),rs.getString (4));
                    Personas.Jugador jugador = personas.new Jugador(rs.getInt (1), rs.getString (2), rs.getString (3)
                            ,rs.getString (4),rs.getInt (5),rs.getString (6),rs.getBoolean (7),rs.getInt (8),rs.getInt (9));
                    //Lo almaceno en la lista creada anteriormente
                    listajugadores.add(jugador);
                }
            }catch (SQLException e){ }
        }
        //Devuelve la lista de Jugadores
        return listajugadores;
    }
    
    /**
     * Método que devuelve una lista de Jugadores de la base de datos mediante la posición en la que juegan
     * 
     * @return ArrayList de Jugadores
     * @param posicion Posición en la que juegan los Jugadores devueltos
     * @throws SQLException al llamar a respuesta.first
     */
    public ArrayList<Personas.Jugador> listarJugadoresPosicion(String posicion) throws SQLException{
        //Creo lista de jugadores
        ArrayList<Personas.Jugador> listajugadores = new ArrayList<Personas.Jugador>();
        
        //Instrucción para mostrar todas las tuplas con una determinada posición
        ResultSet rs= conn.getInstancia().ejecutarConsulta("SELECT id, nombre, email, tlf, salario, titular, numero, equipo "
                + "FROM test.JUGADOR WHERE posicion = '"+ posicion +"';");
        
        //Si hay tuplas devolverá true
        if(rs.first()!=false){
            //Repetimos la consulta sabiendo que hay jugadores con la posicion
             rs= conn.getInstancia().ejecutarConsulta("SELECT id, nombre, email, tlf, salario, titular, numero, equipo "
                + "FROM test.JUGADOR WHERE posicion = '"+ posicion +"';");
            try {
                //Siguiente tupla
                while (rs.next()){
                    //Creo el Jugador mediante su constructor
                    Personas personas = new Personas(rs.getInt (1), rs.getString (2), rs.getString (3),rs.getString (4));
                    Personas.Jugador jugador = personas.new Jugador(rs.getInt (1), rs.getString (2), rs.getString (3)
                            ,rs.getString (4),rs.getInt (5),posicion,rs.getBoolean (6),rs.getInt (7),rs.getInt (8));
                    //Añado el Jugador a la lista
                    listajugadores.add(jugador);
                }
            } catch (SQLException e) { }
        }else{
            JOptionPane.showMessageDialog(null,"No se han encontrado jugadores en esa posición", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Devuelve la lista de Jugadores
        return listajugadores;
    }
    
    /**
     * Método que devuelve una lista de Jugadores de la base de datos mediante el identificador del equipo en el que juegan
     * 
     * @param idequipo Identificador de Equipo donde juegan los jugadores que se devuelven
     * @return ArrayList de Jugadores
     * @throws SQLException en respuesta.first
     */
    public ArrayList<Personas.Jugador> listarJugadoresEquipo(int idequipo) throws SQLException{
        //Creo lista de Jugadores
        ArrayList<Personas.Jugador> listajugadores = new ArrayList<Personas.Jugador>();
        
        //Instrucción que devuelve las tuplas de jugadores con el identificador de equipo en concreto
        ResultSet rs= conn.getInstancia().ejecutarConsulta("SELECT id, nombre, email, tlf, salario, posicion, titular, numero "
                + "FROM test.JUGADOR WHERE equipo = "+ idequipo +";");
        
        //Devuelve true si devuelve alguna tupla
        if(rs.first()!=false){
            //Repetimos la consulta sabiendo que hay jugadores en el equipo
            rs= conn.getInstancia().ejecutarConsulta("SELECT id, nombre, email, tlf, salario, posicion, titular, numero "
                + "FROM test.JUGADOR WHERE equipo = "+ idequipo +";");
            try {
                //Siguiente tupla
                while (rs.next()){
                    //Creo el Jugador mediante su constructor
                    Personas personas = new Personas(rs.getInt (1), rs.getString (2), rs.getString (3),rs.getString (4));
                    Personas.Jugador jugador = personas.new Jugador(rs.getInt (1), rs.getString (2), rs.getString (3)
                            ,rs.getString (4),rs.getInt (5),rs.getString (6),rs.getBoolean (7),rs.getInt (8),idequipo);
                    //Añado el jugador a la lista de jugadores
                    listajugadores.add(jugador);
                }
            } catch (SQLException e) { }
        }else{
            JOptionPane.showMessageDialog(null,"No se han encontrado jugadores de ese equipo", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return listajugadores;
    }
}
