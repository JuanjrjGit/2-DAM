/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ajedrez;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author spair
 */
public class CasillasAjedrez extends JLabel implements MouseListener {
    //Almacenamos los colores que vamos a utilizar para el tablero
    private Color colorjlBlanco = new Color(215, 215, 215);
    private Color colorjlNegro = new Color(60, 60, 60);
    private Color colorjlHoverGreen = new Color(0,255,0);
    char piezaGuardada;// Guarda la pieza que tiene una casilla
    private int sabercolor;// Para saber el color de la casilla
    char pieza;// Pieza que tiene la casilla
    /*Esta variable almacena el color que tenia 
    el tablero antes de cambiarse a verde. Explicado más a fondo en los 
    eventlisteners correspondientes
    */
    
    public void CasillasAjedrez(int color){
        JLabel casillaAjedrez1 = new CasillasAjedrez(color);
        this.addMouseListener(this);
        this.setOpaque(true);/*
        Hay que ponerlo en opaco, porque los label de forma predeterminada estan
        en transparente y al ponerle los colores no se ven.
        */
        this.setPreferredSize(new Dimension(400, 400));//Dimensiones que queremos ponerle al label
        this.setText(" ");//Vacio, para que no de problemas.
    }
    public CasillasAjedrez(int color) {
        this.addMouseListener(this);//Pone el mouselistener 
        this.setOpaque(true);
        if( color == 1){
        setCasillaNegra();/*Puedo llamar este metodo o el de setCasillaBlanca
        Da igual cual se use el objetivo es tener los metodos funcionales*/
        }else{
        setCasillaBlanca();
        }
        
        this.setPreferredSize(new Dimension(400, 400));
    }
    
    public void setCasillaBlanca(){
       this.setBackground(colorjlBlanco);//Pone la casilla en blanco
       sabercolor = 1;/*Hay un metodo para sacar el color del label , pero
       no sirve teniendo en cuenta que queremos modificar la instancia de un objeto
       de hecho me daba un error al utilizar un metodo estatico sobre un 
       contexto no estatico. Entonces usé esta variable que se utiliza luego
       en el mouse listener(explicado ahí que hace)*/
    }
    
    public void setCasillaNegra(){
        this.setBackground(colorjlNegro);//Pone la casilla en negro
        sabercolor = 0;//Explicado en setCasillaBlanca
    }
    
    //método que pone la pieza
    public char getPiezaCasilla(){
        this.pieza = this.piezaGuardada; // Obtiene el char de la casilla
        return pieza;
    }
    public void setPiezaCasilla(char pieza){
        //Variables que almacenan todas las imagenes de las piezas
        
        ImageIcon piezaAlfilB = new ImageIcon("imagenesFichas\\AlfilBlanco.png");
        ImageIcon piezaAlfilN = new ImageIcon("imagenesFichas\\AlfilNegro.png");
        ImageIcon piezaCaballoB = new ImageIcon("imagenesFichas\\CaballoBlanco.png");
        ImageIcon piezaCaballoN = new ImageIcon("imagenesFichas\\CaballoNegro.png");
        ImageIcon piezaPeonB = new ImageIcon("imagenesFichas\\PeonBlanco.png");
        ImageIcon piezaPeonN = new ImageIcon("imagenesFichas\\PeonNegro.png");
        ImageIcon piezaReinaB = new ImageIcon("imagenesFichas\\ReinaBlanco.png");
        ImageIcon piezaReinaN = new ImageIcon("imagenesFichas\\ReinaNegro.png");
        ImageIcon piezaTorreB = new ImageIcon("imagenesFichas\\TorreBlanco.png");
        ImageIcon piezaTorreN = new ImageIcon("imagenesFichas\\TorreNegro.png");
        ImageIcon piezaReyB = new ImageIcon("imagenesFichas\\ReyBlanco.png");
        ImageIcon piezaReyN = new ImageIcon("imagenesFichas\\ReyNegro.png");
        
        //Pones la variable en el metodo setIcon y muestra su imagen correspondiente en el componentegui
        // piezaGuardada , es necesaria para poder hacer luego el get de las casillas cuando necesitemos
        // saber que hay en cada casilla
        if(pieza == 'p'){
            this.setIcon(piezaPeonB);
            piezaGuardada = 'p';
        }
        else if(pieza == 'P'){
            this.setIcon(piezaPeonN);
            piezaGuardada = 'P';
        }
        else if(pieza == 'c'){
            this.setIcon(piezaCaballoB);
            piezaGuardada = 'c';
        }
        else if(pieza == 'C'){
            this.setIcon(piezaCaballoN);
            piezaGuardada = 'C';
        }
        else if(pieza == 'a'){
            this.setIcon(piezaAlfilB);
            piezaGuardada = 'a';
        }
        else if(pieza == 'A'){
            this.setIcon(piezaAlfilN);
             piezaGuardada = 'A';
        }
        else if(pieza == 'r'){
            this.setIcon(piezaReyB);
             piezaGuardada = 'r';
        }
        else if(pieza == 'R'){
            this.setIcon(piezaReyN);
            piezaGuardada = 'R';
        }
        else if(pieza == 'q'){
            this.setIcon(piezaReinaB);
             piezaGuardada = 'q';
        }
        else if(pieza == 'Q'){
            this.setIcon(piezaReinaN);
            piezaGuardada = 'Q';
        }
        else if(pieza == 't'){
            this.setIcon(piezaTorreB);
            piezaGuardada = 't';
        }
        else if(pieza == 'T'){
            this.setIcon(piezaTorreN);
             piezaGuardada = 'T';
        }else if(pieza == ' '){
            this.setIcon(null);
            piezaGuardada = ' ';
        }
    }
  
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       //Cuando el mouse entra dentro del label este se pondrá de color verde.
       this.setBackground(colorjlHoverGreen);
       
    }
    public void mouseDragged(MouseEvent e){}

    @Override
    public void mouseExited(MouseEvent e) {
        //Cuando el mouse sale del label este se pondrá del color que tenía antes
        //sabercolor juega un papel fundamental en esto.
        //setCasillaBlanca hace que la variable sabercolor sea 1 y el 
        //setCasillaNegra hace que la variable sabercolor sea 0.
        //Entonces ponemos en el método mouseExited que si esa variable es 1
        //Le ponemos blanco a la casilla si no, negro.
       if(sabercolor==1){
       
       this.setBackground(colorjlBlanco);
       }else{
       this.setBackground(colorjlNegro);
       }
    }

    /**
     * @param args the command line arguments
     */
    
    
}
