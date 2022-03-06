/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivosbinariosserializacion;

import java.io.*;

/**
 *
 * @author spair
 */
public class Archivosbinariosserializacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
           /* Persona p = new Persona();
            p.printf();*/
           File fichero;
            Persona p = new Persona("Fulanico",23, (float) 1.80,"0184789T");
            p.printf();
            ObjectOutputStream dataOS;
            try{
                fichero = new File("FichPersona.dat");
                FileOutputStream fileout = new FileOutputStream(fichero,true);
            
                dataOS = new ObjectOutputStream(fileout);
                dataOS.writeObject(p);
                dataOS.close();
            }catch (IOException ex){
                System.out.println("Error:"+ex.toString());
            }
            
            
        
        
    }
    
    

}
