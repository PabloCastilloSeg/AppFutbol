/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appfutbol;


import dao.DaoArbitro;
import dao.DaoEquipo;
import dao.DaoEstadio;
import dao.DaoJugador;
import dao.DaoPartido;

import java.io.IOException;
import java.util.*;
import javax.swing.JOptionPane;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Asignatura: Bases de datos
 * Grado en Ingeniería Telemática
 * Universidad de Jaén - Escuela Politécnica Superior de Linares
 * 
 * @version 01/12/2016
 * 
 * @author Pablo Castillo Segura
 */

//En esta clase se llaman a los métodos que trabajan con la base de datos (daos)
public class AppFutbol {
    //Arrays de cadenas predefinidos para jugadores y árbitros
    public String[] posiciones={"Portero","Defensa","Medio","Ataque"};
    public String[] posicionesa={"Principal","Asistente"};
    
    //Variable de la clase ConexionBD que ejecuta sentencias en SQL
    ConexionBD conn;
    
    //Constructor de esta clase
    public AppFutbol(){
        conn.getInstancia();
    }
    
    /**
     * Método que permite dar de alta un estadio, creándolo por medio de las variables que le introducimos,
     * y pasándolo al DAO correspondiente para ejecutar la sentencia de SQL que lo introduce en la base de datos.
     * @see DaoEstadio para el método que introduce el estadio en la base de datos
     * 
     * @param idestadio Identificador del Estadio
     * @param direccion Dirección del Estadio
     * @param ciudad Ciudad donde se encuentra el Estadio
     * @param capacidad Capacidad del Estadio
     */
    public void AltaEstadio(int idestadio, String direccion, String ciudad, int capacidad){
        //Creo Estadio con el constructor
        Estadio estadio= new Estadio(idestadio, direccion, ciudad, capacidad);

        //Creo una instancia del DAO del Estadio
        DaoEstadio dest = new DaoEstadio();
        //Llamo a su método para crear el estadio y se lo paso
        dest.CrearEstadio(estadio);
		        
    }
    
