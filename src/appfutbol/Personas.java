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
public class Personas {
    //Atributos de la clase padre
    int id;
    String nombre;
    String email;
    String tlf;
    //Constructor de clase Personas
    public Personas(int ident, String nombre, String mail, String telefono){
        this.id=ident;
        this.nombre=nombre;
        this.email=mail;
        this.tlf=telefono;
    }

    //Defino uno de los hijos
    public class Jugador extends Personas{
        //Atributos de la clase hijo
        int salario;
        String posicion;
        Boolean titular;
        int num;
        int idestadio;
        //Constructor del hijo Jugador
        public Jugador (int iden, String nombr, String mail, String telefono, int sala, String pos, Boolean tit, int num, int idest){
            super(iden, nombr, mail, telefono);
            this.salario=sala;
            this.posicion=pos;
            this.titular=tit;
            this.num=num;
            this.idestadio=idest;
        }
        //En el método toString obtengo salario,posicion,titular y número
        public int getNum() {
            return this.num;
        }
        public String getPosicion(){        //A esta clase tambien la llamo en el método listarjugadoresposicion
            return this.posicion;
        }
        public Boolean getTitular(){
            return this.titular;
        }

        public int getSalario() {
            return this.salario;
        }

        public int getIdestadio() {
            return idestadio;
        }
        
        
    
        //Redefino el método toString para Arbitro
        
        @Override
        public String toString() {
            return "\n\t\t{" + "nombre=" + nombre + ", email=" + email + ", tlf=" + tlf +", salario=" + salario + ", posicion=" + posicion + ", titular=" + titular + ", num=" + num + '}';
        }
        
    }
    //Defino otro hijo
    public class Arbitro extends Personas{
        //Atributos de la clase hijo
        String tipo;
        //Constructor del hijo Arbitro
        public Arbitro (int iden, String nombr, String mail, String telefono, String tip){
            super(iden, nombr, mail, telefono);
            this.tipo=tip;
        }
        //En el método toString obtengo tipo
        public String getTipo(){
            return this.tipo;
        }
        //Redefino el método toString para Arbitro
        /*
        public String toString() {
            return "[Nombre: "+this.getNombre()+"] [Email: "+this.getEmail()+"] [Tlf: "+this.getTlf()+"] [Tipo: "+this.getTipo()+"]";
        }*/
        @Override
        public String toString() {
            return "Arbitro{" +  "nombre=" + nombre + ", email=" + email + ", tlf=" + tlf + ", tipo=" + tipo + "}"+'\n';
        }
    }
    //Métodos
    public void SetEmail(String mail){
        this.email=mail;
    }
    
    public void SetTlf(String telef){
        this.tlf=telef;
    }
    
    public int getId() {
        return this.id;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getEmail(){
        return this.email;
    }
    public String getTlf(){
        return this.tlf;
    }
    
    
}
