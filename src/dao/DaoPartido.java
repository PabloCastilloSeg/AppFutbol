/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import appfutbol.ConexionBD;
import appfutbol.Partido;
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
public class DaoPartido {
    ConexionBD conn;
    
    /**
     * Método para devolver true si se encuentra el Partido mediante su identificador
     * 
     * @param idpar Identificador de Partido a buscar
     * @return Boolean que indica si existe el partido o no
     */
    public boolean BuscarPartido(int idpar){
        //devuelve verdadero si el equipo se encuentra;
        ResultSet rs= conn.getInstancia().ejecutarConsulta("SELECT * FROM test.PARTIDO WHERE id = "+ idpar +";");		
        try {
            return rs.first();
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null,"No se puede recibir de la base de datos", "Error", JOptionPane.ERROR_MESSAGE); 
        }
        return false;
    }
    
    /**
     * Método que da de baja un Partido de la base de datos
     * 
     * @param idpart Identificador de Partido a borrar
     */
    public void BajaPartido(int idpart){
        //Si existe el Partido en la base de datos
        if(BuscarPartido(idpart)){
            //Instrucción que borra las tuplas con el identificador de partido de la tabla ARBITROS_PARTIDO
            conn.getInstancia().ejecutar("DELETE FROM test.ARBITROS_PARTIDO WHERE idpar = "+ idpart +";");
            //Instrucción que borra las tuplas con el identificador de partido de la tabla JUGADORES_PARTIDO
            conn.getInstancia().ejecutar("DELETE FROM test.JUGADORES_PARTIDO WHERE idpar = "+ idpart +";");
            //Instrucción que borra la tupla con el identificador de partido de la tabla PARTIDO
            conn.getInstancia().ejecutar("DELETE FROM test.PARTIDO WHERE id = "+ idpart +";");
            
            JOptionPane.showMessageDialog(null,"Partido borrado", "Información", JOptionPane.INFORMATION_MESSAGE); 
        }
    }
    
    /**
     * Método que introduce el Partido en la base de datos
     * 
     * @param partido Objeto de la clase Partido del que obtendremos los datos a introducir en la base de datos
     * @param idarbitros ArrayList con los identificadores de los árbitros obtenidos de la interfaz de usuario
     * @param idjugadores ArrayList con los identificadores de los jugadores obtenidos de la interfaz de usuario
     */
    public void CrearPartido(Partido partido, ArrayList<Integer> idarbitros, ArrayList<Integer> idjugadores){
        //Si el Partido no existe ya en la base de datos
        if(!BuscarPartido(partido.getIdpartido())){
            //Creo una instancia del DAO del Estadio
            DaoEstadio daoest = new DaoEstadio();
            //Si existe el Estadio
            if(daoest.BuscarEstadio(partido.getIdestadio())){
                //Creo una instancia del DAO del Equipo
                DaoEquipo daoeq = new DaoEquipo();
                //Si existen ambos equipos en la base de datos
                if(daoeq.BuscarEquipo(partido.getIdEquipo1())&&daoeq.BuscarEquipo(partido.getIdEquipo2())){
                    //Instrucción para introducir el Partido en la base de datos
                    String cadena= "INSERT INTO test.PARTIDO (id,estadio,fecha,equipo1,equipo2,ida,goles1,goles2) VALUES ("
                            +partido.getIdpartido()+","+partido.getIdestadio()+",STR_TO_DATE('"+partido.getFecha()+"', '%d/%m/%Y'),"+partido.getIdEquipo1()+","
                            +partido.getIdEquipo2()+","+partido.getIda()+","+partido.getGoles1()+","
                            +partido.getGoles2()+") ;";
                    conn.getInstancia().ejecutar(cadena);
                    
                    ////////////
                    //Recorro id de arbitros obtenido de la interfaz
                    if(idarbitros.size()>0){
                        int i=0;
                        //Creo una instancia del DAO del Ärbitro
                        DaoArbitro daoar = new DaoArbitro();
                        do{
                            //Si existe el identificador de árbitro en la base de datos
                            if(daoar.BuscarArbitro(idarbitros.get(i))){
                                //Lo introduzco en la tabla ARBITROS_PARTIDO
                                String cad= "INSERT INTO test.ARBITROS_PARTIDO (idpar,idar) VALUES ("+partido.getIdpartido()+","+idarbitros.get(i)+") ;";
                                conn.getInstancia().ejecutar(cad);
                            }
                            i++;
                        }while(i<idarbitros.size());//Recorro la lista de ids hasta su tamaño
                        
                    }else{
                        JOptionPane.showMessageDialog(null,"No se ha introducido ningún Árbitro", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                    ////////////
                    
                    ////////////
                    //Recorro id de jugadores obtenido de la interfaz
                    if(idjugadores.size()>0){
                        int i=0;
                        //Recorro la lista de ids hasta su tamaño
                        DaoJugador daoju = new DaoJugador();
                        do{
                            //Si existe el id introducido en el sistema de arbitros
                            if(daoju.BuscarJugador(idjugadores.get(i))){
                                
                                String cad= "INSERT INTO test.JUGADORES_PARTIDO (idpar,idjugador) VALUES ("+partido.getIdpartido()+","+idjugadores.get(i)+") ;";
                                conn.getInstancia().ejecutar(cad);
                            }
                            i++;
                        }while(i<idjugadores.size());
                    }else{
                        JOptionPane.showMessageDialog(null,"No se ha introducido ningún Jugador", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                    ////////////
                    
                    JOptionPane.showMessageDialog(null,"Partido aceptado", "Información", JOptionPane.INFORMATION_MESSAGE); 
                }else{
                    JOptionPane.showMessageDialog(null,"No existe alguno de los equipos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null,"No existe el estadio", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null,"El partido ya se ha creado", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Método que devuelve el número de partidos que se encuentran en la base de datos
     * 
     * @return número de partidos
     */
    public int nPartidos(){
        try {
            //Instrucción que devuelve el número de tuplas en la tabla PARTIDO
            ResultSet rs= conn.getInstancia().ejecutarConsulta("SELECT count(*) FROM test.PARTIDO;");
            while (rs.next()) {
                //Devuelve el número de tuplas
                return rs.getInt(1);
            }
        } catch (Exception e) { }
        //0 por defecto
        return 0;
    }
    
    /**
     * Método que devuelve una lista de partidos de la base de datos mediante la fecha en la que se jugaron
     * 
     * @param fecha Fecha en la que se han jugado los partidos que se devuelven
     * @return ArrayList de Partidos
     * @throws SQLException en respuesta.first
     */
    public ArrayList<Partido> devolverPartidosDadaFecha(String fecha) throws SQLException{
        //Creamos lista de partidos
        ArrayList<Partido> listapartidos = new ArrayList<Partido>();
    
        //Instrucción que devuelve las tuplas de la tabla PARTIDO en las que se encuentra la fecha indicada
        ResultSet rs= conn.getInstancia().ejecutarConsulta("SELECT id, estadio, equipo1, equipo2, ida, goles1, goles2 "
                + "FROM test.PARTIDO WHERE fecha = STR_TO_DATE('"+fecha+"', '%d/%m/%Y');");
        //Si devuelve true es que existe una tupla con la fecha
        if(rs.first()!=false){
            //Repetimos la sentencia sabiendo que existen partidos
            rs= conn.getInstancia().ejecutarConsulta("SELECT id, estadio, equipo1, equipo2, ida, goles1, goles2 "
                + "FROM test.PARTIDO WHERE fecha = STR_TO_DATE('"+fecha+"', '%d/%m/%Y');");
            try {
                //Siguiente tupla
                while (rs.next()){ 
                    //Creamos partido
                    Partido partido = new Partido(rs.getInt (1),rs.getInt (2),fecha,rs.getInt (3),rs.getInt (4)
                            ,rs.getBoolean (5),rs.getInt (6),rs.getInt (7));
                    //Lo introducimos en la lista
                    listapartidos.add(partido);
                }
            } catch (SQLException e) { }
        }else{
            JOptionPane.showMessageDialog(null,"No se han encontrado partidos con esa fecha", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return listapartidos;
    }
    
    /**
     * Método que devuelve una lista de partidos de la base de datos mediante el identificador de equipo que los ha jugado
     * 
     * @param idequipo Identificador de Equipo que ha jugado los partidos que se devolveran
     * @returnArrayList de Partidos
     * @throws SQLException en respuesta.first
     */
    public ArrayList<Partido> devolverPartidosEquipo(int idequipo) throws SQLException{
        //Creamos lista de partidos
        ArrayList<Partido> listapartidos = new ArrayList<Partido>();
        
        //Instrucción para que devuelva los partidos que ha jugado un equipo
        ResultSet rs= conn.getInstancia().ejecutarConsulta("SELECT id, estadio, fecha, equipo1, equipo2, ida, goles1, goles2 "
                + "FROM test.PARTIDO WHERE equipo1 = "+ idequipo +" OR equipo2 = "+ idequipo +";");
        
        //Devuelve true si existe alguna tupla con el partido
        if(rs.first()!=false){
            //Repetimos la sentencia sabiendo que existen partidos
            rs= conn.getInstancia().ejecutarConsulta("SELECT id, estadio, fecha, equipo1, equipo2, ida, goles1, goles2 "
                + "FROM test.PARTIDO WHERE equipo1 = "+ idequipo +" OR equipo2 = "+ idequipo +";");
            
            try {
                //Siguiente tupla
                while (rs.next()){ 
                    //Creamos partido
                    Partido partido = new Partido(rs.getInt (1),rs.getInt (2),rs.getString(3),rs.getInt (4),rs.getInt (5)
                            ,rs.getBoolean (6),rs.getInt (7),rs.getInt (8));
                    //Guardamos partido
                    listapartidos.add(partido);
                }
            } catch (SQLException e) { }
        }else{
            JOptionPane.showMessageDialog(null,"No se han encontrado partidos de ese equipo", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Devuelve la lista de partidos
        return listapartidos;
    }
    
    /**
     * Método para calcular los puntos a partir de los partidos que existen en la base de datos
     * 
     * @throws SQLException en respuesta.first
     */
    public void CalcularPuntos() throws SQLException{
        int goles1=0,goles2=0;
        int id1=-1,id2=-1;
        
        //Instrucción que devuelve las tuplas de la tabla PARTIDO
        ResultSet rs= conn.getInstancia().ejecutarConsulta("SELECT equipo1, equipo2, goles1, goles2 FROM test.PARTIDO;");
        
        //Si existe alguna tupla
        if(rs.first()!=false){
            //Volvemos a ejecutar la sentencia sabiendo que existen
            rs= conn.getInstancia().ejecutarConsulta("SELECT equipo1, equipo2, goles1, goles2 FROM test.PARTIDO;");
            //Siguiente tupla
            while (rs.next()){
                try {
                    //Guardamos los ids de los equipos que han jugado el partido y sus goles
                    id1 = rs.getInt(1);
                    System.out.println("id1 "+id1);
                    id2 = rs.getInt(2);
                    System.out.println("id2 "+id2);
                    goles1 = rs.getInt(3);
                    System.out.println("goles1 "+goles1);
                    goles2 = rs.getInt(4);
                    System.out.println("goles2 "+goles2);
                } catch (SQLException e) { }

                //Si los goles del equipo 1 son mayores que los del 2
                if(goles1>goles2){
                    System.out.println("Gana equipo1 ");
                    //Actualizamos la base de datos añadiendo 3 puntos a la tupla de la tabla EQUIPO con el identificador 1
                    String cadena= "UPDATE test.EQUIPO SET puntos= puntos+3 WHERE id ="+ id1+"; ";
                    conn.getInstancia().ejecutar(cadena);

                //Si los goles son iguales
                }else if(goles1==goles2){
                    System.out.println("Empatan ");
                    //Actualizamos la base de datos añadiendo 1 punto a la tupla de la tabla EQUIPO con el identificador 1
                    String cadena= "UPDATE test.EQUIPO SET puntos= puntos+1 WHERE id ="+ id1+"; ";
                    conn.getInstancia().ejecutar(cadena);
                    //Actualizamos la base de datos añadiendo 1 punto a la tupla de la tabla EQUIPO con el identificador 2
                    String caden= "UPDATE test.EQUIPO SET puntos= puntos+1 WHERE id ='"+ id2+"'; ";
                    conn.getInstancia().ejecutar(caden);

                //Si los goles del equipo 2 son mayores que los del 1
                }else if(goles1<goles2){
                    System.out.println("Gana equipo2 ");
                    //Actualizamos la base de datos añadiendo 3 puntos a la tupla de la tabla EQUIPO con el identificador 2
                    String cadena= "UPDATE test.EQUIPO SET puntos= puntos+3 WHERE id ="+ id2+"; ";
                    conn.getInstancia().ejecutar(cadena);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null,"No se han encontrado partidos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    /**
     * Método para calcular el campeón de la temporada en función de los puntos que ha conseguido cada equipo
     * @see CalcularPuntos para actualizar todos los puntos de los equipos
     * 
     * @throws SQLException en respuesta.first
     */
    public void CalcularCampeonTemporada() throws SQLException{
        
        //Se establecen los puntos a 0, actualizando todas las tuplas de la tabla EQUIPO de la base de datos
        String cadena= "UPDATE test.EQUIPO SET puntos = 0; ";
        conn.getInstancia().ejecutar(cadena);
            
        //Llamamos a la función para calcular los puntos
        CalcularPuntos();

        int max=-1;
        int ideqmax = 0;
        int idequipo;
        int punt;
        
        //Devuelve todas las tuplas de la tabla EQUIPO
        ResultSet rs= conn.getInstancia().ejecutarConsulta("SELECT id,puntos "
            + "FROM test.EQUIPO;");
        try {
            //Siguiente tupla
            while (rs.next()){ 
                //Obtenemos identificador de equipo y puntos
                idequipo = rs.getInt(1);
                punt = rs.getInt(2);
                
                //Si los puntos superan al máximo establecido
                if(punt>max){
                    //Se establece el identificador como máximo
                    ideqmax = idequipo;
                    //Se establecen los puntos como másximos
                    max = punt;
                }
            }
        } catch (SQLException e) { }
                    
        //Instrucción que devuelve la tupla de la tabla EQUIPO con el identificador máximo
        rs= conn.getInstancia().ejecutarConsulta("SELECT id, nombre, puntos,"
                + " estadio FROM test.EQUIPO WHERE id = "+ ideqmax +";");
        
        //Si existe la tupla (debe de existir)
        if(rs.first()!=false){
            //Volvemos a ejecutar la sentencia sabiendo que existe
            rs= conn.getInstancia().ejecutarConsulta("SELECT id, nombre, puntos,"
                + " estadio FROM test.EQUIPO WHERE id = "+ ideqmax +";");
            try{
                while (rs.next()) { 
                    //Mostramos la información del equipo
                    JOptionPane.showMessageDialog(null,"Identificador = "+ rs.getInt (1)+ "\nNombre = " + rs.getString (2)
                            + "\nPuntos = "+ rs.getInt (3) + "\nId Estadio = "+ rs.getInt (4),
                            "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch (SQLException e){ }
        }else{
            JOptionPane.showMessageDialog(null,"No se ha podido calcular campeón", "Error", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    /**
     * Método para calcular las posiciones de los equipos en función de sus puntos
     * @see CalcularPuntos para establecer los puntos de los Equipos
     * @see DaoEquipo.listarEquiposOrden
     * 
     * @throws SQLException en respuesta.first
     */
    public void CalcularPosicionesEquipos() throws SQLException{
        //Actualizamos todas las tuplas de la tabla EQUIPO con los puntos a 0
        String cadena= "UPDATE test.EQUIPO SET puntos = 0; ";
        conn.getInstancia().ejecutar(cadena);
        
        //Actualizamos los puntos llamando a la función
        CalcularPuntos();
        
        //Creamos una instancia al dao del Equipo
        DaoEquipo daoeq = new DaoEquipo();
        //Llamamos a su función para listar los equipos por orden
        daoeq.listarEquiposOrden();
    }
}
