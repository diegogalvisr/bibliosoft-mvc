package proyectobiblioteca.models;

import java.util.Date;

public class PrestamosDTO {

    private int id_prestamo;
    private char tipo_prestamo;
    private int admin;
    private int usuario;
    private String libro;
    private String asignatura;
    private Date fecha_inicial;
    private Date fecha_final;
    private String estado;
    private String acta;

    public PrestamosDTO() {
    }

    public PrestamosDTO(int id_prestamo, char tipo_prestamo, int admin, int usuario, String libro, String asignatura, Date fecha_inicial, Date fecha_final, String estado, String acta) {
        this.id_prestamo = id_prestamo;
        this.tipo_prestamo = tipo_prestamo;
        this.admin = admin;
        this.usuario = usuario;
        this.libro = libro;
        this.asignatura = asignatura;
        this.fecha_inicial = fecha_inicial;
        this.fecha_final = fecha_final;
        this.estado = estado;
        this.acta = acta;
    }

    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public char getTipo_prestamo() {
        return tipo_prestamo;
    }

    public void setTipo_prestamo(char tipo_prestamo) {
        this.tipo_prestamo = tipo_prestamo;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public Date getFecha_inicial() {
        return fecha_inicial;
    }

    public void setFecha_inicial(Date fecha_inicial) {
        this.fecha_inicial = fecha_inicial;
    }

    public Date getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(Date fecha_final) {
        this.fecha_final = fecha_final;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getActa() {
        return acta;
    }

    public void setActa(String acta) {
        this.acta = acta;
    }


}
