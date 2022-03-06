/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializacionlectura;

/**
 *
 * @author spair
 */
import java.io.*;
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
            
                p1 = (Persona) dataIS.readObject();
                p1.printf();
            
        }
        catch(IOException ex){
            
            System.out.println("Excepcion error de entrada y salida");
            
        }catch (ClassNotFoundException ex){
            System.out.println("Excepcion: el objeto leido no se encuentra la clase");
        }catch (IOException) {
            System.out.println("Excepcion: no funciona")
            
        }
        dataISClose();
    }
    
}
