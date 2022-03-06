package archivosbinariosserializacion;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author spair
 */
public class Persona implements Serializable{
    String Nombre;
    int Edad;
    float Altura;
    String  DNI;
    
    Persona(){
        Nombre = "";
        Edad = 0;
        Altura = 0;
        DNI = "";
    }
    Persona(String Nombre, int Edad, float Altura, String DNI) {
        this.Nombre = Nombre;
        this.Edad = Edad;
        this.Altura = Altura;
        this.DNI = DNI;
        
    }
    //Gets
    String getNombre(){
        return this.Nombre;
        
        
    }
    int getEdad(){
        return this.Edad;
        
    }
    float getAltura(){
        return this.Altura;
        
    }
    String getDNI(){
        return this.DNI;
        
    }
    
    //Sets
    
    void setNombre(String Nombre){
        this.Nombre = Nombre;
        
    }
    void setEdad(int Edad){
        this.Edad = Edad;
        
    }
    void setAltura(float Altura){
        this.Altura = Altura;
    }
    void setDNI(String DNI){
        
    }
    void printf(){
        System.out.println("Persona: "+this.getNombre()+"Edad: "
                +this.getEdad()+
                "Altura: "+this.getAltura()+
                "DNI: "+ this.getDNI()+"");
        
    }
}
