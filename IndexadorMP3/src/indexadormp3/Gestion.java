/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indexadormp3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Daniel
 */

public class Gestion {
    /**
     * Creamos el objeto Metadata
     */
    Metadata md = new Metadata();
    
    /**
     * Creamos el objeto Mp3
     */
    Mp3 mp3 = new Mp3();
    
    /**
     * Creamos el objeto Indexador
     */
    Indexador ix = new Indexador();    
    
    private HibernateUtil hu;
    private Session session;
    
    /**
     * Este método se encarga de añadir las canciones a la base de datos
     * @param ruta: le pasamos la ruta de la carpeta donde están las canciones
     */
    public void annadirCancion(String ruta){
        ArrayList<String> archivos = ix.buscar(ruta); // Creamos un ArrayList<String> donde se guardarán las rutas de los archivos
        
        /**
         * Si existe al menos una canción...
         */
        if(buscarCancionConId(0) != null){
            
            /**
             * ...recorre archivos con las rutas de los archivos
             */
            for(int i = 0; i < archivos.size(); i++){    
                
                /**
                 * Si no hay una canción que no está duplicada...
                 */
                if(!buscarCancion(md.getTitulo(i), md.getArtista(i)).getRuta().equals(archivos.get(i))){
                    
                    /**
                     * ...se crea un objeto Mp3 con los metadatas del archivo MP3
                     */
                    mp3 = new Mp3(md.getTitulo(i), md.getAlbum(i), md.getArtista(i), new Date(md.getDuracion(i)), md.getEstilo(i), md.getAnno(i), archivos.get(i));
                    
                    /**
                     * Se guarda en la base de datos con Hibernate
                     */
                    session = hu.getSessionFactory().openSession();
                    session.beginTransaction();
                    session.save(mp3);                    
                    session.getTransaction().commit();
                    session.close(); 
                    
                    /**
                     * Se crea un archivo log para documentar lo que se ha hecho
                     */
                    log("C:\\Users\\Daniel\\Desktop", "Se ha subido: " + mp3.getNombre() + " de " + mp3.getArtista() + "\n");
                }                           
            }            
            
            /**
             * Avisa por consola que se han terminado de guardas las canciones en la BD
             */
            System.out.println("Listo!!");
            
            /**
             * Si archivos está vacío hace lo mismo que anteriormente pero sin comprobar si hay canciones iguales
             */
        }else{
            for(int i = 0; i < archivos.size(); i++){
                mp3 = new Mp3(md.getTitulo(i), md.getAlbum(i), md.getArtista(i), new Date(md.getDuracion(i)), md.getEstilo(i), md.getAnno(i), archivos.get(i));

                session = hu.getSessionFactory().openSession();
                session.beginTransaction();
                session.save(mp3);
                session.getTransaction().commit();
                session.close();
                
                log("C:\\Users\\Daniel\\Desktop", "Se ha subido: " + mp3.getNombre() + " de " + mp3.getArtista() + "\n");
            }
        }               
    }
    
    /**
     * Este método busca una canción
     * @param nombre: el nombre de la canción
     * @param artista: el nombre del artista de la canción (puede haber canciones con el mismo nombre pero son de un artista distino)
     * @return 
     */
    public Mp3 buscarCancion(String nombre, String artista){
        
        /**
         * Se coge la configuración de Hibernate
         */
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction t = null;
        
        try{
            t = session.beginTransaction();
            
            /**
             * Recoge en una lista la canción que se haya buscado
             */
            List<Mp3> list = session.createCriteria(Mp3.class).list();               
            t.commit();
            
            /**
             * Si la lista no está vacía...
             */
            if(!list.isEmpty()){        
                /**
                 * ...recorre la lista
                 */
                for(int i = 0; i < list.size(); i++){
                    
                    /**
                     * Si existe una canción con el mismo nombre y artista que los parámtros introducidos...
                     */
                    if(list.get(i).getNombre().equals(nombre) && list.get(i).getArtista().equals(artista)){
                        
                        /**
                         * ...se crea un objeto que guarda los metadatos del MP3
                         */
                        mp3 = new Mp3(
                            list.get(i).getNombre(),
                            list.get(i).getAlbum(),
                            list.get(i).getArtista(),
                            list.get(i).getDuracion(),
                            list.get(i).getEstilo(),
                            list.get(i).getAnno(),
                            list.get(i).getRuta()
                        );
                    }                   
                }  
                
                return mp3; 
                
            }else{
                return null;
            }            
            
        }catch(HibernateException he){
            if(t != null){
                t.rollback();
            }
            
            Logger.getLogger("con").info("Excetpion: " + he.getMessage());
            he.printStackTrace(System.err);
        }finally{
            session.close();
        }
        
        return null;
    }
    
