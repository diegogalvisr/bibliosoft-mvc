package proyectobiblioteca.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyectobiblioteca.config.BasedeDatos;
import proyectobiblioteca.models.LibrosDTO;
import proyectobiblioteca.models.PrestamosDTO;
import proyectobiblioteca.models.adminsDTO;

public class PrestamosDAO {

    public static void cargarCategoria(JComboBox<String> comboBox) {
        try {
            BasedeDatos.conectar();
            String consulta = "SELECT nombre FROM categoria";
            PreparedStatement statement = BasedeDatos.conexion.prepareStatement(consulta);
            ResultSet resultado = statement.executeQuery();

            // Limpiar el JComboBox antes de agregar los nombres de autores
            comboBox.removeAllItems();
            comboBox.addItem("Seleccione");
            while (resultado.next()) {
                String nombreAutor = resultado.getString("nombre");
                comboBox.addItem(nombreAutor);
            }

            // Cerrar los recursos
            resultado.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BasedeDatos.desconectar(); // Cerrar la conexión
        }
    }

    public static void cargarAsignatura(JComboBox<String> comboBox) {
        try {
            BasedeDatos.conectar();
            String consulta = "SELECT nombre FROM asignatura";
            PreparedStatement statement = BasedeDatos.conexion.prepareStatement(consulta);
            ResultSet resultado = statement.executeQuery();

            // Limpiar el JComboBox antes de agregar los nombres de autores
            comboBox.removeAllItems();
            comboBox.addItem("Seleccione");
            while (resultado.next()) {
                String nombreAutor = resultado.getString("nombre");
                comboBox.addItem(nombreAutor);
            }

            // Cerrar los recursos
            resultado.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BasedeDatos.desconectar(); // Cerrar la conexión
        }
    }

    public static List<String> obtenerCategoriasP() {
        List<String> categorias = new ArrayList<>();

        try {
            BasedeDatos.conectar();
            String consulta = "SELECT nombre FROM categoria";
            PreparedStatement statement = BasedeDatos.conexion.prepareStatement(consulta);
            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {
                String nombreCategoria = resultado.getString("nombre");
                categorias.add(nombreCategoria);
            }

            // Cerrar los recursos
            resultado.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BasedeDatos.desconectar(); // Cerrar la conexión
        }

        return categorias;
    }


    public static DefaultTableModel busquedaBL2(String nombreSeleccion, int categoria) {
        DefaultTableModel modelo = new DefaultTableModel();

        // Definimos las columnas del modelo
        modelo.addColumn("ID Libro");
        modelo.addColumn("ISBN");
        modelo.addColumn("Título");
        modelo.addColumn("Disponibles");
        modelo.addColumn("Prestados");

        String sql = "SELECT id_libro, isbn, titulo, cantidad as DISPONIBLE, prestado as PRESTADOS FROM libros WHERE id_libro LIKE ? OR isbn LIKE ? OR titulo LIKE ? AND categoria = ?";

        try {
            BasedeDatos.conectar();
            PreparedStatement stmt = BasedeDatos.conexion.prepareStatement(sql);
            String parametro = "%" + nombreSeleccion + "%";
            stmt.setString(1, parametro);
            stmt.setString(2, parametro);
            stmt.setString(3, parametro);
            stmt.setInt(4, categoria);

            ResultSet result = stmt.executeQuery();

            if (result != null) {
                while (result.next()) {
                    int id_libro = result.getInt("id_libro");
                    String isbn = result.getString("isbn");
                    String titulo = result.getString("Titulo");
                    int cantidad = result.getInt("DISPONIBLE");
                    int prestado = result.getInt("PRESTADOS");

                    LibrosDTO librosDTO = new LibrosDTO();
                    librosDTO.setIdLibro(result.getInt("id_libro"));

                    System.out.println("ID LIBRO: " + librosDTO.getIdLibro());

                    // Agregamos los datos al modelo
                    Object[] fila = {id_libro, isbn, titulo, cantidad, prestado};
                    modelo.addRow(fila);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error consultando admins");
        } finally {
            BasedeDatos.desconectar(); // Cerrar la conexión
        }

        return modelo;
    }

    public static DefaultTableModel tablaLibrosDisponibles(int idCategoria) {
        DefaultTableModel modelo = new DefaultTableModel();

        // Definimos las columnas del modelo
        modelo.addColumn("ID Libro");
        modelo.addColumn("ISBN");
        modelo.addColumn("Título");
        modelo.addColumn("Disponibles");
        modelo.addColumn("Prestados");

        try {
            BasedeDatos.conectar();
            // Utilizamos una consulta parametrizada con el signo de interrogación
            String sql = "SELECT id_libro, isbn, titulo, cantidad as DISPONIBLE, prestado as PRESTADOS FROM libros WHERE categoria=?";
            PreparedStatement statement = BasedeDatos.conexion.prepareStatement(sql);
            statement.setInt(1, idCategoria); // Asignamos el valor del parámetro

            ResultSet resultado = statement.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    int id_libro = resultado.getInt("id_libro");
                    String isbn = resultado.getString("isbn");
                    String titulo = resultado.getString("Titulo");
                    int cantidad = resultado.getInt("DISPONIBLE");
                    int prestado = resultado.getInt("PRESTADOS");

                    LibrosDTO librosDTO = new LibrosDTO();
                    librosDTO.setIdLibro(resultado.getInt("id_libro"));

                    System.out.println("ID LIBRO: " + librosDTO.getIdLibro());

                    // Agregamos los datos al modelo
                    Object[] fila = {id_libro, isbn, titulo, cantidad, prestado};
                    modelo.addRow(fila);
                }
            } else {
                System.out.println("No se pudo ejecutar la consulta SQL.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BasedeDatos.desconectar(); // Cerrar la conexión
        }

        return modelo;
    }

    public static String obtenerNombreUsuario(int user_generico) {
        String nombre = "USER NO ENCONTRADO";
        try {
            BasedeDatos.conectar();
            String consulta = "SELECT CONCAT(nombre,' ',apellido) as nombre FROM usuarios WHERE user_generico = ?";
            PreparedStatement statement = BasedeDatos.conexion.prepareStatement(consulta);
            statement.setInt(1, user_generico);
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                nombre = resultado.getString("nombre");
            }
            // Cerrar los recursos
            resultado.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BasedeDatos.desconectar(); // Cerrar la conexión
        }
        return nombre;
    }

    public static int obtenerIDAdmin(String usuario) {
        int idAdmin = 0000; // Valor por defecto si no se encuentra el usuario
        try {
            BasedeDatos.conectar();
            String consulta = "SELECT id_admin FROM admins WHERE usuario = ?";
            PreparedStatement statement = BasedeDatos.conexion.prepareStatement(consulta);
            statement.setString(1, usuario);
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                idAdmin = resultado.getInt("id_admin");
            }
            // Cerrar los recursos
            resultado.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BasedeDatos.desconectar(); // Cerrar la conexión
        }
        return idAdmin;
    }
    
