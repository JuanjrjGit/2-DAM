package tpv;

public class usuarioPimpollo {
    int idUsuario;
    String nombre,apellidos,login,password;
    
    public usuarioPimpollo(){
        
    }
    public usuarioPimpollo( int idUsuario, String nombre, String apellidos, String login, String password) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.login = login;
        this.password = password;
    }

    public usuarioPimpollo(String nombre, String apellidos, String login, String password) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.login = login;
        this.password = password;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    
}
