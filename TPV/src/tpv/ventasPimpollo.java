package tpv;

import java.util.Date;

public class ventasPimpollo {
    int idVentas;
    Date fecha;
    int precioFinal;

    public ventasPimpollo() {
    }

    public ventasPimpollo(int idVentas, Date fecha, int precioFinal) {
        this.idVentas = idVentas;
        this.fecha = fecha;
        this.precioFinal = precioFinal;
    }

    public int getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(int idVentas) {
        this.idVentas = idVentas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(int precioFinal) {
        this.precioFinal = precioFinal;
    }
    
}
