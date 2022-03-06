package tpv;

public class ventaProductoPimpollo {
    int idVentaProducto;
    int producto;
    int venta;

    public ventaProductoPimpollo() {
    }

    public ventaProductoPimpollo(int idVentaProducto, int producto, int venta) {
        this.idVentaProducto = idVentaProducto;
        this.producto = producto;
        this.venta = venta;
    }

    public int getIdVentaProducto() {
        return idVentaProducto;
    }

    public void setIdVentaProducto(int idVentaProducto) {
        this.idVentaProducto = idVentaProducto;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getVenta() {
        return venta;
    }

    public void setVenta(int venta) {
        this.venta = venta;
    }
    
    
}
