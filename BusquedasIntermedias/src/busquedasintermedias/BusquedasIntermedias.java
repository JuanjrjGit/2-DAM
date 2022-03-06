/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busquedasintermedias;

/**
 *
 * @author spair
 */
public class BusquedasIntermedias {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        RandomAccessFile file = new RandomAccessFile(fichero, "rw");
        StringBuffer buffer = null; //bufer para almacenar apellido
        
        int id= 3;
        String apellido="Sultan";
        int dep=35;
        Double salario=1355.01;
        
        
        
        Double salarioTemp = 0.0;
        int depTemp = 0;
        char apellidoTemp[] = new char[10];
        int idTemp = 0;
        
        long posicion = (id -1 ) * 36;
        long posicionActual = file.length;
        long posicionAux = 
        long posicionInsert = (id - 1) * 36;
        
        file.seek(posicionInsert); //nos posicionamos
        
        if(file.readInt()>0){ 
            //Esta posicion esta ocupada
            //Leer el ultimo Empleado
            file.seek(posicionActual - 36);
            idTemp = file.readInt();
            
                    
                    for( int i = 0; i< 10 ; i++) {
                        
                        
                        apellidoTemp[i] = file.readChar();
                        
                    }
                    depTemp = file.readInt();
                    salarioTemp = file.readDouble();
                    //Insertar en la ultima posicion el registro temporal
                file.seek(file.length());
                file.writeInt(idTemp+1);
                file.writeChars(apellidoTemp.toString());
                file.writeInt(depTemp);
                file.writeDouble(salarioTemp);
            
                posicionActual = posicionActual - 36;
                for(long i=posicionActual;i>posicionInsert; i=i-36){
                    idTemp = file.readInt();
                    for(int e = 0; e<10;e++){
                        apellidoTemp[e] = file.readChar();
                    }
                    depTemp = file.readInt();
                    salarioTemp = file.readDouble();
                    file.writeInt(idTemp);
                    file.writeChars(apellidoTemp.toString());
                    file.writeInt(depTemp);
                    file.writeDouble(salarioTemp);
                }
            
            //Crear una posicion al final e ir moviendo
            // todos los registros para dejar hueco
            
        }else{//Esta libre!! hacemos inserción directamente
            
            
            file.writeInt(id); //uso id para identificar empleado
            buffer = new StringBuffer( apellido);
            buffer.setLength(10); //10 caracteres para el apellido
            file.writeChars(buffer.toString());//insertar apellido
            file.writeInt(dep); //insertar departamento
            file.writeDouble(salario);//insertar salario
            file.close(); //cerrar fichero
            
        }
        
        
            file.writeInt(id); //uso id para identificar empleado
            buffer = new StringBuffer( apellido);
            buffer.setLength(10); //10 caracteres para el apellido
            file.writeChars(buffer.toString());//insertar apellido
            file.writeInt(dep); //insertar departamento
            file.writeDouble(salario);//insertar salario
            file.close(); //cerrar fichero
        
        
    }
    
}
