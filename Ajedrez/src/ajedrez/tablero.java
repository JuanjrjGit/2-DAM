/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrez;

import static ajedrez.Ajedrez.f;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author spair
 */
public class tablero extends JFrame {
    private int resto; // Lo necesitamos para 
    private CasillasAjedrez[][] Tablero; //   
    CasillasAjedrez[][] cuadroSeleccionado; //
    CasillasAjedrez[][] cuadroNuevo; //
    int antiguof = -1; // Necesario para almacenar las coordenadas de los 
    //cuadros seleccionados , este es el de filas
    int antiguoc = -1; // Este es el de columnas
    char piececilla;  // Almacena la imagen que tiene 

    tablero() {
        Tablero = new CasillasAjedrez[8][8];
        resto = 0;
        creacionTablero();
        inicializarTablero();
        darListenerCasillas();

    }

    public void creacionTablero() {

        f = new JFrame();//Creamos el frame que va a contener las casillas
        JPanel p = new JPanel(new GridLayout(8, 8));// Creamos los paneles donde se 
        // van a encontrar las casillas , con un gridlayout
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                //Creamos las casillas
                resto = (i + j) % 2;// Necesario para intercalar entre negro y blanco

                if (resto == 0) {
                    Tablero[j][i] = new CasillasAjedrez(1);//Le pasamos el 1 , para darle el color negro a la casilla
                    //se crea la casilla teniendo en cuenta sus filas y sus columnas
                } else {
                    Tablero[j][i] = new CasillasAjedrez(0);//Pasamos el 0, para darle el color blanco a la casilla
                    
                }

                p.add(Tablero[j][i]); // Añadimos las casillas a p, que son los paneles
                f.add(p); // Añadimos el panel al frame

                f.setSize(599, 599);//Elegimos el tamaño de nuestro frame
                p.setSize(800, 800);//Elegimos el tamaño de nuestro panel
                f.setVisible(true);//Ponemos que se vea, que si no no se ve nada
            }
        }

        inicializarTablero();//Explicado abajo

    }

    public void inicializarTablero() {
        for (int fila = 0; fila <= 7; fila++) {
            for (int columna = 0; columna <= 7; columna++) {
                
            // muy poco eficiente checkear if por if 
            // Este método lo que hace es poner las imagenes de las piezas en
            // las casilllas , basandose en los valores del array en el que se
            // encuentran
            
                //Creacion fichas blancas
                if (fila == 0 && columna == 0) {
                    this.Tablero[fila][columna].setPiezaCasilla('t');

                } else if (fila == 0 && columna == 7) {
                    this.Tablero[fila][columna].setPiezaCasilla('t');

                } else if (fila == 0 && columna == 1 || fila == 0 && columna == 6) {
                    this.Tablero[fila][columna].setPiezaCasilla('a');

                } else if (fila == 0 && columna == 2 || fila == 0 && columna == 5) {
                    this.Tablero[fila][columna].setPiezaCasilla('c');

                } else if (fila == 0 && columna == 3) {
                    this.Tablero[fila][columna].setPiezaCasilla('r');

                } else if (fila == 0 && columna == 4) {
                    this.Tablero[fila][columna].setPiezaCasilla('q');

                } else if (fila == 1) {

                    this.Tablero[fila][columna].setPiezaCasilla('p');

                }

                // Creación fichas negras
                if (fila == 6) {
                    this.Tablero[fila][columna].setPiezaCasilla('P');
                } else if (fila == 7 && columna == 7 || fila == 7 && columna == 0) {
                    this.Tablero[fila][columna].setPiezaCasilla('T');

                } else if (fila == 7 && columna == 1 || fila == 7 && columna == 6) {
                    this.Tablero[fila][columna].setPiezaCasilla('A');

                } else if (fila == 7 && columna == 2 || fila == 7 && columna == 5) {
                    this.Tablero[fila][columna].setPiezaCasilla('C');

                } else if (fila == 7 && columna == 4) {
                    this.Tablero[fila][columna].setPiezaCasilla('R');

                } else if (fila == 7 && columna == 3) {
                    this.Tablero[fila][columna].setPiezaCasilla('Q');

                }

            }

        }
    }

    public void darListenerCasillas() { // les da un listener a todas las casillas
        // Para poder crear eventos , y poder seleccionar las fichas.

        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                final int f = fila;
                final int c = columna;
                Tablero[fila][columna].addMouseListener(new java.awt.event.MouseAdapter() {

                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        System.out.println("Fila " + f + " Columna" + c);// Lo tengo para saber si esta funcionando
                        seleccionarCasilla(evt,f,c);
                    }
                });

            }
        }

    }

    public void seleccionarCasilla(java.awt.event.MouseEvent evt, int f, int c ) {

        char piezavacia = ' '; // Necesario para borrar la ficha movida.
        // la primera vez que se ejecute este método ira al else.
        if( antiguof != -1 ){
            // Cuando ya haya pasado por el else:
            piececilla = Tablero[antiguof][antiguoc].getPiezaCasilla();// Obtiene que pieza tiene la primera
            //casilla seleccionada
            System.out.println(piececilla);
            Tablero[f][c].setPiezaCasilla(piececilla);// Se lo ponemos a la segunda casilla seleccionada y tendrá la ficha 
            //Tablero[antiguof][antiguoc] = Tablero[f][c];
            System.out.println("FUNCIONA el otrooo");
            
            Tablero[antiguof][antiguoc].setPiezaCasilla(piezavacia);
            // La primera casilla seleccionada se le borra su imagen
            piececilla = ' ';// Reseteamos piececilla para poder hacer otra seleccion
            antiguof = -1;// Reseteamos antiguofo para poder hacer otra seleccion
        } else {
            //En el else almacenamos la fila y la columna de la primera casilla
            //En otra variable para que no se pierdan los datos.
            antiguof = f;
            antiguoc = c;
            System.out.println(""+antiguof+"");
            System.out.println(""+antiguoc+"");
 
             System.out.println("FUNCIONA");
        }

    }
}
