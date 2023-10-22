package proyectobiblioteca.models;

import java.time.LocalDate;

public class PrestamosDTO {

    private int id_prestamo;
    private char tipo_prestamo;
    private int admin;
    private int usuario;
    private int libroID;
    private int asignaturaID;
    private String libro;
    private String asignatura;
    private LocalDate fecha_inicial;
    private LocalDate fecha_final;
    private String estado;
    private boolean actaB;
    private String acta;

    public PrestamosDTO() {
    }

    public PrestamosDTO(int id_prestamo, char tipo_prestamo, int admin, int usuario, int libroID, int asignaturaID, String libro, String asignatura, LocalDate fecha_inicial, LocalDate fecha_final, String estado, boolean actaB, String acta) {
        this.id_prestamo = id_prestamo;
        this.tipo_prestamo = tipo_prestamo;
        this.admin = admin;
        this.usuario = usuario;
        this.libroID = libroID;
        this.asignaturaID = asignaturaID;
        this.libro = libro;
        this.asignatura = asignatura;
        this.fecha_inicial = fecha_inicial;
        this.fecha_final = fecha_final;
        this.estado = estado;
        this.actaB = actaB;
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

    public int getLibroID() {
        return libroID;
    }

    public void setLibroID(int libroID) {
        this.libroID = libroID;
    }

    public int getAsignaturaID() {
        return asignaturaID;
    }

    public void setAsignaturaID(int asignaturaID) {
        this.asignaturaID = asignaturaID;
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

    public LocalDate getFecha_inicial() {
        return fecha_inicial;
    }

    public void setFecha_inicial(LocalDate fecha_inicial) {
        this.fecha_inicial = fecha_inicial;
    }

    public LocalDate getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(LocalDate fecha_final) {
        this.fecha_final = fecha_final;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isActaB() {
        return actaB;
    }

    public void setActaB(boolean actaB) {
        this.actaB = actaB;
    }

    public String getActa() {
        return acta;
    }

    public void setActa(String acta) {
        this.acta = acta;
    }

    



}
