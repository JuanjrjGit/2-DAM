/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borrarficheros;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author spair
 */
public class BorrarFicheros {
    /*public static void main(String[] args) {
        // TODO code application logic here
        
        
        
        
    }*/
    public static void main(String[] args) {
File base = new File("C://Escuela");
File archivoActual;

if (base.exists()){
	for (int i = 0; i < 5; i++) {
		
		if ( i % 2 == 0) {
			System.out.println("Ruta: "+base+ "/Alumno" + i + "/Teoria.txt");
			archivoActual = new File(base + "/Alumno" + i + "/Teoria.txt");
		} else {
			System.out.println("Ruta: " +base+ "/Alumno" + i + "/Practicas.txt");
			archivoActual = new File(base + "/Alumno" + i + "/Teoria.txt");
		}
		if (archivoActual.isFile() && archivoActual.exists()) {
			if(archivoActual.delete()){
				System.out.println("Archivo borrado!!");
			}else{System.out.println("Error al borrar!!");}
		}
            }
        }
    }
