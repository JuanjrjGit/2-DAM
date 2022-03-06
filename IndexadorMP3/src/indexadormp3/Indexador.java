/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indexadormp3;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class Indexador {
    
    /**
     * Este método sirve para buscar en una carpeta archivos de una extensióne específica
     * @param ruta: aquí le pasamos la ruta donde queremos buscar los archivos
     * @return 
     */
    public ArrayList<String> buscar(String ruta){
        
        /**
         * Crreamos un objeto File
         */
        File root = new File(ruta);
        
        /**
         * Creamos un array de tipo File que guardará las rutas de las carpetas y archivos que haya dentro
         */
        File[] lista = root.listFiles();
        
        /**
         * Creamos un ArrayList<String> rutas
         */
        ArrayList<String> rutas = new ArrayList<String>();
        
        /**
         * Si no hay archivos...
         */
        if(lista == null){
            
            /**
             * ...el método devuelve nulo
             */
            return null;
        }       
        
        /**
         * Hacemos un for each para recorrer el array
         */
        for(File f : lista){
            
            /**
             * Si un archivo termina en .mp3...
             */
            if(f.getName().endsWith(".mp3")){
                
                /**
                 * ...se obtiene su ruta y se guarda en rutas
                 */
                ruta = f.getAbsolutePath();
                rutas.add(f.getAbsolutePath());
            } 
        }
        
        return rutas;
    }
}
