package proyectobiblioteca.models;

public class LibrosDTO {
    private int idLibro;
    private int isbn;
    private String titulo;
    private int idEditorial;
    private int idAutor;
    private String tipoLibro;
    private int precio;
    private String contMaterial;
    private String categoria;
    private int cantidad;

    public LibrosDTO() {
    }

    public LibrosDTO(int idLibro, int isbn, String titulo, int idEditorial, int idAutor, String tipoLibro, int precio, String contMaterial, String categoria, int cantidad) {
        this.idLibro = idLibro;
        this.isbn = isbn;
        this.titulo = titulo;
        this.idEditorial = idEditorial;
        this.idAutor = idAutor;
        this.tipoLibro = tipoLibro;
        this.precio = precio;
        this.contMaterial = contMaterial;
        this.categoria = categoria;
        this.cantidad = cantidad;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(int idEditorial) {
        this.idEditorial = idEditorial;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getTipoLibro() {
        return tipoLibro;
    }

    public void setTipoLibro(String tipoLibro) {
        this.tipoLibro = tipoLibro;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getContMaterial() {
        return contMaterial;
    }

    public void setContMaterial(String contMaterial) {
        this.contMaterial = contMaterial;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
