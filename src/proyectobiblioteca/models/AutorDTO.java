/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectobiblioteca.models;

/**
 *
 * @author juang
 */
public class AutorDTO {
    private int id_autor;
    private String nombre;

    
    public AutorDTO(){
        
    }
    
    public AutorDTO(int id_autor, String nombre) {
        this.id_autor = id_autor;
        this.nombre = nombre;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
