package proyectobiblioteca.models;

public class UsuariosDTO {
    private int id_usuario;
    private int user_generico;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String telefonoF;
    private int grado;
    private int multa;

    public UsuariosDTO() {
        
    }
    
    public UsuariosDTO(int id_usuario, int user_generico,String nombre, String apellido, String direccion, String telefono, String telefonoF, int grado, int multa) {
        this.id_usuario = id_usuario;
        this.user_generico=user_generico;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.telefonoF = telefonoF;
        this.grado = grado;
        this.multa = multa;
    }

    public int getUser_generico() {
        return user_generico;
    }

    public void setUser_generico(int user_generico) {
        this.user_generico = user_generico;
    }
    
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefonoF() {
        return telefonoF;
    }

    public void setTelefonoF(String telefonoF) {
        this.telefonoF = telefonoF;
    }

    public int getGrado() {
        return grado;
    }

    public void setGrado(int grado) {
        this.grado = grado;
    }

    public int getMulta() {
        return multa;
    }

    public void setMulta(int multa) {
        this.multa = multa;
    }
    
    
    
}