    /**
     * Método que muestra los estadios, llamando al DAO correspondiente para listar
     * @see DaoEstadio para el método que devuelve un ArrayList con los estadios de la base de datos
     */
    public void listarEstadios(){
        //Creo una array para almacenar los estadios que devuelve el DAO
        ArrayList<Estadio> listaestadios = new ArrayList<Estadio>();
        
        //Creo una instancia del DAO del Estadio
        DaoEstadio dest = new DaoEstadio();
        //Llamo al método que devuelve un ArrayList con los estadios que se encuentran en la base de datos
        listaestadios = dest.listarEstadios();
        
        //For para recorrer el ArrayList
        for(int i=0;i<listaestadios.size();i++){
            //Va mostrando en una ventana los datos de cada estadio
            JOptionPane.showMessageDialog(null,"Identificador = "+ listaestadios.get(i).getIdestadio()
                    + "\nDirección = " + listaestadios.get(i).getDireccion()
                    + "\nCiudad = "+ listaestadios.get(i).getCiudad()
                    + "\nCapacidad = "+ listaestadios.get(i).getCapacidad(),"Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Método que permite dar de alta un equipo, creándolo por medio de las variables que le introducimos,
     * y pasándolo al DAO correspondiente para ejecutar la sentencia de SQL que lo introduce en la base de datos.
     * @see DaoEquipo para el método que introduce el equipo en la base de datos
     * 
     * @param nombre Nombre del equipo
     * @param idequipo Identificador del Equipo
     * @param idestadio Identificador del Estadio del Equipo, referencia a la tabla Estadios
     */
    public void AltaEquipo(String nombre,int idequipo, int idestadio){
        //Creo el Equipo mediante su segundo constructor
        Equipo equipo= new Equipo(nombre,idequipo, idestadio);

        //Creo una instancia del DAO del Equipo
        DaoEquipo deq = new DaoEquipo();
        //Llamo al método que lo introduce en la base de datos, pasándole el equipo
        deq.CrearEquipo(equipo);	
                
    }
    
    /**
     * Método para eliminar un equipo, llamando al DAO correspondiente que lo elimina de la base de datos
     * @see DaoEquipo para el método que lo borra
     * 
     * @param idequipo Identificador de Equipo a borrar
     */
    public void BajaEquipo(int idequipo){
        //Creo una instancia del DAO del Equipo
        DaoEquipo deq = new DaoEquipo();
        //Llamo al método de borrar equipo, pasándole su id
        deq.BajaEquipo(idequipo);
    }
    
    /**
     * Método que muestra los equipos, llamando al DAO correspondiente para listar
     * @see DaoEquipo para el método que devuelve un ArrayList con los equipos de la base de datos
     */
    public void listarEquipos(){
        //Creo ArrayList donde almacenaré los equipos que devuelve el DAO
        ArrayList<Equipo> listaequipos = new ArrayList<Equipo>();
        
        //Creo una instancia del DAO del Equipo
        DaoEquipo deq = new DaoEquipo();
        //Llamo al método de lista Equipos, que lo almacena en el ArrayList anteriormente creado
        listaequipos = deq.listarEquipos();
        
        //Recorro el ArrayList
        for(int i=0;i<listaequipos.size();i++){
            //Voy mostrando los datos de cada Equipo
            JOptionPane.showMessageDialog(null,"Identificador = "+ listaequipos.get(i).getId()
                    + "\nNombre = " + listaequipos.get(i).getNombre()
                    + "\nPosición = "+ listaequipos.get(i).getPosicion()
                    + "\nPuntos = "+ listaequipos.get(i).getPuntos()
                    + "\nId Estadio = "+ listaequipos.get(i).getEsta(), "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Método que permite dar de alta un jugador, creándolo por medio de las variables que le introducimos,
     * y pasándolo al DAO correspondiente para ejecutar la sentencia de SQL que lo introduce en la base de datos.
     * @see DaoJugador para el método que lo introduce en la base de datos
     * 
     * @param idjugador Identificador del Jugador
     * @param nombre Nombre del Jugador
     * @param mail Email del Jugador
     * @param tlf Teléfono del Jugador
     * @param salario Salario del Jugador
     * @param posicion Posición en la que jugará el Jugador
     *      @see posiciones definido al principio de la clase
     * @param titular Boolean para saber si el Jugador es titular o no
     * @param num Número del Jugador
     * @param idequipo Identificador del Equipo
     */    
    public void AltaJugador(int idjugador, String nombre, String mail, String tlf, int salario, String posicion, 
            Boolean titular, int num, int idequipo){
        
        //Creo un Jugador nuevo mediante su constructor
        //X x=new X();
        //X.Y y=x.new Y();
        Personas personas = new Personas(idjugador, nombre, mail,tlf);
        Personas.Jugador jugador = personas.new Jugador(idjugador, nombre, mail,tlf,salario,posicion,titular,num,idequipo);
        
        //Creo una instancia del DAO del Jugador
        DaoJugador daoj = new DaoJugador();
        //Llamo al método para crear jugador al que le paso el Jugador
        daoj.CrearJugador(jugador);
                
    }
    
    /**
     * Método para eliminar un Jugador, llamando al DAO correspondiente que lo elimina de la base de datos
     * @see DaoJugador para el método que lo borra
     * 
     * @param idjugador Identificador del Jugador a borrar
     */
    public void BajaJugador(int idjugador){
        //Creo una instancia del DAO del Jugador
        DaoJugador daoj = new DaoJugador();
        //Llamo al método que lo da de baja, pasándole el identificador
        daoj.BajaJugador(idjugador);
    }
    
    /**
     * Método que muestra los jugadores, llamando al DAO correspondiente para listar
     * @see DaoJugador para el método que devuelve un ArrayList con los jugadores de la base de datos
     */
    public void listarJugadores(){
        //Creo la lista donde almacenaré los jugadores que devuelve el DAO
        ArrayList<Personas.Jugador> listajugadores = new ArrayList<Personas.Jugador>();
        
        //Creo una instancia del DAO del Jugador
        DaoJugador daoj = new DaoJugador();
        //Llamo al método que devuelve el ArrayList
        listajugadores = daoj.listarJugadores();
        
        //Recorro la lista
        for(int i=0;i<listajugadores.size();i++){
            //Voy mostrando los datos de cada uno
            JOptionPane.showMessageDialog(null,"Identificador = "+ listajugadores.get(i).getId()
                    + "\nNombre = " + listajugadores.get(i).getNombre()
                    + "\nEmail = "+ listajugadores.get(i).getEmail()
                    + "\nTelefono = "+ listajugadores.get(i).getTlf()
                    + "\nSalario = "+ listajugadores.get(i).getSalario()
                    + "\nPosición = "+listajugadores.get(i).getPosicion()
                    + "\nTitular = "+listajugadores.get(i).getTitular()
                    + "\nNúmero = "+listajugadores.get(i).getNum()
                    + "\nId Equipo = "+ listajugadores.get(i).getIdestadio(), "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Método que muestra los jugadores mediante una posición, llamando al DAO correspondiente para listar
     * @see DaoJugador para el método que devuelve un ArrayList con los jugadores de la base de datos 
     * 
     * @param posicion Posición en la que juegan los Jugadores mostrados
     * @throws SQLException al llamar al método "listarJugadoresPosicion" del DAO
     */
    public void listarJugadoresPosicion(String posicion) throws SQLException{
        //Creo ArrayList para listar los jugadores que devueve el DAO
        ArrayList<Personas.Jugador> listajugadores = new ArrayList<Personas.Jugador>();
        
        //Creo una instancia del DAO del Jugador
        DaoJugador daoj = new DaoJugador();
        //Almaceno los jugadores
        listajugadores = daoj.listarJugadoresPosicion(posicion);
        
        //Recorro la lista de jugadores
        for(int i=0;i<listajugadores.size();i++){
            //Voy mostrando sus datos
            JOptionPane.showMessageDialog(null,"Identificador = "+ listajugadores.get(i).getId()
                    + "\nNombre = " + listajugadores.get(i).getNombre()
                    + "\nEmail = "+ listajugadores.get(i).getEmail()
                    + "\nTelefono = "+ listajugadores.get(i).getTlf()
                    +"\nSalario = "+ listajugadores.get(i).getSalario()
                    +"\nTitular = "+listajugadores.get(i).getTitular()
                    +"\nNúmero = "+listajugadores.get(i).getNum()
                    + "\nId Equipo = "+ listajugadores.get(i).getIdestadio(),"Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Método que muestra los jugadores mediante un identificador de equipo, llamando al DAO correspondiente para listar
     * @see DaoJugador para el método que devuelve un ArrayList con los jugadores de la base de datos 
     * 
     * @param idequipo Identificador del Equipo en el que juegan los Jugadores listados
     * @throws SQLException al llamar al método "listarJugadoresEquipo" del DAO
     */
    public void listarJugadoresEquipo(int idequipo) throws SQLException{
        //Creo ArrayList para listar jugadores
        ArrayList<Personas.Jugador> listajugadores = new ArrayList<Personas.Jugador>();

        //Creo instancia del DAO
        DaoJugador daoj = new DaoJugador();
        //Llamo al método que devuelve los jugadores
        listajugadores = daoj.listarJugadoresEquipo(idequipo);
        
        //Recorro la lista
        for(int i=0;i<listajugadores.size();i++){
            //Voy mostrando los datos de cada Jugador
            JOptionPane.showMessageDialog(null,"Identificador = "+ listajugadores.get(i).getId()
                    + "\nNombre = " + listajugadores.get(i).getNombre()
                    + "\nEmail = "+ listajugadores.get(i).getEmail()
                    + "\nTelefono = "+ listajugadores.get(i).getTlf()
                    +"\nSalario = "+ listajugadores.get(i).getSalario()
                    + "\nPosición = "+listajugadores.get(i).getPosicion()
                    +"\nTitular = "+listajugadores.get(i).getTitular()
                    +"\nNúmero = "+listajugadores.get(i).getNum(), "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Método que permite dar de alta un árbitro, creándolo por medio de las variables que le introducimos,
     * y pasándolo al DAO correspondiente para ejecutar la sentencia de SQL que lo introduce en la base de datos.
     * @see DaoArbitro para el método que lo introduce en la base de datos
     * 
     * @param idarb Identificador del Árbitro
     * @param nombre Nombre del Árbitro
     * @param mail Email del Árbitro
     * @param tlf Teléfono del Árbitro
     * @param tipo Tipo de árbitro que es
     *      @see posicionesa definido al principio de la clase
     */
    //Le paso las variables necesarias para crear Árbitro
    public void AltaArbitro(int idarb, String nombre, String mail, String tlf, String tipo){
        
        //Creo el objeto de ese árbitro
        Personas personas = new Personas(idarb,nombre,mail,tlf);
        Personas.Arbitro arbitro = personas.new Arbitro(idarb,nombre,mail,tlf,tipo);
            
        //Creo una instancia del DAO del árbitro
        DaoArbitro daoar = new DaoArbitro();
        //LLamo al método para crearlo
        daoar.CrearArbitro(arbitro);
    }
    
    /**
     * Método para eliminar un Árbitro, llamando al DAO correspondiente que lo elimina de la base de datos
     * @see DaoArbitro para el método que lo borra
     * 
     * @param idarb Identificador del Árbitro a borrar
     */
    public void BajaArbitro(int idarb){
        //Creo instancia del DAO del Árbitro
        DaoArbitro daoar = new DaoArbitro();
        //Llamo al método que borra el árbitro
        daoar.BajaArbitro(idarb);
    }
    
    /**
     * Método que muestra los árbitros, llamando al DAO correspondiente para listar
     * @see DaoArbitro para el método que devuelve un ArrayList con los árbitros de la base de datos
     */
    public void listarArbitros(){
        //Creo lista donde almacenaré árbitros
        ArrayList<Personas.Arbitro> listaarbitros = new ArrayList<Personas.Arbitro>();
        
        //Creo instancia del DAo del árbitro
        DaoArbitro daoar = new DaoArbitro();
        //Llamo al método para almacenar los árbitros
        listaarbitros = daoar.listarArbitros();
        
        //Recorro la lista
        for(int i=0;i<listaarbitros.size();i++){
            //Voy mostrando los datos de cada uno
            JOptionPane.showMessageDialog(null,"Identificador = "+ listaarbitros.get(i).getId()
                    + "\nNombre = " + listaarbitros.get(i).getNombre()
                    + "\nEmail = "+ listaarbitros.get(i).getEmail()
                    + "\nTelefono = "+ listaarbitros.get(i).getTlf()
                    +"\nTipo = "+ listaarbitros.get(i).getTipo(), "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Método que permite dar de alta un partido, creándolo por medio de las variables que le introducimos,
     * y pasándolo al DAO correspondiente para ejecutar la sentencia de SQL que lo introduce en la base de datos.
     * @see DaoPartido para el método que lo introduce en la base de datos
     * 
     * @param idpar Identificador del Partido a crear
     * @param idestadio Identificador del Estadio donde tendrá lugar el Partido
     * @param fecha Fecha del Partido
     * @param idequipo1 Identificador del Equipo local
     * @param idequipo2 Identificador del Equipo visitante
     * @param ida Boolean para saber si el partido es de ida o de vuelta
     * @param ga Goles del Equipo local
     * @param gb Goles del Equipo visitante
     * @param ids ArrayList con los árbitros que trabajan en el partido
     * @param idj ArrayList con los jugadores que juegan en el partido
     * @throws IOException 
     */
    public void AltaPartido(int idpar, int idestadio, String fecha, int idequipo1, int idequipo2, 
            Boolean ida, int ga, int gb, ArrayList<Integer> ids, ArrayList<Integer> idj) throws IOException{
        
        //Creo nuevo partido llamando a su constructor
        Partido partido = new Partido(idpar,idestadio,fecha,idequipo1,idequipo2,ida,ga,gb);
                    
        //Creo lista para los árbitros del partido
        ArrayList <Integer> idarbitros = new ArrayList <Integer>();
        //Recorro la lista de identificadores de árbitros que ha introducido el usuario
        for(int i=0;i<ids.size();i++){
            //Voy almacenando en la lista y elimino los repetidos
            if(!idarbitros.contains(ids.get(i))){
                idarbitros.add(ids.get(i));
            }
        }

        //Creo lista para los jugadores del partido
        ArrayList <Integer> idjugadores = new ArrayList <Integer>();
        //Recorro la lista de identificadores de jugadores que ha introducido el usuario     
        for(int i=0;i<idj.size();i++){
            //Voy almacenando en la lista y elimino los repetidos
            if(!idjugadores.contains(idj.get(i))){
                idjugadores.add(idj.get(i));
            }
        }
        
        //Creo una instancia del DAO del Partido
        DaoPartido daopart = new DaoPartido();
        //Llamo al método para crear Partido
        daopart.CrearPartido(partido,idarbitros,idjugadores);  
                    
    }
    
    /**
     * Método para eliminar un partido, llamando al DAO correspondiente que lo elimina de la base de datos
     * @see DaoPartido para el método que lo borra
     * 
     * @param idpart Identificador del Partido a eliminar
     */
    public void BajaPartido(int idpart){
        //Creo una instancia del DAO del partido
        DaoPartido daopar = new DaoPartido();
        //Llamo al mñetodo para dar de baja el Partido
        daopar.BajaPartido(idpart);
    }
    
    /**
     * Método que muestra los partidos dados una fecha, llamando al DAO correspondiente para listar
     * @see DaoPartido para el método que devuelve un ArrayList con los partidos de la base de datos 
     * 
     * @param fecha Fecha en la que tuvieron lugar los partidos mostrados
     * @throws SQLException al llamar al método "devolverPartidosDadaFecha" del DAO
     */
    public void devolverPartidosDadaFecha(String fecha) throws SQLException{
        //Creo la lista de los partidos
        ArrayList<Partido> listapartidos = new ArrayList<Partido>();
        
        //Creo una instancia del DAO del partido
        DaoPartido daopar = new DaoPartido();
        //Lo almaceno en la lista
        listapartidos = daopar.devolverPartidosDadaFecha(fecha);
        
        //Recorro la lista
        for(int i=0;i<listapartidos.size();i++){
            //Voy mostrando los datos
            JOptionPane.showMessageDialog(null,"Identificador = "+ listapartidos.get(i).getIdpartido()
                    + "\nId Estadio = " + listapartidos.get(i).getIdestadio()
                    + "\nId Equipo 1 = "+ listapartidos.get(i).getIdEquipo1()
                    + "\nId Equipo 2 = "+ listapartidos.get(i).getIdEquipo2()
                    +"\nIda = "+ listapartidos.get(i).getIda()
                    +"\nGoles Equipo 1 = "+ listapartidos.get(i).getGoles1()
                    +"\nGoles Equipo 2 = "+ listapartidos.get(i).getGoles2(), "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Método que muestra los partidos dado un identificador de equipo, llamando al DAO correspondiente para listar
     * @see DaoPartido para el método que devuelve un ArrayList con los partidos de la base de datos 
     * 
     * @param id Identificador del equipo que ha jugado los partidos que se mostraran
     * @throws SQLException al llamar al método "devolverPartidosEquipo" del DAO
     */
    public void devolverPartidosEquipo(int id) throws SQLException{
        //Creo la lista para almacenar los partidos
        ArrayList<Partido> listapartidos = new ArrayList<Partido>();
        
        //Creo instancia del DAO del Partido
        DaoPartido daopar = new DaoPartido();
        //Almaceno los partidos
        listapartidos = daopar.devolverPartidosEquipo(id);
        
        //Recorro la lista
        for(int i=0;i<listapartidos.size();i++){
            //Voy mostrando los datos de cada partido
            JOptionPane.showMessageDialog(null,"Identificador = "+ listapartidos.get(i).getIdpartido()
                    + "\n Fecha = " + listapartidos.get(i).getFecha()
                    + "\nId Estadio = " + listapartidos.get(i).getIdestadio()
                    + "\nId Equipo 1 = "+ listapartidos.get(i).getIdEquipo1()
                    + "\nId Equipo 2 = "+ listapartidos.get(i).getIdEquipo2()
                    +"\nIda = "+ listapartidos.get(i).getIda()
                    +"\nGoles Equipo 1 = "+ listapartidos.get(i).getGoles1()
                    +"\nGoles Equipo 2 = "+ listapartidos.get(i).getGoles2(), "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
            
    /**
     * Método para devolver número de partidos
     */
    public void devolverNumeroPartidos(){
        //Creo una instancia del DAO del partido
        DaoPartido daopar = new DaoPartido();
        //Llamo al método que devuelve el número de partidos
        JOptionPane.showMessageDialog(null,"Total de partidos jugados: "+daopar.nPartidos(), "Información", 
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Método para calcular campeón en función de los partidos
     * 
     * @throws SQLException al llamar al método "CalcularCampeonTemporada" del DAO
     */
    public void CalcularCampeonTemporada() throws SQLException{
        //Creo una instancia del DAO del partido
        DaoPartido daopar = new DaoPartido();
        //Método para calcular campeón
        daopar.CalcularCampeonTemporada();
    }
    
    /**
     * Método para calcular las posiciones de los equipos
     * 
     * @throws SQLException al llamar al método "CalcularPosicionesEquipos" del DAO
     */
    public void CalcularPosicionesEquipos() throws SQLException{
        //Creo una instancia del DAO del partido
        DaoPartido daopar = new DaoPartido();
        //Método para calcular las posiciones
        daopar.CalcularPosicionesEquipos();
    }
    
    
}