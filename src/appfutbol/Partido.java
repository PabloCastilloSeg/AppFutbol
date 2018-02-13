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
public class Partido {
    //Atributos de la clase
    int idpartido;
    int idestadio;
    String fecha;
    int eq1;
    int eq2;
    Boolean ida;
    int golesA;
    int golesB;
    //Constructor
    public Partido(int idpar, int idestad, String fech, int equi1, int equi2, Boolean ida, int ga, int gb){
        this.idpartido=idpar;
        this.idestadio=idestad;
        this.fecha=fech;
        this.eq1=equi1;
        this.eq2=equi2;
        this.ida=ida;
        this.golesA=ga;
        this.golesB=gb;
    }

    public int getIdpartido() {
        return this.idpartido;
    }

    public int getIdestadio() {
        return this.idestadio;
    }

    public Boolean getIda() {
        return this.ida;
    }
    
    //Método para conseguir una fecha de un partido. Lo llamo en Devolver partidos dada fecha
    public String getFecha(){
        return this.fecha;
    }
    //Métodos para conseguir los ids de los equipos de un partido. Lo llamo en Devolver partidos dada fechade equipo
    public Integer getIdEquipo1(){
        return this.eq1;
    }
    public Integer getIdEquipo2(){
        return this.eq2;
    }
    //Métodos para conseguir los goles de cada equipo.
    public Integer getGoles1(){
        return this.golesA;
    }
    public Integer getGoles2(){
        return this.golesB;
    }
    //Redefino tostring
    @Override
    public String toString() {
        return " -> Partido{" + "\n estadio=" + idestadio + "\n fecha=" + fecha + "\n equipo1=" + eq1 + "\n equipo2=" + eq2 + "\n ida=" + ida +  "\n golesA=" + golesA + "\n golesB=" + golesB + "\n"+'}';
    }
    
}