    public static int obtenerIDUsuario(int usuario) {
        int idAdmin = 0000; // Valor por defecto si no se encuentra el usuario
        try {
            BasedeDatos.conectar();
            String consulta = "SELECT id_usuario FROM usuarios WHERE user_generico = ?";
            PreparedStatement statement = BasedeDatos.conexion.prepareStatement(consulta);
            statement.setInt(1, usuario);
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                idAdmin = resultado.getInt("id_usuario");
            }
            // Cerrar los recursos
            resultado.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BasedeDatos.desconectar(); // Cerrar la conexión
        }
        return idAdmin;
    }

  public static void insertarPrestamo(PrestamosDTO prestamo) {
    try {
        BasedeDatos.conectar();
        String consulta = "INSERT INTO prestamos (tipo_prestamo, id_admin, id_usuario, id_libro, asignatura, fecha_inicial, fecha_final, estado, acta) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = BasedeDatos.conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, String.valueOf(prestamo.getTipo_prestamo()));
        statement.setInt(2, prestamo.getAdmin());
        statement.setInt(3, prestamo.getUsuario());
        statement.setInt(4, prestamo.getLibroID());
        statement.setInt(5, prestamo.getAsignaturaID());

        // Convierte LocalDate a java.sql.Date
        java.sql.Date sqlFechaInicial = java.sql.Date.valueOf(prestamo.getFecha_inicial());
        java.sql.Date sqlFechaFinal = java.sql.Date.valueOf(prestamo.getFecha_final());

        statement.setDate(6, sqlFechaInicial);
        statement.setDate(7, sqlFechaFinal);
        statement.setInt(8, 1);
        statement.setString(9, prestamo.getActa());

        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("La inserción del préstamo falló, no se insertaron filas.");
        }

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int idPrestamo = generatedKeys.getInt(1);

            // Actualizar el libro prestado
            String updateLibroQuery = "UPDATE libros SET cantidad = cantidad - 1, prestado = prestado + 1 WHERE id_libro = ?";
            PreparedStatement updateLibroStatement = BasedeDatos.conexion.prepareStatement(updateLibroQuery);
            updateLibroStatement.setInt(1, prestamo.getLibroID());
            updateLibroStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Se ha agregado exitosamente el préstamo al usuario: " + prestamo.getUsuario());
        } else {
            throw new SQLException("La inserción del préstamo falló, no se generó el ID del préstamo.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        BasedeDatos.desconectar(); // Cerrar la conexión
    }
}

    public DefaultTableModel tablaPrestamos() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Tipo");
        modelo.addColumn("Admin");
        modelo.addColumn("Usuario");
        modelo.addColumn("Libro");
        modelo.addColumn("Asignatura");
        modelo.addColumn("Fecha Inicial");
        modelo.addColumn("Fecha Final");
        modelo.addColumn("Estado");
        modelo.addColumn("Acta");
        try {
            BasedeDatos.conectar();
            ResultSet resultado = BasedeDatos.ejecutarSQL("SELECT pt.id_prestamo, pt.tipo_prestamo, pt.id_admin,pt.id_usuario, lb.titulo AS Libro,asg.nombre as asignatura, pt.fecha_inicial, pt.fecha_final, est.nombre as Estado, CASE WHEN pt.acta = 1 THEN 'SI TIENE' ELSE 'NO TIENE' END AS Acta FROM prestamos pt JOIN libros lb ON lb.id_libro = pt.id_libro JOIN estado est ON est.id=pt.estado JOIN asignatura asg on asg.id=pt.asignatura;");
            PrestamosDTO PDTO = new PrestamosDTO();
            if (resultado != null) { // Verificar que el resultado no sea null
                while (resultado.next()) {
                    PDTO.setId_prestamo(resultado.getInt("id_prestamo"));
                    PDTO.setTipo_prestamo(resultado.getString("tipo_prestamo").charAt(0));
                    PDTO.setAdmin(resultado.getInt("id_admin"));
                    PDTO.setUsuario(resultado.getInt("id_usuario"));
                    PDTO.setLibro(resultado.getString("Libro"));
                    PDTO.setAsignatura(resultado.getString("asignatura"));
                    java.sql.Date sqlFechaInicial = resultado.getDate("fecha_inicial");
                    java.sql.Date sqlFechaFinal = resultado.getDate("fecha_final");
                    LocalDate fechaInicial = sqlFechaInicial.toLocalDate();
                    LocalDate fechaFinal = sqlFechaFinal.toLocalDate();

// Luego, puedes establecer las fechas en tu objeto PDTO
                    PDTO.setFecha_inicial(fechaInicial);
                    PDTO.setFecha_final(fechaFinal);
                    PDTO.setEstado(resultado.getString("Estado"));
                    PDTO.setActa(resultado.getString("Acta"));
                    Object[] fila = {PDTO.getId_prestamo(), PDTO.getTipo_prestamo(), PDTO.getAdmin(), PDTO.getUsuario(), PDTO.getLibro(), PDTO.getAsignatura(), PDTO.getFecha_inicial(), PDTO.getFecha_final(), PDTO.getEstado(), PDTO.getActa()};
                    // Agregamos los datos al modelo
                    modelo.addRow(fila);
                }
            } else {
                System.out.println("No se pudo ejecutar la consulta SQL.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BasedeDatos.desconectar(); // Cerrar la conexión
        }
        return modelo;
    }

    public DefaultTableModel busqueda(String nombreSeleccion) {
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Tipo");
        modelo.addColumn("Admin");
        modelo.addColumn("Usuario");
        modelo.addColumn("Libro");
        modelo.addColumn("Asignatura");
        modelo.addColumn("Fecha Inicial");
        modelo.addColumn("Fecha Final");
        modelo.addColumn("Estado");
        modelo.addColumn("Acta");

        String sql = "SELECT pt.id_prestamo, pt.tipo_prestamo, pt.id_admin, pt.id_usuario, lb.titulo AS Libro, pt.asignatura, pt.fecha_inicial, pt.fecha_final, est.nombre as Estado, CASE WHEN pt.acta = 1 THEN 'SI TIENE' ELSE 'NO TIENE' END AS Acta FROM prestamos pt JOIN libros lb ON lb.id_libro = pt.id_libro JOIN estado est ON est.id = pt.estado WHERE est.nombre LIKE ? OR id_prestamo LIKE ? OR id_admin LIKE ? OR id_usuario LIKE ? ";
        try {
            BasedeDatos.conectar();
            PreparedStatement stmt = BasedeDatos.conexion.prepareStatement(sql);
            String parametro = "%" + nombreSeleccion + "%";
            stmt.setString(1, parametro);
            stmt.setString(2, parametro);
            stmt.setString(3, parametro);
            stmt.setString(4, parametro);

            ResultSet result = stmt.executeQuery();

            if (result != null) {
                while (result.next()) {
                    int id_prestamo = result.getInt("id_prestamo");
                    char tipo_prestamo = result.getString("tipo_prestamo").charAt(0);
                    int id_admin = result.getInt("id_admin");
                    int id_usuario = result.getInt("id_usuario");
                    String Libro = result.getString("Libro");
                    String asignatura = result.getString("asignatura");
                    Date fecha_inicial = result.getDate("fecha_inicial");
                    Date fecha_final = result.getDate("fecha_final");
                    String estado = result.getString("Estado");
                    String acta = result.getString("Acta");
                    Object[] fila = {id_prestamo, tipo_prestamo, id_admin, id_usuario, Libro, asignatura, fecha_inicial, fecha_final, estado, acta};
                    modelo.addRow(fila);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error consultando prestamos");
        } finally {
            BasedeDatos.desconectar(); // Cerrar la conexión
        }

        return modelo;
    }

}
