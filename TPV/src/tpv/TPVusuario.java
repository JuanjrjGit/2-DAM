package tpv;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author spair
 */
public class TPVusuario extends javax.swing.JFrame {

    int pillaId = 0;
    int posicionCarrito = 0;
    int ejeYdisponible = 0;
    float precioAntiguo;
    float precioNuevo;

    public JLabel[][][] producto;
    public JLabel[][] carrito;
    public JLabel[][] precio;
    public String[] productoNombre = new String[100];

    private String usuario, psw, bd, host;
    private Connection conexion;
    private Statement sentencia;
    private ResultSet resultado;

    /*
    Tenemos nuestra variable JLabel producto que es de un array de 3 dimensiones,
    las necesitamos las 2 primeras para el posicionamiento de los productos
    dentro del panel del que van a estar contenidos los labels (imagenes del producto)
    la primera controla el eje x y la segunda el eje y
    luego el ultimo array, lo utilizamos para saber que jlabel es cual, es decir.
    Es como un ID que verifica que cierto jlabel corresponde a cierto producto.
    Lo necesitamos porque los jlabels se generan automaticamente basandose
    en los productos que hay en la base de datos y queremos que al añadir un producto
    al carro de la compra (no esta hecho) se cree un jlabel basandose en ese producto pulsado
    lo cual es muy fácil si ya estan predefinidos por el diseñador, pero al no ser el caso
    se requiere de esa matriz para que el administrador solo tenga que añadir un producto
    por la interfaz de administrador y ya funcione todo automaticamente sin tener que 
    acceder al diseñador.

     */
    /**
     * Creates new form TPVusuario
     */
    public TPVusuario() throws SQLException {
        initComponents();
        gestionPimpollo g = new gestionPimpollo("root", "", "pimpollosenterprise", "localhost");

        //g.buscarProductosTPV();
        int numProducto = g.buscarProductosTPV();//Obtiene el numero de productos
        producto = new JLabel[3][numProducto / 3][numProducto];//Crea el objeto 
        crearLabelsProducto(numProducto);//Crea los labels

    }

    public void crearLabelsProducto(int numProducto) throws SQLException {
        gestionPimpollo g = new gestionPimpollo("root", "", "pimpollosenterprise", "localhost");

        int k = 1;// Al crear el objeto almacena su "ID".
        for (int j = 0; j < numProducto / 3; j++) {
            for (int i = 0; i < 3; i++) {
                //Configuramos nuestro jlabel producto según lo que necesite
                producto[i][j][k] = new JLabel(g.buscarImagenesProductoTPV(k));
                //Las imagenes estan guardadas como 1.jpg 2.jpg secuencialmente
                //Así que ponemos una k donde la ruta para la generacion de los labels
                producto[i][j][k].setSize(100, 100);
                producto[i][j][k].setLocation(i * 110, 110 * j);
                producto[i][j][k].setVisible(true);
                contenedorProductos.setLayout(null);
                contenedorProductos.add(producto[i][j][k]);//Lo añadimos al panel
                k++;
            }
        }
        darListenerImagenes(numProducto);
    }

