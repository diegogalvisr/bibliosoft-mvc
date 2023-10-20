package proyectobiblioteca.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
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

    /* public static void insertarAdmin(adminsDTO admin) {
        try {
            BasedeDatos.conectar();
            String consulta = "INSERT INTO admins (usuario, clave, nombre, apellido, direccion, cargo) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = BasedeDatos.conexion.prepareStatement(consulta);
            statement.setString(1, admin.getUsuario());
            statement.setString(2, admin.getClave());
            statement.setString(3, admin.getNombres());
            statement.setString(4, admin.getApellidos());
            statement.setString(5, admin.getDireccion());
            statement.setInt(6, admin.getCargo());
            statement.executeUpdate();
            // BasedeDatos.desconectar();
// Crea un nuevo JDialog personalizado

// Muestra un mensaje en el cuadro de diálogo
            JOptionPane.showMessageDialog(null, "Se ha agregado exitosamente el usuario " + admin.getUsuario());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BasedeDatos.desconectar(); // Cerrar la conexión
        }
    }*/
    public String buscarUsuario() {
        String mensaje = "Hola";

        return mensaje;
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
                    PDTO.setFecha_inicial(resultado.getDate("fecha_inicial"));
                    PDTO.setFecha_final(resultado.getDate("fecha_final"));
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
