/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leercaracteres;

/**
 *
 * @author spair
 */
import java.io.*;
public class LeerFichTexto {
public static void main(String[] args) throws IOException {
 
//declarar fichero
File fichero = new File("C:\\Users\\spair\\Documents\\NetBeansProjects\\LeerCaracteres\\Fichero1.txt");
//crear el flujo de entrada
FileReader fic = new FileReader(fichero);
int i;
//se va leyendo un carácter, -1 final fichero
while ((i = fic.read()) != -1)
System.out.println( (char) i + "==>"+ i);
fic.close(); //cerrar fichero
    
}
}
