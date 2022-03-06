/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indexadormp3;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class Metadata {

    /**
     * Creamos un objeto indexador
     */
    Indexador ix = new Indexador();
    
    /**
     * Creamos un ArrayList<String> donde se guardarán las rutas de los archivos que terminen en .mp3
     */
    ArrayList<String> archivos = ix.buscar("C:\\Users\\Daniel\\Desktop\\NetBeans\\IndexadorMP3\\src\\canciones");

    /*
    <-------------------------------------- Todos los métodos siguen la misma lógica por lo que solo se explicará el primero -------------------------------------->
    */
    
    /**
     * Este método sirve para conseguir el título de un archivo MP3 desde sus metadatos
     * @param i: aquí le pasamos la posición en archivos 
     * @return 
     */
    public String getTitulo(int i) {
        
        /**
         * Inicializamos un String
         */
        String titulo = "";

        try {
            
            /**
             * Si en la posición i de archivos hay algo...
             */
            if (archivos.get(i) != null) {
                
                /**
                 * ...creamos un objeto Mp3File
                 */
                Mp3File mp3file = new Mp3File(archivos.get(i));

                /**
                 * Si el archivo MP3 tiene tag ID3v2...
                 */
                if (mp3file.hasId3v2Tag()) {
                    
                    /**
                     * ...creamos un objeto ID3v2
                     * Y conseguimos el título de la canción
                     */
                    ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                    
                    titulo = id3v2Tag.getTitle();

                    return titulo;
                }else{ // Si no tiene ID3v2...
                    
                    titulo = "Sin información";
                    return titulo;
                }

            } else {
                return null;
            }
        } catch (IOException ioe) {
            System.out.println("Error al conseguir el título del archivo MP3: " + ioe.getMessage());
        } catch (UnsupportedTagException ute) {
            System.out.println("Error al conseguir el título del archivo MP3: " + ute.getMessage());
        } catch (InvalidDataException ide) {
            System.out.println("Error al conseguir título del archivo MP3: " + ide.getMessage());
        }

        return null;
    }

    public String getArtista(int i) {
        String artista = "";

        try {
            if (archivos.get(i) != null) {
                Mp3File mp3file = new Mp3File(archivos.get(i));

                if (mp3file.hasId3v2Tag()) {
                    ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                    artista = id3v2Tag.getArtist();

                    return artista;
                }else{
                    artista = "Sin información";
                    
                    return artista;
                }

            } else {
                return null;
            }
        } catch (IOException ioe) {
            System.out.println("Error al conseguir el artista del archivo MP3: " + ioe.getMessage());
        } catch (UnsupportedTagException ute) {
            System.out.println("Error al conseguir el artista del archivo MP3: " + ute.getMessage());
        } catch (InvalidDataException ide) {
            System.out.println("Error al conseguir el artista del archivo MP3: " + ide.getMessage());
        }

        return null;
    }

    public String getAlbum(int i) {
        String album = "";

        try {
            if (archivos.get(i) != null) {
                Mp3File mp3file = new Mp3File(archivos.get(i));

                if (mp3file.hasId3v2Tag()) {
                    ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                    album = id3v2Tag.getAlbum();

                    return album;
                }else{
                    album = "Sin información";
                    
                    return album;
                }
                
            } else {
                return null;
            }
        } catch (IOException ioe) {
            System.out.println("Error al conseguir el album del archivo MP3: " + ioe.getMessage());
        } catch (UnsupportedTagException ute) {
            System.out.println("Error al conseguir el album del archivo MP3: " + ute.getMessage());
        } catch (InvalidDataException ide) {
            System.out.println("Error al conseguir album del archivo MP3: " + ide.getMessage());
        }

        return null;
    }

    public String getEstilo(int i) {
        String estilo = "";

        try {
            if (archivos.get(i) != null) {
                Mp3File mp3file = new Mp3File(archivos.get(i));

                if (mp3file.hasId3v2Tag()) {
                    ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                    estilo = id3v2Tag.getGenreDescription();

                    return estilo;
                }else{
                    estilo = "Sin información";
                    
                    return estilo;
                }
                
            } else {
                return null;
            }
        } catch (IOException ioe) {
            System.out.println("Error al conseguir el estilo del archivo MP3: " + ioe.getMessage());
        } catch (UnsupportedTagException ute) {
            System.out.println("Error al conseguir el estilo del archivo MP3: " + ute.getMessage());
        } catch (InvalidDataException ide) {
            System.out.println("Error al conseguir el estilo del archivo MP3: " + ide.getMessage());
        }

        return null;
    }

    public String getAnno(int i) {
        String anno = "";

        try {
            if (archivos.get(i) != null) {
                Mp3File mp3file = new Mp3File(archivos.get(i));

                if (mp3file.hasId3v2Tag()) {
                    ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                    anno = id3v2Tag.getYear();

                    return anno;
                }else{
                    anno = "Sin información";
                    
                    return anno;
                }
                
            } else {
                return null;
            }
        } catch (IOException ioe) {
            System.out.println("Error al conseguir el año del archivo MP3: " + ioe.getMessage());
        } catch (UnsupportedTagException ute) {
            System.out.println("Error al conseguir el año del archivo MP3: " + ute.getMessage());
        } catch (InvalidDataException ide) {
            System.out.println("Error al conseguir el año del archivo MP3: " + ide.getMessage());
        }

        return null;
    }

    public long getDuracion(int i) {
        long duracion = 0;

        try {
            if (archivos.get(i) != null) {
                Mp3File mp3file = new Mp3File(archivos.get(i));
                
                if (mp3file.hasId3v2Tag()) {
                    duracion = mp3file.getLengthInMilliseconds();
                    
                    return duracion;
                }else{            
                    duracion = 0;
                    
                    return duracion;
                }
            }
            
        } catch (IOException ioe) {
            System.out.println("Error al conseguir la duración del archivo MP3: " + ioe.getMessage());
        } catch (UnsupportedTagException ute) {
            System.out.println("Error al conseguir la duración del archivo MP3: " + ute.getMessage());
        } catch (InvalidDataException ide) {
            System.out.println("Error al conseguir la duración del archivo MP3: " + ide.getMessage());
        }
        return 0;
    }
}
