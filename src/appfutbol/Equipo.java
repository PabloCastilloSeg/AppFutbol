/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appfutbol;
import java.util.*;
/**
 *
 * @author Pablo
 */
public class Equipo {
    //Atributos de la clase
    String nombre;//Añado el nombre para que aparezca a la hora de listar los equipos
    int idequipo;
    int posicion;
    int puntos;//Variable para ir contando los puntos en liga
    int idestadio;
    //Constructor
    public Equipo(int ideq, String nombr,  int posic, int puntos, int idest){
        this.nombre=nombr;
        this.idequipo=ideq;
        this.posicion=posic;
        this.puntos=puntos;
        this.idestadio=idest;
    }
    //Constructor
    public Equipo(String nombr, int ideq, int idest){
        this.nombre=nombr;
        this.idequipo=ideq;
        this.posicion=0;
        this.puntos=0;
        this.idestadio=idest;
    }
    //Métodos
    //Alta jugador y baja jugador son estáticos
    //porque require llamarlos desde AppFutbol
    
    
    //Método para establecer bien las posiciones

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdequipo(int idequipo) {
        this.idequipo = idequipo;
    }

    public void setEsta(int esta) {
        this.idestadio = esta;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }


    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public int getIdequipo() {
        return idequipo;
    }

    public int getEsta() {
        return idestadio;
    }

    public int getPosicion() {
        return posicion;
    }


    //Métodos para redefinir el toString
    public int getPuntos() {    
        return puntos;
    }

    //Al siguiente método lo llamo en los métodos de la clase partido para obtener ids de los equipos
    public Integer getId(){
        return idequipo;
    }
    
    
    @Override
    public String toString() {
        return "Equipo{" + "\n\tnombre=" + nombre + "\n\tidequipo=" + idequipo + "\n\testa=" + idestadio + "\n\tposicion=" + posicion + "\n\tpuntos=" + puntos + "\n  "+'}';
    }
    
    
}