    public void darListenerImagenes(int numProducto) { // les da un listener a todas las imagenes
        // Para poder crear eventos , y poder pasar las ventas al carrito
        System.out.println("Añade el listener");
        int k = 1;
        for (int j = 0; j < numProducto / 3; j++) {
            for (int i = 0; i < 3; i++) {

                final int f = i;
                final int c = j;
                final int id = k;
                System.out.println("Hasta aqui llega");
                producto[i][j][k].addMouseListener(new java.awt.event.MouseAdapter() {

                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        System.out.println("Fila " + f + " Columna" + c);// Lo tengo para saber si esta funcionando

                        gestionPimpollo g = new gestionPimpollo("root", "", "pimpollosenterprise", "localhost");
                        try {
                            if(g.buscarStock(id)<1){
                            }else{
                            g.buscarImagenesProductoTPV(id);
                            crearCarrito(c, id);
                            g.cambiarStock(id);
                            
                            System.out.println("Escuchado");
                            //carrito[i][j] = new JLabel(g.buscarImagenesProductoTPV(id));
                            //buscarImagenEnBd(evt,id);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(TPVusuario.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                k++;
            }
        }

    }

    public void buscarImagenEnBd(java.awt.event.MouseEvent evt, int id) {

    }

    public void crearCarrito(int y, int id) throws SQLException {
        DecimalFormat df = new DecimalFormat("#.00");
        //Creamos el jlabel con el nombre
        gestionPimpollo g = new gestionPimpollo("root", "", "pimpollosenterprise", "localhost");
        System.out.println("Funciona");
        carrito = new JLabel[100][100];
        carrito[y][id] = new JLabel(g.buscarNombreProductoPorId(id));
        carrito[y][id].setText(g.buscarNombreProductoPorId(id));
        carrito[y][id].setLocation(0, ejeYdisponible * 15);
        carrito[y][id].setSize(120, 20);
        carrito[y][id].setOpaque(true);
        carrito[y][id].setBackground(Color.gray);
        carrito[y][id].setVisible(true);
        carritocojone.setLayout(null);
        carritocojone.add(carrito[y][id]);

        carritocojone.repaint();
        System.out.println("FUNCIONAME COÑO");

        //Creamos el jlabel con el precio asociado a su nombre
        precio = new JLabel[100][100];
        precio[y][id] = new JLabel(g.buscarNombreProductoPorId(id));
        precio[y][id].setText(String.valueOf(g.buscarPrecioProductoPorId(id)));
        precioNuevo = g.buscarPrecioProductoPorId(id);
        precio[y][id].setLocation(125, ejeYdisponible * 15);
        precio[y][id].setSize(120, 20);
        precio[y][id].setOpaque(true);
        precio[y][id].setBackground(Color.gray);
        precio[y][id].setVisible(true);
        carritocojone.setLayout(null);
        carritocojone.add(precio[y][id]);
        carritocojone.repaint();
        System.out.println("FUNCIONAME COÑO");

        ejeYdisponible++;

        precioTotal.setText(String.valueOf(df.format(precioAntiguo + precioNuevo)));
        precioAntiguo = precioAntiguo + precioNuevo;
    }

    // create a boolean to know if key has been found
    boolean found = false;

    // iterate over first column of your matrix array
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        precioTotal = new javax.swing.JTextArea();
        carritocojone = new javax.swing.JPanel();
        contenedorScroll = new javax.swing.JScrollPane();
        contenedorProductos = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        volver = new javax.swing.JButton();
        comprar = new javax.swing.JButton();
        borrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        precioTotal.setEditable(false);
        precioTotal.setColumns(20);
        precioTotal.setRows(5);
        jScrollPane1.setViewportView(precioTotal);

        javax.swing.GroupLayout carritocojoneLayout = new javax.swing.GroupLayout(carritocojone);
        carritocojone.setLayout(carritocojoneLayout);
        carritocojoneLayout.setHorizontalGroup(
            carritocojoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 157, Short.MAX_VALUE)
        );
        carritocojoneLayout.setVerticalGroup(
            carritocojoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout contenedorProductosLayout = new javax.swing.GroupLayout(contenedorProductos);
        contenedorProductos.setLayout(contenedorProductosLayout);
        contenedorProductosLayout.setHorizontalGroup(
            contenedorProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        contenedorProductosLayout.setVerticalGroup(
            contenedorProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 584, Short.MAX_VALUE)
        );

        contenedorScroll.setViewportView(contenedorProductos);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        volver.setText("Volver");
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });

        comprar.setText("Comprar");
        comprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comprarActionPerformed(evt);
            }
        });

        borrar.setText("Borrar");
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contenedorScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volver, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(carritocojone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(comprar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(borrar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(carritocojone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comprar, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(borrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(volver)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contenedorScroll)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new inicioSesionUsuario().setVisible(true);

    }//GEN-LAST:event_volverActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        // TODO add your handling code here:
        precioAntiguo = 0;
        precioNuevo = 0;
        ejeYdisponible = 0;
        precioTotal.setText("");
        carritocojone.removeAll();
        carritocojone.repaint();
    }//GEN-LAST:event_borrarActionPerformed

    private void comprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comprarActionPerformed
        // TODO add your handling code here:
        System.out.println("Comprar pulsado");
        gestionPimpollo g = new gestionPimpollo("root", "", "pimpollosenterprise", "localhost");
        double precioEnviado = 0;
        String precioEnviadoString = "";
        System.out.println("Precio" + precioTotal.getText());
        precioEnviadoString = precioTotal.getText();

        precioEnviado = Double.parseDouble(precioEnviadoString.replace(",", "."));

        precioAntiguo = 0;
        precioNuevo = 0;
        ejeYdisponible = 0;
        precioTotal.setText("");
        carritocojone.removeAll();
        carritocojone.repaint();
        try {
            System.out.println(precioEnviado);
            g.realizarCompra(precioEnviado);
        } catch (SQLException ex) {
        }

    }//GEN-LAST:event_comprarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TPVusuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TPVusuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TPVusuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TPVusuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TPVusuario().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(TPVusuario.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrar;
    private javax.swing.JPanel carritocojone;
    private javax.swing.JButton comprar;
    private javax.swing.JPanel contenedorProductos;
    private javax.swing.JScrollPane contenedorScroll;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea precioTotal;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
