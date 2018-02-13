/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appfutbol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Pablo
 */
public class AppFutbolMenu {
    
    static String[] posiciones={"Portero","Defensa","Medio","Ataque"};
    static String[] posicionesa={"Principal","Asistente"};
    
    //IOException para el read.line
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        
        
        //opcion en -1 para asegurar que se repite el do-while
        //si no se introduce el valor correcto
        int opcion=-1;
        
        while(opcion!=0){//Capturo opción desde teclado hasta que sea 0, que salgo

            System.out.println("Introduzca que desea hacer");
            System.out.println("0: Salir");
            System.out.println("1: Alta Equipo");
            System.out.println("2: Baja Equipo");
            System.out.println("3: Alta Jugador");
            System.out.println("4: Baja Jugador");
            System.out.println("5: Alta Árbitro");
            System.out.println("6: Baja Árbitro");
            System.out.println("7: Alta Estadio");
            System.out.println("8: Alta Partido");
            System.out.println("9: Baja Partido");
            System.out.println("10: Listar Equipos");
            System.out.println("11: Listar Estadios");
            System.out.println("12: Listar Árbitros");
            System.out.println("13: Devolver Total de Partidos");
            System.out.println("14: Listar información de los Partidos dada una fecha");
            System.out.println("15: Listar los Partidos hechos por un Equipo");
            System.out.println("16: Listar los Jugadores que hay en un posición");
            System.out.println("17: Listar los Jugadores de un Equipo y sus Posiciones");
            System.out.println("18: Cargar Sistema");
            System.out.println("19: Salvar los datos");
            System.out.println("20: Calcular campeón de liga");
            System.out.println("21: Calcular posiciones");

        
            //Do-while para capturar la opcion por teclado hasta
            //Que sea mayor igual que 0 o menor igual que 21
            do{
                try{
                    BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
                    opcion = Integer.parseInt(in.readLine());
                }catch(NumberFormatException e){ 
                    System.out.println("Error en la opción");
                }
                if(opcion<0||opcion>21){
                    System.out.println("Introduce opción correcta");
                }
            }while(opcion<0||opcion>21);


            switch(opcion){
                case 0:
                    System.out.println("Ha salido");
                    break;
                case 1:
                    //Variables para introducir un nuevo Equipo
                    int idestadio = 0, idequipo = 0, posicion = 0;
                    String nombre=null;
                    do{
                        try{
                            BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
                            System.out.println("Introduzca identificador de equipo: ");
                            idequipo = Integer.parseInt(in.readLine());
                            System.out.println("Introduzca nombre del equipo: ");
                            nombre = in.readLine();
                            System.out.println("Introduzca posición de equipo: ");
                            posicion = Integer.parseInt(in.readLine());
                            System.out.println("Introduzca identificador de estadio: ");
                            idestadio = Integer.parseInt(in.readLine());
                        }catch(NumberFormatException e){ 
                            System.out.println("Error al introducir Equipo");
                        }
                    }while(nombre==null||idequipo==0||idestadio==0);
                    
                    //AppFutbol.AltaEquipo(nombre,idequipo,idestadio,posicion);
                    
                    break;
                    
                case 2:
                    idequipo=0;
                    do{
                        try{
                            BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
                            System.out.println("Introduzca identificador de equipo: ");
                            idequipo = Integer.parseInt(in.readLine());
                        }catch(NumberFormatException e){ 
                            System.out.println("Error al introducir Equipo");
                        }
                    }while(idequipo==0);
                    
                    //AppFutbol.BajaEquipo(idequipo);
                    
                    break;
                    
                case 3:
                    //Variables para introducir un nuevo Jugador
                    int id = 0, salario = 0, num = 0;
                    String email = null, tlf = null, posici = null;
                    nombre = null;
                    Boolean titular = null;
                    do{
                        try{
                            BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
                            System.out.println("Introduzca identificador del Jugador: ");
                            id = Integer.parseInt(in.readLine());
                            System.out.println("Introduzca nombre del Jugador: ");
                            nombre = in.readLine();
                            System.out.println("Introduzca email del Jugador: ");
                            email = in.readLine();
                            System.out.println("Introduzca teléfono del Jugador: ");
                            tlf = in.readLine();
                            System.out.println("Introduzca salario del Jugador: ");
                            salario = Integer.parseInt(in.readLine());
                            System.out.println("Introduzca si el Jugador es titular:   Sí:True   No:False");
                            titular = Boolean.parseBoolean(in.readLine());
                            System.out.println("Introduzca posicion del Jugador");
                            System.out.println("1.Portero");
                            System.out.println("2.Defensa");
                            System.out.println("3.Medio");
                            System.out.println("4.Ataque");
                            int pos = Integer.parseInt(in.readLine());
                            switch(pos){
                                case 1:
                                    posici=posiciones[0];
                                    break;
                                case 2:
                                    posici=posiciones[1];
                                    break;
                                case 3:
                                    posici=posiciones[2];
                                    break;
                                case 4:
                                    posici=posiciones[3];
                                    break;
                                default:
                                    System.out.println("Error al introducir posición");
                                    break;
                            }
                            System.out.println("Introduzca número del Jugador: ");
                            num = Integer.parseInt(in.readLine());
                        }catch(NumberFormatException e){ 
                            System.out.println("Error al introducir Jugador");
                        }
                    }while(id==0||salario==0||num==0||nombre==null||email==null||tlf==null||posici==null||titular==null);
                    
                    idequipo=0;
                    do{
                        try{
                            BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
                            System.out.println("Introduzca identificador de equipo donde jugará: ");
                            idequipo = Integer.parseInt(in.readLine());
                        }catch(NumberFormatException e){ 
                            System.out.println("Error al introducir Equipo");
                        }
                    }while(idequipo==0);
                    
                    //AppFutbol.AltaJugador(id, nombre, email, tlf,salario,posici,titular,num,idequipo);
                    
                    break;
                case 4:
                    int idjugador = 0;
                    idequipo = 0;
                    do{
                        try{
                            BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
                            System.out.println("Introduzca identificador de jugador: ");
                            idjugador = Integer.parseInt(in.readLine());
                            System.out.println("Introduzca identificador de equipo: ");
                            idequipo = Integer.parseInt(in.readLine());
                        }catch(NumberFormatException e){ 
                            System.out.println("Error al introducir Estadio");
                        }
                    }while(idjugador==0);
                    
                    //AppFutbol.BajaJugador(idjugador, idequipo);
                    
                    break;
                    
                case 5:
                    String tipo = null;
                    id=0;nombre=null;email=null;tlf=null;
                    do{
                        try{
                            BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
                            System.out.println("Introduzca identificador del Árbitro: ");
                            id = Integer.parseInt(in.readLine());
                            System.out.println("Introduzca nombre del Árbitro: ");
                            nombre = in.readLine();
                            System.out.println("Introduzca email del Árbitro: ");
                            email = in.readLine();
                            System.out.println("Introduzca teléfono del Árbitro: ");
                            tlf = in.readLine();
                            System.out.println("Introduzca posicion del Árbitro");
                            System.out.println("1.Principal");
                            System.out.println("2.Asistente");
                            int pos = Integer.parseInt(in.readLine());
                            switch(pos){
                                case 1:
                                    tipo=posicionesa[0];
                                    break;
                                case 2:
                                    tipo=posicionesa[1];
                                    break;
                                default:
                                    System.out.println("Error al introducir posición");
                                    break;
                            }
                        }catch(NumberFormatException e){ 
                            System.out.println("Error al introducir Jugador");
                        }
                    }while(id==0||nombre==null||email==null||tlf==null||tipo==null);
                    
                    //AppFutbol.AltaArbitro(id, nombre, email, tlf, tipo);
                    
                    break;
                case 6:
                    id=0;
                    do{
                       try{
                           BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
                           System.out.println("Introduzca identificador del Árbitro: ");
                            id = Integer.parseInt(in.readLine());
                       }catch(NumberFormatException e){
                           System.out.println("Error al introducir identificador");
                       }
                    }while(id==0);
                    
                    //AppFutbol.BajaArbitro(id);
                    
                    break;
                    
                case 7:
                    //Variables para introducir un nuevo Estadio
                    idestadio = 0; 
                    int capacidad = 0;
                    String direccion = null, ciudad = null;
                    do{
                        try{
                            BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
                            System.out.println("Introduzca identificador de estadio: ");
                            idestadio = Integer.parseInt(in.readLine());
                            System.out.println("Introduzca capacidad de estadio: ");
                            capacidad = Integer.parseInt(in.readLine());
                            System.out.println("Introduzca direción de estadio: ");
                            direccion = in.readLine();
                            System.out.println("Introduzca ciudad de estadio: ");
                            ciudad = in.readLine();
                        }catch(NumberFormatException e){ 
                            System.out.println("Error al introducir Estadio");
                        }
                    }while(idestadio==0||capacidad==0||direccion==null||ciudad==null);
                    
                    //AppFutbol.AltaEstadio(idestadio,direccion,ciudad,capacidad);
                    
                    break;
                    
                case 8:
                    idestadio=0;
                    int idpartido=0,ideq1=0,ideq2=0,goles1=-1,goles2=-1;
                    String fecha=null;
                    Boolean ida=null;
                    do{
                        try{
                            BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
                            System.out.println("Introduzca identificador de partido: ");
                            idpartido = Integer.parseInt(in.readLine());
                            System.out.println("Introduzca identificador de estadio: ");
                            idestadio = Integer.parseInt(in.readLine());
                            System.out.println("Introduzca fecha de partido (dd/mm/aa): ");
                            fecha = in.readLine();
                            System.out.println("Introduzca identificador de equipo 1: ");
                            ideq1 = Integer.parseInt(in.readLine());
                            do{
                                System.out.println("Introduzca identificador de equipo 2: ");
                                ideq2 = Integer.parseInt(in.readLine());
                            }while(ideq1==ideq2);
                            System.out.println("Introduzca si es ida:   Sí:True   No:False");
                            ida = Boolean.parseBoolean(in.readLine());
                            System.out.println("Introduzca goles de equipo 1: ");
                            goles1 = Integer.parseInt(in.readLine());
                            System.out.println("Introduzca goles de equipo 2: ");
                            goles2 = Integer.parseInt(in.readLine());
                        }catch(NumberFormatException e){ 
                            System.out.println("Error al introducir Estadio");
                        }
                    }while(idpartido==0||idestadio==0||fecha==null||ideq1==0||ideq2==0||ida==null||goles1==-1||goles2==-1);
                    
                    ArrayList <Integer> arbitros = new ArrayList <Integer>();
                    
                    //AppFutbol.AltaPartido(idpartido, idestadio, fecha, ideq1, ideq2, ida, goles1, goles2, arbitros);
                        //A cada partido se calculan las posiciones. Este mismo método se usa para mostrar todos los equipos (21)
                    //AppFutbol.CalcularPosicionesEquipos();
                    break;
                    
                case 9:
                    idpartido=0;
                    do{
                        try{
                            BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
                            System.out.println("Introduzca identificador de partido a borrar: ");
                            idpartido = Integer.parseInt(in.readLine());
                        }catch(NumberFormatException e){ 
                            System.out.println("Error al introducir Estadio");
                        }
                    }while(idpartido==0);
                    
                    //AppFutbol.BajaPartido(idpartido);
                    
                    break;
                    
                case 10:
                    //AppFutbol.listarEquipos();
                    break;
                case 11:
                    //AppFutbol.listarEstadios();
                    break;
                case 12:
                    //AppFutbol.listarArbitros();
                    break;
                case 13:
                    //AppFutbol.devolverNumeroPartidos();
                    break;
                case 14:
                    fecha=null;
                    do{
                        try{
                            BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
                            System.out.println("Introduzca fecha (dd/mm/aa): ");
                            fecha = in.readLine();
                        }catch(NumberFormatException e){ 
                            System.out.println("Error al introducir fecha");
                        }
                    }while(fecha==null);
                    
                    //AppFutbol.devolverPartidosDadaFecha(fecha);
                    break;
                case 15:
                    idpartido=0;
                    do{
                        try{
                            BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
                            System.out.println("Introduzca identificador: ");
                            idpartido = Integer.parseInt(in.readLine());
                        }catch(NumberFormatException e){ 
                            System.out.println("Error al introducir identificador");
                        }
                    }while(idpartido==0);
                    
                    //AppFutbol.devolverPartidosEquipo(idpartido);
                    
                    break;
                case 16:
                    posici=null;
                    do{
                        try{
                            BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
                            System.out.println("Introduzca posicion de los jugadores");
                            System.out.println("1.Portero");
                            System.out.println("2.Defensa");
                            System.out.println("3.Medio");
                            System.out.println("4.Ataque");
                            int pos = Integer.parseInt(in.readLine());
                            switch(pos){
                                case 1:
                                    posici=posiciones[0];
                                    break;
                                case 2:
                                    posici=posiciones[1];
                                    break;
                                case 3:
                                    posici=posiciones[2];
                                    break;
                                case 4:
                                    posici=posiciones[3];
                                    break;
                                default:
                                    System.out.println("Error al introducir posición");
                                    break;
                            }
                        }catch(NumberFormatException e){ 
                            System.out.println("Error al introducir Jugador");
                        }
                    }while(posici==null);
                    
                    //AppFutbol.listarJugadoresPosicion(posici);
                    
                    break;
                case 17:
                    idpartido=0;
                    do{
                        try{
                            BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
                            System.out.println("Introduzca identificador: ");
                            idpartido = Integer.parseInt(in.readLine());
                        }catch(NumberFormatException e){ 
                            System.out.println("Error al introducir identificador");
                        }
                    }while(idpartido==0);
                    
                    //AppFutbol.listarJugadoresEquipo(idpartido);
                    
                    break;
                case 18:
                    //AppFutbol.cargarSistema();
                    break;
                case 19:
                    //AppFutbol.salvarDatos();
                    break;
                case 20:
                    //AppFutbol.CalcularCampeonTemporada();
                    break;
                case 21:
                    //ArrayList<Integer> clavesordenadas = new ArrayList<Integer>();
                    //clavesordenadas=AppFutbol.CalcularPosicionesEquipos();
                    //AppFutbol.MostrarPosiciones(clavesordenadas);
                    break;
            }
        }
        
    }
    
}
