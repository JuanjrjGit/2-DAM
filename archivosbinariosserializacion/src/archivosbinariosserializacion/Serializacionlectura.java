/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivosbinariosserializacion;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author spair
 */
import java.io.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import java.io.ObjectInputStream;

import java.io.File;

import java.io.ObjectInputStream;

public class Serializacionlectura {

    /**
     * @param args the command line arguments
     */
    public static void main() {
        // TODO code application logic here
        Persona p;
        ObjectInputStream dataIS = null;
        try{
            File fichero = new File("FichPersona.dat");
            dataIS = new ObjectInputStream(new FileInputStream(fichero));
            
            while (true)
            
                p = (Persona) dataIS.readObject();
                p.printf();
            
        }
        catch(IOException ex){
            
            System.out.println("Excepcion error de entrada y salida");
            
        }catch (ClassNotFoundException ex){
            System.out.println("Excepcion: el objeto leido no se encuentra la clase");
        }catch (IOException ex) {
            System.out.println("Excepcion: no funciona")
            
        }
        dataISClose();
    }
    
}

