/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appfutbol;

/**
 *
 * @author Pablo
 */
public class Estadio {
    //Atributos de la clase
    int idestadio;
    String  direccion;
    String ciudad;
    int capacidad;
    //Constructor
    public Estadio(int ides, String direc, String ciud, int capac){
        this.idestadio=ides;
        this.direccion=direc;
        this.ciudad=ciud;
        this.capacidad=capac;
    }
    //Métodos para redefinir el toString

    public int getIdestadio() {
        return idestadio;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public int getCapacidad() {
        return capacidad;
    }
    
    /*
    public String toString() {
        return "[Dirección: "+this.getDireccion()+"] [Ciudad: "+this.getCiudad()+"] [Capacidad: "+this.getCapacidad()+"]";
    }*/

    @Override
    public String toString() {
        return "{" + "idestadio=" + idestadio + ", direccion=" + direccion + ", ciudad=" + ciudad + ", capacidad=" + capacidad + '}';
    }
    
}
