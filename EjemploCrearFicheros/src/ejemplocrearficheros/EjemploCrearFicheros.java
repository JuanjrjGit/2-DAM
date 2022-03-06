/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplocrearficheros;

import java.io.File;

/**
 *
 * @author spair
 */
public class EjemploCrearFicheros {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // 5 alumnos
        // 2 archivos : 1 teoria otro archivo de practicas
        File carpetaAlumno;
        File archivoAlumno;
        File base = new File("Users\\spair\\Documents\\NetBeansProjects\\EjemploCrearFicheros");
        // comprobar si existe la carpeta base
        if(!base.exists()){
            base.mkdir();
        }
         for(int i=0;i<5;i++){
              carpetaAlumno = new File(base+"\\Alumno"+i);
              if(carpetaAlumno.exists()){
                  carpetaAlumno.mkdir();
                  
              }
              archivoAlumno = new File(new File(carpetaAlumno,"Teoria.txt");
              if(!archivoAlumno.exists()){
                  archivoAlumno.createNewFile();
              }
              archivoAlumno = new File(new File(carpetaAlumno,"Practicas.txt");
              if(!archivoAlumno.exists()){
                  archivoAlumno.createNewFile();
              }
              
        }
        
        
        
        
        
        
    }
    
}
