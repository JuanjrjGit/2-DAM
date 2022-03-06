package tpv;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.ImageIcon;

//Esta es la clase que tiene todos los metodos para hacer cualquier tipo de 
//modificacion de la base de datos tambien comprueba los inicios de sesión
//Basados en la base de datos, y comprobar cualquier cosa de esta
//
public class gestionPimpollo {

    private String usuario, psw, bd, host;
    private Connection conexion;
    private Statement sentencia;
    private ResultSet resultado;

    //Constructor
    public gestionPimpollo(String usuario, String psw, String bd, String host) {
        this.usuario = usuario;
        this.psw = psw;
        this.bd = bd;
        this.host = host;
        conectar();
    }

    //METODOS GESTION BD
    //Para poder meterse en la bd
    private void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://" + host + "/" + bd + "?serverTimezone=UTC", usuario, psw);

        } catch (Exception ex) {
            System.out.println("Error al conectar" + ex.getMessage());//ex.toString()
        }
    }

    //Para salir de la bd
    private void desconectar() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexion" + ex.toString());

        }
    }

    private void crearDB() {

    }

    //METODOS GESTION EMPLEADO
    //Para insertar usuarios
    public boolean insertarUsuarios(usuarioPimpollo usu) throws SQLException {

        boolean resultado = false;
        conectar();// Pendiente de cambiar el metodo.

        String sql = String.format("INSERT INTO usuario (nombre,apellidos,login,password) VALUES( '%s', '%s', '%s' , '%s')",
                usu.getNombre(),
                usu.getApellidos(),
                usu.getLogin(),
                usu.getPassword());

        try {
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("Error al insertar usuarios" + ex.toString());
            resultado = false;
        }

        desconectar();
        return resultado;
    }

    //Para modifica usuarios
    public boolean modificarUsuario(usuarioPimpollo usu) throws SQLException {

        boolean resultado = false;
        conectar();
        String sqlmodificar = String.format("UPDATE usuario SET Nombre = '%s', Apellidos = '%s', login = '%s', password = '%s'  WHERE idUsuario = " + usu.getIdUsuario() + "", usu.getNombre(),
                usu.getApellidos(),
                usu.getLogin(),
                usu.getPassword());
        try {
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sqlmodificar);
        } catch (SQLException ex) {
            System.out.println("Error al modificar usuario" + ex.toString());
            resultado = false;
        }
        //TODO
        desconectar();
        return resultado;

    }

    //Para buscar usuarios ( 
    public usuarioPimpollo buscarUsuario(int id) {
        usuarioPimpollo usuario = new usuarioPimpollo();
        conectar();

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM  usuario");
            while (resultado.next()) {

                int idreal = resultado.getInt("idUsuario");

                if (idreal == id) {
                    String nombre = resultado.getString("Nombre");
                    String apellidos = resultado.getString("apellidos");
                    String login = resultado.getString("login");
                    String password = resultado.getString("password");
                    System.out.println(idreal + " | " + nombre + " | " + apellidos + " | " + login + " | " + password + " | ");

                }
            }

        } catch (SQLException ex) {
            System.out.println("Error al buscar Usuario" + ex.toString());
        }
        desconectar();
        return usuario;
    }

    //Borra usuarios
    public boolean borrarUsuario(int id) {
        boolean resultado = false;
        conectar();
        String sqlborrar = String.format("DELETE FROM usuario WHERE idUsuario = %s", id);
        try {
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sqlborrar);
        } catch (SQLException ex) {
            System.out.println("Error al borrar empleado" + ex.toString());
            resultado = false;
        }
        desconectar();
        return resultado;
    }

    //METODOS GESTION PRODUCTOS
    public boolean insertarProducto(productosPimpollo pp) {
        boolean resultado = false;
        conectar();

        String sql = String.format("INSERT INTO productos (nombre, descripcion, precio, numeroStock) VALUES( '%s', '%s', %s, %s)",
                pp.getNombre(),
                pp.getDescripcion(),
                pp.getPrecio(),
                pp.getStock());

        try {
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("Error al insertar producto" + ex.toString());
            resultado = false;
        }

        desconectar();
        return resultado;
    }

    public boolean modificarProducto(productosPimpollo pp) {
        boolean resultado = false;
        conectar();
        String sqlmodificar = String.format("UPDATE productos SET nombre = '%s', descripcion = '%s', numeroStock = %s, precio = %s  WHERE idProducto = " + pp.getId() + "", pp.getNombre(),
                pp.getDescripcion(),
                pp.getStock(),
                pp.getPrecio());

        try {
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sqlmodificar);
        } catch (SQLException ex) {
            System.out.println("Error al modificar producto" + ex.toString());
            resultado = false;
        }
        //TODO
        desconectar();
        return resultado;
    }

    public productosPimpollo buscarProducto(int id) {
        productosPimpollo pp = new productosPimpollo();
        conectar();

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM  productos");
            while (resultado.next()) {

                int idReal = resultado.getInt("idProducto");

                if (idReal == id) {
                    String nombre = resultado.getString("Nombre");
                    String descripcion = resultado.getString("descripcion");
                    String precio = resultado.getString("precio");
                    String stock = resultado.getString("numeroStock");
                    System.out.println(idReal + " | " + nombre + " | " + descripcion + " | " + precio + " | " + stock + " | ");

                    //usuario.setNombre(nombre);
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error al buscar producto" + ex.toString());
        }
        desconectar();
        return pp;
    }

    public boolean borrarProducto(int id) {
        boolean resultado = false;
        conectar();
        String sqlborrar = String.format("DELETE FROM productos WHERE idProducto = %s", id);
        try {
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sqlborrar);
        } catch (SQLException ex) {
            System.out.println("Error al borrar producto" + ex.toString());
            resultado = false;
        }
        desconectar();
        return resultado;
    }

    //METODOS GESTION VENTAS
    public boolean registroVentas(int id) {
        boolean resultado = false;
        ventasPimpollo ventas = new ventasPimpollo();
        conectar();
        String sql = String.format("INSERT INTO ventas (idVenta, fecha, precioFinal) VALUES( %s, NOW(), %s)",
                ventas.getIdVentas(),
                ventas.getPrecioFinal());

        try {
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("Error al registrar la venta" + ex.toString());
            resultado = false;
        }
        desconectar();
        return resultado;
    }

    public boolean borrarVenta(int id) {
        boolean resultado = false;
        conectar();
        String sqlborrar = String.format("DELETE FROM ventas WHERE idVenta = %s", id);
        try {
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sqlborrar);
        } catch (SQLException ex) {
            System.out.println("Error al borrar venta" + ex.toString());
            resultado = false;
        }
        desconectar();
        return resultado;
    }

    public boolean modificarVenta(ventasPimpollo ventas) {
        boolean resultado = false;
        conectar();
        String sqlmodificar = String.format("UPDATE productos SET idVenta = %s, fecha = NOW(), precioFinal = %s WHERE idVenta = %s",
                ventas.getIdVentas(),
                ventas.getPrecioFinal());

        try {
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sqlmodificar);
        } catch (SQLException ex) {
            System.out.println("Error al modificar producto" + ex.toString());
            resultado = false;
        }
        //TODO
        desconectar();
        return resultado;
    }

    //Registra ventas, aunque ahora mismo no se usa porque no hemos llegado ahi
    //Es para eso
    public boolean registroVentaUsuario(int id) {
        boolean resultado = false;
        conectar();
        ventaUsuarioPimpollo ventasUsuario = new ventaUsuarioPimpollo();
        String sql = String.format("INSERT INTO ventausuario (idVentaUsuario, usuario, venta) VALUES( %s, %s, %s)",
                ventasUsuario.getIdVentaUsuario(),
                ventasUsuario.getUsuario(),
                ventasUsuario.getVenta());
        try {
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("Error al registrar la venta del usuario" + ex.toString());
            resultado = false;
        }
        desconectar();
        return resultado;
    }
    //Registra ventas, aunque ahora mismo no se usa porque no hemos llegado ahi

    public boolean registroVentaProducto(int id) {
        boolean resultado = false;
        conectar();
        ventaProductoPimpollo ventasProducto = new ventaProductoPimpollo(1, 1, 1);
        String sql = String.format("INSERT INTO ventaproducto (idVentaProducto, producto, venta) VALUES( %s, %s, %s)",
                ventasProducto.getIdVentaProducto(),
                ventasProducto.getProducto(),
                ventasProducto.getVenta());
        try {
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("Error al registrar la venta del producto" + ex.toString());
            resultado = false;
        }
        desconectar();
        return resultado;
    }

    //Para iniciar sesion como admin
    public boolean buscarAdminPorNombreYContrasenia(String contra, String email) throws SQLException {
        String contramodelo;
        conectar();

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery("SELECT password FROM `usuario` WHERE password = '" + contra + "' AND login = '" + email + "' AND admin = 1");
            //Busca el admin de cierta tabla
            while (resultado.next()) {//Recorremos la tabla
                contramodelo = resultado.getString("password");//Almacenamos el admin en la variable
                if (contramodelo.equals(contra)) {//Compara el empleado metido con el modelo
                    //Si coincide lleva a la pagina de admin
                    ventanaprincipaladmin vp = new ventanaprincipaladmin();
                    new ventanaprincipaladmin().setVisible(true);
                    System.out.println(contra);
                    return true;
                } else {
                    return false;//si no no pasa nada.
                }
            }
        } catch (Exception e) {
            System.out.println("Ha habido un error o no se ha encontrado el usuario " + e.getMessage());
            return false;
        }

        desconectar();
        return false;
    }

    //Para iniciar sesion como usuario
    public boolean buscarUsuarioPorNombreYContrasenia(String contra, String email) throws SQLException {
        String contramodelo;
        conectar();

        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery("SELECT password FROM `usuario` WHERE password = '" + contra + "' AND login = '" + email + "' AND admin = 0");
            //Busca el usuario de cierta tabla
            while (resultado.next()) {//Recorremos la tabla
                contramodelo = resultado.getString("password");//Almacenamos el usuario en la variable
                if (contramodelo.equals(contra)) {
                    TPVusuario vp = new TPVusuario();//Te lleva a la ventana de la TPV(no completamente funcional)
                    new TPVusuario().setVisible(true);
                    System.out.println(contra);
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Ha habido un error o no se ha encontrado el usuario " + e.getMessage());
            return false;
        }

        desconectar();
        return false;
    }

    //Cuenta la cantidad de productos que hay y devuelve el valor en un int
    //Se necesita para la tpv en una variable llamado numProducto.
    //Que es esencial para la creacion de jlabels con los productos correspondientes
    public int buscarProductosTPV() throws SQLException {
        conectar();
        int contador = 0;
        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM  productos");
            while (resultado.next()) {

                contador++;

            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar producto" + ex.toString());
        }
        desconectar();
        return contador;
    }

    public ImageIcon buscarImagenesProductoTPV(int id) throws SQLException {
        conectar();
        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery("SELECT imagen FROM productos WHERE idProducto = " + id + "");

            byte[] imagen = null;
            while (resultado.next()) {
                System.out.println();
                imagen = resultado.getBytes("imagen");
            }
            
            Image img = Toolkit.getDefaultToolkit().createImage(imagen);
            ImageIcon icon = new ImageIcon(img);
            return icon;
            
        } catch (SQLException ex) {
            System.out.println("Error al buscar producto" + ex.toString());
        }
        desconectar();
        return null; 
    }
    public String buscarNombreProductoPorId(int id) throws SQLException {
         conectar();
        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM  productos");
            while (resultado.next()) {

                int idReal = resultado.getInt("idProducto");

                if (idReal == id) {
                   String nombre = resultado.getString("Nombre");
                   System.out.println("Nombre producto:"+nombre);
                   return nombre;
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error al buscar producto" + ex.toString());
        }
        desconectar();
        return "potato";
        
        
        
    } 
    public float buscarPrecioProductoPorId(int id) throws SQLException{
     conectar();
        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM  productos");
            while (resultado.next()) {

                int idReal = resultado.getInt("idProducto");

                if (idReal == id) {
                   float precio = resultado.getFloat("precio");
                   System.out.println("Nombre producto:"+precio);
                   return precio;
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error al buscar producto" + ex.toString());
        }
        desconectar();
        return 0;
        
        
    }
    public boolean realizarCompra(double precio) throws SQLException{
        boolean resultado = false;
        Date d = new Date(); 
        conectar();

        String sql = String.format("INSERT INTO ventas ( fecha, precioFinal) VALUES(  NOW(), %s)",precio);
        try {
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("Error al insertar producto" + ex.toString());
            resultado = false;
        }

        desconectar();
        return resultado;
    }
    public int buscarStock(int id) throws SQLException{
        conectar();
        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM  productos");
            while (resultado.next()) {

                int idReal = resultado.getInt("idProducto");

                if (idReal == id) {
                   int stock = resultado.getInt("numeroStock");
                   System.out.println("Cantidad Stock:"+stock);
                   return stock;
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error al buscar producto" + ex.toString());
        }
        desconectar();
        return 0;
    }
    public void cambiarStock(int id) throws SQLException{
        boolean resultado = false;
        int stock = buscarStock(id);
        stock = stock-1;
        conectar();
        String sqlmodificar = String.format("UPDATE productos SET numeroStock = %s WHERE idProducto = %s",
                stock,id);

        try {
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sqlmodificar);
        } catch (SQLException ex) {
            System.out.println("Error al modificar producto" + ex.toString());
            resultado = false;
        }
       
        //TODO
        desconectar();
    }
    public void cogerImagen() throws SQLException{
        
    }
    /*public String buscarIdProductoPorNombre(String nombre) throws SQLException {
         conectar();
        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM  productos");
            while (resultado.next()) {

                String nombreReal = resultado.getString("nombre");

                if (idReal == id) {
                   String nombre = resultado.getString("Nombre");
                   System.out.println("Nombre producto:"+nombre);
                   return nombre;
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error al buscar producto" + ex.toString());
        }
        desconectar();
        return "potato";
        
        
        
    } */
}
