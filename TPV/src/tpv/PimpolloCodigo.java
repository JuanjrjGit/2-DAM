package tpv;

import java.sql.SQLException;

public class PimpolloCodigo {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        gestionPimpollo g = new gestionPimpollo("root", "", "pimpollosenterprise", "localhost");
        
        g.buscarUsuarioPorNombreYContrasenia("141298", "daniduranmartin@gmail.com");
    }
    
}
