package tpv;

public class ventaUsuarioPimpollo {
    int idVentaUsuario;
    int usuario;
    int venta;

    public ventaUsuarioPimpollo() {
    }

    public ventaUsuarioPimpollo(int idVentaUsuario, int usuario, int venta) {
        this.idVentaUsuario = idVentaUsuario;
        this.usuario = usuario;
        this.venta = venta;
    }

    public int getIdVentaUsuario() {
        return idVentaUsuario;
    }

    public void setIdVentaUsuario(int idVentaUsuario) {
        this.idVentaUsuario = idVentaUsuario;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getVenta() {
        return venta;
    }

    public void setVenta(int venta) {
        this.venta = venta;
    }
    
    
}
