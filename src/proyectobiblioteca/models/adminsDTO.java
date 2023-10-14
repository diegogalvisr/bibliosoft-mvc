package proyectobiblioteca.models;

public class adminsDTO {
    private int id_admin;
    private String usuario;
    private String clave;
    private String nombres;
    private String apellidos;
    private String direccion;
    private int cargo;

    public adminsDTO() {
    }

    public adminsDTO(int id_admin, String usuario, String clave, String nombres, String apellidos, String direccion, int cargo) {
        this.id_admin = id_admin;
        this.usuario = usuario;
        this.clave = clave;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.cargo = cargo;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "adminsDTO{" +
                "id_admin=" + id_admin +
                ", usuario='" + usuario + '\'' +
                ", clave='" + clave + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion='" + direccion + '\'' +
                ", cargo=" + cargo +
                '}';
    }
}