    /**
     * Este método hace exactamente lo mismo que el anterior pero pasándole un id
     * Además es privado ya que solo se va a usar en esta clase
     * @param i
     * @return 
     */
    private Mp3 buscarCancionConId(int i){
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction t = null;
        
        try{
            t = session.beginTransaction();
            
            List<Mp3> list = session.createCriteria(Mp3.class).list();               
            t.commit();
            
            
            if(!list.isEmpty()){                
                    
                mp3 = new Mp3(
                    list.get(i).getNombre(),
                    list.get(i).getAlbum(),
                    list.get(i).getArtista(),
                    list.get(i).getDuracion(),
                    list.get(i).getEstilo(),
                    list.get(i).getAnno(),
                    list.get(i).getRuta()
                );
                
                return mp3;                 
            }else{
                return null;
            }            
            
        }catch(HibernateException he){
            if(t != null){
                t.rollback();
            }
            
            Logger.getLogger("con").info("Excetpion: " + he.getMessage());
            he.printStackTrace(System.err);
        }finally{
            session.close();
        }
        
        return null;
    }
    
    /**
     * Este método lista las canciones que hay en la BD y sigue la misma lógica que los métodos anteriores
     * @return 
     */
    public ArrayList<Mp3> listarCanciones(){
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction t = null;
        
        try{
            t = session.beginTransaction();
            
            ArrayList<Mp3> list = (ArrayList<Mp3>) session.createCriteria(Mp3.class).list();               
            t.commit();
            
            for(int i = 0; i < list.size(); i++){
                    
                mp3 = new Mp3(
                    list.get(i).getNombre(),
                    list.get(i).getAlbum(),
                    list.get(i).getArtista(),
                    list.get(i).getDuracion(),
                    list.get(i).getEstilo(),
                    list.get(i).getAnno(),
                    list.get(i).getRuta()
                ); 
            }
            
            return list;
        }catch(HibernateException he){
            if(t != null){
                t.rollback();
            }
            
            Logger.getLogger("con").info("Excetpion: " + he.getMessage());
            he.printStackTrace(System.err);
        }finally{
            session.close();
        }
        
        return null;
    }
    
    /**
     * Este método crea un archivo de tipo log
     * @param ruta: aquí le pasaremos la ruta donde queremos que se cree el archivo
     * @param mensaje: aquí le pasaremos lo que queremos que escriba en el archivo
     */
    public void log(String ruta, String mensaje){
        /**
         * Creamos un objeto de tipo Date para guardar fechas
         */
        Date fecha = new Date();
        
        /**
         * Pasamos la fecha anterior a tipo String
         */
        String fechaTexto = String.valueOf(fecha);
        
        try{
            
            /**
             * Creamos un objeto de tipo File
             */
            File file = new File(ruta, "console.log");
            
            /**
             * Si el archivo existe...
             */
            if(!file.exists()){
                
                /**
                 * ...creamos un archivo nuevo
                 */
                file.createNewFile();
            }
            
            /**
             * Escribimos lo necesario en el archivo
             */
            FileWriter fw = new FileWriter(file, true); // El segundo parámetro es true para que se pueda sobreescribir sobre el archivo
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(fechaTexto + "\n");
            bw.write(mensaje + "\n");
            
            /**
             * Cerramos el archivo
             */
            bw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Este método sirve para crear el archivo M3U y sigue la misma lógica que el método anterior
     * @param ruta: aquí le pasamos la ruta donde queremos que se cree el archivo
     * @param nombrePlaylist: aquí le pasamos el nombre que queremos ponerle al archivo
     * @param lista: aquí le pasamos las canciones que queremos introducir en la playlist en forma de lista
     */
    public void m3u(String ruta, String nombrePlaylist, ArrayList<Mp3> lista){        
        try{
            File file = new File(ruta, nombrePlaylist + ".m3u");
            
            if(!file.exists()){
                file.createNewFile();
            }
            
            /**
             * Al contrario que en log() aquí debe de ser falso ya que si creamos un M3U con el mismo nombre y con las mismas canciones se corromperá
             */
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write("#EXTM3U\n");
            
            /**
             * Recorremos la lista de canciones
             */
            for(int i = 0; i < lista.size(); i++){
               bw.write("#EXTINF:" + i + ","
                    + lista.get(i).getArtista() + " - "
                    + lista.get(i).getNombre() + "\n");
            
            bw.write(lista.get(i).getRuta() + "\n"); 
            }           
            
            bw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}